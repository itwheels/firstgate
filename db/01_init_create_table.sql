alter session set current_schema=edigate;
-- 创建批处理日志表
create table LOG_BATCH
(
  ID            NUMBER(10) not null,
  TYPE          NUMBER(4),
  START_DTE     TIMESTAMP(6),
  END_DTE       TIMESTAMP(6),
  PROC_CONT     NUMBER(10),
  STATUS        NUMBER(1),
  TOT_CONT      NUMBER(10),
  RPT_START_DTE TIMESTAMP(6),
  RPT_END_DTE   TIMESTAMP(6)
);
-- Add comments to the table 
comment on table LOG_BATCH
  is '批处理日志表';
-- Add comments to the columns 
comment on column LOG_BATCH.TYPE
  is '批处理类型 1: slsrpt; 2: invrpt; 3:pricat';
comment on column LOG_BATCH.START_DTE
  is '开始时间';
comment on column LOG_BATCH.END_DTE
  is '结束时间';
comment on column LOG_BATCH.PROC_CONT
  is '已处理数量';
comment on column LOG_BATCH.STATUS
  is '批处理状态 1: running 0: completed';
comment on column LOG_BATCH.TOT_CONT
  is '需处理总量';
comment on column LOG_BATCH.RPT_START_DTE
  is '统计期初时间';
comment on column LOG_BATCH.RPT_END_DTE
  is '统计期末时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table LOG_BATCH
  add constraint PK_BATCH_ID primary key (ID);

-- 创建接口供应商/店仓过滤控制表
create table EDI_OUTLIMIT
(
  ID            NUMBER(10) not null,
  TYPE          VARCHAR2(20),
  CUSTOMER_CODE VARCHAR2(80),
  STORE_CODE    VARCHAR2(80)
);
-- Add comments to the columns 
comment on column EDI_OUTLIMIT.TYPE
  is '类型：slsrpt/invrpt';
comment on column EDI_OUTLIMIT.CUSTOMER_CODE
  is '经销商';
comment on column EDI_OUTLIMIT.STORE_CODE
  is '店仓';

alter table EDI_OUTLIMIT
  add constraint pk_outlimit_id primary key (ID);  
-- 创建价格列表头信息表
create table EDI_PRICAT_HEAD
(
  ID                    NUMBER(10) not null,
  MSG_REF_NUM           VARCHAR2(14),
  MSG_TYPE              VARCHAR2(6),
  MSG_VER_NUM           VARCHAR2(3),
  MSG_RLS_NUM           VARCHAR2(3),
  CONTROL_AGENCY        VARCHAR2(2),
  ASSOCIATE_ASSIGN_CODE VARCHAR2(6),
  DOC_NAME              VARCHAR2(3),
  DOC_NUM               VARCHAR2(35),
  MSG_FUNC              VARCHAR2(3),
  DOC_DATE              VARCHAR2(3),
  VALIDITY_DATE         VARCHAR2(3),
  DOC_DATE_TIME         VARCHAR2(35),
  VALIDITY_DATE_TIME    VARCHAR2(35),
  DOC_DATE_FORMAT       VARCHAR2(3),
  VALIDITY_DATE_FORMAT  VARCHAR2(3),
  REF_NUM               VARCHAR2(3),
  REF_NUM_CODE          NUMBER(11),
  PARTY_CODE_BY         VARCHAR2(3),
  PARTY_CODE_SU         VARCHAR2(3),
  PARTY_IDE_NUM_BY      VARCHAR2(35),
  PARTY_IDE_NUM_SU      VARCHAR2(35),
  IDE_CODE_BY           VARCHAR2(3),
  IDE_CODE_SU           VARCHAR2(3),
  PRODUCT_GROUP_CODE    VARCHAR2(3),
  PRODUCT_GROUP         VARCHAR2(3),
  PRODUCT_GROUP_DES     VARCHAR2(35),
  CURRENCY_CODE         VARCHAR2(3),
  CURRENCY              VARCHAR2(3),
  CURRENCY_LIST         VARCHAR2(3),
  PARTY_BY_NAME         VARCHAR2(100),
  POST_BY_CODE          VARCHAR2(20),
  PARTY_SU_NAME         VARCHAR2(100),
  POST_SU_CODE          VARCHAR2(20),
  COUNTRY_CODE_BY       VARCHAR2(10),
  COUNTRY_CODE_SU       VARCHAR2(10),
  SEG_NUM               NUMBER(10),
  MSG_NUM               VARCHAR2(14)
);
-- Add comments to the table 
comment on table EDI_PRICAT_HEAD
  is '价格列表头信息';
