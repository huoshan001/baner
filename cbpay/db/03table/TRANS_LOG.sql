--trans_log  �������
begin
	execute immediate'
	create table TRANS_LOG
	(
	  flowno              VARCHAR2(50) not null,
	  trandate            VARCHAR2(8) not null,
	  trantime            VARCHAR2(8) not null,
	  trancode            VARCHAR2(6) not null,
	  tranfrom            VARCHAR2(15) not null,
	  servercode          VARCHAR2(10) not null,
	  serverid            VARCHAR2(12) not null,
	  serviceresponsecode VARCHAR2(15),
	  serviceresponsedesc VARCHAR2(250),
	  platresponsecode    VARCHAR2(50)
	)
	tablespace CBPAY_JOURNAL
	PARTITION BY RANGE (trandate)
	(
		PARTITION TRANS_LOG_'||to_char(sysdate+1*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+1*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION TRANS_LOG_'||to_char(sysdate+2*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+2*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION TRANS_LOG_'||to_char(sysdate+3*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+3*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION TRANS_LOG_'||to_char(sysdate+4*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+4*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION TRANS_LOG_'||to_char(sysdate+5*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+5*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION TRANS_LOG_'||to_char(sysdate+6*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+6*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION TRANS_LOG_'||to_char(sysdate+7*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+7*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION TRANS_LOG_'||to_char(sysdate+8*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+8*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION TRANS_LOG_'||to_char(sysdate+9*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+9*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION TRANS_LOG_'||to_char(sysdate+10*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+10*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION TRANS_LOG_MAX VALUES LESS THAN (MAXVALUE) tablespace CBPAY_JOURNAL
	)';
	execute immediate 'comment on table TRANS_LOG is ''ƽ̨��־��''';
	execute immediate 'comment on column TRANS_LOG.flowno is ''ƽ̨��ˮ��''';
	execute immediate 'comment on column TRANS_LOG.trandate is ''ƽ̨����''';
	execute immediate 'comment on column TRANS_LOG.trantime is ''ƽ̨ʱ��''';
	execute immediate 'comment on column TRANS_LOG.trancode is ''���׷��𷽽�����''';
	execute immediate 'comment on column TRANS_LOG.tranfrom is ''���׷���ID''';
	execute immediate 'comment on column TRANS_LOG.servercode is ''���񷽽�����''';
	execute immediate 'comment on column TRANS_LOG.serverid is ''���񷽱�ID''';
	execute immediate 'comment on column TRANS_LOG.serviceresponsecode is ''������Ӧ��''';
	execute immediate 'comment on column TRANS_LOG.serviceresponsedesc is ''������Ӧ������''';
	execute immediate 'comment on column TRANS_LOG.platresponsecode  is ''ƽ̨��Ӧ��''';
end;
/
