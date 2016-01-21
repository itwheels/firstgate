package com.itwheel.edigate.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.milyn.edi.unedifact.d96a.SLSRPT.SegmentGroup1;
import org.milyn.edi.unedifact.d96a.SLSRPT.SegmentGroup4;
import org.milyn.edi.unedifact.d96a.SLSRPT.SegmentGroup8;
import org.milyn.edi.unedifact.d96a.common.BGMBeginningOfMessage;
import org.milyn.edi.unedifact.d96a.common.CUXCurrencies;
import org.milyn.edi.unedifact.d96a.common.DTMDateTimePeriod;
import org.milyn.edi.unedifact.d96a.common.LOCPlaceLocationIdentification;
import org.milyn.edi.unedifact.d96a.common.MOAMonetaryAmount;
import org.milyn.edi.unedifact.d96a.common.NADNameAndAddress;
import org.milyn.edi.unedifact.d96a.common.PRIPriceDetails;
import org.milyn.edi.unedifact.d96a.common.QTYQuantity;
import org.milyn.edi.unedifact.d96a.common.RFFReference;
import org.milyn.edi.unedifact.d96a.common.field.C002DocumentMessageName;
import org.milyn.edi.unedifact.d96a.common.field.C082PartyIdentificationDetails;
import org.milyn.edi.unedifact.d96a.common.field.C186QuantityDetails;
import org.milyn.edi.unedifact.d96a.common.field.C212ItemNumberIdentification;
import org.milyn.edi.unedifact.d96a.common.field.C5041CurrencyDetails;
import org.milyn.edi.unedifact.d96a.common.field.C506Reference;
import org.milyn.edi.unedifact.d96a.common.field.C507DateTimePeriod;
import org.milyn.edi.unedifact.d96a.common.field.C509PriceInformation;
import org.milyn.edi.unedifact.d96a.common.field.C516MonetaryAmount;
import org.milyn.edi.unedifact.d96a.common.field.C517LocationIdentification;
import org.milyn.smooks.edi.unedifact.model.r41.UNH41;
import org.milyn.smooks.edi.unedifact.model.r41.UNT41;
import org.milyn.smooks.edi.unedifact.model.r41.types.MessageIdentifier;

public class EdiObjectFactory {
	
	final static private BigDecimal TAX_RATE = new BigDecimal(1.17);
	
