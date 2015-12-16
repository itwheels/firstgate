package com.itwheel.edigate.asnprocessor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.itwheel.edigate.asn.DOCUMENT;
import com.itwheel.edigate.asn.DOCUMENT.VOUCHERS;
import com.itwheel.edigate.asn.VENDINVOICE;
import com.itwheel.edigate.asn.VOUCHER;
import com.itwheel.edigate.asn.VOUCHER.VOUITEMS;
import com.itwheel.edigate.asn.VOUCHER.VOUITEMS.VOUITEM;

public class AsnProcessor implements Processor{

	private DataSource ediDs = null;
	public void setEdiDs(DataSource ediDs) {
		this.ediDs = ediDs;
	}
	
	@Override
	public void process(Exchange exchange) throws Exception {
		Object o = exchange.getIn().getBody();
		DOCUMENT document = (DOCUMENT)o;
		VOUCHERS vouchers =  document.getVOUCHERS();
		
		String sqlVoucher = "insert into VOUCHER(ID,VOU_SID, SBS_NO, STORE_NO, VOU_TYPE, VOU_CLASS, VEND_CODE, PAYEE_CODE, "
				+ "STATION, WORKSTATION, ORIG_STORE_NO, ORIG_STATION, PO_NO, PKG_NO, COST_HANDLING_CODE, "
				+ "UPDATE_PRICE_FLAG, USE_VAT, CREATED_DATE, MODIFIED_DATE, CMS, WS_SEQ_NO, HELD, ACTIVE, "
				+ "VEND_INVC_NO, VEND_INVC_DATE, CREATEDATE) "
				+ "values (SEQ_PK_VOUCHER_ID.Nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = ediDs.getConnection();
		
		List<VOUCHER> listVouchect = vouchers.getVOUCHER();
		PreparedStatement ps = conn.prepareStatement(sqlVoucher);
		for(int i = 0; i < listVouchect.size(); i++) {
			VOUCHER v = listVouchect.get(i);
			VENDINVOICE vi = v.getVENDINVOICE();
			
			ps.setString(1, v.getVouSid());
			ps.setString(2, v.getSbsNo());
			ps.setString(3, v.getStoreNo());
			ps.setString(4, v.getVouType());
			ps.setString(5, v.getVouClass());
			ps.setString(6, v.getVendCode());
			ps.setString(7, v.getPayeeCode());
			ps.setString(8, v.getStation());
			ps.setString(9, v.getWorkstation());
			ps.setString(10, v.getOrigStoreNo());
			ps.setString(11, v.getOrigStation());
			ps.setString(12, v.getPoNo());
			ps.setString(13, v.getPkgNo());
			ps.setString(14, v.getCostHandlingCode());
			ps.setString(15, v.getUpdatePriceFlag());
			ps.setString(16, v.getUseVat());
			ps.setString(17, v.getCreatedDate());
			ps.setString(18, v.getModifiedDate());
			ps.setString(19, v.getCms());
			ps.setString(20, v.getWsSeqNo());
			ps.setString(21, v.getHeld());
			ps.setString(22, v.getActive());
			ps.setString(23, vi.getVendInvcNo());
			ps.setString(24, vi.getVendInvcDate());
			ps.setTimestamp(25, new Timestamp(new java.util.Date().getTime()));
			
			ps.execute();
			
			VOUITEMS vis = v.getVOUITEMS();
			List<VOUITEM> listItem = vis.getVOUITEM();
			saveItems(conn, listItem, v.getVouSid());
		}
		ps.close();
		
		conn.close();
		
		exchange.getOut().setHeaders(exchange.getIn().getHeaders());
	}
	
	private void saveItems(Connection conn, List<VOUITEM> list, String vid) throws SQLException {
		String sqlVoucherItem = "insert into VOUCHER_ITEM(ID,VOU_SID, ITEM_POS, ITEM_SID, QTY, ORIG_QTY, PRICE, COST, UPC, CREATEDATE) "
				+ "values (SEQ_VOUCHER_ITEM_ID.Nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sqlVoucherItem);
		for(int i =0; i < list.size(); i++) {
			VOUITEM v = list.get(i);
			ps.setString(1, vid);
			ps.setString(2, v.getItemPos());
			ps.setString(3, v.getItemSid());
			ps.setString(4, v.getQty());
			ps.setString(5, v.getOrigQty());
			ps.setString(6, v.getPrice());
			ps.setString(7, v.getCost());
			ps.setString(8, v.getINVNBASEITEM().getUpc());
			ps.setTimestamp(9, new Timestamp(new java.util.Date().getTime()));
			ps.addBatch();
		}
		ps.executeBatch();
		ps.close();
	}
}
