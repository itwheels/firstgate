//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.16 at 10:28:12 PM CST 
//


package com.itwheel.edigate.po;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for INVN_BASE_ITEM complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="INVN_BASE_ITEM">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="item_sid" use="required" type="{}integerNullable" />
 *       &lt;attribute name="upc" type="{}integerNullable" />
 *       &lt;attribute name="alu" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="style_sid" type="{}integerNullable" />
 *       &lt;attribute name="dcs_code" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="vend_code" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="scale_no" type="{}integerNullable" />
 *       &lt;attribute name="description1" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="description2" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="description3" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="description4" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="attr" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="siz" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="use_qty_decimals" type="{}integerNullable" />
 *       &lt;attribute name="tax_code" type="{}integerNullable" />
 *       &lt;attribute name="flag" type="{}integerNullable" />
 *       &lt;attribute name="ext_flag" type="{}integerNullable" />
 *       &lt;attribute name="item_no" type="{}integerNullable" />
 *       &lt;attribute name="udf3_value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="udf4_value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="udf5_value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="udf6_value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="aux1_value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="aux2_value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="aux3_value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="aux4_value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="aux5_value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="aux6_value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="aux7_value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="aux8_value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "INVN_BASE_ITEM")
public class INVNBASEITEM {

    @XmlAttribute(name = "item_sid", required = true)
    protected String itemSid;
    @XmlAttribute(name = "upc")
    protected String upc;
    @XmlAttribute(name = "alu")
    protected String alu;
    @XmlAttribute(name = "style_sid")
    protected String styleSid;
    @XmlAttribute(name = "dcs_code")
    protected String dcsCode;
    @XmlAttribute(name = "vend_code")
    protected String vendCode;
    @XmlAttribute(name = "scale_no")
    protected String scaleNo;
    @XmlAttribute(name = "description1")
    protected String description1;
    @XmlAttribute(name = "description2")
    protected String description2;
    @XmlAttribute(name = "description3")
    protected String description3;
    @XmlAttribute(name = "description4")
    protected String description4;
    @XmlAttribute(name = "attr")
    protected String attr;
    @XmlAttribute(name = "siz")
    protected String siz;
    @XmlAttribute(name = "use_qty_decimals")
    protected String useQtyDecimals;
    @XmlAttribute(name = "tax_code")
    protected String taxCode;
    @XmlAttribute(name = "flag")
    protected String flag;
    @XmlAttribute(name = "ext_flag")
    protected String extFlag;
    @XmlAttribute(name = "item_no")
    protected String itemNo;
    @XmlAttribute(name = "udf3_value")
    protected String udf3Value;
    @XmlAttribute(name = "udf4_value")
    protected String udf4Value;
    @XmlAttribute(name = "udf5_value")
    protected String udf5Value;
    @XmlAttribute(name = "udf6_value")
    protected String udf6Value;
    @XmlAttribute(name = "aux1_value")
    protected String aux1Value;
    @XmlAttribute(name = "aux2_value")
    protected String aux2Value;
    @XmlAttribute(name = "aux3_value")
    protected String aux3Value;
    @XmlAttribute(name = "aux4_value")
    protected String aux4Value;
    @XmlAttribute(name = "aux5_value")
    protected String aux5Value;
    @XmlAttribute(name = "aux6_value")
    protected String aux6Value;
    @XmlAttribute(name = "aux7_value")
    protected String aux7Value;
    @XmlAttribute(name = "aux8_value")
    protected String aux8Value;

