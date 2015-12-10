-- Create sequence 
create sequence SEQ_PK_VOUCHER_ID
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

-- Create sequence 
create sequence SEQ_VOUCHER_ITEM_ID
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

-- Create table
create table VOUCHER
(
  vou_sid            VARCHAR2(20),
  sbs_no             VARCHAR2(10),
  store_no           VARCHAR2(10),
  vou_type           VARCHAR2(10),
  vou_class          VARCHAR2(10),
  vend_code          VARCHAR2(10),
  payee_code         VARCHAR2(10),
  station            VARCHAR2(10),
  workstation        VARCHAR2(10),
  orig_store_no      VARCHAR2(10),
  orig_station       VARCHAR2(10),
  po_no              VARCHAR2(15),
  pkg_no             VARCHAR2(15),
  cost_handling_code VARCHAR2(10),
  update_price_flag  VARCHAR2(10),
  use_vat            VARCHAR2(10),
  created_date       VARCHAR2(20),
  modified_date      VARCHAR2(20),
  cms                VARCHAR2(10),
  ws_seq_no          VARCHAR2(10),
  held               VARCHAR2(10),
  active             VARCHAR2(10),
  vend_invc_no       VARCHAR2(15),
  vend_invc_date     VARCHAR2(20),
  id                 NUMBER(10) not null,
  createdate         TIMESTAMP(6)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column VOUCHER.vou_sid
  is '单据编号，SAP出库单据号';
comment on column VOUCHER.sbs_no
  is '市场代码，
205， 用于表示上海
207，用于表示台湾
200，用于表示香港
204,  用于表示澳门';
comment on column VOUCHER.store_no
  is '门店代码';
comment on column VOUCHER.vou_type
  is '凭单类型， 固定值，0
';
comment on column VOUCHER.vou_class
  is '凭单分类，固定值，0
';
comment on column VOUCHER.vend_code
  is '供应商代码，固定值，01
';
comment on column VOUCHER.payee_code
  is '付款者代码，固定值，01
';
comment on column VOUCHER.station
  is '固定值，P
';
comment on column VOUCHER.workstation
  is '固定值，1
';
comment on column VOUCHER.orig_store_no
  is '门店代码
';
comment on column VOUCHER.orig_station
  is '固定值，P
';
comment on column VOUCHER.po_no
  is '原始订单号，对应于前面的m_transfer.docno
';
comment on column VOUCHER.pkg_no
  is '发货单号，用于标注于伯俊系统的出库单的运货单号
';
comment on column VOUCHER.cost_handling_code
  is '成本控制代码，固定值，0
';
comment on column VOUCHER.update_price_flag
  is '价格更新标志，固定值，0
';
comment on column VOUCHER.use_vat
  is '是否VAT发票，固定值，1
';
comment on column VOUCHER.created_date
  is '单据创建日期
';
comment on column VOUCHER.modified_date
  is '单据修改日期
';
comment on column VOUCHER.cms
  is '固定值，1
';
comment on column VOUCHER.ws_seq_no
  is '工作站流水号，固定值，1
';
comment on column VOUCHER.held
  is '单据冻结标志，固定值，0
';
comment on column VOUCHER.active
  is '单据激活标志，固定值，1
';
comment on column VOUCHER.vend_invc_no
  is '发票号，导入Burgeon供办公室人员参考
';
comment on column VOUCHER.vend_invc_date
  is '发票日期
';
comment on column VOUCHER.id
  is '主键';
comment on column VOUCHER.createdate
  is '生成时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table VOUCHER
  add constraint PK_VOUCHER_ID primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

-- Create table
create table VOUCHER_ITEM
(
  vou_sid    VARCHAR2(20),
  item_pos   VARCHAR2(20),
  item_sid   VARCHAR2(20),
  qty        VARCHAR2(20),
  orig_qty   VARCHAR2(20),
  price      VARCHAR2(20),
  cost       VARCHAR2(20),
  upc        VARCHAR2(20),
  id         NUMBER(10) not null,
  createdate TIMESTAMP(6)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column VOUCHER_ITEM.vou_sid
  is '单据编号，SAP出库单据号
';
comment on column VOUCHER_ITEM.item_pos
  is '行号，从1开始的流水号
';
comment on column VOUCHER_ITEM.item_sid
  is '商品条码
';
comment on column VOUCHER_ITEM.qty
  is '固定值，0
';
comment on column VOUCHER_ITEM.orig_qty
  is '发货数量
';
comment on column VOUCHER_ITEM.price
  is '商品零售价
';
comment on column VOUCHER_ITEM.cost
  is '成本
';
comment on column VOUCHER_ITEM.upc
  is '条码
';
comment on column VOUCHER_ITEM.createdate
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table VOUCHER_ITEM
  add constraint PK_VOUCHER_ITEM_ID primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


-- Create sequence 
create sequence SEQ_LOGPO
minvalue 1
maxvalue 9999999
start with 1
increment by 1
cache 20;

-- Create table
create table LOGPO
(
  id      NUMBER not null,
  po_sid  VARCHAR2(80),
  logdate VARCHAR2(80)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 8
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column LOGPO.po_sid
  is '订单号';
  
grant select on m_transfer to edigate;
grant select on c_store to edigate;
grant select on m_transferitem to edigate;
grant select on m_product_alias to edigate;
grant select on m_product to edigate;
grant select on m_attributesetinstance to edigate;
grant select on m_product to edigate;
grant select on m_dim to edigate;
