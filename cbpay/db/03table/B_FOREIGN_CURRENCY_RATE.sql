--B_TRANSFER_FILE_INFO  �������
begin
	--�������
	execute immediate'
	create table B_FOREIGN_CURRENCY_RATE
	(
	  from_id              VARCHAR2(50) not null,
	  currency_code        VARCHAR2(508) not null,
	  recv_date            CHAR(8) not null,
	  recv_time            CHAR(6) not null,
	  transtime            VARCHAR2(6),
	  transdate            VARCHAR2(8),
	  cashbuyprice         NUMBER(12,6),
	  exbuyprice           NUMBER(12,6),
	  cashsellprice        NUMBER(12,6),
	  exsellprice          NUMBER(12,6),
	  exquotedate          CHAR(8),
	  exquotetime          VARCHAR2(20),
	  e3rdpayno            VARCHAR2(50),
	  price                NUMBER(12,8),
	  direction_flag       CHAR(1),
	  tran_amt             NUMBER(17,2),
	  client_exchange_rate NUMBER(12,8),
	  discount_type        CHAR(1),
	  dis_amt              NUMBER(17,8),
	  amt                  NUMBER(17,2)
	)
	tablespace CBPAY_JOURNAL
	PARTITION BY RANGE (exquotedate)
	(
		PARTITION B_FOREIGN_CURRENCY_RATE_'||to_char(sysdate+1*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+1*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_FOREIGN_CURRENCY_RATE_'||to_char(sysdate+2*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+2*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_FOREIGN_CURRENCY_RATE_'||to_char(sysdate+3*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+3*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_FOREIGN_CURRENCY_RATE_'||to_char(sysdate+4*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+4*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_FOREIGN_CURRENCY_RATE_'||to_char(sysdate+5*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+5*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_FOREIGN_CURRENCY_RATE_'||to_char(sysdate+6*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+6*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_FOREIGN_CURRENCY_RATE_'||to_char(sysdate+7*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+7*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_FOREIGN_CURRENCY_RATE_'||to_char(sysdate+8*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+8*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_FOREIGN_CURRENCY_RATE_'||to_char(sysdate+9*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+9*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_FOREIGN_CURRENCY_RATE_'||to_char(sysdate+10*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+10*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_FOREIGN_CURRENCY_RATE_MAX VALUES LESS THAN (MAXVALUE) tablespace CBPAY_JOURNAL
	)';
	--ע�����
	execute immediate 'comment on column B_FOREIGN_CURRENCY_RATE.from_id is ''��Դid''';
	execute immediate 'comment on column B_FOREIGN_CURRENCY_RATE.currency_code is ''���ִ���''';
	execute immediate 'comment on column B_FOREIGN_CURRENCY_RATE.recv_date is ''�������ڣ�yyyyMMdd''';
	execute immediate 'comment on column B_FOREIGN_CURRENCY_RATE.recv_time is ''����ʱ�䣬HHmmss''';
	execute immediate 'comment on column B_FOREIGN_CURRENCY_RATE.transtime is ''���з���ʱ��  HHMMSS''';
	execute immediate 'comment on column B_FOREIGN_CURRENCY_RATE.transdate is ''���з�������  YYYYMMDD''';
	execute immediate 'comment on column B_FOREIGN_CURRENCY_RATE.cashbuyprice is ''����ۣ���100Ϊ��λ''';
	execute immediate 'comment on column B_FOREIGN_CURRENCY_RATE.exbuyprice is ''����ۣ���100Ϊ��λ''';
	execute immediate 'comment on column B_FOREIGN_CURRENCY_RATE.cashsellprice is ''�����ۣ���100Ϊ��λ''';
	execute immediate 'comment on column B_FOREIGN_CURRENCY_RATE.exsellprice is ''�����ۣ���100Ϊ��λ''';
	execute immediate 'comment on column B_FOREIGN_CURRENCY_RATE.exquotedate is ''�Ƽ�����''';
	execute immediate 'comment on column B_FOREIGN_CURRENCY_RATE.exquotetime is ''�Ƽ�ʱ��''';
	execute immediate 'comment on column B_FOREIGN_CURRENCY_RATE.e3rdpayno is ''������֧����''';
	execute immediate 'comment on column B_FOREIGN_CURRENCY_RATE.price is ''�г���''';
	execute immediate 'comment on column B_FOREIGN_CURRENCY_RATE.direction_flag is ''���ʷ���ȡֵΪT��F 
T����ʾ���ʷ�������ҶԷ���һ�£����ͻ�������=�ͻ��������*����
F����ʾ���ʷ�������ҶԷ����෴�����ͻ�������=�ͻ��������/����''';
	execute immediate 'comment on column B_FOREIGN_CURRENCY_RATE.tran_amt is ''�����г��ۼ������������һ�����''';
	execute immediate 'comment on column B_FOREIGN_CURRENCY_RATE.client_exchange_rate is ''�ͻ��ɽ�����''';
	execute immediate 'comment on column B_FOREIGN_CURRENCY_RATE.discount_type is ''�ͻ�Ĭ���Żݷ�ʽ P��PIONT�������Ż�  S��SCALE�������Ż� D��DISCOUNT�ۿ�''';
	execute immediate 'comment on column B_FOREIGN_CURRENCY_RATE.dis_amt is ''�ͻ�Ĭ���Ż�ֵ   ���������ٵ㣬����50�㣬��λΪ�㣬��ʾΪ�� ������ƫ���м�ı�������λΪ���֮һ����ʾΪ%% �ۿۣ�����85�ۣ���λΪ�ٷ�֮һ����ʾΪ��''';
	execute immediate 'comment on column B_FOREIGN_CURRENCY_RATE.amt is ''�м۽��''';

	--��������
	execute immediate '
		alter table B_FOREIGN_CURRENCY_RATE add constraint FOREIGN_CURRENCY_RATE_PK primary key (FROM_ID, RECV_DATE, RECV_TIME, CURRENCY_CODE)
		using index 
		tablespace CBPAY_INDEX
	';
end;
/