    /**
     * Gets the value of the itemSid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemSid() {
        return itemSid;
    }

    /**
     * Sets the value of the itemSid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemSid(String value) {
        this.itemSid = value;
    }

    /**
     * Gets the value of the upc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpc() {
        return upc;
    }

    /**
     * Sets the value of the upc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpc(String value) {
        this.upc = value;
    }

    /**
     * Gets the value of the alu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlu() {
        return alu;
    }

    /**
     * Sets the value of the alu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlu(String value) {
        this.alu = value;
    }

    /**
     * Gets the value of the styleSid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStyleSid() {
        return styleSid;
    }

    /**
     * Sets the value of the styleSid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStyleSid(String value) {
        this.styleSid = value;
    }

    /**
     * Gets the value of the dcsCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDcsCode() {
        return dcsCode;
    }

    /**
     * Sets the value of the dcsCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDcsCode(String value) {
        this.dcsCode = value;
    }

    /**
     * Gets the value of the vendCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendCode() {
        return vendCode;
    }

    /**
     * Sets the value of the vendCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendCode(String value) {
        this.vendCode = value;
    }

    /**
     * Gets the value of the scaleNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScaleNo() {
        return scaleNo;
    }

    /**
     * Sets the value of the scaleNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScaleNo(String value) {
        this.scaleNo = value;
    }

    /**
     * Gets the value of the description1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription1() {
        return description1;
    }

    /**
     * Sets the value of the description1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription1(String value) {
        this.description1 = value;
    }

    /**
     * Gets the value of the description2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription2() {
        return description2;
    }

    /**
     * Sets the value of the description2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription2(String value) {
        this.description2 = value;
    }

    /**
     * Gets the value of the description3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription3() {
        return description3;
    }

    /**
     * Sets the value of the description3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription3(String value) {
        this.description3 = value;
    }

    /**
     * Gets the value of the description4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription4() {
        return description4;
    }

    /**
     * Sets the value of the description4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription4(String value) {
        this.description4 = value;
    }

    /**
     * Gets the value of the attr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttr() {
        return attr;
    }

    /**
     * Sets the value of the attr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttr(String value) {
        this.attr = value;
    }

    /**
     * Gets the value of the siz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiz() {
        return siz;
    }

    /**
     * Sets the value of the siz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiz(String value) {
        this.siz = value;
    }

    /**
     * Gets the value of the useQtyDecimals property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseQtyDecimals() {
        return useQtyDecimals;
    }

    /**
     * Sets the value of the useQtyDecimals property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseQtyDecimals(String value) {
        this.useQtyDecimals = value;
    }

    /**
     * Gets the value of the taxCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxCode() {
        return taxCode;
    }

    /**
     * Sets the value of the taxCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxCode(String value) {
        this.taxCode = value;
    }

    /**
     * Gets the value of the flag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlag() {
        return flag;
    }

    /**
     * Sets the value of the flag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlag(String value) {
        this.flag = value;
    }

    /**
     * Gets the value of the extFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtFlag() {
        return extFlag;
    }

    /**
     * Sets the value of the extFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtFlag(String value) {
        this.extFlag = value;
    }

    /**
     * Gets the value of the itemNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemNo() {
        return itemNo;
    }

    /**
     * Sets the value of the itemNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemNo(String value) {
        this.itemNo = value;
    }

    /**
     * Gets the value of the udf3Value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUdf3Value() {
        return udf3Value;
    }

    /**
     * Sets the value of the udf3Value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUdf3Value(String value) {
        this.udf3Value = value;
    }

    /**
     * Gets the value of the udf4Value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUdf4Value() {
        return udf4Value;
    }

    /**
     * Sets the value of the udf4Value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUdf4Value(String value) {
        this.udf4Value = value;
    }

    /**
     * Gets the value of the udf5Value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUdf5Value() {
        return udf5Value;
    }

    /**
     * Sets the value of the udf5Value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUdf5Value(String value) {
        this.udf5Value = value;
    }

    /**
     * Gets the value of the udf6Value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUdf6Value() {
        return udf6Value;
    }

    /**
     * Sets the value of the udf6Value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUdf6Value(String value) {
        this.udf6Value = value;
    }

    /**
     * Gets the value of the aux1Value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAux1Value() {
        return aux1Value;
    }

    /**
     * Sets the value of the aux1Value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAux1Value(String value) {
        this.aux1Value = value;
    }

    /**
     * Gets the value of the aux2Value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAux2Value() {
        return aux2Value;
    }

    /**
     * Sets the value of the aux2Value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAux2Value(String value) {
        this.aux2Value = value;
    }

    /**
     * Gets the value of the aux3Value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAux3Value() {
        return aux3Value;
    }

    /**
     * Sets the value of the aux3Value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAux3Value(String value) {
        this.aux3Value = value;
    }

    /**
     * Gets the value of the aux4Value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAux4Value() {
        return aux4Value;
    }

    /**
     * Sets the value of the aux4Value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAux4Value(String value) {
        this.aux4Value = value;
    }

    /**
     * Gets the value of the aux5Value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAux5Value() {
        return aux5Value;
    }

    /**
     * Sets the value of the aux5Value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAux5Value(String value) {
        this.aux5Value = value;
    }

    /**
     * Gets the value of the aux6Value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAux6Value() {
        return aux6Value;
    }

    /**
     * Sets the value of the aux6Value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAux6Value(String value) {
        this.aux6Value = value;
    }

    /**
     * Gets the value of the aux7Value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAux7Value() {
        return aux7Value;
    }

    /**
     * Sets the value of the aux7Value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAux7Value(String value) {
        this.aux7Value = value;
    }

    /**
     * Gets the value of the aux8Value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAux8Value() {
        return aux8Value;
    }

    /**
     * Sets the value of the aux8Value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAux8Value(String value) {
        this.aux8Value = value;
    }

}
