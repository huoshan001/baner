create or replace procedure switchplatdate
is
-----------------------------------------------------
--���ܣ��л�ƽ̨����
--ʱ�䣺2016��6��7��
--���ߣ�zhangxiaoyun
--�汾��v1.0
--
--
------------------------------------------------------
       var_dateFormate varchar2(8):='YYYYMMDD';
       var_currentDate varchar2(8):=to_char(sysdate,var_dateFormate);
       var_nextDay varchar2(8) /*:=to_char(sysdate+1,'YYYYMMDD')*/;--��ȡ��һ������
       var_platDateRow plat_date_param%rowtype; 

begin
  --��ѯ����ԭʼ����
  select * into var_platDateRow from plat_date_param where sysname='cbpay';

  var_nextDay:=to_char((to_date(var_platDateRow.Nextacdt,var_dateFormate)+1),var_dateFormate);
  update plat_date_param set  acdt = var_platDateRow.NEXTACDT, lastacdt = var_platDateRow.acdt, nextacdt = var_nextDay  where sysname='cbpay';
    
  tool_pro(var_platDateRow.NEXTACDT,'plat_date_param',var_platDateRow.NEXTACDT,var_platDateRow.acdt,'ҵ�������л��ɹ�','insert');  
  commit;
  exception
    when OTHERS then
      errorlog_pro(var_currentDate,'switchplatdate',SQLCODE,SQLERRM,dbms_utility.format_error_backtrace);
      RAISE;
end switchplatdate;
/
