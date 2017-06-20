-- Create table
create table MERCHANT_CLEAR_STATUS
(
  merchantid VARCHAR2(50) not null,
  clear_date VARCHAR2(8) not null,
  clear_time VARCHAR2(6) not null,
  bus_date   VARCHAR2(8) not null,
  status     VARCHAR2(3) not null
)
tablespace CBPAY_JOURNAL
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the columns 
comment on column MERCHANT_CLEAR_STATUS.merchantid
  is '�̻�id';
comment on column MERCHANT_CLEAR_STATUS.clear_date
  is '�������ڣ�yyyyMMdd';
comment on column MERCHANT_CLEAR_STATUS.clear_time
  is '����ʱ�䣬HHmmss';
comment on column MERCHANT_CLEAR_STATUS.bus_date
  is 'ҵ�����ڣ�yyyyMMdd';
comment on column MERCHANT_CLEAR_STATUS.status
  is '����״̬��1:��ʼ����
2:���˳ɹ�
3:����ʧ��
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table MERCHANT_CLEAR_STATUS
  add constraint MERCHANT_CLEAR_STATUS_PK primary key (MERCHANTID, CLEAR_DATE)
  using index 
  tablespace CBPAY_INDEX
  pctfree 10
  initrans 2
  maxtrans 255;