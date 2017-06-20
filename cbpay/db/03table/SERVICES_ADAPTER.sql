-- Create table
create table SERVICES_ADAPTER
(
  systemid    VARCHAR2(50) not null,
  trancode    VARCHAR2(50) not null,
  processlist VARCHAR2(1024),
  owner       VARCHAR2(20)
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
comment on column SERVICES_ADAPTER.systemid
  is 'ϵͳid';
comment on column SERVICES_ADAPTER.trancode
  is '������';
comment on column SERVICES_ADAPTER.processlist
  is '�����б�';
comment on column SERVICES_ADAPTER.owner
  is '�������� IN:in���� OUT:out���� DISCENTER:DISCENTER����';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SERVICES_ADAPTER
  add constraint SERVICES_ADAPTER_PK primary key (SYSTEMID, TRANCODE)
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
