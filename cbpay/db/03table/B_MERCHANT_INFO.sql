-- Create table
create table B_MERCHANT_INFO
(
  merchantid                  VARCHAR2(16) not null,
  merchantname                VARCHAR2(50),
  merchantdesc                VARCHAR2(200),
  isencrypt                   VARCHAR2(2),
  encrypt_algorithm           VARCHAR2(20),
  issignature                 VARCHAR2(2),
  signature_algorithm         VARCHAR2(20),
  public_key_file             VARCHAR2(100),
  key_file_type               VARCHAR2(2),
  encrypt_type                VARCHAR2(2),
  signature_type              VARCHAR2(2),
  key_store_file              VARCHAR2(100),
  key_store_alias             VARCHAR2(20),
  key_store_password          VARCHAR2(20),
  country_code                CHAR(3),
  currency_type               CHAR(3),
  merplatacctalias            VARCHAR2(30),
  protocolno                  VARCHAR2(30),
  valid                       CHAR(1),
  poundageflag                CHAR(1),
  poundagerate                NUMBER(12,6),
  contract_no                 VARCHAR2(20),
  merchant_principal          VARCHAR2(20),
  principal_tel               VARCHAR2(20),
  payee_acct_no               VARCHAR2(34),
  address                     VARCHAR2(120),
  open_branch_id              VARCHAR2(14),
  bank_card_csttype           CHAR(2),
  bank_card_type              CHAR(2),
  local_currency_account_no   VARCHAR2(34),
  foreign_currency_account_no VARCHAR2(34),
  supplier_list               VARCHAR2(200)
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
comment on column B_MERCHANT_INFO.merchantid
  is '�̻�id';
comment on column B_MERCHANT_INFO.merchantname
  is '�̻�����';
comment on column B_MERCHANT_INFO.merchantdesc
  is '�̻�����';
comment on column B_MERCHANT_INFO.country_code
  is '�̻����ڹ���';
comment on column B_MERCHANT_INFO.currency_type
  is '�̻���ͨ�ı���';
comment on column B_MERCHANT_INFO.merplatacctalias
  is 'ƽ̨�������˻��˺ű������̻��ɿ�������˻�';
comment on column B_MERCHANT_INFO.protocolno
  is '�̻���ͨЭ����֤�����';
comment on column B_MERCHANT_INFO.valid
  is '�Ƿ���ã�0 �����ã�1 ʧЧ';
comment on column B_MERCHANT_INFO.poundageflag
  is '�����ѽ��ɷ��� 0�̻���1�û�  2 �̻���';
comment on column B_MERCHANT_INFO.poundagerate
  is '��������';
comment on column B_MERCHANT_INFO.contract_no
  is '��ͬ��';
comment on column B_MERCHANT_INFO.merchant_principal
  is '�̻�������';
comment on column B_MERCHANT_INFO.principal_tel
  is '�̻���������ϵ�绰';
comment on column B_MERCHANT_INFO.payee_acct_no
  is '�տ�˻�';
comment on column B_MERCHANT_INFO.address
  is '�̻����ڵ�ַ';
comment on column B_MERCHANT_INFO.open_branch_id
  is '�տ��˿�����BIC CODE';
comment on column B_MERCHANT_INFO.bank_card_csttype
  is '���пͻ����ͣ�00����˽��
01���Թ���
';
comment on column B_MERCHANT_INFO.bank_card_type
  is '�����˻����ͣ�01  ����ǿ� 02 ���ǿ� 03 ���� 99 ��ҵ�˻�';
comment on column B_MERCHANT_INFO.local_currency_account_no
  is '�̻��ڱ��׻�ͨ���ߵı��������˻�';
comment on column B_MERCHANT_INFO.foreign_currency_account_no
  is '�̻��ڱ��׻�ͨ���ߵ���������˻�';
comment on column B_MERCHANT_INFO.supplier_list
  is '�������б�';
-- Create/Recreate primary, unique and foreign key constraints 
alter table B_MERCHANT_INFO
  add constraint B_MERCHANT_INFO_PK primary key (MERCHANTID)
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
