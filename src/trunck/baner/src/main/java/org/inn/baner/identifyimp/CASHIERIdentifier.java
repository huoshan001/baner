package org.inn.baner.identifyimp;

import com.ztkx.cbpay.container.constant.ContainerConstantField;
import com.ztkx.cbpay.container.msg.MessageIdentifier;
import com.ztkx.cbpay.platformutil.context.CommonContext;
import org.apache.log4j.Logger;

/**
 * �������еĽ�������url���Ҫ��ȡurlȻ���ȡ����
 * @author zhangxiaoyun
 * 2016��2��2�� ����5:03:20
 * <p>Company:ztkx</p>
 */
public class CASHIERIdentifier implements MessageIdentifier{
	private Logger logger = Logger.getLogger(CASHIERIdentifier.class);
	@Override
	public String identify(CommonContext context) {
		//url = https://�������п羳������������ַ/CBEC/CEBATEXC.do;
		String url = context.getFieldStr(ContainerConstantField.REQ_URL);
		
		if(url==null){
			return null;
		}
		String tranCode = url.substring(url.lastIndexOf("/") + 1,url.lastIndexOf(".action"));
		return tranCode;
	}
}
