<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<link rel='icon' href='img/msds.ico' type='image/x-ico'/>
<title>tiedcard</title>
<link rel="stylesheet" type="text/css" href="css/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="css/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/bootstramp/buttons.css">
<script type="text/javascript" src="js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
</head>
<body>
	<div id="p" iconCls="icon-man" class="easyui-panel" title="��" style="width:90%;height:400px;padding:10px;">
		<form id="tiedcard" action="login" method="post">
			<input type="hidden" name="merchantId" value="${merchantTiedCardMsg.head.merchantNo }"/>
			<input type="hidden" name="orderDate" value="${merchantTiedCardMsg.body.createDate }"/>
			<input type="hidden" name="orderTime" value="${merchantOrderMsg.body.createTime }"/>
			<input type="hidden" name="purchaserId" value="${merchantTiedCardMsg.body.purchaserId }"/>
			<input type="hidden" name="Flowno" value="${Flowno}"/>
				<table style="width: 90%; margin-left: 20px; height: 180px">
					<tr>
						<td width="30%" class="f2"><font size="3px">���п����ţ�</font></td>
						<td class="f3"><input id="bankCard" name="bankCard"
							class="easyui-validatebox textbox" required="required"
							validType="bankcard[16,19]" value=""
							style="height: 25px; width: 250px" /></td>
					</tr>
					<tr>
						<td class="f2"><font size="3px">���У�</font></td>
						<td class="f3"><input id="bankname" class="easyui-combobox" name="bankname"  required="required"
							style="height: 25px; width: 150px" data-options=" valueField: 'id', textField: 'text', url: 'bank'" />
						</td>
					</tr>
					<tr>
						<td class="f2"><font size="3px">�����У�</font></td>
						<td class="f3"><input id="cc1" class="easyui-combobox"
							style="height: 25px; width: 100px"
							data-options=" valueField: 'id', textField: 'text'" /> <input
							id="cc2" class="easyui-combobox"
							data-options="valueField:'id',textField:'text'"
							style="height: 25px; width: 100px" /></td>
					</tr>
					<tr>
						<td class="f2"><font size="3px">������</font></td>
						<td class="f3"><input id="personname" name="personname" required="required"
							class="easyui-validatebox textbox"
							data-options="valueField:'id',textField:'text'"
							style="height: 25px; width: 180px" /></td>
					</tr>
					<tr>
						<td class="f2"><font size="3px">֤���ţ�</font></td>
						<td class="f3"><select id="identitycard"
							class="easyui-combobox" name="identitycard"
							style="height: 27px; width: 80px">
								<option value="1" checked>���֤</option>
								<option value="2">��ʻ֤</option>
								<option value="3">��ס֤</option>
						</select> 
						 <input id="identitycardnum" name="identitycardnum"
							class="easyui-validatebox textbox" required="required"
							data-options="valueField:'id',textField:'text'"
							style="height: 25px; width: 180px" /></td>
					</tr>
					<tr>
						<td class="f2"><font size="3px">�ֻ��ţ�</font></td>
						<td class="f3"><input id="mobile" name="mobile"
							class="easyui-validatebox textbox" required="required"
							style="height: 25px; width: 180px" validType="phoneRex" value=""
							style="height: 20px;width: 180px" /></td>
					</tr>
					<tr>
						<td class="f2"><font size="3px">������֤�룺</font></td>
						<td><input id="sms" name="sms"
							class="easyui-validatebox textbox" required="required"
							validType="elength[6]" value=""
							style="height: 20px; width: 100px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" id="sms_html"
							onclick="sendsms()">���Ͷ���</a><font id="smsresult"></font></td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<a href="javascript:void(0)"
								class="button button-primary button-rounded button-small"
								onclick="thirdToEnd();">ȷ��</a>
						</td>
					</tr>
		</form>
	</div>
<style scoped>
.f1 {
	width: 200px;
}

.f2 {
	text-align: right;
}

