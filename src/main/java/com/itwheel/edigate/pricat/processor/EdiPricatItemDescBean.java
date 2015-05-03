package com.itwheel.edigate.pricat.processor;

// SEQ_EDI_PRICAT_ITEM_DESC_ID
public class EdiPricatItemDescBean {
	private int id;
	private int item_id;
	private String FORMAT;
	private String TYPE;
	private String desc_id;
	private String codelist_qualifier;
	private String codelist_agency;
	private String desctxt1;
	private String desctxt2;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getFORMAT() {
		return FORMAT;
	}
	public void setFORMAT(String fORMAT) {
		FORMAT = fORMAT;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getDesc_id() {
		return desc_id;
	}
	public void setDesc_id(String desc_id) {
		this.desc_id = desc_id;
	}
	public String getCodelist_qualifier() {
		return codelist_qualifier;
	}
	public void setCodelist_qualifier(String codelist_qualifier) {
		this.codelist_qualifier = codelist_qualifier;
	}
	public String getCodelist_agency() {
		return codelist_agency;
	}
	public void setCodelist_agency(String codelist_agency) {
		this.codelist_agency = codelist_agency;
	}
	public String getDesctxt1() {
		return desctxt1;
	}
	public void setDesctxt1(String desctxt1) {
		this.desctxt1 = desctxt1;
	}
	public String getDesctxt2() {
		return desctxt2;
	}
	public void setDesctxt2(String desctxt2) {
		this.desctxt2 = desctxt2;
	}
	
}
