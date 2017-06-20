--B_MERCHANT_ORDER  �������
begin
	--�������
	execute immediate'
	create table B_MERCHANT_ORDER
	(
	  payjnlno           VARCHAR2(32),
	  paytime            CHAR(6),
	  merchantid         VARCHAR2(16) not null,
	  orderid            VARCHAR2(64) not null,
	  ordertime          CHAR(6),
	  clientip           VARCHAR2(15),
	  purchaserid        VARCHAR2(64),
	  validunit          varchar2(2),
	  validnum           varchar2(3),
	  orderdesc          VARCHAR2(128),
	  orderamount        NUMBER(17,2),
	  currency           VARCHAR2(10),
	  merchantpoundage   NUMBER(17,2),
	  purchaseamount     NUMBER(17,2),
	  channel            VARCHAR2(8),
	  acceptancerate     NUMBER(12,8),
	  acceptancemount    NUMBER(17,2),
	  acceptancycurrency VARCHAR2(10),
	  acceptancypoundage NUMBER(17,2),
	  orderstatus        VARCHAR2(2),
	  pagereturnurl      VARCHAR2(256),
	  offlinenotifyurl   VARCHAR2(256),
	  payorderid         VARCHAR2(32),
	  buybatno           VARCHAR2(32),
	  buybatstatus       CHAR(2),
	  buybatrate         NUMBER(12,8),
	  upordownmount      NUMBER(17,2),
	  paybatno           VARCHAR2(32),
	  paybatstatus       CHAR(2),
	  mountchangeno      VARCHAR2(32),
	  mountchangestatus  CHAR(2),
	  settlebatno        VARCHAR2(32),
	  settlebatstatus    CHAR(1),
	  userid             VARCHAR2(32),
	  orderdate          CHAR(8) not null,
	  trade_type         VARCHAR2(4),
	  buybatdate         CHAR(8),
	  paycard            VARCHAR2(32),
	  paydate            CHAR(8),
	  trade_code         VARCHAR2(6),
	  is_ref             CHAR(1),
	  paybatdate         CHAR(8),
	  productid          VARCHAR2(256),
	  productname        VARCHAR2(256),
	  productdesc        VARCHAR2(256),
	  showurl            VARCHAR2(400),
	  isresponse         CHAR(1),
	  rcvtime            VARCHAR2(6),
	  rcvdate            VARCHAR2(8),
	  tmsmp              VARCHAR2(14),
	  invoiceno          VARCHAR2(16)
	)
	tablespace CBPAY_JOURNAL
	PARTITION BY RANGE (orderdate)
	(
		PARTITION B_MERCHANT_ORDER_'||to_char(sysdate+1*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+1*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_MERCHANT_ORDER_'||to_char(sysdate+2*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+2*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_MERCHANT_ORDER_'||to_char(sysdate+3*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+3*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_MERCHANT_ORDER_'||to_char(sysdate+4*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+4*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_MERCHANT_ORDER_'||to_char(sysdate+5*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+5*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_MERCHANT_ORDER_'||to_char(sysdate+6*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+6*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_MERCHANT_ORDER_'||to_char(sysdate+7*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+7*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_MERCHANT_ORDER_'||to_char(sysdate+8*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+8*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_MERCHANT_ORDER_'||to_char(sysdate+9*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+9*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_MERCHANT_ORDER_'||to_char(sysdate+10*40,'YYMMDD')||' VALUES LESS THAN ('||to_char(sysdate+10*40,'YYYYMMDD')||') tablespace CBPAY_JOURNAL,
		PARTITION B_MERCHANT_ORDER_MAX VALUES LESS THAN (MAXVALUE) tablespace CBPAY_JOURNAL
	)';
	--ע�����
	execute immediate 'comment on column B_MERCHANT_ORDER.payjnlno is ''֧��ϵͳ��ˮ��''';
	execute immediate 'comment on column B_MERCHANT_ORDER.paytime is ''֧�����ʱ��''';
	execute immediate 'comment on column B_MERCHANT_ORDER.merchantid is ''�̻���''';
	execute immediate 'comment on column B_MERCHANT_ORDER.orderid is ''�̻�������''';
	execute immediate 'comment on column B_MERCHANT_ORDER.ordertime is ''�̻�����ʱ��''';
	execute immediate 'comment on column B_MERCHANT_ORDER.clientip is ''�ͻ��˵�ַ''';
	execute immediate 'comment on column B_MERCHANT_ORDER.purchaserid is ''�����߱�ʶ''';
	execute immediate 'comment on column B_MERCHANT_ORDER.validunit is ''������Ч�ڵ�λ''';
	execute immediate 'comment on column B_MERCHANT_ORDER.validnum is ''������Ч������''';
	execute immediate 'comment on column B_MERCHANT_ORDER.orderdesc is ''�̻���������''';
	execute immediate 'comment on column B_MERCHANT_ORDER.orderamount is ''�̻��������''';
	execute immediate 'comment on column B_MERCHANT_ORDER.currency is ''�̻���������''';
	execute immediate 'comment on column B_MERCHANT_ORDER.merchantpoundage is ''�̻�������''';
	execute immediate 'comment on column B_MERCHANT_ORDER.purchaseamount is ''������''';
	execute immediate 'comment on column B_MERCHANT_ORDER.channel is ''��������''';
	execute immediate 'comment on column B_MERCHANT_ORDER.acceptancerate is ''�յ�����''';
	execute immediate 'comment on column B_MERCHANT_ORDER.acceptancemount is ''�յ����''';
	execute immediate 'comment on column B_MERCHANT_ORDER.acceptancycurrency is ''�յ�����''';
	execute immediate 'comment on column B_MERCHANT_ORDER.acceptancypoundage is ''�յ������� �ͺ�̨''';
	execute immediate 'comment on column B_MERCHANT_ORDER.orderstatus is ''�̻�����֧��״̬  00 �ȴ�����    01 ֧�����   02 �����ر�  03 ��������  04 ����ȡ��  05 �����˿� ''';
	execute immediate 'comment on column B_MERCHANT_ORDER.pagereturnurl is ''ҳ��֪ͨurl''';
	execute immediate 'comment on column B_MERCHANT_ORDER.offlinenotifyurl is ''�̻�֪ͨ�ص���ַ''';
	execute immediate 'comment on column B_MERCHANT_ORDER.payorderid is ''֧��������''';
	execute immediate 'comment on column B_MERCHANT_ORDER.buybatno is ''�������κ�''';
	execute immediate 'comment on column B_MERCHANT_ORDER.buybatstatus is ''����״̬ 00 δ����  01 ����Ǽ���  02 ����Ǽǳɹ�  03 ����Ǽ�ʧ��  04 ������   05 ����ʧ��   06 ����ɹ�''';
	execute immediate 'comment on column B_MERCHANT_ORDER.buybatrate is ''�������''';
	execute immediate 'comment on column B_MERCHANT_ORDER.upordownmount is ''������''';
	execute immediate 'comment on column B_MERCHANT_ORDER.paybatno is ''�������κ�''';
	execute immediate 'comment on column B_MERCHANT_ORDER.paybatstatus is ''����״̬ 00 δ���� 01 ���ɸ����ļ� 02 ����Ǽ��� 03 ����Ǽǳɹ� 04 ����Ǽ�ʧ�� 05 ��������� 06 ����ʧ�� 07 ����ɹ�''';
	execute immediate 'comment on column B_MERCHANT_ORDER.mountchangeno is ''�˻���ת��ˮ��''';
	execute immediate 'comment on column B_MERCHANT_ORDER.mountchangestatus is ''�˻���ת״̬ 00 δ��ת   01 ����ǰ�˻���ת��ʼ  02 ����ǰ�˻���ת�ɹ�  03 ����ǰ�˻���תʧ��  04 ������˻���ת��ʼ  05 ������˻���ת�ɹ�  06 ������˻���תʧ��  07 ����ǰ�˻���ת��ʼ  08 ����ǰ�˻���ת�ɹ�  09 ����ǰ�˻���תʧ��''';
	execute immediate 'comment on column B_MERCHANT_ORDER.settlebatno is ''�������κ�''';
	execute immediate 'comment on column B_MERCHANT_ORDER.settlebatstatus is ''�����־''';
	execute immediate 'comment on column B_MERCHANT_ORDER.userid is ''�û���ʶ��''';
	execute immediate 'comment on column B_MERCHANT_ORDER.orderdate is ''��������''';
	execute immediate 'comment on column B_MERCHANT_ORDER.trade_type is ''��������''';
	execute immediate 'comment on column B_MERCHANT_ORDER.buybatdate is ''��������''';
	execute immediate 'comment on column B_MERCHANT_ORDER.paycard is ''�����˿���''';
	execute immediate 'comment on column B_MERCHANT_ORDER.paydate is ''֧���������''';
	execute immediate 'comment on column B_MERCHANT_ORDER.trade_code is ''������������֧���״����д���''';
	execute immediate 'comment on column B_MERCHANT_ORDER.is_ref is ''�Ƿ�˰�������¸���  Y���� N����''';
	execute immediate 'comment on column B_MERCHANT_ORDER.paybatdate is ''��������''';
	execute immediate 'comment on column B_MERCHANT_ORDER.productid is ''��Ʒid''';
	execute immediate 'comment on column B_MERCHANT_ORDER.productname is ''��Ʒ����''';
	execute immediate 'comment on column B_MERCHANT_ORDER.productdesc is ''��Ʒ����''';
	execute immediate 'comment on column B_MERCHANT_ORDER.showurl is ''չʾurl''';
	execute immediate 'comment on column B_MERCHANT_ORDER.isresponse is ''�Ƿ�����''';
	execute immediate 'comment on column B_MERCHANT_ORDER.rcvtime is ''��������ʱ��''';
	execute immediate 'comment on column B_MERCHANT_ORDER.rcvdate is ''������������''';
	execute immediate 'comment on column B_MERCHANT_ORDER.tmsmp is ''ʱ���''';
	execute immediate 'comment on column B_MERCHANT_ORDER.invoiceno is ''��Ʊ��''';
	
	
	--��������
	execute immediate '
		alter table B_MERCHANT_ORDER add constraint B_MERCHANT_ORDER_PK primary key (MERCHANTID, ORDERID, ORDERDATE)
		using index 
		tablespace CBPAY_INDEX
	';
end;
/
