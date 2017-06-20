--B_PAYMENT_FLOW  �������
begin
	--�������
	execute immediate'
	create table B_PAYMENT_FLOW
	(
	  payorderid     VARCHAR2(32) not null,
	  trandate       VARCHAR2(10),
	  trantime       VARCHAR2(10),
	  txnamt         NUMBER(17,2) not null,
	  currency       CHAR(3) not null,
	  merchantid     VARCHAR2(20),
	  orderid        VARCHAR2(30),
	  ordertime      VARCHAR2(20),
	  channel        VARCHAR2(10),
	  paytime        VARCHAR2(8),
	  paystatus      CHAR(2) not null,
	  purchaserid    VARCHAR2(64) not null,
	  checkingstatus CHAR(2) not null,
	  tmsmp          CHAR(14),
	  ordertdate     VARCHAR2(12),
	  paycard        VARCHAR2(30),
	  paydate        VARCHAR2(8)
	)
	tablespace CBPAY_JOURNAL
	PARTITION BY RANGE (trandate)
	(
		PARTITION B_PAYMENT_FLOW_'||to_char(sysdate+1*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+1*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_PAYMENT_FLOW_'||to_char(sysdate+2*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+2*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_PAYMENT_FLOW_'||to_char(sysdate+3*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+3*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_PAYMENT_FLOW_'||to_char(sysdate+4*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+4*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_PAYMENT_FLOW_'||to_char(sysdate+5*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+5*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_PAYMENT_FLOW_'||to_char(sysdate+6*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+6*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_PAYMENT_FLOW_'||to_char(sysdate+7*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+7*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_PAYMENT_FLOW_'||to_char(sysdate+8*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+8*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_PAYMENT_FLOW_'||to_char(sysdate+9*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+9*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_PAYMENT_FLOW_'||to_char(sysdate+10*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+10*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_PAYMENT_FLOW_MAX VALUES LESS THAN (MAXVALUE) tablespace CBPAY_JOURNAL
	)';
	--ע�����
	execute immediate 'comment on table B_PAYMENT_FLOW is ''����֧����ˮ��''';
	execute immediate 'comment on column B_PAYMENT_FLOW.payorderid is ''֧����ˮ��''';
	execute immediate 'comment on column B_PAYMENT_FLOW.trandate is ''����֧����ƽ̨����''';
	execute immediate 'comment on column B_PAYMENT_FLOW.trantime is ''����֧����ƽ̨ʱ��''';
	execute immediate 'comment on column B_PAYMENT_FLOW.txnamt is ''֧�����''';
	execute immediate 'comment on column B_PAYMENT_FLOW.currency is ''֧������''';
	execute immediate 'comment on column B_PAYMENT_FLOW.merchantid is ''�̻���''';
	execute immediate 'comment on column B_PAYMENT_FLOW.orderid is ''�̻�������''';
	execute immediate 'comment on column B_PAYMENT_FLOW.ordertime is ''�̻�����ʱ��''';
	execute immediate 'comment on column B_PAYMENT_FLOW.channel is ''����֧������''';
	execute immediate 'comment on column B_PAYMENT_FLOW.paytime is ''֧�����ʱ��''';
	execute immediate 'comment on column B_PAYMENT_FLOW.paystatus is ''֧��״̬,0�ȴ�֧����1֧�������У�2֧����ɣ�3��֧��ʧ��''';
	execute immediate 'comment on column B_PAYMENT_FLOW.purchaserid is ''�����߱�ʶ''';
	execute immediate 'comment on column B_PAYMENT_FLOW.checkingstatus is ''����״̬,0���ȴ����ˣ�1�����˽����У�2�����˳ɹ� ��3������ʧ��''';
	execute immediate 'comment on column B_PAYMENT_FLOW.tmsmp is ''ʱ���''';
	execute immediate 'comment on column B_PAYMENT_FLOW.ordertdate is ''�̻���������''';
	execute immediate 'comment on column B_PAYMENT_FLOW.paycard is ''�����߸����''';
	execute immediate 'comment on column B_PAYMENT_FLOW.paydate is ''֧���������''';
	

	--��������
	execute immediate '
		alter table B_PAYMENT_FLOW add constraint B_PAYMENT_FLOW_PK primary key (PAYORDERID)
		using index 
		tablespace CBPAY_INDEX
	';
end;
/
