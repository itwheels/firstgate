-- 初始化批次数据
insert into LOG_BATCH (ID, TYPE, START_DTE, END_DTE, PROC_CONT, STATUS)
values (1, 1, to_timestamp('09-05-2015 23:59:59.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-05-2015 23:59:59.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into LOG_BATCH (ID, TYPE, START_DTE, END_DTE, PROC_CONT, STATUS)
values (2, 2, to_timestamp('09-05-2015 23:59:59.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-05-2015 23:59:59.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
commit;

-- 初始化slsrpt/invrpt供应商过滤配置
insert into EDI_OUTLIMIT (ID, TYPE, CUSTOMER_CODE, STORE_CODE)
values (1, 'slsrpt', '8800', 'all');
insert into EDI_OUTLIMIT (ID, TYPE, CUSTOMER_CODE, STORE_CODE)
values (2, 'invrpt', '8800', 'all');
commit;

-- 初始化用户数据
insert into AD_USER (ID, LOGIN_NAME, NAME, PASSWORD, SALT, ROLES, REGISTER_DATE)
values (1, 'admin', 'admin', '1d4bc060c60a48f4937fc4831ad85a1c073f9f5b', '955813d910ea4dcc', 'admin', to_date('28-04-2015 19:06:07', 'dd-mm-yyyy hh24:mi:ss'));
commit;