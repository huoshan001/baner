--TRANS_MSG_LOG  �������
begin
	execute immediate'
	create table TRANS_MSG_LOG
	(
	  flowno    VARCHAR2(50) not null,
	  trandate char(8) not null,
	  trantime char(6) not null,
	  msg_order VARCHAR2(2) not null,
	  msg       BLOB not null
	)
	lob (msg) store as TRANS_MSG_LOG_MSG
	(
	  tablespace CBPAY_JOURNAL
	  disable storage in row
	  chunk 8192
	  pctversion 10
	  NOCACHE
	)
	tablespace CBPAY_JOURNAL
	PARTITION BY RANGE (trandate)
	(
		PARTITION TRANS_MSG_LOG_'||to_char(sysdate+1*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+1*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION TRANS_MSG_LOG_'||to_char(sysdate+2*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+2*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION TRANS_MSG_LOG_'||to_char(sysdate+3*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+3*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION TRANS_MSG_LOG_'||to_char(sysdate+4*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+4*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION TRANS_MSG_LOG_'||to_char(sysdate+5*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+5*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION TRANS_MSG_LOG_'||to_char(sysdate+6*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+6*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION TRANS_MSG_LOG_'||to_char(sysdate+7*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+7*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION TRANS_MSG_LOG_'||to_char(sysdate+8*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+8*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION TRANS_MSG_LOG_'||to_char(sysdate+9*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+9*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION TRANS_MSG_LOG_'||to_char(sysdate+10*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+10*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION TRANS_MSG_LOG_MAX VALUES LESS THAN (MAXVALUE) tablespace CBPAY_JOURNAL
	)';
	execute immediate 'comment on column TRANS_MSG_LOG.flowno is ''ƽ̨��ˮ��''';
	execute immediate 'comment on column TRANS_MSG_LOG.trandate is ''ƽ̨���ڣ���ʽ��yyyyMMdd''';
	execute immediate 'comment on column TRANS_MSG_LOG.trantime  is ''ƽ̨ʱ�䣬��ʽ��HHmmss''';
	execute immediate 'comment on column TRANS_MSG_LOG.msg_order is ''��Ϣ˳��1:��һ�ʱ��� 2:�ڶ��ʱ��� 3:�����ʱ��� 4:���ıʱ���''';
	execute immediate 'comment on column TRANS_MSG_LOG.msg is ''��Ϣ����''';
end;
/
