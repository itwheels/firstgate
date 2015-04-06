package com.itwheel.edigate.processor;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.milyn.edi.unedifact.d96a.D96AInterchangeFactory;
import org.milyn.edi.unedifact.d96a.SLSRPT.SegmentGroup1;
import org.milyn.edi.unedifact.d96a.SLSRPT.SegmentGroup4;
import org.milyn.edi.unedifact.d96a.SLSRPT.SegmentGroup5;
import org.milyn.edi.unedifact.d96a.SLSRPT.SegmentGroup7;
import org.milyn.edi.unedifact.d96a.SLSRPT.SegmentGroup8;
import org.milyn.edi.unedifact.d96a.SLSRPT.Slsrpt;
import org.milyn.edi.unedifact.d96a.common.BGMBeginningOfMessage;
import org.milyn.edi.unedifact.d96a.common.DTMDateTimePeriod;
import org.milyn.edi.unedifact.d96a.common.LINLineItem;
import org.milyn.edi.unedifact.d96a.common.LOCPlaceLocationIdentification;
import org.milyn.edi.unedifact.d96a.common.PRIPriceDetails;
import org.milyn.edi.unedifact.d96a.common.RFFReference;
import org.milyn.edi.unedifact.d96a.common.field.C507DateTimePeriod;
import org.milyn.edisax.model.internal.Delimiters;
import org.milyn.edisax.unedifact.UNEdifactInterchangeParser;
import org.milyn.payload.JavaSource;
import org.milyn.payload.JavaSourceWithoutEventStream;
import org.milyn.smooks.edi.unedifact.model.UNEdifactInterchange;
import org.milyn.smooks.edi.unedifact.model.r41.UNB41;
import org.milyn.smooks.edi.unedifact.model.r41.UNEdifactInterchange41;
import org.milyn.smooks.edi.unedifact.model.r41.UNEdifactMessage41;
import org.milyn.smooks.edi.unedifact.model.r41.UNH41;
import org.milyn.smooks.edi.unedifact.model.r41.UNT41;
import org.milyn.smooks.edi.unedifact.model.r41.UNZ41;
import org.milyn.smooks.edi.unedifact.model.r41.types.DateTime;
import org.milyn.smooks.edi.unedifact.model.r41.types.MessageIdentifier;
import org.milyn.smooks.edi.unedifact.model.r41.types.Party;
import org.milyn.smooks.edi.unedifact.model.r41.types.SyntaxIdentifier;

import com.itwheel.edigate.utils.EdiObjectFactory;


