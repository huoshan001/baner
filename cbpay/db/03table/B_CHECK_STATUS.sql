-- Create table
create table B_CHECK_STATUS
(
  trandate    VARCHAR2(8) not null,
  accountdate VARCHAR2(8) not null,
  checktype   CHAR(1) not null,
  status      CHAR(1) not null,
  trantime    VARCHAR2(6) not null,
  filename    VARCHAR2(30),
  tmsmp       TIMESTAMP(6) not null,
  jnlno       VARCHAR2(40),
  checknl     CHAR(1),
  ishandled   CHAR(1)
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
comment on column B_CHECK_STATUS.trandate
  is '��������';
comment on column B_CHECK_STATUS.accountdate
  is '��������';
comment on column B_CHECK_STATUS.checktype
  is '�������� 0��֧����ˮ����   1���ʽ�ת���� 2��������ˮ���� 3�������ˮ����';
comment on column B_CHECK_STATUS.status
  is '���˽�� 0�����˿�ʼ 1�����˳ɹ� 2������ʧ��';
comment on column B_CHECK_STATUS.trantime
  is '����ʱ��';
comment on column B_CHECK_STATUS.filename
  is '�����ļ���';
comment on column B_CHECK_STATUS.tmsmp
  is 'ʱ���';
comment on column B_CHECK_STATUS.jnlno
  is 'ƽ̨��ˮ��';
comment on column B_CHECK_STATUS.checknl
  is '�������� 0 �����׻�ͨ 1:ƽ������';
comment on column B_CHECK_STATUS.ishandled
  is '�Ƿ���0��δ����1 �Ѵ���';
-- Create/Recreate primary, unique and foreign key constraints 
alter table B_CHECK_STATUS
  add constraint B_CHECK_STATUS_PK primary key (TRANDATE, CHECKTYPE,checknl)
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
