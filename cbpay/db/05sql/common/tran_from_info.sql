--20160607 �޸� ����̨����
delete from tran_from_info where FROM_TYPE='4' and FROM_ID='CASHIER_CLI';
insert into tran_from_info (FROM_TYPE, FROM_ID, FROM_DESC, ISENCRYPT, ENCRYPT_ALGORITHM, ISSIGNATURE, SIGNATURE_ALGORITHM, PUBLIC_KEY_FILE, KEY_FILE_TYPE, ENCRYPT_TYPE, SIGNATURE_TYPE, CHANEL_CODE, CHANEL_DES, KEY_STORE_FILE, KEY_STORE_ALIAS, KEY_STORE_PASSWORD)values ('4', 'CASHIER_CLI', '����̨', '0', null, '0', null, '/ztkx/cbpay/CrossBorderPay/cers/cashier_cli/zlex.cer', null, null, null, 'respCode', 'respMsg', '/ztkx/cbpay/CrossBorderPay/cers/cashier_cli/zlex.keystore', 'www.zlex.org', '123456');


commit;

--20160321 ���� ��ʱ��������
delete from tran_from_info where FROM_TYPE='4' and FROM_ID='CRONTAB_CLI';
insert into tran_from_info (FROM_TYPE, FROM_ID, FROM_DESC, ISENCRYPT, ENCRYPT_ALGORITHM, ISSIGNATURE, SIGNATURE_ALGORITHM, PUBLIC_KEY_FILE, KEY_FILE_TYPE, ENCRYPT_TYPE, SIGNATURE_TYPE, CHANEL_CODE, CHANEL_DES, KEY_STORE_FILE, KEY_STORE_ALIAS, KEY_STORE_PASSWORD) values ('4', 'CRONTAB_CLI', '��ʱ����', '0', '', '0', '', '', '', '', '', 'respcode', 'respmsg', '', '', '');

commit;

--20160324 ���� ����̨����
delete from tran_from_info where FROM_TYPE='4' and FROM_ID='CONSOLE_CLI';
insert into tran_from_info (FROM_TYPE, FROM_ID, FROM_DESC, ISENCRYPT, ENCRYPT_ALGORITHM, ISSIGNATURE, SIGNATURE_ALGORITHM, PUBLIC_KEY_FILE, KEY_FILE_TYPE, ENCRYPT_TYPE, SIGNATURE_TYPE, CHANEL_CODE, CHANEL_DES, KEY_STORE_FILE, KEY_STORE_ALIAS, KEY_STORE_PASSWORD) values ('4', 'CONSOLE_CLI', '����̨', '0', '', '0', '', '', '', '', '', 'respcode', 'respmsg', '', '', '');
commit;

--20160504 ���� ƽ����������
delete from tran_from_info where FROM_TYPE='2' and FROM_ID='PAB_CLI';
insert into tran_from_info (FROM_TYPE, FROM_ID, FROM_DESC, ISENCRYPT, ENCRYPT_ALGORITHM, ISSIGNATURE, SIGNATURE_ALGORITHM, PUBLIC_KEY_FILE, KEY_FILE_TYPE, ENCRYPT_TYPE, SIGNATURE_TYPE, CHANEL_CODE, CHANEL_DES, KEY_STORE_FILE, KEY_STORE_ALIAS, KEY_STORE_PASSWORD) values ('2', 'PAB_CLI', 'ƽ������', '0', '', '0', '', '', '', '', '', '', '', '', '', '');
commit;

--20160606 ���� �̻��ͻ�����
delete from tran_from_info where FROM_TYPE='3' and FROM_ID='MERCHANT_CLI';
insert into tran_from_info (FROM_TYPE, FROM_ID, FROM_DESC, ISENCRYPT, ENCRYPT_ALGORITHM, ISSIGNATURE, SIGNATURE_ALGORITHM, PUBLIC_KEY_FILE, KEY_FILE_TYPE, ENCRYPT_TYPE, SIGNATURE_TYPE, CHANEL_CODE, CHANEL_DES, KEY_STORE_FILE, KEY_STORE_ALIAS, KEY_STORE_PASSWORD) values ('3', 'MERCHANT_CLI', '�̻��ͻ���', '1', null, '1', null, '/ztkx/cbpay/CrossBorderPay/cers/merchant/zlex.cer', null, null, null, 'respCode', 'respMsg', '/ztkx/cbpay/CrossBorderPay/cers/merchant/zlex.keystore', 'www.zlex.org', '123456');
commit;