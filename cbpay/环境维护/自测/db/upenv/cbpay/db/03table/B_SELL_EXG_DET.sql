--B_SELL_EXG_DET  �������
begin
	--�������
	execute immediate'
	create table B_SELL_EXG_DET
	(
	  paybatno         VARCHAR2(32) not null,
	  paydate          VARCHAR2(8) not null,
	  pay_seqno        VARCHAR2(32) not null,
	  merchantid       VARCHAR2(16) not null,
	  payerid          VARCHAR2(32) not null,
	  payeraccount     VARCHAR2(32) not null,
	  payername        VARCHAR2(128) not null,
	  remit_ccy        CHAR(3) not null,
	  remit_amt        NUMBER(17,2) not null,
	  orderid          VARCHAR2(32) not null,
	  payeename        VARCHAR2(128),
	  payeecountrycode CHAR(3),
	  pay_type         CHAR(1),
	  tran_code        VARCHAR2(6),
	  tran_desc        VARCHAR2(50),
	  is_ref           CHAR(1),
	  contract_no      VARCHAR2(20),
	  invoice_no       VARCHAR2(35),
	  applicant        VARCHAR2(20),
	  applicant_tel    VARCHAR2(20),
	  stamp            TIMESTAMP(6)
	)
	tablespace CBPAY_JOURNAL
	PARTITION BY RANGE (paydate)
	(
		PARTITION B_SELL_EXG_DET_'||to_char(sysdate+1*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+1*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_SELL_EXG_DET_'||to_char(sysdate+2*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+2*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_SELL_EXG_DET_'||to_char(sysdate+3*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+3*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_SELL_EXG_DET_'||to_char(sysdate+4*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+4*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_SELL_EXG_DET_'||to_char(sysdate+5*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+5*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_SELL_EXG_DET_'||to_char(sysdate+6*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+6*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_SELL_EXG_DET_'||to_char(sysdate+7*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+7*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_SELL_EXG_DET_'||to_char(sysdate+8*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+8*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_SELL_EXG_DET_'||to_char(sysdate+9*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+9*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_SELL_EXG_DET_'||to_char(sysdate+10*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+10*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_SELL_EXG_DET_MAX VALUES LESS THAN (MAXVALUE) tablespace CBPAY_JOURNAL
	)';
	--ע�����
	execute immediate 'comment on column B_SELL_EXG_DET.paybatno is ''�������κ�''';
	execute immediate 'comment on column B_SELL_EXG_DET.paydate  is ''��������''';
	execute immediate 'comment on column B_SELL_EXG_DET.pay_seqno is ''����Ψһָ����''';
	execute immediate 'comment on column B_SELL_EXG_DET.merchantid is ''�̻���''';
	execute immediate 'comment on column B_SELL_EXG_DET.payerid is ''����ͻ���֤����''';
	execute immediate 'comment on column B_SELL_EXG_DET.payeraccount is ''����ͻ��˺�''';
	execute immediate 'comment on column B_SELL_EXG_DET.payername is ''����ͻ�����''';
	execute immediate 'comment on column B_SELL_EXG_DET.remit_ccy is ''������''';
	execute immediate 'comment on column B_SELL_EXG_DET.remit_amt is ''�����''';
	execute immediate 'comment on column B_SELL_EXG_DET.orderid is ''������''';
	execute immediate 'comment on column B_SELL_EXG_DET.payeename is ''�տ�̻�����''';
	execute immediate 'comment on column B_SELL_EXG_DET.payeecountrycode is ''�տ���ڹ���''';
	execute immediate 'comment on column B_SELL_EXG_DET.pay_type is ''��������   A��Ԥ������ P���������� R���˿� O-����''';
	execute immediate 'comment on column B_SELL_EXG_DET.tran_code is ''���ױ���  ������������֧���״����д���''';
	execute immediate 'comment on column B_SELL_EXG_DET.tran_desc is ''ó������Ϊ��Ʒ��Ϣ''';
	execute immediate 'comment on column B_SELL_EXG_DET.is_ref is ''�Ƿ�˰�� Y���� N����''';
	execute immediate 'comment on column B_SELL_EXG_DET.contract_no is ''��ͬ��''';
	execute immediate 'comment on column B_SELL_EXG_DET.invoice_no is ''��Ʊ��  ������֧������Ϣ''';
	execute immediate 'comment on column B_SELL_EXG_DET.applicant is ''������֧����˾�ӿͻ�����ϵ��''';
	execute immediate 'comment on column B_SELL_EXG_DET.applicant_tel is ''������֧����˾�ӿͻ�����ϵ�绰''';
	execute immediate 'comment on column B_SELL_EXG_DET.stamp is ''ʱ���''';
	
	--��������
	execute immediate '
		alter table B_SELL_EXG_DET add constraint B_SELL_EXG_DET_PK primary key (PAYBATNO, PAYDATE)
		using index 
		tablespace CBPAY_INDEX
	';
end;
/

