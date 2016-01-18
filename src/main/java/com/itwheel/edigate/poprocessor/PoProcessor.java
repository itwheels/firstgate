package com.itwheel.edigate.poprocessor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

		Connection conn = this.ediDs.getConnection();
		String destCode = code(conn, _po.get("c_dest_id") == null ? "" : ((BigDecimal)_po.get("c_dest_id")).toString());
		
		POS pos = new POS();
		
		List<PO> poList = pos.getPO();
		
		PO po = new PO();
		po.setPoSid((String)_po.get("po_sid"));
		po.setSbsNo((String)_po.get("205"));
		String code = (String)_po.get("code");
		po.setStoreNo(code);
		po.setShiptoStoreNo(destCode);
		po.setBilltoStoreNo(destCode);
		po.setMarkedforStoreNo(destCode);
		po.setPoNo((String)_po.get("po_no"));
		po.setPoType(_po.get("po_type") == null ? "" : ((BigDecimal)_po.get("po_type")).toString());
		po.setCreatedDate(_po.get("created_date") == null ? "" : ((Timestamp)_po.get("created_date")).toString());
		po.setModifiedDate(_po.get("modified_date") == null ? "" : ((Timestamp)_po.get("modified_date")).toString());
		
		String shippingDate = _po.get("shipping_date") == null ? "" : ((BigDecimal)_po.get("shipping_date")).toString();
		
		if(shippingDate != "") {
			shippingDate = shippingDate.substring(0, 4) + "-" +  shippingDate.substring(4, 6) + "-" + shippingDate.substring(6, 8);
		}
		
		po.setShippingDate(shippingDate);
		po.setLstActivityDate(_po.get("lst_activity_date") == null ? "" : ((Timestamp)_po.get("lst_activity_date")).toString());
		po.setSentDate(_po.get("lst_activity_date") == null ? "" : ((Timestamp)_po.get("lst_activity_date")).toString());
		po.setCmsPostDate(_po.get("sent_date") == null ? "" : ((Timestamp)_po.get("sent_date")).toString());
		po.setEmplName(_po.get("empl_name") == null ? "" : ((BigDecimal)_po.get("empl_name")).toString());
		
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
		
		List<POITEM> list = getPoItems(conn, Long.valueOf(((BigDecimal)_po.get("id")).longValue()), code);
		POITEMS pitems = new POITEMS();
		pitems.getPOITEM().addAll(list);
		po.setPOITEMS(pitems);
		
		poList.add(po);
		
		record(conn, (String)_po.get("po_sid"));
		
		conn.close();
		
		Map<String, Object> map = exchange.getIn().getHeaders();
		DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dStr = sdf.format(new Date());
		map.put("DATE", dStr);
		map.put("CODE", code);
		map.put("NUM", ((BigDecimal)_po.get("n")).toString());
		exchange.getOut().setHeaders(map);
		document.setPOS(pos);
		exchange.getOut().setBody(document);
	}
	
	private List<POITEM> getPoItems(Connection conn, long id, String code) {
		String sql = "select mtf.id,mpa.no item_sid, mtf.price, mtf.precost cost, mp.name alu, mp.serialno style_sid, "
					+ "mp.value  ,mp.fairpdttype, mp.flowno, ma.value2, mtf.m_product_id item_no, mtf.qty "
					+ " from NEANDS3.m_transferitem mtf, NEANDS3.m_product_alias mpa, NEANDS3.m_product mp,"
					+ "NEANDS3.m_attributesetinstance ma "
					+ " where mtf.m_transfer_id =" + id + " and mtf.m_productalias_id = mpa.id "
							+ "and mtf.m_product_id = mp.id and mtf.m_attributesetinstance_id = ma.id";
		
		
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
				in.setDescription3(null != rs.getString("value") && rs.getString("value").split("-").length > 1 ? rs.getString("value").split("-")[1] : rs.getString("value"));
				in.setDescription4(null != rs.getString("fairpdttype") &&rs.getString("fairpdttype").split("-").length > 1 ? rs.getString("fairpdttype").split("-")[1] : rs.getString("fairpdttype"));
				in.setAttr(rs.getString("flowno"));
				in.setSiz(rs.getString("value2"));
				in.setUseQtyDecimals("0");
				in.setTaxCode("0");
				in.setFlag("0");
				in.setExtFlag("0");
				in.setItemNo(rs.getString("item_no"));
				
				getM(conn, rs.getString("item_no"), in);
				
				item.setINVNBASEITEM(in);
				
				POQTYS poqtys = new POQTYS();
				POQTY poqty = new POQTY();
				poqty.setStoreNo(code);
				poqty.setOrdQty(rs.getString("qty"));
				poqty.setRcvdQty("0");
				List<POQTY> lp = poqtys.getPOQTY();
				lp.add(poqty);
				item.setPOQTYS(poqtys);
				list.add(item);
			}
			rs.close();
			state.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void getM(Connection conn, String itemno, INVNBASEITEM in) {
		String sql1 = "select value aux3_value from NEANDS3.m_product mp where mp.id=" +itemno;
		String sql2 = "select md.attribname aux3_value from NEANDS3.m_product mp, NEANDS3.m_dim md where mp.id=" + itemno + " and mp.m_dim5_id=md.id "; 
		String sql3 = "select md.attribname aux3_value from NEANDS3.m_product mp, NEANDS3.m_dim md where mp.id =" + itemno + " and mp.m_dim4_id=md.id";
		String sql4 = "select md.attribname aux3_value from NEANDS3.m_product mp, NEANDS3.m_dim md where mp.id =" + itemno + " and mp.m_dim6_id=md.id";
		String sql5 = "select md.attribname aux3_value from NEANDS3.m_product mp, NEANDS3.m_dim md where mp.id =" + itemno + " and mp.m_dim7_id=md.id";
		String sql6 = "select md.attribname aux3_value from NEANDS3.m_product mp, NEANDS3.m_dim md where mp.id =" + itemno + " and mp.m_dim9_id=md.id";
		String sql7 = "select md.attribname aux3_value from NEANDS3.m_product mp, NEANDS3.m_dim md where mp.id =" + itemno + " and mp.m_dim2_id=md.id";
		String sql8 = "select md.attribname aux3_value from NEANDS3.m_product mp, NEANDS3.m_dim md where mp.id =" + itemno + " and mp.m_dim3_id=md.id";
				
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql1);
			String s = "";
			if(rs.next()) {
				s = rs.getString(1);
				in.setAux1Value(s.split("-").length > 1 ? s.split("-")[1] : s);
			}
			
			rs.close();
			state.close();
			
			state = conn.createStatement();
			rs = state.executeQuery(sql2);
			if(rs.next()) {
				s = rs.getString(1);
				in.setAux2Value(s.split("-").length > 1 ? s.split("-")[1] : s);
			}
			
			rs.close();
			state.close();
			
			state = conn.createStatement();
			rs = state.executeQuery(sql3);
			if(rs.next()) {
				s = rs.getString(1);
				in.setAux3Value(s.split("-").length > 1 ? s.split("-")[1] : s);
			}
			
			rs.close();
			state.close();
			
			state = conn.createStatement();
			rs = state.executeQuery(sql4);
			if(rs.next()) {
				s = rs.getString(1);
				in.setAux4Value(s.split("-").length > 1 ? s.split("-")[1] : s);
			}
			
			rs.close();
			state.close();
			
			state = conn.createStatement();
			rs = state.executeQuery(sql5);
			if(rs.next()) {
				s = rs.getString(1);
				in.setAux5Value(s.split("-").length > 1 ? s.split("-")[1] : s);
			}
			
			rs.close();
			state.close();
			
			state = conn.createStatement();
			rs = state.executeQuery(sql6);
			if(rs.next()) {
				s = rs.getString(1);
				in.setAux5Value(s.split("-").length > 1 ? s.split("-")[1] : s);
			}
			
			rs.close();
			state.close();
			
			state = conn.createStatement();
			rs = state.executeQuery(sql7);
			if(rs.next()) {
				s = rs.getString(1);
			}
			else s = "";
			
			rs.close();
			state.close();
			
			state = conn.createStatement();
			rs = state.executeQuery(sql8);
			if(rs.next()) {
				s += "_" + rs.getString(1).replace("Q", "");
			}
			
			in.setAux7Value(s);
			
			rs.close();
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void record(Connection conn, String id) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dStr = sdf.format(new Date());
		String sql = "insert into LOGPO values(SEQ_LOGPO.Nextval,'" + id + "', '" + dStr + "')";
		try {
			Statement state = conn.createStatement();
			state.execute(sql);
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String code(Connection conn, String id) {
		String sql = "select code from NEANDS3.c_store c where c.id=" + id;
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			String s = "";
			if(rs.next()) {
				s =  rs.getString(1);
			}
			rs.close();
			state.close();
			return s;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
}
