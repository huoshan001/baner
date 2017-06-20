--B_TRANSFER_FILE_INFO  �������
begin
	--�������
	execute immediate'
	create table B_TRANSFER_FILE_INFO
	(
	  transfer_date CHAR(8) not null,
	  transfer_time CHAR(6) not null,
	  seqbatchno    VARCHAR2(20) not null,
	  transfer_type CHAR(1) not null,
	  filetype      CHAR(1) not null,
	  count         NUMBER(5) not null,
	  filename      VARCHAR2(100) not null,
	  randompwd     VARCHAR2(100),
	  hashvalue     VARCHAR2(200),
	  signvalue     VARCHAR2(4000),
	  filestatus    VARCHAR2(2)
	)
	tablespace CBPAY_JOURNAL
	PARTITION BY RANGE (transfer_date)
	(
		PARTITION B_TRANSFER_FILE_INFO_'||to_char(sysdate+1*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+1*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_TRANSFER_FILE_INFO_'||to_char(sysdate+2*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+2*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_TRANSFER_FILE_INFO_'||to_char(sysdate+3*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+3*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_TRANSFER_FILE_INFO_'||to_char(sysdate+4*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+4*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_TRANSFER_FILE_INFO_'||to_char(sysdate+5*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+5*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_TRANSFER_FILE_INFO_'||to_char(sysdate+6*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+6*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_TRANSFER_FILE_INFO_'||to_char(sysdate+7*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+7*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_TRANSFER_FILE_INFO_'||to_char(sysdate+8*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+8*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_TRANSFER_FILE_INFO_'||to_char(sysdate+9*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+9*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_TRANSFER_FILE_INFO_'||to_char(sysdate+10*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+10*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_TRANSFER_FILE_INFO_MAX VALUES LESS THAN (MAXVALUE) tablespace CBPAY_JOURNAL
	)';
	--ע�����
	execute immediate 'comment on column B_TRANSFER_FILE_INFO.transfer_type is ''��������   2 �ϴ�  1 ����''';
	execute immediate 'comment on column B_TRANSFER_FILE_INFO.filetype is ''1:�ͻ���Ϣ�Ǽ�ά���ϴ�(�ͻ�������)
	5: ���㽻����ϸ�����ļ��ϴ�(�ͻ�������)
	6: �ջ㽻����ϸ�����ļ��ϴ�(�ͻ�������)  2:�������ļ�֪ͨ����
	3:����Ǽ�״̬��ѯ�ļ�
	4:���㽻����ϸ�����ļ�
	7:����ջ㽻����ϸ�����ļ�
	8: �ջ㵽���ļ�����֪ͨ(���е��ͻ�)
	9: SWIFT CODE�ļ�֪ͨ�����е�֧����˾��
	C�������嵥
	D��BC����Ϣ֪ͨ''';
	execute immediate 'comment on column B_TRANSFER_FILE_INFO.count is ''��ϸ����''';
	execute immediate 'comment on column B_TRANSFER_FILE_INFO.filename  is ''�ļ�����''';
	execute immediate 'comment on column B_TRANSFER_FILE_INFO.randompwd  is ''����''';
	execute immediate 'comment on column B_TRANSFER_FILE_INFO.hashvalue is ''�ļ�ժҪ''';
	execute immediate 'comment on column B_TRANSFER_FILE_INFO.signvalue is ''ǩ��ֵ''';
	execute immediate 'comment on column B_TRANSFER_FILE_INFO.filestatus  is ''�ļ��ϴ�����״̬ 1 �ļ�����  2 �ļ���ʼ�ϴ�  3 �ļ��ϴ��ɹ�  4 �ļ����ؿ�ʼ  5 �ļ��������''';
	
	--��������
	execute immediate '
		alter table B_TRANSFER_FILE_INFO add constraint B_TRANSFER_FILE_INFO_PK primary key (TRANSFER_DATE, FILENAME)
		using index 
		tablespace CBPAY_INDEX
	';
end;
/
