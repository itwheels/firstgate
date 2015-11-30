package com.itwheel.edigate.poprocessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.itwheel.edigate.po.DOCUMENT;
import com.itwheel.edigate.po.DOCUMENT.POS;
import com.itwheel.edigate.po.INVNBASEITEM;
import com.itwheel.edigate.po.PO;
import com.itwheel.edigate.po.PO.POFEES;
import com.itwheel.edigate.po.PO.POFEES.POFEE;
import com.itwheel.edigate.po.PO.POITEMS;
import com.itwheel.edigate.po.PO.POITEMS.POITEM;
import com.itwheel.edigate.po.PO.POITEMS.POITEM.POQTYS;
import com.itwheel.edigate.po.PO.POITEMS.POITEM.POQTYS.POQTY;
import com.itwheel.edigate.po.PO.POTERMS;
import com.itwheel.edigate.po.PO.POTERMS.POTERM;

public class PoProcessor implements Processor{

	private DataSource ediDs = null;
	public void setEdiDs(DataSource ediDs) {
		this.ediDs = ediDs;
	}
	
	@Override
	public void process(Exchange exchange) throws Exception {
		Map<String, Object> _po = (Map<String, Object>)exchange.getIn().getHeader("po_detail");
		
		DOCUMENT document = new DOCUMENT();
		POS pos = document.getPOS();
		
		List<PO> poList = pos.getPO();
		
		PO po = new PO();
		po.setPoSid((String)_po.get("po_sid"));
		po.setSbsNo((String)_po.get("205")); 
		
		po.setVendCode("01");
		po.setInstruction1("ECCO");
		po.setInstruction3(".");
		po.setUseVat("0");
		po.setCms("0");
		po.setActive("1");
		po.setVerified("0");
		po.setHeld("0");
		po.setEdiFlag("0");
		po.setController("0");
		po.setOrigController("0");
		po.setPendingPo("0");
		po.setPendingOverride("0");
		po.setEmplSbsNo("205");
		po.setTaxAreaName("CHINA");
		
		POFEES pofees = new POFEES();
		List<POFEE> lP = pofees.getPOFEE();
		POFEE pofee = new POFEE();
		pofee.setAmt("0");
		pofee.setFeeType("0");
		pofee.setFeeName("Fee");
		lP.add(pofee);
		po.setPOFEES(pofees);
		
		POTERMS poterms = new POTERMS();
		List<POTERM> lpoterm = poterms.getPOTERM();
		POTERM poterm = new POTERM();
		poterm.setDays("0");
		poterm.setDiscPerc("0");
		poterm.setTermNo("1");
		poterm.setTermType("0");;
		lpoterm.add(poterm);
		po.setPOTERMS(poterms);
		
		Connection conn = this.ediDs.getConnection();
		
		List<POITEM> list = getPoItems(conn, Long.valueOf((String)_po.get("id")));
		POITEMS pitems = new POITEMS();
		pitems.getPOITEM().addAll(list);
		po.setPOITEMS(pitems);
		
		poList.add(po);
		
		exchange.getOut().setHeaders(exchange.getIn().getHeaders());
		exchange.getOut().setBody(document);
	}
	
	private List<POITEM> getPoItems(Connection conn, long id) {
		String sql = "select mtf.id,mpa.no item_sid, mtf.price, mtf.precost cost, mp.name alu, mp.serialno style_sid, "
					+ "mp.value  ,mp.fairpdttype, mp.flowno, ma.value2, mtf.m_product_id item_no, mtf.qty "
					+ " from m_transferitem mtf, m_product_alias mpa, m_product mp,m_attributesetinstance ma "
					+ " where mtf.m_transfer_id =" + id + " and mtf.m_productalias_id = mpa.id and mtf.m_product_id = mp.id and mtf.m_attributesetinstance_id = ma.id";
		
		
		try {
			List<POITEM> list = new ArrayList<POITEM>();
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			POITEM item = null;
			int i = 1;
			while(rs.next()) {
				item = new POITEM();
				item.setItemPos(String.valueOf(i++));
				item.setItemSid(rs.getString("item_sid"));
				item.setPrice(rs.getString("price"));
				item.setCost(rs.getString("cost"));
				item.setFcCost("0");
				item.setTaxCode("0");
				item.setTaxPerc("0");
				item.setTaxCode2("0");
				item.setTaxPerc2("0");
				
				INVNBASEITEM in = new INVNBASEITEM();
				in.setItemSid(rs.getString("item_sid"));
				in.setUpc(rs.getString("item_sid"));
				in.setAlu(rs.getString("alu"));
				in.setStyleSid(rs.getString("style_sid"));
				in.setVendCode("01");
				in.setDescription1(rs.getString("style_sid"));
				in.setDescription2("1");
				in.setDescription3(rs.getString("value"));
				in.setDescription4(rs.getString("fairpdttype"));
				in.setAttr(rs.getString("flowno"));
				in.setSiz(rs.getString("value2"));
				in.setUseQtyDecimals("0");
				in.setTaxCode("0");
				in.setFlag("0");
				in.setExtFlag("0");
				in.setItemNo(rs.getString("item_no"));
				item.setINVNBASEITEM(in);
				
				POQTYS poqtys = new POQTYS();
				POQTY poqty = new POQTY();
				poqty.setOrdQty(rs.getString("qty"));
				poqty.setRcvdQty("0");
				List<POQTY> lp = poqtys.getPOQTY();
				lp.add(poqty);
				item.setPOQTYS(poqtys);
				list.add(item);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}