package com.itwheel.edigate.pricat.processor;

import java.util.List;

// SEQ_EDI_PRICAT_ITEM_ID
public class EdiPricatItemBean {
	private int id;
	private int edi_head_id; //	商品所属head的id。
	private String line_item_num; //	商品在head中的编号。
	private String action_code; //	动作请求代码。取值为：1 = added。
	private String item_id; //	商品唯一码（条形码）。
	private String item_id_code; //	商品唯一码类型。取值：en = ean。
	private String product_ide; //	商品标识代码。取值为：5 = product identification，1 = additional identification。
	private String product_ide_num; //	由商品提供者分配的代码(货号）。
	private String product_ide_code; //	由商品提供者分配的代码类型。取值为：sa = supplier’s article number，cg = commodity grouping。
	private String product_ide_code_list; //	由商品提供者分配的代码列表。取值为：91 = assigned by supplier or supplie’r。
	private String order_code; //	订货时间代码。取值为：47e = order period。
	private String deliver_code; //	交付时间代码。取值为：64 = delivery period
	private String order_date; //	订货时间。
	private String deliver_date; //	交货时间。
	private String order_date_format; //	订货时间格式。
	private String deliver_date_format; //	交货时间格式。
	private String text_sub_code; //	文本主题代码。取值为：zzz。
	private String text_func_code; //	文本功能代码。取值为：1 = text for subsequent use。
	private String text_lite_content; //	文本描述内容。
	private String text_lite_pic; //	链接。
	private String aaa_value; //	商品净价（不含税价）。
	private String aae_value; //	商品原价。
	private String aab_value; //	商品价格（含税价）。
	private String aaa_value_type; //	取值为：aaa。
	private String aae_value_type; //	取值为：aae。
	private String aab_value_type; //	取值为：aab。
	private String aaa_value_code; //	取值为：ca = catalogue
	private String aae_value_code; //	取值为：ca = catalogue
	private String aab_value_code; //	取值为：ca = catalogue
	private String aaa_value_spec; //	aaa价格说明。
	private String aae_value_spec; //	aae价格说明。
	private String aab_value_spec; //	aab价格说明。
	
	private List<EdiPricatItemDescBean> itemDescList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEdi_head_id() {
		return edi_head_id;
	}
	public void setEdi_head_id(int edi_head_id) {
		this.edi_head_id = edi_head_id;
	}
	public String getLine_item_num() {
		return line_item_num;
	}
	public void setLine_item_num(String line_item_num) {
		this.line_item_num = line_item_num;
	}
	public String getAction_code() {
		return action_code;
	}
	public void setAction_code(String action_code) {
		this.action_code = action_code;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getItem_id_code() {
		return item_id_code;
	}
	public void setItem_id_code(String item_id_code) {
		this.item_id_code = item_id_code;
	}
	public String getProduct_ide() {
		return product_ide;
	}
	public void setProduct_ide(String product_ide) {
		this.product_ide = product_ide;
	}
	public String getProduct_ide_num() {
		return product_ide_num;
	}
	public void setProduct_ide_num(String product_ide_num) {
		this.product_ide_num = product_ide_num;
	}
	public String getProduct_ide_code() {
		return product_ide_code;
	}
	public void setProduct_ide_code(String product_ide_code) {
		this.product_ide_code = product_ide_code;
	}
	public String getProduct_ide_code_list() {
		return product_ide_code_list;
	}
	public void setProduct_ide_code_list(String product_ide_code_list) {
		this.product_ide_code_list = product_ide_code_list;
	}
	public String getOrder_code() {
		return order_code;
	}
	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}
	public String getDeliver_code() {
		return deliver_code;
	}
	public void setDeliver_code(String deliver_code) {
		this.deliver_code = deliver_code;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getDeliver_date() {
		return deliver_date;
	}
	public void setDeliver_date(String deliver_date) {
		this.deliver_date = deliver_date;
	}
	public String getOrder_date_format() {
		return order_date_format;
	}
	public void setOrder_date_format(String order_date_format) {
		this.order_date_format = order_date_format;
	}
	public String getDeliver_date_format() {
		return deliver_date_format;
	}
	public void setDeliver_date_format(String deliver_date_format) {
		this.deliver_date_format = deliver_date_format;
	}
	public String getText_sub_code() {
		return text_sub_code;
	}
	public void setText_sub_code(String text_sub_code) {
		this.text_sub_code = text_sub_code;
	}
	public String getText_func_code() {
		return text_func_code;
	}
	public void setText_func_code(String text_func_code) {
		this.text_func_code = text_func_code;
	}
	public String getText_lite_content() {
		return text_lite_content;
	}
	public void setText_lite_content(String text_lite_content) {
		this.text_lite_content = text_lite_content;
	}
	public String getText_lite_pic() {
		return text_lite_pic;
	}
	public void setText_lite_pic(String text_lite_pic) {
		this.text_lite_pic = text_lite_pic;
	}
	public String getAaa_value() {
		return aaa_value;
	}
	public void setAaa_value(String aaa_value) {
		this.aaa_value = aaa_value;
	}
	public String getAae_value() {
		return aae_value;
	}
	public void setAae_value(String aae_value) {
		this.aae_value = aae_value;
	}
	public String getAab_value() {
		return aab_value;
	}
	public void setAab_value(String aab_value) {
		this.aab_value = aab_value;
	}
	public String getAaa_value_type() {
		return aaa_value_type;
	}
	public void setAaa_value_type(String aaa_value_type) {
		this.aaa_value_type = aaa_value_type;
	}
	public String getAae_value_type() {
		return aae_value_type;
	}
	public void setAae_value_type(String aae_value_type) {
		this.aae_value_type = aae_value_type;
	}
	public String getAab_value_type() {
		return aab_value_type;
	}
	public void setAab_value_type(String aab_value_type) {
		this.aab_value_type = aab_value_type;
	}
	public String getAaa_value_code() {
		return aaa_value_code;
	}
	public void setAaa_value_code(String aaa_value_code) {
		this.aaa_value_code = aaa_value_code;
	}
	public String getAae_value_code() {
		return aae_value_code;
	}
	public void setAae_value_code(String aae_value_code) {
		this.aae_value_code = aae_value_code;
	}
	public String getAab_value_code() {
		return aab_value_code;
	}
	public void setAab_value_code(String aab_value_code) {
		this.aab_value_code = aab_value_code;
	}
	public String getAaa_value_spec() {
		return aaa_value_spec;
	}
	public void setAaa_value_spec(String aaa_value_spec) {
		this.aaa_value_spec = aaa_value_spec;
	}
	public String getAae_value_spec() {
		return aae_value_spec;
	}
	public void setAae_value_spec(String aae_value_spec) {
		this.aae_value_spec = aae_value_spec;
	}
	public String getAab_value_spec() {
		return aab_value_spec;
	}
	public void setAab_value_spec(String aab_value_spec) {
		this.aab_value_spec = aab_value_spec;
	}
	
	public List<EdiPricatItemDescBean> getItemDescList() {
		return itemDescList;
	}
	public void setItemDescList(List<EdiPricatItemDescBean> itemDescList) {
		this.itemDescList = itemDescList;
	}
	
}
