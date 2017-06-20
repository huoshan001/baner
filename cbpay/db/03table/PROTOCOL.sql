-- Create table
create table PROTOCOL
(
  protocolid   VARCHAR2(50) not null,
  serverid     VARCHAR2(50) not null,
  xmlconf      VARCHAR2(600) not null,
  owner        VARCHAR2(20) not null,
  protocoltype VARCHAR2(50) not null
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
comment on column PROTOCOL.protocolid
  is 'Э��id';
comment on column PROTOCOL.serverid
  is 'ͨ��id';
comment on column PROTOCOL.xmlconf
  is 'Э������';
comment on column PROTOCOL.owner
  is '�������� IN   OUT  DISCENTER';
comment on column PROTOCOL.protocoltype
  is 'Э������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PROTOCOL
  add constraint PROTOCOL_PK primary key (PROTOCOLID)
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
