-- Create table
create table ROUT_TYPE
(
  routeres   VARCHAR2(100) not null,
  routetype  VARCHAR2(6) not null,
  routefield VARCHAR2(60),
  tran_opr   VARCHAR2(50) not null
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
comment on column ROUT_TYPE.routeres
  is '·�ɽ��  SERVER: ����ϵͳ   TRANS:����ϵͳ�ͽ�����    FIELDVAL:ĳ�ֶε�ֵ';
comment on column ROUT_TYPE.routetype
  is '·������  FTP: �ļ�����  RAT:����  MER:�̻�';
comment on column ROUT_TYPE.routefield
  is '·���ֶ�';
comment on column ROUT_TYPE.tran_opr
  is 'ҵ����';
