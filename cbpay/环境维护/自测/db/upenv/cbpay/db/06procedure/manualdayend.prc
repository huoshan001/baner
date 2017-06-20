create or replace procedure manualdayend
is
-----------------------------------------------------
--���ܣ�ƽ̨�ֶ����У����Զ����еĻ�����ȥ���������Ƿ���ɵļ��
--ʱ�䣺2016��6��7��
--���ߣ�zhangxiaoyun
--�汾��v1.0
--
--
------------------------------------------------------
       var_currentDate varchar2(8);
       var_currenttime varchar2(6):=to_char(sysdate,'hh24miss');
       var_nextacdt varchar2(8);

begin
	------------------------------��ȡ����ҵ������-----------------------------
	select acdt,nextacdt into var_currentDate,var_nextacdt from plat_date_param t ;

    insert into dayendlog (platdate, plattime,acdt, status) values (var_currentDate, var_currenttime,var_nextacdt, '0');
    commit;
	--��ʼ����
	switchplatdate();
	--��ʼ�б����
	switchdatapart();

  update dayendlog set status='1' where platdate=var_currentDate;
  commit;
  
  exception
    when OTHERS then
      update dayendlog set status='0' where platdate=var_currentDate;
      commit;
      errorlog_pro(var_currentDate,'manualdayend',SQLCODE,SQLERRM,dbms_utility.format_error_backtrace);
      RAISE;
end manualdayend;
/
