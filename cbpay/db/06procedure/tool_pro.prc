create or replace procedure tool_pro(PLATDATE in varchar2,TABLENAME in varchar2,DATAPARTNEW in varchar2,DATAPARTOLD in varchar2,STATUS in varchar2,opr_type in varchar2)
  is
--------------------------------
--���ڣ�2016��6��4��
--���ߣ�zhangxiaoyun
--���ܣ��ǼǱ������־���ݿ�
--------------------------------
  var_inser_sql varchar2(2000);
  var_update_sql varchar2(2000);
begin
  if opr_type='insert' then
    --��ʼ������־
    var_inser_sql:='insert into SWITCHDATAPARTLOG (platdate, tablename, datapartnew, datapartold, status) values ('''||PLATDATE||''', '''||TABLENAME||''', '''||DATAPARTNEW||''', '''||DATAPARTOLD||''', '''||STATUS||''')';
    execute immediate var_inser_sql;
  elsif opr_type='update' then
    var_update_sql:='update SWITCHDATAPARTLOG set status = '''||STATUS||'''';
    if DATAPARTNEW is not null then
      var_update_sql:=var_update_sql||',datapartnew = '''||DATAPARTNEW||'''';
    end if;
    if DATAPARTOLD is not null then
      var_update_sql:=var_update_sql||',datapartold = '''||DATAPARTOLD||'''';
    end if;

    var_update_sql:=var_update_sql||' where platdate='''||PLATDATE||''' and tablename='''||TABLENAME||'''';
    execute immediate var_update_sql;
  end if;


  commit;--�ύ����
end tool_pro;
/
