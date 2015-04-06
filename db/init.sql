drop user edigate cascade;
create user edigate identified by edi_123;
grant create session to edigate;
grant resource to edigate;
grant create any view to edigate;

-- Create sequence 
CREATE SEQUENCE  "EDIGATE"."SEQ_BATCH_ID"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;

CREATE SEQUENCE  "EDIGATE"."SEQ_INTERCHANGE_ID"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;


-- Create table
-- Create table
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
