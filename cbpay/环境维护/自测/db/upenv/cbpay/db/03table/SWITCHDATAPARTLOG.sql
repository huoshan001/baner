-- Create table
create table SWITCHDATAPARTLOG
(
  platdate    VARCHAR2(8) not null,
  tablename   VARCHAR2(50) not null,
  datapartnew VARCHAR2(50),
  datapartold VARCHAR2(50),
  status      VARCHAR2(200)
)
tablespace USERS
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
comment on column SWITCHDATAPARTLOG.platdate
  is 'ƽ̨����';
comment on column SWITCHDATAPARTLOG.tablename
  is '������';
comment on column SWITCHDATAPARTLOG.datapartnew
  is '�·�������';
comment on column SWITCHDATAPARTLOG.datapartold
  is '�Ϸ�������';
comment on column SWITCHDATAPARTLOG.status
  is '״̬����';
