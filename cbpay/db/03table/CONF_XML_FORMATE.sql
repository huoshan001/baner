-- Create table
create table CONF_XML_FORMATE
(
  trancode VARCHAR2(15) not null,
  systemid VARCHAR2(50) not null,
  path     VARCHAR2(100) not null,
  type     VARCHAR2(2) not null,
  owner    VARCHAR2(20)
)
tablespace CBPAY_CONF
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column CONF_XML_FORMATE.trancode
  is '������';
comment on column CONF_XML_FORMATE.systemid
  is 'ϵͳid';
comment on column CONF_XML_FORMATE.path
  is '���屨�ĸ�ʽ��xml�ļ�����';
comment on column CONF_XML_FORMATE.type
  is '���ͣ�0��� 1���';
comment on column CONF_XML_FORMATE.owner
  is '�������� IN   OUT  DISCENTER';
-- Create/Recreate primary, unique and foreign key constraints 
alter table CONF_XML_FORMATE
  add constraint CONF_XML_FORMATE_PK primary key (TRANCODE, SYSTEMID, TYPE)
  using index 
  tablespace CBPAY_INDEX
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
