-- Create table
create table B_MERCHANT_RATE_INFO
(
  id              VARCHAR2(24) not null,
  merchantno      VARCHAR2(30) not null,
  roundingmode    VARCHAR2(1),
  chargemode      VARCHAR2(1),
  overstrategy    VARCHAR2(1),
  chargecycle     VARCHAR2(1),
  beginamt        NUMBER(16,2),
  endamt          NUMBER(16,2),
  trantype        VARCHAR2(10),
  trancode        VARCHAR2(6),
  paychannelcode  VARCHAR2(12),
  bankcardcsttype VARCHAR2(2),
  bankcardtype    VARCHAR2(2),
  bankcode        VARCHAR2(20),
  curtype         VARCHAR2(3),
  chargestatestr  VARCHAR2(300),
  state           VARCHAR2(1),
  weight          INTEGER,
  availbegintime  VARCHAR2(14),
  availendtime    VARCHAR2(14),
  secchargetype   VARCHAR2(1),
  tmsmp           VARCHAR2(14)
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
-- Add comments to the table 
comment on table B_MERCHANT_RATE_INFO
  is '�̻����������';
-- Add comments to the columns 
comment on column B_MERCHANT_RATE_INFO.id
  is '�շѹ���ID ';
comment on column B_MERCHANT_RATE_INFO.merchantno
  is '�̻��� ';
comment on column B_MERCHANT_RATE_INFO.roundingmode
  is '������� ';
comment on column B_MERCHANT_RATE_INFO.chargemode
  is '�շ�ģʽ ';
comment on column B_MERCHANT_RATE_INFO.overstrategy
  is '�������ݺ��շѰ취';
comment on column B_MERCHANT_RATE_INFO.chargecycle
  is '�շ����� ';
comment on column B_MERCHANT_RATE_INFO.beginamt
  is '���׽�� ';
comment on column B_MERCHANT_RATE_INFO.endamt
  is '�ⶥ��� ';
comment on column B_MERCHANT_RATE_INFO.trantype
  is '�������� ';
comment on column B_MERCHANT_RATE_INFO.trancode
  is '������ ';
comment on column B_MERCHANT_RATE_INFO.paychannelcode
  is '֧��������� ';
comment on column B_MERCHANT_RATE_INFO.bankcardcsttype
  is '���пͻ����� ';
comment on column B_MERCHANT_RATE_INFO.bankcardtype
  is '�����˻����� ';
comment on column B_MERCHANT_RATE_INFO.bankcode
  is '���б��� ';
comment on column B_MERCHANT_RATE_INFO.curtype
  is '���� ';
comment on column B_MERCHANT_RATE_INFO.chargestatestr
  is '�շ�״̬ ';
comment on column B_MERCHANT_RATE_INFO.state
  is '����״̬ ';
comment on column B_MERCHANT_RATE_INFO.weight
  is 'Ȩ�� ';
comment on column B_MERCHANT_RATE_INFO.availbegintime
  is '��Ч��ʼʱ�� ';
comment on column B_MERCHANT_RATE_INFO.availendtime
  is '��Ч����ʱ�� ';
comment on column B_MERCHANT_RATE_INFO.secchargetype
  is '�����շ����� ';
comment on column B_MERCHANT_RATE_INFO.tmsmp
  is 'ʱ���';
-- Create/Recreate primary, unique and foreign key constraints 
alter table B_MERCHANT_RATE_INFO
  add constraint B_MERCHANT_RATE_PK primary key (MERCHANTNO, ID)
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