-- Add comments to the columns 
comment on column EDI_PRICAT_HEAD.MSG_REF_NUM
  is '唯一信息编号';
comment on column EDI_PRICAT_HEAD.MSG_TYPE
  is '消息类型。取值为：PRICAT = Price catalogue message';
comment on column EDI_PRICAT_HEAD.MSG_VER_NUM
  is '消息版本号。';
comment on column EDI_PRICAT_HEAD.MSG_RLS_NUM
  is '消息发布号。';
comment on column EDI_PRICAT_HEAD.CONTROL_AGENCY
  is '控制代理。';
comment on column EDI_PRICAT_HEAD.ASSOCIATE_ASSIGN_CODE
  is '关联指定代码。';
comment on column EDI_PRICAT_HEAD.DOC_NAME
  is '由代码表述的文档或者消息标识。';
comment on column EDI_PRICAT_HEAD.DOC_NUM
  is '由文档/消息发布者指定的编号。';
comment on column EDI_PRICAT_HEAD.MSG_FUNC
  is '表示消息功能的代码。例如：2 = Addition';
comment on column EDI_PRICAT_HEAD.DOC_DATE
  is '文档/消息生成时间代码。取值为：137 = Document/message date/time';
comment on column EDI_PRICAT_HEAD.VALIDITY_DATE
  is '文档有效期起止时间。';
comment on column EDI_PRICAT_HEAD.DOC_DATE_TIME
  is '文档生成时间。';
comment on column EDI_PRICAT_HEAD.VALIDITY_DATE_TIME
  is '文档有效时段。';
comment on column EDI_PRICAT_HEAD.DOC_DATE_FORMAT
  is '文档生成时间格式。取值为：203 = CCYYMMDDHHMM';
comment on column EDI_PRICAT_HEAD.VALIDITY_DATE_FORMAT
  is '文档有效期时间格式。取值为：718 = CCYYMMDD-CCYYMMDD';
comment on column EDI_PRICAT_HEAD.REF_NUM
  is '编号代码。取值为：ZZZ';
comment on column EDI_PRICAT_HEAD.REF_NUM_CODE
  is '有效季节。';
comment on column EDI_PRICAT_HEAD.PARTY_CODE_BY
  is '购买方。BY = Buyer';
comment on column EDI_PRICAT_HEAD.PARTY_CODE_SU
  is '提供方。SU = Supplier';
comment on column EDI_PRICAT_HEAD.PARTY_IDE_NUM_BY
  is '标识BY的唯一代码。';
comment on column EDI_PRICAT_HEAD.PARTY_IDE_NUM_SU
  is '标识SU的唯一代码。';
comment on column EDI_PRICAT_HEAD.IDE_CODE_BY
  is '购买方代码识别机构负责列表。';
comment on column EDI_PRICAT_HEAD.IDE_CODE_SU
  is '提供方代码识别机构负责列表。';
comment on column EDI_PRICAT_HEAD.PRODUCT_GROUP_CODE
  is '产品组代码。';
comment on column EDI_PRICAT_HEAD.PRODUCT_GROUP
  is '销售者提供的产品组唯一代码。';
comment on column EDI_PRICAT_HEAD.PRODUCT_GROUP_DES
  is '产品组描述（类别）。';
comment on column EDI_PRICAT_HEAD.CURRENCY_CODE
  is '币种编号。';
comment on column EDI_PRICAT_HEAD.CURRENCY
  is '使用的币种。';
comment on column EDI_PRICAT_HEAD.CURRENCY_LIST
  is '价格列表币种。';
comment on column EDI_PRICAT_HEAD.PARTY_BY_NAME
  is '购买方的名称';
comment on column EDI_PRICAT_HEAD.POST_BY_CODE
  is '购买方邮政编码';
comment on column EDI_PRICAT_HEAD.PARTY_SU_NAME
  is '提供方的名称';
comment on column EDI_PRICAT_HEAD.POST_SU_CODE
  is '提供方邮政编码';
comment on column EDI_PRICAT_HEAD.COUNTRY_CODE_BY
  is '购买方国家代码';
comment on column EDI_PRICAT_HEAD.COUNTRY_CODE_SU
  is '提供方国家代码';
comment on column EDI_PRICAT_HEAD.SEG_NUM
  is '消息段数量。';
comment on column EDI_PRICAT_HEAD.MSG_NUM
  is '与MSG_REF_NUM字段取值相同。';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EDI_PRICAT_HEAD
  add constraint PK_EDI_PRICAT_HEAD primary key (ID);