.f3 {
	text-align: left;
}
</style>
<script type="text/javascript">
		$(function() {
			$.extend($.fn.validatebox.defaults.rules, {
				bankcard : {
					validator : function(value, param) {
						var rex = /^\d+$/;
						return value.length >= param[0]
								&& value.length <= param[1] && rex.test(value);
					},
					message : '����{0}��{1}λ����'
				},
				minLength : {
					validator : function(value, param) {
						return value.length >= param[0];
					},
					message : '����������{0}λ����'
				},
				elength : {
					validator : function(value, param) {
						return value.length == param[0];
					},
					message : '������6λ����'
				},
				phoneRex : {
					validator : function(value) {
						var rex = /^1[3-8]+\d{9}$/;
						var rex2 = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
						if (rex.test(value) || rex2.test(value)) {
							return true;
						} else {
							return false;
						}
					},
					message : '��������ȷ�绰���ֻ���ʽ'
				}
			});
		});
		var sendsms=function(){
			if(!$('#mobile').validatebox("isValid")){
				$('#mobile').click();
			}else if(!$('#personname').validatebox("isValid")){
				$('#personname').click();
			}else{
				$.ajax({
				cache : true,
				type : "POST",
				url : "smssend",
				data : $('#tiedcard').serializeArray(),// ���formid
				async : false,
				error : function(request) {
					location.href="error?pageReturnUrl="+encodeURI("${merchantTiedCardMsg.body.pageReturnUrl}")+"&respMsg="+encodeURI(encodeURI("֧��ϵͳ����"));
				},
				success : function(data) {
					flag = data;
					if(data=="true"){
						$("#smsresult").html("");
						$('#sms_html').hide();
						waitfor(120);
					}else{
						alert("���ŷ���ʧ�ܣ������ԣ�");
					}
				}
			});
			}
		}
		var thirdToEnd = function() {
			if(!$('#bankCard').validatebox("isValid")) {
				$('#bankCard').click();
			}else if(!$('#sms').validatebox("isValid")){
				$('#sms').click();
			}else if (!$('#personname').validatebox("isValid")) {
				$('#personname').click();
			} else if (!$('#identitycardnum').validatebox("isValid")) {
				$('#identitycardnum').click();
			} else if (!$('#mobile').validatebox("isValid")) {
				$('#mobile').click();
			}else{
				
				$.ajax({
					cache : true,
					type : "POST",
					url : "tiedcard",
					data : $('#tiedcard').serializeArray(),// ���formid
					async : false,
					error : function(request) {
						location.href="error?pageReturnUrl="+encodeURI("${merchantTiedCardMsg.body.pageReturnUrl}")+"&respMsg="+encodeURI(encodeURI("֧��ϵͳ����"));
					},
					success : function(data) {
						var result = data.split(";");
						if(result[0]=="true"){
							location.href="success?pageReturnUrl="+encodeURI("${merchantTiedCardMsg.body.pageReturnUrl}")
						}else if(result[0]=="smsfail"){
							alert("��֤��Ϣ�������������");
						}else if(result[0]=="CBP002"||result[0]=="CBP003"||result[0]=="CBP004"||result[0]=="CBP005"){
							alert(result[1]);
						}else if(data=="systemfail"){
							location.href="error?pageReturnUrl="+encodeURI("${merchantOrderMsg.body.pageReturnUrl}")+"&respMsg="+encodeURI(encodeURI("֧��ϵͳ����"));
						}else{
							location.href="error?pageReturnUrl="+encodeURI("${merchantOrderMsg.body.pageReturnUrl}")+"&respMsg="+encodeURI(encodeURI(result[1]));
						}
					}
				});
			}
		}
		function waitfor(sec){
			$("#smsresult").html('���ŷ��ͳɹ���'+sec+'S���������');
			if(sec==0){
				$("#smsresult").html('');
				$('#sms_html').show();
			}else{
				window.setTimeout('waitfor('+(sec-1)+')',1000);
			}
		}
	</script>
</body>
</html>