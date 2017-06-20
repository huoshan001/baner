-- Create table
create table B_VERIFICATION_CODE
(
  verjnlno  VARCHAR2(32) not null,
  mblno     VARCHAR2(11) not null,
  verchnl   VARCHAR2(5),
  verbiztyp VARCHAR2(2) not null,
  vercode   VARCHAR2(8),
  prdtime   VARCHAR2(6),
  status    VARCHAR2(1) not null,
  tmsmp     VARCHAR2(14),
  prddate   VARCHAR2(8)
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
comment on table B_VERIFICATION_CODE
  is '��֤����Ϣ��';
-- Add comments to the columns 
comment on column B_VERIFICATION_CODE.verjnlno
  is '��֤ҵ����ˮ��';
comment on column B_VERIFICATION_CODE.mblno
  is '�ֻ���';
comment on column B_VERIFICATION_CODE.verchnl
  is '������֤����';
comment on column B_VERIFICATION_CODE.verbiztyp
  is '��֤ҵ������ ��1���� ��2���� ��ȱʧ״̬�±�ʾ����';
comment on column B_VERIFICATION_CODE.vercode
  is '��֤��';
comment on column B_VERIFICATION_CODE.prdtime
  is '��֤������ʱ��';
comment on column B_VERIFICATION_CODE.status
  is '��Ч��־ 0 ��Ч �� 1 ʧЧ ��';
comment on column B_VERIFICATION_CODE.tmsmp
  is 'ʱ���';
comment on column B_VERIFICATION_CODE.prddate
  is '��֤����������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table B_VERIFICATION_CODE
  add primary key (VERJNLNO)
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