-- 创建价格列表item信息
create table EDI_PRICAT_ITEM
(
  EDI_HEAD_ID              NUMBER(10),
  LINE_ITEM_NUM            NUMBER(6),
  ACTION_CODE              VARCHAR2(10),
  ITEM_ID                  VARCHAR2(35),
  ITEM_ID_CODE             VARCHAR2(3),
  PRODUCT_IDE              VARCHAR2(3),
  ADDITIONAL_IDE           VARCHAR2(3),
  PRODUCT_IDE_NUM          VARCHAR2(35),
  ADDITIONAL_IDE_NUM       VARCHAR2(35),
  PRODUCT_IDE_CODE         VARCHAR2(3),
  ADDITIONAL_IDE_CODE      VARCHAR2(3),
  PRODUCT_IDE_CODE_LIST    VARCHAR2(3),
  ADDITIONAL_IDE_CODE_LIST VARCHAR2(3),
  ORDER_CODE               VARCHAR2(3),
  DELIVER_CODE             VARCHAR2(3),
  ORDER_DATE               VARCHAR2(35),
  DELIVER_DATE             VARCHAR2(35),
  ORDER_DATE_FORMAT        VARCHAR2(3),
  DELIVER_DATE_FORMAT      VARCHAR2(3),
  TEXT_SUB_CODE            VARCHAR2(3),
  TEXT_FUNC_CODE           VARCHAR2(3),
  TEXT_LITE_CONTENT        VARCHAR2(70),
  AAA_VALUE                NUMBER(15),
  AAE_VALUE                NUMBER(15),
  AAB_VALUE                NUMBER(15),
  AAA_VALUE_TYPE           VARCHAR2(3),
  AAE_VALUE_TYPE           VARCHAR2(3),
  AAB_VALUE_TYPE           VARCHAR2(3),
  AAA_VALUE_CODE           VARCHAR2(3),
  AAE_VALUE_CODE           VARCHAR2(3),
  AAB_VALUE_CODE           VARCHAR2(3),
  ID                       NUMBER(10) not null,
  TEXT_LITE_PIC            VARCHAR2(100)
);
-- Add comments to the table 
comment on table EDI_PRICAT_ITEM
  is '价格列表行信息';
-- Add comments to the columns 
comment on column EDI_PRICAT_ITEM.EDI_HEAD_ID
  is '商品所属head的id。';
comment on column EDI_PRICAT_ITEM.LINE_ITEM_NUM
  is '商品在head中的编号。';
comment on column EDI_PRICAT_ITEM.ACTION_CODE
  is '动作请求代码。取值为：1 = added。';
comment on column EDI_PRICAT_ITEM.ITEM_ID
  is '商品唯一码（条形码）。';
comment on column EDI_PRICAT_ITEM.ITEM_ID_CODE
  is '商品唯一码类型。';
comment on column EDI_PRICAT_ITEM.PRODUCT_IDE
  is '商品标识代码。';
comment on column EDI_PRICAT_ITEM.PRODUCT_IDE_NUM
  is '由商品提供者分配的代码(货号）。';
comment on column EDI_PRICAT_ITEM.PRODUCT_IDE_CODE
  is '由商品提供者分配的代码类型。';
comment on column EDI_PRICAT_ITEM.PRODUCT_IDE_CODE_LIST
  is '由商品提供者分配的代码列表。';
comment on column EDI_PRICAT_ITEM.ORDER_CODE
  is '订货时间代码。';
comment on column EDI_PRICAT_ITEM.DELIVER_CODE
  is '交付时间代码。';
comment on column EDI_PRICAT_ITEM.ORDER_DATE
  is '订货时间。';
comment on column EDI_PRICAT_ITEM.DELIVER_DATE
  is '交货时间。';
comment on column EDI_PRICAT_ITEM.ORDER_DATE_FORMAT
  is '订货时间格式。';
comment on column EDI_PRICAT_ITEM.DELIVER_DATE_FORMAT
  is '交货时间格式。';
comment on column EDI_PRICAT_ITEM.TEXT_SUB_CODE
  is '文本主题代码。取值为：ZZZ。';
comment on column EDI_PRICAT_ITEM.TEXT_FUNC_CODE
  is '文本功能代码。';
comment on column EDI_PRICAT_ITEM.TEXT_LITE_CONTENT
  is '文本描述内容。';
comment on column EDI_PRICAT_ITEM.AAA_VALUE
  is '商品净价（不含税价）。';
comment on column EDI_PRICAT_ITEM.AAE_VALUE
  is '商品原价。';
comment on column EDI_PRICAT_ITEM.AAB_VALUE
  is '商品价格（含税价）。';
