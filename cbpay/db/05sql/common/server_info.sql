--20160310 �������׻�ͨ����
delete from server_info where SERVERID='UMB_SVR';
insert into server_info (SERVERID, SERVER_KEY, SERVERDESC, IP, PROT, ISENCRYPT, ENCRYPT_ALGORITHM, PUBLIC_KEY_FILE, ISSIGNATURE, SIGNATURE_ALGORITHM, REMITTANCE_FEE, RES_CODE, KEY_STORE_FILE, KEY_STORE_ALIAS, KEY_STORE_PASSWORD, ACCOUNTNO, RES_MSG) values ('UMB_SVR', 'SMS', '���׻�ͨ����ƽ̨', null, null, '0', null, null, '0', null, null, 'respcode', null, null, null, null, 'respmsg');

commit;


--20160310 ����ƽ�����з���
delete from server_info where SERVERID='PAB_SVR';
insert into server_info (SERVERID, SERVER_KEY, SERVERDESC, IP, PROT, ISENCRYPT, ENCRYPT_ALGORITHM, PUBLIC_KEY_FILE, ISSIGNATURE, SIGNATURE_ALGORITHM, REMITTANCE_FEE, RES_CODE, KEY_STORE_FILE, KEY_STORE_ALIAS, KEY_STORE_PASSWORD, ACCOUNTNO, RES_MSG) values ('PAB_SVR', 'PAB', 'ƽ������', null, null, '0', null, null, '0', null, null, 'respcode', null, null, null, '10120000000010050074', 'respmsg');

commit;

--20160606 ����ƽ̨����
delete from server_info where SERVERID='PLATFORM_SVR';
insert into server_info (SERVERID, SERVER_KEY, SERVERDESC, IP, PROT, ISENCRYPT, ENCRYPT_ALGORITHM, PUBLIC_KEY_FILE, ISSIGNATURE, SIGNATURE_ALGORITHM, REMITTANCE_FEE, RES_CODE, KEY_STORE_FILE, KEY_STORE_ALIAS, KEY_STORE_PASSWORD, ACCOUNTNO, RES_MSG) values ('PLATFORM_SVR', 'CHECK', 'ƽ̨����', null, null, '0', null, null, '0', null, null, 'status', null, null, null, null, null);

commit;