	public static UNH41 createSlsrptUnh(String messageRefNum)
	{
		UNH41 messageHeader = new UNH41();
		messageHeader.setMessageRefNum(messageRefNum);
		MessageIdentifier messageIdentifier = new MessageIdentifier();
		messageIdentifier.setId("SLSRPT");
		messageIdentifier.setVersionNum("D");
		messageIdentifier.setReleaseNum("96A");
		messageIdentifier.setControllingAgencyCode("UN");
		messageIdentifier.setAssociationAssignedCode("EAN004");
		messageHeader.setMessageIdentifier(messageIdentifier);
		return messageHeader;
	}
	public static UNT41 createSlsrptUnt(int segmentCount,String messageRefNum) {
		UNT41 messageTrailer = new UNT41();
		messageTrailer.setSegmentCount(segmentCount);
		messageTrailer.setMessageRefNum(messageRefNum);
		return messageTrailer;
	}
	public static BGMBeginningOfMessage createSlsrptBgm(String e1004DocumentMessageNumber) {
		BGMBeginningOfMessage bGMBeginningOfMessage = new BGMBeginningOfMessage();
		
		C002DocumentMessageName c002DocumentMessageName = new C002DocumentMessageName();
		c002DocumentMessageName.setE1001DocumentMessageNameCoded("73E");
		bGMBeginningOfMessage.setC002DocumentMessageName(c002DocumentMessageName);
		
		bGMBeginningOfMessage.setE1004DocumentMessageNumber(e1004DocumentMessageNumber);
		
		return bGMBeginningOfMessage;
	}
	public static DTMDateTimePeriod getMsgDte(Date msgDte) {
		C507DateTimePeriod c507DateTimePeriod = new C507DateTimePeriod();
		c507DateTimePeriod.setE2005DateTimePeriodQualifier("137");
		c507DateTimePeriod.setE2379DateTimePeriodFormatQualifier("102");
		String msgDteStr = 
				DateFormatUtils.format(msgDte, "yyyyMMdd");
		c507DateTimePeriod.setE2380DateTimePeriod(msgDteStr);
		DTMDateTimePeriod msgDteObj = new DTMDateTimePeriod();
		msgDteObj.setC507DateTimePeriod(c507DateTimePeriod);
		return msgDteObj;
	}
	public static DTMDateTimePeriod getRptStartDte(Date startDte) {
		C507DateTimePeriod c507DateTimePeriod = new C507DateTimePeriod();
		c507DateTimePeriod.setE2005DateTimePeriodQualifier("90");
		c507DateTimePeriod.setE2379DateTimePeriodFormatQualifier("102");
		String startDteStr = 
				DateFormatUtils.format(startDte, "yyyyMMdd");
		c507DateTimePeriod.setE2380DateTimePeriod(startDteStr);
		DTMDateTimePeriod startDteObj = new DTMDateTimePeriod();
		startDteObj.setC507DateTimePeriod(c507DateTimePeriod);
		return startDteObj;
	}
	public static DTMDateTimePeriod getRptEndDte(Date endDte) {
		C507DateTimePeriod c507DateTimePeriod = new C507DateTimePeriod();
		c507DateTimePeriod.setE2005DateTimePeriodQualifier("91");
		c507DateTimePeriod.setE2379DateTimePeriodFormatQualifier("102");
		String endDteStr = 
				DateFormatUtils.format(endDte, "yyyyMMdd");
		c507DateTimePeriod.setE2380DateTimePeriod(endDteStr);
		DTMDateTimePeriod endDteObj = new DTMDateTimePeriod();
		endDteObj.setC507DateTimePeriod(c507DateTimePeriod);
		return endDteObj;
	}
	public static SegmentGroup1 createSgNadCo(String custCod) {
		SegmentGroup1 sg1NadCo = new SegmentGroup1();
		NADNameAndAddress nadCo = new NADNameAndAddress();
		nadCo.setE3035PartyQualifier("CO");
		C082PartyIdentificationDetails partyDetail = new C082PartyIdentificationDetails();
		partyDetail.setE3039PartyIdIdentification(custCod);
		partyDetail.setE3055CodeListResponsibleAgencyCoded("86");
		nadCo.setC082PartyIdentificationDetails(partyDetail);
		sg1NadCo.setNADNameAndAddress(nadCo);
		return sg1NadCo;
	}
	public static SegmentGroup1 createSgNadSu() {
		SegmentGroup1 sg1NadSu = new SegmentGroup1();
		NADNameAndAddress nadSu = new NADNameAndAddress();
		nadSu.setE3035PartyQualifier("SU");
		C082PartyIdentificationDetails partyDetail = new C082PartyIdentificationDetails();
		partyDetail.setE3039PartyIdIdentification("0909");
		partyDetail.setE3055CodeListResponsibleAgencyCoded("86");
		nadSu.setC082PartyIdentificationDetails(partyDetail);
		sg1NadSu.setNADNameAndAddress(nadSu);
		return sg1NadSu;
	}
	public static SegmentGroup1 createSgNadSn(String snCod) {
		SegmentGroup1 sg1NadSn = new SegmentGroup1();
		NADNameAndAddress nadSn = new NADNameAndAddress();
		nadSn.setE3035PartyQualifier("SN");
		C082PartyIdentificationDetails partyDetail = new C082PartyIdentificationDetails();
		partyDetail.setE3039PartyIdIdentification(snCod);
		partyDetail.setE3055CodeListResponsibleAgencyCoded("86");
		nadSn.setC082PartyIdentificationDetails(partyDetail);
		sg1NadSn.setNADNameAndAddress(nadSn);
		return sg1NadSn;
	}
	public static SegmentGroup4 createSgCurCny() {
		SegmentGroup4 sg4CurCny = new SegmentGroup4();
		CUXCurrencies curCny = new CUXCurrencies();
		C5041CurrencyDetails curCnyDetails = new C5041CurrencyDetails();
		curCnyDetails.setE6347CurrencyDetailsQualifier("2");
		curCnyDetails.setE6345CurrencyCoded("CNY");
		curCnyDetails.setE6343CurrencyQualifier("10");
		curCny.setC5041CurrencyDetails(curCnyDetails);
		sg4CurCny.setCUXCurrencies(curCny);
		return sg4CurCny;
	}
	public static LOCPlaceLocationIdentification getLocOfSale(String saleLoc) {
		LOCPlaceLocationIdentification loc = new LOCPlaceLocationIdentification();
		loc.setE3227PlaceLocationQualifier("162");
		C517LocationIdentification locId = new C517LocationIdentification();
		locId.setE3225PlaceLocationIdentification(saleLoc);
		locId.setE3055CodeListResponsibleAgencyCoded("9");
		loc.setC517LocationIdentification(locId);
		return loc;
	}
	public static List<DTMDateTimePeriod> getDtmSalesDte(String billDte) {
		List<DTMDateTimePeriod> dtmSalesDteList = new ArrayList<DTMDateTimePeriod>();
		DTMDateTimePeriod dtm = new DTMDateTimePeriod();
		C507DateTimePeriod dtSaleDte = new C507DateTimePeriod();
		dtSaleDte.setE2005DateTimePeriodQualifier("356");
		dtSaleDte.setE2380DateTimePeriod(billDte);
		dtSaleDte.setE2379DateTimePeriodFormatQualifier("102");
		dtm.setC507DateTimePeriod(dtSaleDte);
		dtmSalesDteList.add(dtm);
		return dtmSalesDteList;
	}
	public static C212ItemNumberIdentification createItemNum(String itemNum) {
		C212ItemNumberIdentification itemNumObj = new C212ItemNumberIdentification();
		itemNumObj.setE7140ItemNumber(itemNum);
		itemNumObj.setE7143ItemNumberTypeCoded("EN");
		itemNumObj.setE3055CodeListResponsibleAgencyCoded("9");
		return itemNumObj;
	}
	public static List<RFFReference> createReference(String refNo) {
		List<RFFReference> refList = new ArrayList<RFFReference>();
		RFFReference ref = new RFFReference();
		C506Reference c506Reference = new C506Reference();
		c506Reference.setE1154ReferenceNumber(refNo);
		c506Reference.setE1153ReferenceQualifier("TN");
		ref.setC506Reference(c506Reference);
		refList.add(ref);
		return refList;
	}
	public static List<MOAMonetaryAmount> createMoa(BigDecimal priceAct) {
		C516MonetaryAmount c516maGross = new C516MonetaryAmount();
		c516maGross.setE5025MonetaryAmountTypeQualifier("139");
		c516maGross.setE5004MonetaryAmount(priceAct);
		MOAMonetaryAmount moaGross = new MOAMonetaryAmount();
		moaGross.setC516MonetaryAmount(c516maGross);
		
		BigDecimal priceNet = priceAct.divide(TAX_RATE, 2, RoundingMode.HALF_UP);
		C516MonetaryAmount c516maNet = new C516MonetaryAmount();
		c516maNet.setE5025MonetaryAmountTypeQualifier("128");
		c516maNet.setE5004MonetaryAmount(priceNet);
		MOAMonetaryAmount moaNet = new MOAMonetaryAmount();
		moaNet.setC516MonetaryAmount(c516maNet);
		
		List<MOAMonetaryAmount> moaList = new ArrayList<MOAMonetaryAmount>();
		moaList.add(moaGross);
		moaList.add(moaNet);
		
		return moaList;
	}
	public static List<PRIPriceDetails> createPrice(BigDecimal priceAct, BigDecimal priceLst) {
//		actual price = 1500, recommended retail price = 2000, 17% VAT
//		PRI+AAA:1282.05::ABE
//		PRI+AAB:1500.00::RTP
//		PRI+AAA:1709.40::SRP
//		PRI+AAE:2000.00::SRP
//		PRI+AAA:427.35::DPR
//		PRI+AAE:500.00:DPR
		
		PRIPriceDetails pgd = new PRIPriceDetails();
		C509PriceInformation pgi= new C509PriceInformation();
		pgi.setE5118Price(priceAct);
		pgi.setE5125PriceQualifier("AAB");
		pgi.setE5387PriceTypeQualifier("RTP");
		pgd.setC509PriceInformation(pgi);
		
		PRIPriceDetails pnd = new PRIPriceDetails();
		C509PriceInformation pni= new C509PriceInformation();
		BigDecimal priceNet = priceAct.divide(TAX_RATE, 2, RoundingMode.HALF_UP);
		pni.setE5118Price(priceNet);
		pni.setE5125PriceQualifier("AAA");
		pni.setE5387PriceTypeQualifier("ABE");
		pnd.setC509PriceInformation(pni);
		
		PRIPriceDetails pgdLst = new PRIPriceDetails();
		C509PriceInformation pgiLst= new C509PriceInformation();
		pgiLst.setE5118Price(priceLst);
		pgiLst.setE5125PriceQualifier("AAB");
		pgiLst.setE5387PriceTypeQualifier("SRP");
		pgdLst.setC509PriceInformation(pgiLst);
		
		PRIPriceDetails pndLst = new PRIPriceDetails();
		C509PriceInformation pniLst= new C509PriceInformation();
		BigDecimal priceLstNet = priceLst.divide(TAX_RATE, 2, RoundingMode.HALF_UP);
		pniLst.setE5118Price(priceLstNet);
		pniLst.setE5125PriceQualifier("AAA");
		pniLst.setE5387PriceTypeQualifier("SRP");
		pndLst.setC509PriceInformation(pniLst);
		
		PRIPriceDetails pgdDisc = new PRIPriceDetails();
		C509PriceInformation pgiDisc= new C509PriceInformation();
		BigDecimal discount = priceLst.subtract(priceAct).setScale(2, RoundingMode.HALF_UP);
		pgiDisc.setE5118Price(discount);
		pgiDisc.setE5125PriceQualifier("AAB");
		pgiDisc.setE5387PriceTypeQualifier("DPR");
		pgdDisc.setC509PriceInformation(pgiDisc);
		
		PRIPriceDetails pndDisc = new PRIPriceDetails();
		C509PriceInformation pniDisc= new C509PriceInformation();
		BigDecimal discNet = discount.divide(TAX_RATE, 2, RoundingMode.HALF_UP);
		pniDisc.setE5118Price(discNet);
		pniDisc.setE5125PriceQualifier("AAA");
		pniDisc.setE5387PriceTypeQualifier("DPR");
		pndDisc.setC509PriceInformation(pniDisc);
		
		
		List<PRIPriceDetails> priceList = new ArrayList<PRIPriceDetails>();
//		成交价转移到MOA segment 2015-11-12
//		priceList.add(pnd);
//		priceList.add(pgd);
		priceList.add(pgdLst);
		priceList.add(pndLst);
		priceList.add(pgdDisc);
		priceList.add(pndDisc);
		
		return priceList;
	}
	public static List<SegmentGroup8> createQty(BigDecimal qty) {
		C186QuantityDetails qtyDetail = new C186QuantityDetails();
		qtyDetail.setE6063QuantityQualifier("153");
		qtyDetail.setE6060Quantity(qty);
		
		QTYQuantity qtyObj = new QTYQuantity();
		qtyObj.setC186QuantityDetails(qtyDetail);
		
		SegmentGroup8 sg8 = new SegmentGroup8();
		sg8.setQTYQuantity(qtyObj);
		
		List<SegmentGroup8> sg8List = new ArrayList<SegmentGroup8>();
		sg8List.add(sg8);
		
		return sg8List;
	}
}