public class ProduceSlsrptMsgProc implements Processor {

	
	public void process(Exchange exchange) throws Exception {
		
		Map<String, Object> retail = 
				(Map<String, Object>)exchange.getIn().getHeader("retail");
		List<Map<String, Object>> retailItems = 
				(List<Map<String, Object>>)exchange.getIn().getBody();
		
		List<UNEdifactMessage41> messages = 
				(List<UNEdifactMessage41>)exchange.getIn().getHeader("messages");
		
		UNEdifactMessage41 msg = new UNEdifactMessage41();
//		msg.setInterchangeHeader(interchangeHeader);
		
		String retailIdStr = retail.get("id").toString(); 
		String docNo = retail.get("docno").toString();
		String storeCod = retail.get("code").toString();
		String billDte = retail.get("billdate").toString();
		UNH41 messageHeader = 
				EdiObjectFactory.createSlsrptUnh(retailIdStr);
		msg.setMessageHeader(messageHeader);
		
		Slsrpt message = new Slsrpt();
		
		BGMBeginningOfMessage bGMBeginningOfMessage = 
				EdiObjectFactory.createSlsrptBgm(retailIdStr);
		message.setBGMBeginningOfMessage(bGMBeginningOfMessage);
		
		List<DTMDateTimePeriod> dTMDateTimePeriod = new ArrayList<DTMDateTimePeriod>();
		Timestamp lastStartDte = 
				(Timestamp)exchange.getIn().getHeader("last_start_dte");
		Timestamp startDte = 
				(Timestamp)exchange.getIn().getHeader("start_dte");
		DTMDateTimePeriod msgDte = EdiObjectFactory.getMsgDte(startDte);
		DTMDateTimePeriod rptStartDte = EdiObjectFactory.getRptStartDte(lastStartDte);
		DTMDateTimePeriod rptEndDte = EdiObjectFactory.getRptEndDte(startDte);
		dTMDateTimePeriod.add(msgDte);
		dTMDateTimePeriod.add(rptStartDte);
		dTMDateTimePeriod.add(rptEndDte);
		message.setDTMDateTimePeriod(dTMDateTimePeriod);
		
		List<SegmentGroup1> segmentGroup1 = new ArrayList<SegmentGroup1>();
		SegmentGroup1 sgNadCo = EdiObjectFactory.createSgNadCo();
		SegmentGroup1 sgNadSu = EdiObjectFactory.createSgNadSu();
		SegmentGroup1 sgNadSn = EdiObjectFactory.createSgNadSn(storeCod);
		
		segmentGroup1.add(sgNadCo);
		segmentGroup1.add(sgNadSu);
		segmentGroup1.add(sgNadSn);
		message.setSegmentGroup1(segmentGroup1);
		
		List<SegmentGroup4> segmentGroup4 = new ArrayList<SegmentGroup4>();
		SegmentGroup4 sgCurCny = EdiObjectFactory.createSgCurCny();
		segmentGroup4.add(sgCurCny);
		message.setSegmentGroup4(segmentGroup4);
		
		
		LOCPlaceLocationIdentification loc = EdiObjectFactory.getLocOfSale(storeCod);
		
		List<DTMDateTimePeriod> salesDte = EdiObjectFactory.getDtmSalesDte(billDte);
		
		List<SegmentGroup7> segmentGroup7 = new ArrayList<SegmentGroup7>();
		for(int i=0; i<retailItems.size(); i++) {
			
			Map<String, Object> item = retailItems.get(i);
			String itemNum = item.get("no").toString();
			BigDecimal priceAct = (BigDecimal)item.get("priceactual");
			BigDecimal qty = (BigDecimal)item.get("qty");
			
			LINLineItem lINLineItem = new LINLineItem();
			BigDecimal lineNum = new BigDecimal(i+1);
			lineNum.setScale(0);
			lINLineItem.setE1082LineItemNumber(lineNum);
			lINLineItem.setC212ItemNumberIdentification(EdiObjectFactory.createItemNum(itemNum));
			
			List<RFFReference> rFFReference = EdiObjectFactory.createReference(docNo);
			
			List<PRIPriceDetails> pRIPriceDetails = EdiObjectFactory.createPrice(priceAct);
			
			List<SegmentGroup8> sg8List = EdiObjectFactory.createQty(qty);
			
			SegmentGroup7 sg7 = new SegmentGroup7();
			sg7.setLINLineItem(lINLineItem);
			sg7.setRFFReference(rFFReference);
			sg7.setPRIPriceDetails(pRIPriceDetails);
			sg7.setSegmentGroup8(sg8List);
			
			segmentGroup7.add(sg7);
		}
		
		SegmentGroup5 sg5 = new SegmentGroup5();
		sg5.setLOCPlaceLocationIdentification(loc);
		sg5.setDTMDateTimePeriod(salesDte);
		sg5.setSegmentGroup7(segmentGroup7);
		
		List<SegmentGroup5> segmentGroup5 = new ArrayList<SegmentGroup5>();
		segmentGroup5.add(sg5);
		
		message.setSegmentGroup5(segmentGroup5);
		
		msg.setMessage(message);
		int segmentCount = 12 + retailItems.size()*5;
		UNT41 messageTrailer = 
				EdiObjectFactory.createSlsrptUnt(segmentCount, retailIdStr);
		msg.setMessageTrailer(messageTrailer);
		
		messages.add(msg);

		exchange.getOut().setHeaders(exchange.getIn().getHeaders());
//		exchange.getOut().setBody(js);
	}

}
