package org.inn.baner.identifyimp;

import com.ztkx.cbpay.container.msg.MessageIdentifier;
import com.ztkx.cbpay.platformutil.baseconfig.ConstantConfigField;
import com.ztkx.cbpay.platformutil.context.CommonContext;
import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * ��ȡƽ�����еĽ�����
 * @author zhangxiaoyun
 * 2016��3��30�� ����11:48:23
 * <p>Company:ztkx</p>
 */
public class PABCLIIdentifier implements MessageIdentifier{
	private Logger logger = Logger.getLogger(PABCLIIdentifier.class);
	@Override
	public String identify(CommonContext context) {
		byte[] msg = context.getByteArray(ConstantConfigField.ORIGINAL_MSG_BYTE_ARRAY, CommonContext.SCOPE_GLOBAL);
		byte[] tranCode =  Arrays.copyOfRange(msg, 40, 46);
		String tranCodeStr = new String(tranCode).trim();
		logger.info("tranCodeStr ["+tranCodeStr+"]");
		return tranCodeStr;
	}
}
