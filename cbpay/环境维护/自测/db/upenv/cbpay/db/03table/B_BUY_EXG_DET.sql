--b_buy_exg_det  �������
begin
	--�������
	execute immediate'
	create table B_BUY_EXG_DET
	(
	  buybatno        VARCHAR2(32) not null,
	  tradeno         VARCHAR2(32) not null,
	  tran_amt        NUMBER(17,2),
	  client_no       VARCHAR2(20),
	  type            CHAR(1),
	  tran_code       CHAR(6),
	  payer_global_id VARCHAR2(32),
	  payer_name      VARCHAR2(128),
	  trade_type      CHAR(3),
	  country_code    CHAR(3),
	  buydate         CHAR(8)
	)
	tablespace CBPAY_JOURNAL
	PARTITION BY RANGE (buydate)
	(
		PARTITION B_BUY_EXG_DET_'||to_char(sysdate+1*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+1*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_BUY_EXG_DET_'||to_char(sysdate+2*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+2*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_BUY_EXG_DET_'||to_char(sysdate+3*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+3*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_BUY_EXG_DET_'||to_char(sysdate+4*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+4*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_BUY_EXG_DET_'||to_char(sysdate+5*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+5*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_BUY_EXG_DET_'||to_char(sysdate+6*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+6*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_BUY_EXG_DET_'||to_char(sysdate+7*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+7*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_BUY_EXG_DET_'||to_char(sysdate+8*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+8*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_BUY_EXG_DET_'||to_char(sysdate+9*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+9*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_BUY_EXG_DET_'||to_char(sysdate+10*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+10*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_BUY_EXG_DET_MAX VALUES LESS THAN (MAXVALUE) tablespace CBPAY_JOURNAL
	)';
	--ע�����
	execute immediate 'comment on column B_BUY_EXG_DET.buybatno is ''�������κ�''';
	execute immediate 'comment on column B_BUY_EXG_DET.tradeno is ''֧��ϵͳ��ˮ��  ���ױ�ţ����Ե�ͬ�ڶ����ţ���������ҵ���ѯ��һ��ҵ���Ӧһ�ʹ���Ǽǣ�����������ش����ڳ�ʱ��ʱ����ͬ���ı�ŷ��Ϳ��Է�ֹ�ظ�''';
	execute immediate 'comment on column B_BUY_EXG_DET.tran_amt is ''������  ÿ�ʶ����Ľ���ϸ����֮��Ľ������ܽ��''';
	execute immediate 'comment on column B_BUY_EXG_DET.client_no is ''�ӿͻ���  ֤������''';
	execute immediate 'comment on column B_BUY_EXG_DET.type is ''���������  C���Թ��û�  D����˽''';
	execute immediate 'comment on column B_BUY_EXG_DET.tran_code is ''���ױ���  ������������֧���״����д��ڣ������˻��ڽ��ۻ㱨�ͣ��Թ���������''';
	execute immediate 'comment on column B_BUY_EXG_DET.payer_global_id is ''������֤����''';
	execute immediate 'comment on column B_BUY_EXG_DET.payer_name is ''����������''';
	execute immediate 'comment on column B_BUY_EXG_DET.trade_type is ''�������� ��˽������
		310����ó��
		321����
		3221�Էѳ���ѧϰ
		3222��˽����
		3223�����������
		3225������������
		323���ںͱ��շ���
		324ר��Ȩ��ʹ�÷Ѻ������
		325��ѯ����
		326��������''';
	execute immediate 'comment on column B_BUY_EXG_DET.country_code is ''��������           ��˽�����乺����ջ��˵Ĺ������''';
	execute immediate 'comment on column B_BUY_EXG_DET.buydate is ''��������''';

	--��������
	execute immediate '
		alter table B_BUY_EXG_DET add constraint B_BUY_EXG_DET_PK primary key (BUYBATNO, TRADENO)
		using index 
		tablespace CBPAY_INDEX
	';
end;
/

