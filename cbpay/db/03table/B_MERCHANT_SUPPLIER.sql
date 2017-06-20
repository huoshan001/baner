-- Create table
create table B_MERCHANT_SUPPLIER
(
  merchantid     VARCHAR2(16) not null,
  supplierid     VARCHAR2(16) not null,
  name           VARCHAR2(50) not null,
  address        VARCHAR2(70) not null,
  account        VARCHAR2(34) not null,
  open_branch_id VARCHAR2(14) not null,
  clear_bank_bic VARCHAR2(14)
)
tablespace CBPAY_JOURNAL
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the columns 
comment on column B_MERCHANT_SUPPLIER.merchantid
  is '�̻�id';
comment on column B_MERCHANT_SUPPLIER.supplierid
  is '������id';
comment on column B_MERCHANT_SUPPLIER.name
  is '����������';
comment on column B_MERCHANT_SUPPLIER.address
  is '�տ��˵�ַ';
comment on column B_MERCHANT_SUPPLIER.account
  is '�տ����˻�';
comment on column B_MERCHANT_SUPPLIER.open_branch_id
  is '�տ��˿�����BIC CODE';
comment on column B_MERCHANT_SUPPLIER.clear_bank_bic
  is '�羳RMB�м���';
-- Create/Recreate primary, unique and foreign key constraints 
alter table B_MERCHANT_SUPPLIER
  add constraint B_MERCHANT_SUPPLIER_PK primary key (MERCHANTID, SUPPLIERID)
  using index 
  tablespace CBPAY_INDEX
  pctfree 10
  initrans 2
  maxtrans 255;
