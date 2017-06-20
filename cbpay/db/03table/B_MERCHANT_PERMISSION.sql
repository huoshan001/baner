-- Create table
create table B_MERCHANT_PERMISSION
(
  merchantid VARCHAR2(64),
  trancode   VARCHAR2(16),
  valid      CHAR(1)
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
comment on column B_MERCHANT_PERMISSION.merchantid
  is '�̻���';
comment on column B_MERCHANT_PERMISSION.trancode
  is 'ҵ����';
comment on column B_MERCHANT_PERMISSION.valid
  is '��Ч��ʾ 0��Ч 1��Ч';
