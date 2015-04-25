package com.itwheel.edigate.pricat.processor;

import java.util.List;

// SEQ_EDI_PRICAT_HEAD_ID
public class EdiPricatHeadBean {
	private String msg_ref_num; // 由发送方提供的唯一信息编号
	private String msg_type; // 消息类型。取值为：PRICAT = Price catalogue message
	private String msg_ver_num; // 
	private String msg_rls_num;
	private String control_agency;
	private String associate_assign_code;
	private String doc_name;
	private String doc_num;
	private String msg_func;
	private String doc_date;
	private String validity_date;
	private String doc_date_time;
	private String validity_date_time;
	private String doc_date_format;
	private String validity_date_format;
	private String ref_num;
	private String ref_num_code;
	private String party_code_by;
	private String party_code_su;
	private String party_ide_num_by;
	private String party_ide_num_su;
	private String party_by_name;
	private String party_su_name;
	private String post_by_code;
	private String post_su_code;
	private String country_code_by;
	private String country_code_su;
	private int id;
	private String product_group_code;
	private String product_group;
	private String product_group_des;
	private String currency_code;
	private String currency;
	private String currency_list;
	
	private int seg_num; //	消息段数量。
	private String msg_num; //	与msg_ref_num字段取值相同。
	
	private List<EdiPricatItemBean> itemList;
	
	public String getParty_by_name() {
		return party_by_name;
	}
	public void setParty_by_name(String party_by_name) {
		this.party_by_name = party_by_name;
	}
	public String getParty_su_name() {
		return party_su_name;
	}
	public void setParty_su_name(String party_su_name) {
		this.party_su_name = party_su_name;
	}
	public String getMsg_ref_num() {
		return msg_ref_num;
	}
	public void setMsg_ref_num(String msg_ref_num) {
		this.msg_ref_num = msg_ref_num;
	}
	public String getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
	public String getMsg_ver_num() {
		return msg_ver_num;
	}
	public void setMsg_ver_num(String msg_ver_num) {
		this.msg_ver_num = msg_ver_num;
	}
	public String getMsg_rls_num() {
		return msg_rls_num;
	}
	public void setMsg_rls_num(String msg_rls_num) {
		this.msg_rls_num = msg_rls_num;
	}
	public String getControl_agency() {
		return control_agency;
	}
	public void setControl_agency(String control_agency) {
		this.control_agency = control_agency;
	}
	public String getAssociate_assign_code() {
		return associate_assign_code;
	}
	public void setAssociate_assign_code(String associate_assign_code) {
		this.associate_assign_code = associate_assign_code;
	}
	public String getDoc_name() {
		return doc_name;
	}
	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}
	public String getDoc_num() {
		return doc_num;
	}
	public void setDoc_num(String doc_num) {
		this.doc_num = doc_num;
	}
	public String getMsg_func() {
		return msg_func;
	}
	public void setMsg_func(String msg_func) {
		this.msg_func = msg_func;
	}
	public String getDoc_date() {
		return doc_date;
	}
	public void setDoc_date(String doc_date) {
		this.doc_date = doc_date;
	}
	public String getValidity_date() {
		return validity_date;
	}
	public void setValidity_date(String validity_date) {
		this.validity_date = validity_date;
	}
	public String getDoc_date_time() {
		return doc_date_time;
	}
	public void setDoc_date_time(String doc_date_time) {
		this.doc_date_time = doc_date_time;
	}
	public String getValidity_date_time() {
		return validity_date_time;
	}
	public void setValidity_date_time(String validity_date_time) {
		this.validity_date_time = validity_date_time;
	}
	public String getDoc_date_format() {
		return doc_date_format;
	}
	public void setDoc_date_format(String doc_date_format) {
		this.doc_date_format = doc_date_format;
	}
	public String getValidity_date_format() {
		return validity_date_format;
	}
	public void setValidity_date_format(String validity_date_format) {
		this.validity_date_format = validity_date_format;
	}
	public String getRef_num() {
		return ref_num;
	}
	public void setRef_num(String ref_num) {
		this.ref_num = ref_num;
	}
	public String getRef_num_code() {
		return ref_num_code;
	}
	public void setRef_num_code(String ref_num_code) {
		this.ref_num_code = ref_num_code;
	}
	public String getParty_code_by() {
		return party_code_by;
	}
	public void setParty_code_by(String party_code_by) {
		this.party_code_by = party_code_by;
	}
	public String getParty_code_su() {
		return party_code_su;
	}
	public void setParty_code_su(String party_code_su) {
		this.party_code_su = party_code_su;
	}
	public String getParty_ide_num_by() {
		return party_ide_num_by;
	}
	public void setParty_ide_num_by(String party_ide_num_by) {
		this.party_ide_num_by = party_ide_num_by;
	}
	public String getParty_ide_num_su() {
		return party_ide_num_su;
	}
	public void setParty_ide_num_su(String party_ide_num_su) {
		this.party_ide_num_su = party_ide_num_su;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProduct_group_code() {
		return product_group_code;
	}
	public void setProduct_group_code(String product_group_code) {
		this.product_group_code = product_group_code;
	}
	public String getProduct_group() {
		return product_group;
	}
	public void setProduct_group(String product_group) {
		this.product_group = product_group;
	}
	public String getProduct_group_des() {
		return product_group_des;
	}
	public void setProduct_group_des(String product_group_des) {
		this.product_group_des = product_group_des;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCurrency_list() {
		return currency_list;
	}
	public void setCurrency_list(String currency_list) {
		this.currency_list = currency_list;
	}
	public String getPost_by_code() {
		return post_by_code;
	}
	public void setPost_by_code(String post_by_code) {
		this.post_by_code = post_by_code;
	}
	public String getPost_su_code() {
		return post_su_code;
	}
	public void setPost_su_code(String post_su_code) {
		this.post_su_code = post_su_code;
	}
	public String getCountry_code_by() {
		return country_code_by;
	}
	public void setCountry_code_by(String country_code_by) {
		this.country_code_by = country_code_by;
	}
	public String getCountry_code_su() {
		return country_code_su;
	}
	public void setCountry_code_su(String country_code_su) {
		this.country_code_su = country_code_su;
	}
	public List<EdiPricatItemBean> getItemList() {
		return itemList;
	}
	public void setItemList(List<EdiPricatItemBean> itemList) {
		this.itemList = itemList;
	}
	public int getSeg_num() {
		return seg_num;
	}
	public void setSeg_num(int seg_num) {
		this.seg_num = seg_num;
	}
	public String getMsg_num() {
		return msg_num;
	}
	public void setMsg_num(String msg_num) {
		this.msg_num = msg_num;
	}
}
