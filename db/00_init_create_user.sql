drop user edigate cascade;
create user edigate identified by edi_123;
grant create session to edigate;
grant resource to edigate;
grant create any view to edigate;
grant select on neands3.m_retail to edigate;
grant select on neands3.m_retailitem to edigate;
grant select on neands3.m_product_alias to edigate;
grant select on neands3.m_product to edigate;
grant select on neands3.c_store to edigate;
grant select on neands3.fa_storage  to edigate;
grant select on neands3.c_customer  to edigate;