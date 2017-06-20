--B_SELL_EXG_CTRL  �������
begin
	--�������
	execute immediate'
	create table B_SELL_EXG_CTRL
	(
	  paybatno                  VARCHAR2(32) not null,
	  paydate                   CHAR(8) not null,
	  paytime                   CHAR(6) not null,
	  quotechnl                 VARCHAR2(10) not null,
	  remit_ccy                 CHAR(3) not null,
	  remit_amt                 NUMBER(17,2) not null,
	  cost_type                 VARCHAR2(3) not null,
	  payee_acct_no             VARCHAR2(34) not null,
	  payee_client_name         VARCHAR2(120) not null,
	  payee_address             VARCHAR2(120) not null,
	  payer_acct_no             VARCHAR2(34) not null,
	  payer_client_name         VARCHAR2(120) not null,
	  payer_address             VARCHAR2(120) not null,
	  payee_acct_open_branch_id VARCHAR2(14) not null,
	  remark                    VARCHAR2(120) not null,
	  clear_bank_bic            VARCHAR2(14),
	  process_msg               VARCHAR2(14),
	  process_status            CHAR(1),
	  fail_reason               VARCHAR2(50),
	  chkstatus                 CHAR(1),
	  stamp                     TIMESTAMP(6) not null,
	  filename                  VARCHAR2(32),
	  buss_status               CHAR(1),
	  tot_num                   NUMBER(3)
	)
	tablespace CBPAY_JOURNAL
	PARTITION BY RANGE (paydate)
	(
		PARTITION B_SELL_EXG_CTRL_'||to_char(sysdate+1*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+1*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_SELL_EXG_CTRL_'||to_char(sysdate+2*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+2*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_SELL_EXG_CTRL_'||to_char(sysdate+3*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+3*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_SELL_EXG_CTRL_'||to_char(sysdate+4*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+4*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_SELL_EXG_CTRL_'||to_char(sysdate+5*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+5*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_SELL_EXG_CTRL_'||to_char(sysdate+6*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+6*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_SELL_EXG_CTRL_'||to_char(sysdate+7*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+7*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_SELL_EXG_CTRL_'||to_char(sysdate+8*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+8*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_SELL_EXG_CTRL_'||to_char(sysdate+9*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+9*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_SELL_EXG_CTRL_'||to_char(sysdate+10*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+10*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_SELL_EXG_CTRL_MAX VALUES LESS THAN (MAXVALUE) tablespace CBPAY_JOURNAL
	)';
	--ע�����
	execute immediate 'comment on column B_SELL_EXG_CTRL.paybatno is ''�������κ�''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.paydate is ''��������''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.paytime is ''����ʱ��''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.quotechnl is ''��������''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.remit_ccy  is ''������''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.remit_amt is ''�����''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.cost_type is  ''��������   
		OUR Ϊ������ȫ���е�������������+�м��з��ã����дӿ۷��ʺ���ȡ��ȡ�����տ��˿�ȫ��ˡ� 
		SHA  Ϊ��ͬ�е����������˳е����������ѣ����дӿ۷��ʺ���ȡ��ȡ���տ��˳е��м��з��ã����˽����٣�
		BEN�տ���ȫ���е�������������+�м��з��ã�ȫ���ӵ��˽���пۼ���������֧��ʵ���У����ڱ����������⣬����֧��ҵ���ݲ�֧��ʹ��''
		';
	execute immediate 'comment on column B_SELL_EXG_CTRL.payee_acct_no is  ''�տ����˺�''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.payee_client_name is  ''�տ�������''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.payee_address is  ''�տ��˵�ַ''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.payer_acct_no is  ''�������˺�''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.payer_client_name is  ''����������''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.payer_address is  ''�����˵�ַ''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.payee_acct_open_branch_id is  ''�տ��˿������к�''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.remark is  ''����''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.clear_bank_bic is  ''�羳�м���''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.process_msg is  ''������Ϣ''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.process_status is  ''����״̬''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.fail_reason is  ''ʧ��ԭ��''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.chkstatus is  ''����״̬  0��δ���� 1�����˳ɹ� 2��֧��ϵͳ���� 3��״̬����''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.stamp is  ''����ʱ���''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.filename is  ''�����ļ�''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.buss_status is  ''ҵ��״̬   1�����н��մ���ɹ� 2�����ɹ� 3���˻�  4�����ʧ�� 5�������쳣''';
	execute immediate 'comment on column B_SELL_EXG_CTRL.tot_num is  ''�ܱ���''';
	
	--��������
	execute immediate '
		alter table B_SELL_EXG_CTRL add constraint B_SELL_EXG_CTRL_PK primary key (PAYBATNO, PAYDATE)
		using index 
		tablespace CBPAY_INDEX
	';
end;
/
