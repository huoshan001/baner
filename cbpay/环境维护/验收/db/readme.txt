--------------------------dayend---------------------------------------
һ���Զ����нű�����
1.���ȱ�֤�û��Ƿ����sqlplus�����
2.��dayendĿ¼ȫ�������û�Ŀ¼��cbpayĿ¼�С�
3.���ļ�dayend/tns/tnsnames.ora�鿴�Ƿ��Ѿ����ú�Ҫ�����ݿ��tns
4.���ļ�dayend/setEnv.sh�У������������ݿ��tns���û��������롣
5.���ļ�dayend/cfg/baseparam.properties�У����������������mqurl��mqqueue��
6.�ļ�dayend/cfg/containid.properties�У�������������У�������Ҫ���е�������containid�����á�
7.��ֵdayendĿ¼�������ļ�755Ȩ��
8.����ϵͳ��crontab
���磺0 0 * * * . $HOME/.bash_profile;$HOME/cbpay/dayend/start.sh $HOME/cbpay/dayend
------------------------------------------------------------------------
�����ֶ����нű�
1.����Զ����в���1������7
2.ֱ��ִ��dayend/shell/handdayend.sh
-------------------------------------------------------------------------