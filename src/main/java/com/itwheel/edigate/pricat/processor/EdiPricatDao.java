package com.itwheel.edigate.pricat.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EdiPricatDao {
	
	public void handler(Connection conn, EdiPricatHeadBean head) throws Exception {
		Statement s = conn.createStatement();
		ResultSet rs = s.executeQuery("select SEQ_EDI_PRICAT_HEAD_ID.NEXTVAL from dual");
		int headId = 0;
		if(rs.next()) {
			headId = rs.getInt(1);
		}
		
		head.setId(headId);

		String headSql = "insert into edi_pricat_head (ID, MSG_REF_NUM, MSG_TYPE, MSG_VER_NUM, MSG_RLS_NUM, CONTROL_AGENCY, "
				+ "ASSOCIATE_ASSIGN_CODE, DOC_NAME, DOC_NUM, "
				+ "MSG_FUNC, DOC_DATE, VALIDITY_DATE, DOC_DATE_TIME, "
				+ "VALIDITY_DATE_TIME, DOC_DATE_FORMAT, VALIDITY_DATE_FORMAT, "
				+ "REF_NUM, REF_NUM_CODE, PARTY_CODE_BY, PARTY_CODE_SU, "
				+ "PARTY_IDE_NUM_BY, PARTY_IDE_NUM_SU, IDE_CODE_BY, IDE_CODE_SU, "
				+ "PRODUCT_GROUP_CODE, PRODUCT_GROUP, PRODUCT_GROUP_DES, "
				+ "CURRENCY_CODE, CURRENCY, CURRENCY_LIST, PARTY_BY_NAME, "
				+ "POST_BY_CODE, PARTY_SU_NAME, POST_SU_CODE, COUNTRY_CODE_BY, "
				+ "COUNTRY_CODE_SU, SEG_NUM, MSG_NUM)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(headSql);
		ps.setInt(1, headId);
		ps.setString(2, head.getMsg_ref_num());
		ps.setString(3, head.getMsg_type());
		ps.setString(4, head.getMsg_ver_num());
		ps.setString(5, head.getMsg_rls_num());
		ps.setString(6, head.getControl_agency());
		ps.setString(7, head.getAssociate_assign_code());
		ps.setString(8, head.getDoc_name());
		ps.setString(9, head.getDoc_num());
		ps.setString(10, head.getMsg_func());
		ps.setString(11, head.getDoc_date());
		ps.setString(12, head.getValidity_date());
		ps.setString(13, head.getDoc_date_time());
		ps.setString(14, head.getValidity_date_time());
		ps.setString(15, head.getDoc_date_format());
		ps.setString(16, head.getValidity_date_format());
		ps.setString(17, head.getRef_num());
		ps.setString(18, head.getRef_num_code());
		ps.setString(19, head.getParty_code_by());
		ps.setString(20, head.getParty_code_su());
		ps.setString(21, head.getParty_ide_num_by());
		ps.setString(22, head.getParty_ide_num_su());
		ps.setString(23, null);
		ps.setString(24, null);
		ps.setString(25, head.getProduct_group_code());
		ps.setString(26, head.getProduct_group());
		ps.setString(27, head.getProduct_group_des());
		ps.setString(28, head.getCurrency_code());
		ps.setString(29, head.getCurrency());
		ps.setString(30, head.getCurrency_list());
		ps.setString(31, head.getParty_by_name());
		ps.setString(32, head.getPost_by_code());
		ps.setString(33, head.getParty_su_name());
		ps.setString(34, head.getPost_su_code());
		ps.setString(35, head.getCountry_code_by());
		ps.setString(36, head.getCountry_code_su());
		ps.setInt(37, head.getSeg_num());
		ps.setString(38, head.getMsg_num());
		
		ps.executeUpdate();
		
		String lineItemSql = "insert into EDI_PRICAT_ITEM (EDI_HEAD_ID, LINE_ITEM_NUM, ACTION_CODE, ITEM_ID, ITEM_ID_CODE, PRODUCT_IDE, ADDITIONAL_IDE, PRODUCT_IDE_NUM, ADDITIONAL_IDE_NUM, PRODUCT_IDE_CODE, ADDITIONAL_IDE_CODE, PRODUCT_IDE_CODE_LIST, ADDITIONAL_IDE_CODE_LIST, ORDER_CODE, DELIVER_CODE, ORDER_DATE, DELIVER_DATE, ORDER_DATE_FORMAT, DELIVER_DATE_FORMAT, TEXT_SUB_CODE, TEXT_FUNC_CODE, TEXT_LITE_CONTENT, AAA_VALUE, AAE_VALUE, AAB_VALUE, AAA_VALUE_TYPE, AAE_VALUE_TYPE, AAB_VALUE_TYPE, AAA_VALUE_CODE, AAE_VALUE_CODE, AAB_VALUE_CODE, ID, TEXT_LITE_PIC) " +
				" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String lineItemDescSql = "insert into EDI_PRICAT_ITEM_DESC (ID, ITEM_ID, ITEM_DESC_FORMAT, ITEM_DESC_CODE, ITEM_DESC_IDE_CODE, ITEM_DESC_IDE_CODELIST, ITEM_DESC) " +
				" values (SEQ_EDI_PRICAT_ITEM_DESC_ID.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		ps = conn.prepareStatement(lineItemSql);
		PreparedStatement psId = conn.prepareStatement(lineItemDescSql);
		for(EdiPricatItemBean item : head.getItemList()) {
			ps.setInt(1, headId);
			ps.setString(2, item.getLine_item_num());
			ps.setString(3, item.getAction_code());
			ps.setString(4, item.getItem_id());
			ps.setString(5, item.getItem_id_code());
			ps.setString(6, item.getProduct_ide());
			ps.setString(7, null);
			ps.setString(8, item.getProduct_ide_num());
			ps.setString(9, null);
			ps.setString(10, item.getProduct_ide_code());
			ps.setString(11, null);
			ps.setString(12, item.getProduct_ide_code_list());
			ps.setString(13, null);
			ps.setString(14, item.getOrder_code());
			ps.setString(15, item.getDeliver_code());
			ps.setString(16, item.getOrder_date());
			ps.setString(17, item.getDeliver_date());
			ps.setString(18, item.getOrder_date_format());
			ps.setString(19, item.getDeliver_date_format());
			ps.setString(20, item.getText_sub_code());
			ps.setString(21, item.getText_func_code());
			ps.setString(22, item.getText_lite_content());
			ps.setString(23, item.getAaa_value());
			ps.setString(24, item.getAae_value());
			ps.setString(25, item.getAab_value());
			ps.setString(26, item.getAaa_value_type());
			ps.setString(27, item.getAae_value_type());
			ps.setString(28, item.getAab_value_type());
			ps.setString(29, item.getAaa_value_code());
			ps.setString(30, item.getAae_value_code());
			ps.setString(31, item.getAab_value_code());
			ps.setInt(32, getItemId(conn));
			ps.setString(33, item.getText_lite_pic());
			
			for(EdiPricatItemDescBean id : item.getItemDescList()) {
				psId.setString(1, id.getItem_id());
				psId.setString(2, id.getItem_desc_format());
				psId.setString(3, id.getItem_desc_code());
				psId.setString(4, id.getItem_desc_ide_code());
				psId.setString(5, id.getItem_desc_ide_codelist());
				psId.setString(6, id.getItem_desc());
				
				psId.addBatch();
			}
			psId.executeBatch();
			
			ps.addBatch();
		}
		ps.executeBatch();
	}
	
	private int getItemId(Connection conn) throws Exception {
		Statement s = conn.createStatement();
		ResultSet rs = s.executeQuery("select SEQ_EDI_PRICAT_ITEM_ID.NEXTVAL from dual");
		int id = 0;
		if(rs.next()) {
			id = rs.getInt(1);
		}
		rs.close();
		s.close();
		return id;
	}
}
