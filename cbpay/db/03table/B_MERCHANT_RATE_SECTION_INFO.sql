-- Create table
create table B_MERCHANT_RATE_SECTION_INFO
(
  merchantno     VARCHAR2(30) not null,
  ruleid         VARCHAR2(24) not null,
  begininput     NUMBER(20),
  endinput       NUMBER(20),
  secchargemode  VARCHAR2(1),
  secchargeamt   NUMBER(14,2),
  secchargeratio INTEGER,
  tmsmp          VARCHAR2(14)
)
tablespace CBPAY_JOURNAL
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
comment on column B_MERCHANT_RATE_SECTION_INFO.merchantno
  is '�̻�id';
comment on column B_MERCHANT_RATE_SECTION_INFO.ruleid
  is '����ID ';
comment on column B_MERCHANT_RATE_SECTION_INFO.begininput
  is '��ʼ�������� ';
comment on column B_MERCHANT_RATE_SECTION_INFO.endinput
  is '������������ ';
comment on column B_MERCHANT_RATE_SECTION_INFO.secchargemode
  is '�����շ�ģʽ ';
comment on column B_MERCHANT_RATE_SECTION_INFO.secchargeamt
  is '�����շѽ�� ';
comment on column B_MERCHANT_RATE_SECTION_INFO.secchargeratio
  is '�����շѱ��� ';
comment on column B_MERCHANT_RATE_SECTION_INFO.tmsmp
  is 'ʱ���';
-- Create/Recreate primary, unique and foreign key constraints 
alter table B_MERCHANT_RATE_SECTION_INFO
  add constraint B_MERCHANT_RATE_DETAIL_PK primary key (MERCHANTNO, RULEID)
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
