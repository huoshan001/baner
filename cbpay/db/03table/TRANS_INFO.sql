-- Create table
create table TRANS_INFO
(
  trancode    VARCHAR2(15) not null,
  trandesc    VARCHAR2(200) not null,
  tran_opr    VARCHAR2(50) not null,
  channel_diy VARCHAR2(100),
  overtime    VARCHAR2(10) not null,
  tranfrom    VARCHAR2(20) not null,
  tran_type   VARCHAR2(2),
  route_type  VARCHAR2(4)
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
comment on column TRANS_INFO.trancode
  is '������';
comment on column TRANS_INFO.trandesc
  is '��������';
comment on column TRANS_INFO.tran_opr
  is 'ҵ����';
comment on column TRANS_INFO.channel_diy
  is '�������⴦��';
comment on column TRANS_INFO.overtime
  is '���׳�ʱʱ��';
comment on column TRANS_INFO.tranfrom
  is '������Դ';
comment on column TRANS_INFO.tran_type
  is '��������  1.ͬ������    2.�첽����    3.��ʱ����Ӧ����';
comment on column TRANS_INFO.route_type
  is '·������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TRANS_INFO
  add constraint TRANS_INFO_PK primary key (TRANCODE, TRANFROM)
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
