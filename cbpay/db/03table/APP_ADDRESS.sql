-- Create table
create table APP_ADDRESS
(
  appid     VARCHAR2(15) not null,
  type      VARCHAR2(5) not null,
  url       VARCHAR2(500) not null,
  appstatus VARCHAR2(1) not null
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
comment on column APP_ADDRESS.appid
  is 'Ӧ�ñ�ʾ';
comment on column APP_ADDRESS.type
  is '����ӳ���ʾ��IN����
OUT�ӳ�
IO ����ӳ�
';
comment on column APP_ADDRESS.url
  is 'App��Ϣ�����ַ';
comment on column APP_ADDRESS.appstatus
  is 'App״̬ 1:����   0:����';
-- Create/Recreate primary, unique and foreign key constraints 
alter table APP_ADDRESS
  add constraint APP_ADDRESS_PK primary key (APPID, TYPE)
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