comment on column EDI_PRICAT_ITEM.AAA_VALUE_TYPE
  is '取值为：AAA。';
comment on column EDI_PRICAT_ITEM.AAE_VALUE_TYPE
  is '取值为：AAE。';
comment on column EDI_PRICAT_ITEM.AAB_VALUE_TYPE
  is '取值为：AAB。';
comment on column EDI_PRICAT_ITEM.AAA_VALUE_CODE
  is '取值为：CA = Catalogue';
comment on column EDI_PRICAT_ITEM.AAE_VALUE_CODE
  is '取值为：CA = Catalogue';
comment on column EDI_PRICAT_ITEM.AAB_VALUE_CODE
  is '取值为：CA = Catalogue';
comment on column EDI_PRICAT_ITEM.TEXT_LITE_PIC
  is '链接';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EDI_PRICAT_ITEM
  add constraint PK_EDI_PRICAT_ITEM primary key (ID);

-- 创建价格列表item描述信息表
create table EDI_PRICAT_ITEM_DESC
(
  ID                 NUMBER(10) not null,
  ITEM_ID            NUMBER(10),
  FORMAT             VARCHAR2(3),
  TYPE               VARCHAR2(3),
  DESC_ID            VARCHAR2(17),
  CODELIST_QUALIFIER VARCHAR2(3),
  CODELIST_AGENCY    VARCHAR2(3),
  DESCTXT1           VARCHAR2(35),
  DESCTXT2           VARCHAR2(35)
);
-- Add comments to the table 
comment on table EDI_PRICAT_ITEM_DESC
  is '价格列表行商品描述';
-- Add comments to the columns 
comment on column EDI_PRICAT_ITEM_DESC.FORMAT
  is '商品描述格式， 取值：C=代码，来自于行业代码表；S=结构化，来自于行业代码表；F=自由文本描述';
comment on column EDI_PRICAT_ITEM_DESC.TYPE
  is '商品描述类型，取值：13 = 质量级别，35 = 颜色，98 = 尺寸，5 = 应用领域，例如户外/室内，TPE = Product type (EAN) 产品类型，STE = Model/design (EAN) 款式设计，BRN = Product name (EAN) 产品名称，DSC = description (EAN) 产品描述文本，MNF = Manufacturer (EAN) 制造商，EN = EAN ，TDS = Technical description (EAN) 技术描述，TGA = target area/age group	应用领域或者应用年龄段, XX9 = 性别';
comment on column EDI_PRICAT_ITEM_DESC.DESC_ID
  is '商品特性描述代码，一般来自于某些行业代码表';
comment on column EDI_PRICAT_ITEM_DESC.CODELIST_QUALIFIER
  is '1131';
comment on column EDI_PRICAT_ITEM_DESC.CODELIST_AGENCY
  is '代码表机构，取值：91 = Assigned by seller 销售商分配编码，9 = EAN编码，XXX= additional ID for product grouping 产品组其他的ID';
comment on column EDI_PRICAT_ITEM_DESC.DESCTXT1
  is '商品文字描述1';
comment on column EDI_PRICAT_ITEM_DESC.DESCTXT2
  is '商品文字描述2';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EDI_PRICAT_ITEM_DESC
  add constraint PK_EDI_PRICAT_ITEM_DESC primary key (ID);

-- 创建管理控制台用户表
create table AD_USER
(
  ID            NUMBER(19) not null,
  LOGIN_NAME    VARCHAR2(64) not null,
  NAME          VARCHAR2(64) not null,
  PASSWORD      VARCHAR2(255) not null,
  SALT          VARCHAR2(64) not null,
  ROLES         VARCHAR2(255) not null,
  REGISTER_DATE DATE not null
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table AD_USER
  add primary key (ID);
alter table AD_USER
  add unique (LOGIN_NAME);
  
  
  -- Create sequence 
CREATE SEQUENCE  SEQ_BATCH_ID  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 100 CACHE 20 NOORDER  NOCYCLE ;

CREATE SEQUENCE  SEQ_INTERCHANGE_ID  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;

-- Create sequence 
create sequence SEQ_EDI_OUTLIMIT_ID
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

-- Create sequence 
create sequence AD_SEQ_USER
minvalue 1
maxvalue 9999999999
start with 100
increment by 1
cache 20;

-- Create sequence 
create sequence SEQ_EDI_PRICAT_HEAD_ID
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
-- Create sequence 
create sequence SEQ_EDI_PRICAT_ITEM_ID
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
-- Create sequence 
create sequence SEQ_EDI_PRICAT_ITEM_DESC_ID
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;