create or replace procedure dayend
is
-----------------------------------------------------
--���ܣ�ƽ̨����
--ʱ�䣺2016��6��7��
--���ߣ�zhangxiaoyun
--�汾��v1.0
--
--
------------------------------------------------------
       var_dateFormate varchar2(8):='YYYYMMDD';
       var_currentDate varchar2(8):=to_char(sysdate,var_dateFormate);
       var_currenttime varchar2(6):=to_char(sysdate,'hh24miss');
       var_acdt varchar2(8);
       var_nextacdt varchar2(8);
       var_count number;

begin
  --��ѯ����ԭʼ����
  select count(1) into var_count from dayendlog where platdate=var_currentDate;
  select t.acdt,t.nextacdt  into var_acdt,var_nextacdt  from plat_date_param t where t.sysname='cbpay';
  
  if var_count>0 then
    --��������Ѿ��������У�ƽ̨�����õ���ҵ������
	tool_pro(var_acdt,'plat_date_param','','','�����Ѿ�����','insert');
  else
    
    insert into dayendlog (platdate, plattime,acdt, status) values (var_currentDate, var_currenttime,var_nextacdt, '0');
    commit;
	--��ʼ����
	switchplatdate();
	--��ʼ�б����
	switchdatapart();
  
  update dayendlog set status='1' where platdate=var_currentDate;
  commit;
  
  end if;
  exception
    when OTHERS then
      update dayendlog set status='0' where platdate=var_currentDate;
      commit;
      errorlog_pro(var_currentDate,'dayend',SQLCODE,SQLERRM,dbms_utility.format_error_backtrace);
      RAISE;
end dayend;
/