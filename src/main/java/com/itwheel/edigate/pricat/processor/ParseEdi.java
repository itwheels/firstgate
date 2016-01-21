package com.itwheel.edigate.pricat.processor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.milyn.edi.unedifact.d96a.PRICAT.Pricat;
import org.milyn.edi.unedifact.d96a.PRICAT.SegmentGroup1;
import org.milyn.edi.unedifact.d96a.PRICAT.SegmentGroup16;
import org.milyn.edi.unedifact.d96a.PRICAT.SegmentGroup2;
import org.milyn.edi.unedifact.d96a.PRICAT.SegmentGroup33;
import org.milyn.edi.unedifact.d96a.PRICAT.SegmentGroup37;
import org.milyn.edi.unedifact.d96a.PRICAT.SegmentGroup6;
import org.milyn.edi.unedifact.d96a.common.BGMBeginningOfMessage;
import org.milyn.edi.unedifact.d96a.common.CUXCurrencies;
import org.milyn.edi.unedifact.d96a.common.DTMDateTimePeriod;
import org.milyn.edi.unedifact.d96a.common.FTXFreeText;
import org.milyn.edi.unedifact.d96a.common.IMDItemDescription;
import org.milyn.edi.unedifact.d96a.common.LINLineItem;
import org.milyn.edi.unedifact.d96a.common.NADNameAndAddress;
import org.milyn.edi.unedifact.d96a.common.PGIProductGroupInformation;
import org.milyn.edi.unedifact.d96a.common.PIAAdditionalProductId;
import org.milyn.edi.unedifact.d96a.common.PRIPriceDetails;
import org.milyn.edi.unedifact.d96a.common.RFFReference;
import org.milyn.edi.unedifact.d96a.common.field.C002DocumentMessageName;
import org.milyn.edi.unedifact.d96a.common.field.C080PartyName;
import org.milyn.edi.unedifact.d96a.common.field.C082PartyIdentificationDetails;
import org.milyn.edi.unedifact.d96a.common.field.C108TextLiteral;
import org.milyn.edi.unedifact.d96a.common.field.C212ItemNumberIdentification;
import org.milyn.edi.unedifact.d96a.common.field.C273ItemDescription;
import org.milyn.edi.unedifact.d96a.common.field.C288ProductGroup;
import org.milyn.edi.unedifact.d96a.common.field.C5041CurrencyDetails;
import org.milyn.edi.unedifact.d96a.common.field.C506Reference;
import org.milyn.edi.unedifact.d96a.common.field.C507DateTimePeriod;
import org.milyn.edi.unedifact.d96a.common.field.C509PriceInformation;
import org.milyn.smooks.edi.unedifact.model.UNEdifactInterchange;
import org.milyn.smooks.edi.unedifact.model.r41.UNEdifactInterchange41;
import org.milyn.smooks.edi.unedifact.model.r41.UNEdifactMessage41;
import org.milyn.smooks.edi.unedifact.model.r41.UNH41;
import org.milyn.smooks.edi.unedifact.model.r41.UNT41;
import org.milyn.smooks.edi.unedifact.model.r41.types.MessageIdentifier;

import com.itwheel.edigate.pricat.processor.EdiPricatHeadBean;
import com.itwheel.edigate.pricat.processor.EdiPricatItemBean;
import com.itwheel.edigate.pricat.processor.EdiPricatItemDescBean;

public class ParseEdi {

