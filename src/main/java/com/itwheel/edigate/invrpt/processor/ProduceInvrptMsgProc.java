package com.itwheel.edigate.invrpt.processor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.milyn.edi.unedifact.d96a.INVRPT.Invrpt;
import org.milyn.edi.unedifact.d96a.INVRPT.SegmentGroup2;
import org.milyn.edi.unedifact.d96a.INVRPT.SegmentGroup4;
import org.milyn.edi.unedifact.d96a.INVRPT.SegmentGroup5;
import org.milyn.edi.unedifact.d96a.INVRPT.SegmentGroup9;
import org.milyn.edi.unedifact.d96a.INVRPT.SegmentGroup11;
import org.milyn.edi.unedifact.d96a.INVRPT.SegmentGroup13;
import org.milyn.edi.unedifact.d96a.common.BGMBeginningOfMessage;
import org.milyn.edi.unedifact.d96a.common.DTMDateTimePeriod;
import org.milyn.edi.unedifact.d96a.common.LINLineItem;
import org.milyn.edi.unedifact.d96a.common.LOCPlaceLocationIdentification;
import org.milyn.edi.unedifact.d96a.common.PRIPriceDetails;
import org.milyn.edi.unedifact.d96a.common.RFFReference;
import org.milyn.smooks.edi.unedifact.model.r41.UNEdifactMessage41;
import org.milyn.smooks.edi.unedifact.model.r41.UNH41;
import org.milyn.smooks.edi.unedifact.model.r41.UNT41;

import com.itwheel.edigate.utils.EdiObjectFactory;
import com.itwheel.edigate.utils.InvrptEdiObjectFactory;


public class ProduceInvrptMsgProc implements Processor {

	
	public void process(Exchange exchange) throws Exception {
		
		Map<String, Object> retail = (Map<String, Object>)exchange.getIn().getHeader("invrpt_retail");
		List<Map<String, Object>> retailItems = (List<Map<String, Object>>)exchange.getIn().getBody();
		
		List<UNEdifactMessage41> messages = (List<UNEdifactMessage41>)exchange.getIn().getHeader("invrpt_messages");
		
		UNEdifactMessage41 msg = new UNEdifactMessage41();
		
		String retailIdStr = retail.get("id").toString(); 
		String storeCod = retail.get("code").toString();
		
		// UNH
		UNH41 messageHeader =  InvrptEdiObjectFactory.createInvrptUnh(retailIdStr);
		msg.setMessageHeader(messageHeader);
		
		// 实体
		Invrpt message = new Invrpt();
		
		// BGM
		BGMBeginningOfMessage bGMBeginningOfMessage = InvrptEdiObjectFactory.createInvrptBgm(retailIdStr);
		message.setBGMBeginningOfMessage(bGMBeginningOfMessage);
		
		// DTM
		List<DTMDateTimePeriod> dTMDateTimePeriod = new ArrayList<DTMDateTimePeriod>();
		Timestamp lastStartDte = (Timestamp)exchange.getIn().getHeader("invrpt_last_start_dte");
		Timestamp startDte = (Timestamp)exchange.getIn().getHeader("invrpt_start_dte");
		
		DTMDateTimePeriod msgDte = InvrptEdiObjectFactory.getMsgDte(startDte);
		DTMDateTimePeriod rptStartDte = InvrptEdiObjectFactory.getRptStartDte(lastStartDte);
		DTMDateTimePeriod rptEndDte = InvrptEdiObjectFactory.getRptEndDte(startDte);
		dTMDateTimePeriod.add(msgDte);
		dTMDateTimePeriod.add(rptStartDte);
		dTMDateTimePeriod.add(rptEndDte);
		message.setDTMDateTimePeriod(dTMDateTimePeriod);
		
		// SG2
		List<SegmentGroup2> segmentGroup2 = new ArrayList<SegmentGroup2>();
		SegmentGroup2 sgNadSu = InvrptEdiObjectFactory.createSgNadSu();
		SegmentGroup2 sgNadGy = InvrptEdiObjectFactory.createSgNadGy(storeCod);
		SegmentGroup2 sgNadCo = InvrptEdiObjectFactory.createSgNadCo();
		
		segmentGroup2.add(sgNadSu);
		segmentGroup2.add(sgNadGy);
		segmentGroup2.add(sgNadCo);
		message.setSegmentGroup2(segmentGroup2);
		
		// SG5
		List<SegmentGroup5> segmentGroup5 = new ArrayList<SegmentGroup5>();
		SegmentGroup5 sgCurCny = InvrptEdiObjectFactory.createSgCurCny();
		segmentGroup5.add(sgCurCny);
		message.setSegmentGroup5(segmentGroup5);
		
		// SG9
		LOCPlaceLocationIdentification loc = InvrptEdiObjectFactory.getLocOfSale(storeCod);
		
		List<SegmentGroup9> segmentGroup9 = new ArrayList<SegmentGroup9>();
		for(int i = 0; i < retailItems.size(); i++) {
			
			Map<String, Object> item = retailItems.get(i);
			String itemNum = item.get("no").toString();
			BigDecimal pricelist = (BigDecimal)item.get("pricelist");// 标准价
			//BigDecimal pricewholesale = (BigDecimal)item.get("pricewholesale"); // 批发价
			BigDecimal qty = (BigDecimal)item.get("qty");
			
			LINLineItem lINLineItem = new LINLineItem();
			BigDecimal lineNum = new BigDecimal(i+1);
			lineNum.setScale(0);
			lINLineItem.setE1082LineItemNumber(lineNum);
			lINLineItem.setC212ItemNumberIdentification(InvrptEdiObjectFactory.createItemNum(itemNum));
			
			PRIPriceDetails pRIPriceDetails = InvrptEdiObjectFactory.createPrice(pricelist);
			
			List<SegmentGroup11> sg11List = InvrptEdiObjectFactory.createQty(qty, loc);
			
			for(SegmentGroup11 sg : sg11List) {
				sg.setSegmentGroup13(InvrptEdiObjectFactory.createSG13PriceList(pRIPriceDetails));
			}
			
			SegmentGroup9 sg9 = new SegmentGroup9();
			sg9.setLINLineItem(lINLineItem);
			sg9.setSegmentGroup11(sg11List);
			
			segmentGroup9.add(sg9);
		}
		
		message.setSegmentGroup9(segmentGroup9);
		
		msg.setMessage(message);
		int segmentCount = 12 + retailItems.size()*5;
		UNT41 messageTrailer = EdiObjectFactory.createSlsrptUnt(segmentCount, retailIdStr);
		msg.setMessageTrailer(messageTrailer);
		
		messages.add(msg);

		exchange.getOut().setHeaders(exchange.getIn().getHeaders());
//		exchange.getOut().setBody(js);
	}

}
