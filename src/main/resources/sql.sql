CREATE TABLE "EDIGATE"."EDI_PRICAT_HEAD" 
   (	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"MSG_REF_NUM" VARCHAR2(14), 
	"MSG_TYPE" VARCHAR2(6), 
	"MSG_VER_NUM" VARCHAR2(3), 
	"MSG_RLS_NUM" VARCHAR2(3), 
	"CONTROL_AGENCY" VARCHAR2(2), 
	"ASSOCIATE_ASSIGN_CODE" VARCHAR2(6), 
	"DOC_NAME" VARCHAR2(3), 
	"DOC_NUM" VARCHAR2(35), 
	"MSG_FUNC" VARCHAR2(3), 
	"DOC_DATE" VARCHAR2(3), 
	"VALIDITY_DATE" VARCHAR2(3), 
	"DOC_DATE_TIME" VARCHAR2(35), 
	"VALIDITY_DATE_TIME" VARCHAR2(35), 
	"DOC_DATE_FORMAT" VARCHAR2(3), 
	"VALIDITY_DATE_FORMAT" VARCHAR2(3), 
	"REF_NUM" VARCHAR2(3), 
	"REF_NUM_CODE" NUMBER(11,0), 
	"PARTY_CODE_BY" VARCHAR2(3), 
	"PARTY_CODE_SU" VARCHAR2(3), 
	"PARTY_IDE_NUM_BY" VARCHAR2(35), 
	"PARTY_IDE_NUM_SU" VARCHAR2(35), 
	"IDE_CODE_BY" VARCHAR2(3), 
	"IDE_CODE_SU" VARCHAR2(3), 
	"PRODUCT_GROUP_CODE" VARCHAR2(3), 
	"PRODUCT_GROUP" VARCHAR2(3), 
	"PRODUCT_GROUP_DES" VARCHAR2(35), 
	"CURRENCY_CODE" VARCHAR2(3), 
	"CURRENCY" VARCHAR2(3), 
	"CURRENCY_LIST" VARCHAR2(3), 
	"PARTY_BY_NAME" VARCHAR2(100), 
	"POST_BY_CODE" VARCHAR2(20), 
	"PARTY_SU_NAME" VARCHAR2(100), 
	"POST_SU_CODE" VARCHAR2(20), 
	"COUNTRY_CODE_BY" VARCHAR2(10), 
	"COUNTRY_CODE_SU" VARCHAR2(10), 
	"SEG_NUM" NUMBER(10,0), 
	"MSG_NUM" VARCHAR2(14)
   );
   
   
CREATE TABLE "EDIGATE"."EDI_PRICAT_ITEM" 
   (	"EDI_HEAD_ID" NUMBER(10,0), 
	"LINE_ITEM_NUM" NUMBER(6,0), 
	"ACTION_CODE" VARCHAR2(10), 
	"ITEM_ID" VARCHAR2(35), 
	"ITEM_ID_CODE" VARCHAR2(3), 
	"PRODUCT_IDE" VARCHAR2(3), 
	"ADDITIONAL_IDE" VARCHAR2(3), 
	"PRODUCT_IDE_NUM" VARCHAR2(35), 
	"ADDITIONAL_IDE_NUM" VARCHAR2(35), 
	"PRODUCT_IDE_CODE" VARCHAR2(3), 
	"ADDITIONAL_IDE_CODE" VARCHAR2(3), 
	"PRODUCT_IDE_CODE_LIST" VARCHAR2(3), 
	"ADDITIONAL_IDE_CODE_LIST" VARCHAR2(3), 
	"ORDER_CODE" VARCHAR2(3), 
	"DELIVER_CODE" VARCHAR2(3), 
	"ORDER_DATE" VARCHAR2(35), 
	"DELIVER_DATE" VARCHAR2(35), 
	"ORDER_DATE_FORMAT" VARCHAR2(3), 
	"DELIVER_DATE_FORMAT" VARCHAR2(3), 
	"TEXT_SUB_CODE" VARCHAR2(3), 
	"TEXT_FUNC_CODE" VARCHAR2(3), 
	"TEXT_LITE_CONTENT" VARCHAR2(70), 
	"AAA_VALUE" NUMBER(15,0), 
	"AAE_VALUE" NUMBER(15,0), 
	"AAB_VALUE" NUMBER(15,0), 
	"AAA_VALUE_TYPE" VARCHAR2(3), 
	"AAE_VALUE_TYPE" VARCHAR2(3), 
	"AAB_VALUE_TYPE" VARCHAR2(3), 
	"AAA_VALUE_CODE" VARCHAR2(3), 
	"AAE_VALUE_CODE" VARCHAR2(3), 
	"AAB_VALUE_CODE" VARCHAR2(3), 
	"ID" NUMBER(10,0), 
	"TEXT_LITE_PIC" VARCHAR2(100)
   );
   
CREATE TABLE "EDIGATE"."EDI_PRICAT_ITEM_DESC" 
   (	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"ITEM_ID" VARCHAR2(35), 
	"ITEM_DESC_FORMAT" VARCHAR2(3), 
	"ITEM_DESC_CODE" VARCHAR2(3), 
	"ITEM_DESC_IDE_CODE" VARCHAR2(17), 
	"ITEM_DESC_IDE_CODELIST" VARCHAR2(3), 
	"ITEM_DESC" VARCHAR2(35)
   );


CREATE SEQUENCE  "EDIGATE"."SEQ_EDI_PRICAT_HEAD_ID"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "EDIGATE"."SEQ_EDI_PRICAT_ITEM_ID"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "EDIGATE"."SEQ_EDI_PRICAT_ITEM_DESC_ID"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 321 CACHE 20 NOORDER  NOCYCLE ;