	public EdiPricatHeadBean parse(UNEdifactInterchange interchange) throws Exception {
		 EdiPricatHeadBean head = null;
		 if (interchange instanceof UNEdifactInterchange41) {
             UNEdifactInterchange41 interchange41 = (UNEdifactInterchange41) interchange;
             
             for (UNEdifactMessage41 messageWithControlSegments : interchange41.getMessages()) {
            	 
            	 head = new EdiPricatHeadBean();
            	 
            	 UNH41 unh41 = messageWithControlSegments.getMessageHeader();
            	 
            	 UNT41 unt41 = messageWithControlSegments.getMessageTrailer();
            	 int seg = unt41.getSegmentCount();
            	 String untmsg = unt41.getMessageRefNum();
            	 
            	 head.setSeg_num(seg);
            	 head.setMsg_num(untmsg);
            	 
            	 String refNum = unh41.getMessageRefNum(); // Senders unique message reference
            	 head.setMsg_ref_num(refNum);
            	 MessageIdentifier identifier = unh41.getMessageIdentifier();
            	 String assignedCode = identifier.getAssociationAssignedCode(); // Association assigned code
            	 head.setAssociate_assign_code(assignedCode);
            	 String id = identifier.getId(); // PRICAT
            	 head.setMsg_type(id);
            	 String MSG_VER_NUM = identifier.getVersionNum(); // D
            	 head.setMsg_ver_num(MSG_VER_NUM);
            	 head.setControl_agency(identifier.getControllingAgencyCode());
            	 head.setMsg_rls_num(identifier.getReleaseNum());
            	 
                 Object messageObj = messageWithControlSegments.getMessage();
                 // 解析消息
                 if (messageObj instanceof Pricat) {
                	 Pricat pricat = (Pricat) messageObj;
                	 
                	 // BGM
                	 BGMBeginningOfMessage bgm = pricat.getBGMBeginningOfMessage();
                	 C002DocumentMessageName c002 = bgm.getC002DocumentMessageName(); // 9 Price/sales catalogue
                	 String e1225 = bgm.getE1225MessageFunctionCoded(); // 2 Addition
                	 head.setDoc_name(c002.getE1000DocumentMessageName());
                	 head.setDoc_num(c002.getE1001DocumentMessageNameCoded());
                	 head.setMsg_func(e1225);
                	 
                	 // DTM
                	 List<DTMDateTimePeriod> listDTM = pricat.getDTMDateTimePeriod();
                	 for(DTMDateTimePeriod dtm : listDTM) {
                		 C507DateTimePeriod c507 = dtm.getC507DateTimePeriod();
                		 String e2005 = c507.getE2005DateTimePeriodQualifier();
                		 // 137
                		 if("137".equalsIgnoreCase(e2005)) {
                			 head.setDoc_date(e2005);
                			 head.setDoc_date_time(c507.getE2380DateTimePeriod());
                			 head.setDoc_date_format(c507.getE2379DateTimePeriodFormatQualifier());
                		 }
                		 // 273
                		 else {
                			 head.setValidity_date(e2005);
                			 head.setValidity_date_time(c507.getE2380DateTimePeriod());
                			 head.setValidity_date_format(c507.getE2379DateTimePeriodFormatQualifier());
                		 }
                	 }
                	 
                	 // sg1
                	 List<SegmentGroup1> sg1List = pricat.getSegmentGroup1();
                	 for(SegmentGroup1 sg1 : sg1List) {
                		 RFFReference rff = sg1.getRFFReference();
                		 C506Reference c506 = rff.getC506Reference();
                		 String e1153 = c506.getE1153ReferenceQualifier();
                		 String e1154 = c506.getE1154ReferenceNumber();
                		 head.setRef_num(e1153);
                		 head.setRef_num_code(e1154);
                	 }
                	 
                	 // sg2
                	 List<SegmentGroup2> sg2List = pricat.getSegmentGroup2();
                	 for(SegmentGroup2 sg2 : sg2List) {
                		 NADNameAndAddress nad = sg2.getNADNameAndAddress();
                		 String e3035 = nad.getE3035PartyQualifier();
                		 // Buyer
                		 if("BY".equalsIgnoreCase(e3035)) {
                			 head.setParty_code_by(e3035);
                			 C080PartyName c080 = nad.getC080PartyName();
                			 String e30361 = c080.getE30361PartyName(); // shop name
                			 head.setParty_by_name(e30361);
                			 C082PartyIdentificationDetails c082 = nad.getC082PartyIdentificationDetails();
                			 String e3039 = c082.getE3039PartyIdIdentification(); // 0001028193
                			 head.setParty_ide_num_by(e3039);
                			 String e3055 = c082.getE3055CodeListResponsibleAgencyCoded(); // 91
//                			 head.setParty_ide_num_by(e3055);
                			 
                			 String e3207 = nad.getE3207CountryCoded(); // CN
                			 head.setCountry_code_by(e3207);
                			 String e3251 = nad.getE3251PostcodeIdentification();
                			 head.setPost_by_code(e3251);
                		 }
                		 // SU Supplier
                		 else {
                			 head.setParty_code_su(e3035);
                			 C080PartyName c080 = nad.getC080PartyName();
                			 String e30361 = c080.getE30361PartyName(); // shop name
                			 head.setParty_su_name(e30361);
                			 C082PartyIdentificationDetails c082 = nad.getC082PartyIdentificationDetails();
                			 String e3039 = c082.getE3039PartyIdIdentification(); // 0001028193
                			 head.setParty_ide_num_su(e3039);
                			 String e3055 = c082.getE3055CodeListResponsibleAgencyCoded(); // 91
//                			 head.setParty_ide_num_su(e3055);
                			 String e3207 = nad.getE3207CountryCoded(); // CN
                			 head.setCountry_code_su(e3207);
                			 String e3251 = nad.getE3251PostcodeIdentification();
                			 head.setPost_su_code(e3251);
                		 }
                	 }
                	 
                	 // sg6
                	 List<SegmentGroup6> sg6List = pricat.getSegmentGroup6();
                	 for(SegmentGroup6 sg6 : sg6List) {
                		 CUXCurrencies cux = sg6.getCUXCurrencies();
                		 C5041CurrencyDetails c5041 = cux.getC5041CurrencyDetails();
                		 String e6343 = c5041.getE6343CurrencyQualifier();
                		 String e6345 = c5041.getE6345CurrencyCoded();
                		 String e6347 = c5041.getE6347CurrencyDetailsQualifier();
                		 
                		 head.setCurrency_code(e6347);
                		 head.setCurrency(e6345);
                		 head.setCurrency_list(e6343);
                	 }
                	 
                	 // sg16
                	 List<SegmentGroup16> sg16List = pricat.getSegmentGroup16();
                	 
                	 List<EdiPricatItemBean> itemList = new ArrayList<EdiPricatItemBean>();
                     for(SegmentGroup16 sg : sg16List) {
                    	 PGIProductGroupInformation  pgi = sg.getPGIProductGroupInformation();
                    	 String e5347 = pgi.getE5379ProductGroupTypeCoded();
                    	 head.setProduct_group_code(e5347);
                    	 C288ProductGroup c288 = pgi.getC288ProductGroup();
                    	 String e5389 = c288.getE5389ProductGroupCoded();
                    	 head.setProduct_group(e5389);
                    	 String e5388 = c288.getE5388ProductGroup();
                    	 head.setProduct_group_des(e5388);
                    	 
                    	 // 存放行数据
                    	 EdiPricatItemBean lineItem = null;
                    	 
                    	 // sg33 LIN
                    	 List<SegmentGroup33> sg33List = sg.getSegmentGroup33();
                    	 for(SegmentGroup33 sg33 : sg33List) {
                    		 lineItem = new EdiPricatItemBean();
                    		 
                    		 // Line
                    		 LINLineItem line = sg33.getLINLineItem();
                    		 BigDecimal e1082 = line.getE1082LineItemNumber();
                    		 lineItem.setLine_item_num(e1082.toString());
                    		 String e1229 = line.getE1229ActionRequestNotificationCoded();
                    		 lineItem.setAction_code(e1229);
                    		 C212ItemNumberIdentification e212 = line.getC212ItemNumberIdentification();
                    		 String e7140 = e212.getE7140ItemNumber();
                    		 lineItem.setItem_id(e7140);
                    		 String e7143 = e212.getE7143ItemNumberTypeCoded();
                    		 lineItem.setItem_id_code(e7143);
                    		 
                    		 // PIA
                    		 List<PIAAdditionalProductId> piaList = sg33.getPIAAdditionalProductId();
                    		 for(PIAAdditionalProductId pia : piaList) {
                    			 String e4347 = pia.getE4347ProductIdFunctionQualifier();
                    			 C212ItemNumberIdentification c212 = pia.getC2121ItemNumberIdentification();
                    			 String c212e7140 = c212.getE7140ItemNumber();
                    			 String c212e7143 = c212.getE7143ItemNumberTypeCoded();
                    			 String c212e3055 = c212.getE3055CodeListResponsibleAgencyCoded();
                    			 
                    			 lineItem.setProduct_ide(e4347);
                    			 lineItem.setProduct_ide_num(c212e7140);
                    			 lineItem.setProduct_ide_code(c212e7143);
                    			 lineItem.setProduct_ide_code_list(c212e3055);
                    		 }
                    		 
                    		 // EDI_PRICAT_ITEM_DESC
                    		 List<EdiPricatItemDescBean> itemDescList = new ArrayList<EdiPricatItemDescBean>();
                    		 EdiPricatItemDescBean itemDesc = null;
                    		 
                    		 // IMD
                    		 List<IMDItemDescription> imdList = sg33.getIMDItemDescription();
                    		 for(IMDItemDescription imd : imdList) {
                    			 itemDesc = new EdiPricatItemDescBean();
                    			 String e7077 = imd.getE7077ItemDescriptionTypeCoded(); 
                    			 String e7081 = imd.getE7081ItemCharacteristicCoded();
                    			 C273ItemDescription c2731 = imd.getC273ItemDescription();
                    			 String e7009 = c2731.getE7009ItemDescriptionIdentification();
                    			 String e1131 = c2731.getE1131CodeListQualifier();
                    			 String e3055 = c2731.getE3055CodeListResponsibleAgencyCoded();
                    			 String e70081 = c2731.getE70081ItemDescription();
                    			 String e70082 = c2731.getE70082ItemDescription();
                    			 
                    			 itemDesc.setFORMAT(e7077);
                    			 itemDesc.setTYPE(e7081);
                    			 itemDesc.setDesc_id(e7009);
                    			 itemDesc.setCodelist_qualifier(e1131);
                    			 itemDesc.setCodelist_agency(e3055);
                    			 itemDesc.setDesctxt1(e70081);
                    			 itemDesc.setDesctxt2(e70082);
                    			 
                    			 itemDescList.add(itemDesc);
                    		 }
                    		 
                    		 lineItem.setItemDescList(itemDescList);
                    		 
                    		 // DTM
                    		 List<DTMDateTimePeriod> dtmList = sg33.getDTMDateTimePeriod();
                    		 
                    		 // FTX
                    		 List<FTXFreeText> ftxList = sg33.getFTXFreeText();
                    		 for(FTXFreeText ftx : ftxList) {
                    			 String e4451 = ftx.getE4451TextSubjectQualifier();
                    			 String e4453 = ftx.getE4453TextFunctionCoded();
                    			 C108TextLiteral c108 = ftx.getC108TextLiteral();
                    			 String e44401 = c108.getE44401FreeText();
                    			 String e44402 = c108.getE44402FreeText();
                    			 
                    			 lineItem.setText_sub_code(e4451);
                    			 lineItem.setText_func_code(e4453);
                    			 lineItem.setText_lite_content(e44401);
                    			 lineItem.setText_lite_pic(e44402);
                    		 }
                    		 
                    		 // SG37
                    		 List<SegmentGroup37> sg37List = sg33.getSegmentGroup37();
                    		 
                    		 // PRI
                    		 for(SegmentGroup37 sg37 : sg37List) {
                    			 PRIPriceDetails pri = sg37.getPRIPriceDetails();
                    			 C509PriceInformation c509 = pri.getC509PriceInformation();
                    			 String e5125 = c509.getE5125PriceQualifier(); // AAA
                    			 BigDecimal e5118 = c509.getE5118Price();
                    			 String e5375 = c509.getE5375PriceTypeCoded();
                    			 String e5387 = c509.getE5387PriceTypeQualifier();
                    			 
                    			 if("AAA".equalsIgnoreCase(e5125)) {
                    				 lineItem.setAaa_value_type(e5125);
                    				 lineItem.setAaa_value(e5118.toString());
                    				 lineItem.setAaa_value_code(e5375);
                    				 lineItem.setAaa_value_spec(e5387);
                    			 }
                    			 else if("AAB".equalsIgnoreCase(e5125)) {
                    				 lineItem.setAab_value_type(e5125);
                    				 lineItem.setAab_value(e5118.toString());
                    				 lineItem.setAab_value_code(e5375);
                    				 lineItem.setAab_value_spec(e5387);
                    			 }
                    			 else {
                    				 lineItem.setAae_value_type(e5125);
                    				 lineItem.setAae_value(e5118.toString());
                    				 lineItem.setAae_value_code(e5375);
                    				 lineItem.setAae_value_spec(e5387);
                    			 }
                    		 }
                    		 
                    		 itemList.add(lineItem);
                    	 }
                    	 
                    	 head.setItemList(itemList);
                     }
                 }
             }
         }
		 return head;
	}
}
