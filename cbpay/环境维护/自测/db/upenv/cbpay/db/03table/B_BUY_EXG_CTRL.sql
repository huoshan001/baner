--B_BUY_EXG_CTRL  �������
begin
	--�������
	execute immediate'
	create table B_BUY_EXG_CTRL
	(
	  buybatno             VARCHAR2(32) not null,
	  buydate              CHAR(8) not null,
	  buytime              CHAR(6),
	  quotechnl            VARCHAR2(10),
	  becif                VARCHAR2(32),
	  tot_num              NUMBER(3),
	  sale_ccy             CHAR(3),
	  buy_ccy              CHAR(3),
	  buy_sell_flag        CHAR(1),
	  total_amt            NUMBER(17,2),
	  spot_flag            CHAR(1),
	  register_date        CHAR(8),
	  price                NUMBER(17,8),
	  client_exchange_rate NUMBER(17,8),
	  discount_type        CHAR(1),
	  dis_amt              NUMBER(17,8),
	  amt                  NUMBER(17,2),
	  tran_amt             NUMBER(17,2),
	  sell_acct_no         VARCHAR2(32),
	  buy_acct_no          VARCHAR2(32),
	  sale_amount          NUMBER(17,2),
	  buy_amount           NUMBER(17,2),
	  rate_time            VARCHAR2(14),
	  exceed_flag          CHAR(1),
	  process_status       VARCHAR2(2),
	  process_msg          VARCHAR2(50),
	  txn_sts              CHAR(2),
	  chk_sts              CHAR(1)
	)
	tablespace CBPAY_JOURNAL
	PARTITION BY RANGE (buydate)
	(
		PARTITION B_BUY_EXG_CTRL_'||to_char(sysdate+1*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+1*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_BUY_EXG_CTRL_'||to_char(sysdate+2*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+2*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_BUY_EXG_CTRL_'||to_char(sysdate+3*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+3*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_BUY_EXG_CTRL_'||to_char(sysdate+4*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+4*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_BUY_EXG_CTRL_'||to_char(sysdate+5*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+5*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_BUY_EXG_CTRL_'||to_char(sysdate+6*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+6*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_BUY_EXG_CTRL_'||to_char(sysdate+7*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+7*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_BUY_EXG_CTRL_'||to_char(sysdate+8*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+8*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_BUY_EXG_CTRL_'||to_char(sysdate+9*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+9*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_BUY_EXG_CTRL_'||to_char(sysdate+10*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+10*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_BUY_EXG_CTRL_MAX VALUES LESS THAN (MAXVALUE) tablespace CBPAY_JOURNAL
	)';
	--ע�����
	execute immediate 'comment on column B_BUY_EXG_CTRL.buybatno is ''�������κ�''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.buydate is ''��������''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.buytime is ''����ʱ��''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.quotechnl is ''��������''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.becif is ''�ͻ���''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.tot_num is ''����''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.sale_ccy is ''�ͻ���������       ���ұ���''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.buy_ccy is ''�ͻ��������       ��ұ���''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.buy_sell_flag is ''���㷽ʽ''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.total_amt is ''�ܽ��''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.spot_flag is ''T+0�����־''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.register_date is ''���еǼ�����       ��������ñ�ҵ���������ҵ������''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.price is ''�г��� �û��ҶԵ��г���''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.client_exchange_rate is ''�ͻ��ɽ�����       �ÿͻ��������������������Żݣ��������г������Żݺ�ļ۸����û���Żݣ��ü۸�����г���''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.discount_type is ''�ͻ�Ĭ���Żݷ�ʽ   �ÿͻ������������Ĭ�ϵ��Żݷ�ʽ��P��PIONT�������Ż� S��SCALE�������Ż� D��DISCOUNT�ۿ�''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.dis_amt is ''�ͻ�Ĭ���Ż�ֵ     �ÿͻ������������Ĭ�ϵ��Ż�ֵ:���������ٵ㣬����50�㣬��λΪ�㣬��ʾΪ�� ������ƫ���м�ı�������λΪ���֮һ����ʾΪ%%�ۿۣ�����85�ۣ���λΪ�ٷ�֮һ����ʾΪ��''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.amt is ''�м۽��           �����г��ۼ������������һ�����''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.tran_amt is ''�Żݺ���         ���տͻ��Ż�ֵ�������������һ��������ͻ�û���Żݣ������г��۽��''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.sell_acct_no is ''�����˺�''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.buy_acct_no is ''�����˺�''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.sale_amount is ''�������''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.buy_amount is ''������''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.rate_time is ''����ʱ��           YYYYMMDDHHMMSS����ȡ����ʱ�����ں�ʱ��''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.exceed_flag is ''���ۻ��� I������� O�������''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.process_status is ''����״̬ T���ɹ� F��ʧ��''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.process_msg is ''������Ϣ �ɹ���Ϣ���ߴ�����Ϣ''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.txn_sts is ''����״̬''';
	execute immediate 'comment on column B_BUY_EXG_CTRL.chk_sts is ''���˱�־  0 δ����  1 �Ѷ���''';
	
	--��������
	execute immediate '
		alter table B_BUY_EXG_CTRL add constraint B_BUY_EXG_CTRL_PK primary key (BUYBATNO, BUYDATE)
		using index 
		tablespace CBPAY_INDEX
	';
end;
/
