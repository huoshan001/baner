create or replace procedure errorlog_pro(PLATDATE in varchar2,PRONAME in varchar2,ERRORCODE in varchar2,ERRORMSG in varchar2,STACKTRACE in varchar2)
  is
begin
  insert into proerrlog (platdate, proname, errorcode, ERRORMSG, STACKTRACE)
	values
	(PLATDATE, PRONAME, ERRORCODE, ERRORMSG, STACKTRACE);

  commit;--�ύ����
end errorlog_pro;
/
