--B_ACCOUNT_TRANSFER_FLOW  �������
begin
	--�������
	execute immediate'
	create table B_ACCOUNT_TRANSFER_FLOW
	(
	  transferbatchno VARCHAR2(32) not null,
	  txndate         VARCHAR2(8) not null,
	  txntime         VARCHAR2(6) not null,
	  currency        VARCHAR2(3) not null,
	  txnamt          NUMBER(17,2) not null,
	  payaccountno    VARCHAR2(50) not null,
	  transferflg     VARCHAR2(1),
	  acttrafstatus   VARCHAR2(2),
	  paytxndate      VARCHAR2(8),
	  paytxntime      VARCHAR2(6),
	  buybatno        VARCHAR2(32),
	  paybatno        VARCHAR2(32),
	  tmsmp           VARCHAR2(14),
	  checkingstatus  VARCHAR2(2),
	  orderid         VARCHAR2(64),
	  bak             VARCHAR2(250),
	  exrate          NUMBER(12,8),
	  recaccountno    VARCHAR2(50) not null,
	  transtype       VARCHAR2(2),
	  transferflowno  VARCHAR2(32) not null,
	  paycustomerno   VARCHAR2(50),
	  reccustomerno   VARCHAR2(50)
	)
	tablespace CBPAY_JOURNAL
	PARTITION BY RANGE (txndate)
	(
		PARTITION B_ACCOUNT_TRANSFER_FLOW_'||to_char(sysdate+1*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+1*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_ACCOUNT_TRANSFER_FLOW_'||to_char(sysdate+2*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+2*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_ACCOUNT_TRANSFER_FLOW_'||to_char(sysdate+3*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+3*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_ACCOUNT_TRANSFER_FLOW_'||to_char(sysdate+4*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+4*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_ACCOUNT_TRANSFER_FLOW_'||to_char(sysdate+5*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+5*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_ACCOUNT_TRANSFER_FLOW_'||to_char(sysdate+6*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+6*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_ACCOUNT_TRANSFER_FLOW_'||to_char(sysdate+7*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+7*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_ACCOUNT_TRANSFER_FLOW_'||to_char(sysdate+8*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+8*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_ACCOUNT_TRANSFER_FLOW_'||to_char(sysdate+9*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+9*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_ACCOUNT_TRANSFER_FLOW_'||to_char(sysdate+10*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+10*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_ACCOUNT_TRANSFER_FLOW_MAX VALUES LESS THAN (MAXVALUE) tablespace CBPAY_JOURNAL
	)';
	--ע�����
	execute immediate 'comment on table B_ACCOUNT_TRANSFER_FLOW is ''�˻���ת��ˮ��''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.transferbatchno is ''�˻���ת���κ�''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.txndate is ''ƽ̨����''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.txntime is ''ƽ̨ʱ��''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.currency is ''����''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.txnamt is ''���''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.payaccountno is ''�����˻���''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.transferflg is ''ת��ת����־''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.acttrafstatus is ''�˻���ת״̬,00,��ʼ״̬��01�������У�02����ת�ɹ���03 ��תʧ��''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.paytxndate is ''���׻�ͨ��������''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.paytxntime is ''���׻�ͨ����ʱ��''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.buybatno is ''�������κ�''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.paybatno is ''�������κ�''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.tmsmp is ''ʱ���''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.checkingstatus is ''����״̬ 00���ȴ����ˣ�01 �����ڶ��ˣ�02 �����˳ɹ���03������ʧ��''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.orderid is ''������''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.bak is ''��ע''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.exrate is ''����''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.recaccountno is ''�տ��˺�''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.transtype is ''��ת���ͣ�01������ǰ��ת��02�����ת;03 ����ǰ��ת''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.transferflowno is ''�˻���ת��ˮ��''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.paycustomerno is ''����ͻ���''';
	execute immediate 'comment on column B_ACCOUNT_TRANSFER_FLOW.reccustomerno is ''�տ�ͻ���''';

	--��������
	execute immediate '
		alter table B_ACCOUNT_TRANSFER_FLOW add constraint B_ACCOUNT_TRANSFER_FLOW_PK primary key (TRANSFERBATCHNO, TRANSFERFLOWNO)
		using index 
		tablespace CBPAY_INDEX
	';
end;
/
