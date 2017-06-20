-- Create table
create table B_CHECK_ERROR_LIST
(
  accountdate   VARCHAR2(8) not null,
  chkchnl       VARCHAR2(10) not null,
  orderid       VARCHAR2(64) not null,
  chnljnlno     VARCHAR2(32) not null,
  chkerrtyp     CHAR(1) not null,
  chkerrdealtyp VARCHAR2(2),
  chkerrdealsts CHAR(1),
  errcancelmark VARCHAR2(60),
  dealdate      VARCHAR2(8),
  stamp         TIMESTAMP(6),
  trandate      VARCHAR2(8) not null
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
comment on table B_CHECK_ERROR_LIST
  is '���˲���';
-- Add comments to the columns 
comment on column B_CHECK_ERROR_LIST.accountdate
  is 'ҵ������';
comment on column B_CHECK_ERROR_LIST.chkchnl
  is '��������';
comment on column B_CHECK_ERROR_LIST.orderid
  is '����id';
comment on column B_CHECK_ERROR_LIST.chnljnlno
  is '����������ˮ��';
comment on column B_CHECK_ERROR_LIST.chkerrtyp
  is '�������  0��״̬����
1��ƽ̨����
2��������������';
comment on column B_CHECK_ERROR_LIST.chkerrdealtyp
  is '����ʽ �ֶ�Ԥ��';
comment on column B_CHECK_ERROR_LIST.chkerrdealsts
  is '����״̬ 0��δ����
1���Ѵ���
2�����ȡ��';
comment on column B_CHECK_ERROR_LIST.errcancelmark
  is 'ȡ�����ԭ��';
comment on column B_CHECK_ERROR_LIST.dealdate
  is '��������';
comment on column B_CHECK_ERROR_LIST.trandate
  is '��������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table B_CHECK_ERROR_LIST
  add constraint B_CHECK_ERROR_LIST_PK primary key (ACCOUNTDATE, CHKCHNL, ORDERID, CHNLJNLNO, CHKERRTYP)
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
