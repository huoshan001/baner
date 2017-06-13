package org.inn.baner.handler.system;

import com.ztkx.cbpay.business.constant.BusinessConstantField;
import com.ztkx.cbpay.business.constant.BusinessErrorCodeConstant;
import com.ztkx.cbpay.business.initdata.BServerParamData;
import com.ztkx.cbpay.container.HandlerException;
import com.ztkx.cbpay.container.system.SystemHandler;
import com.ztkx.cbpay.platformutil.baseconfig.ConstantConfigField;
import com.ztkx.cbpay.platformutil.context.CommonContext;
import com.ztkx.cbpay.platformutil.time.TimeUtil;
import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * ƽ��������Ϊ�ͻ��˵�ϵͳ���⴦��
 * @author zhangxiaoyun
 * 2016��3��28�� ����4:48:23
 * <p>Company:ztkx</p>
 */
public class PABCLISpecialHandler implements SystemHandler {
	private Logger logger = Logger.getLogger(PABCLISpecialHandler.class);

	@Override
	public CommonContext beforeHandler(CommonContext context) throws HandlerException{
		logger.info("start exec PABCLISpecialHandler beforeHandler");
		byte[] msg = context.getByteArray(ConstantConfigField.ORIGINAL_MSG_BYTE_ARRAY, CommonContext.SCOPE_GLOBAL);
		try{
			byte[] msgHead = Arrays.copyOfRange(msg, 0, 222);
			String tranDate = new String(Arrays.copyOfRange(msgHead, 53, 61));
			String tranTime = new String(Arrays.copyOfRange(msgHead, 61, 67));
			String flowno = new String(Arrays.copyOfRange(msgHead, 67, 87));
			logger.info("msg head tranDate ["+tranDate+"] tranTime ["+tranTime+"]");
			context.setFieldStr(BusinessConstantField.PAB_TRAN_DATE, tranDate, CommonContext.SCOPE_GLOBAL);
			context.setFieldStr(BusinessConstantField.PAB_TRAN_TIME, tranTime, CommonContext.SCOPE_GLOBAL);
			context.setByteArray(BusinessConstantField.PAB_MSG_HEAD, msgHead, CommonContext.SCOPE_GLOBAL);
			context.setFieldStr(BusinessConstantField.PAB_MSGHEAD_FLOWNO, flowno, CommonContext.SCOPE_GLOBAL);
			byte[] msgBody =Arrays.copyOfRange(msg, 222, msg.length);
			String msgBodyStr = new String(msgBody,context.getSDO().enCodeing);
			logger.info("message body is ["+msgBodyStr+"]");
			context.setOrginalField(msgBodyStr);
		}catch(Exception e){
			logger.error("PABCLISpecialHandler exec exception",e);
			context.setErrorCode(BusinessErrorCodeConstant.BUSS_PLA000520);
			throw new HandlerException(e);
		}
		logger.info("PABCLISpecialHandler beforeHandler exec succ");
		return context;
	}

	/**
	 * ��������Ӧ���ĵ�ǰ222λ�ص�
	 */
	@Override
	public CommonContext afterHandler(CommonContext context) throws HandlerException{
		logger.info("start exec PABCLISpecialHandler afterHandler");

		StringBuilder sb = new StringBuilder();
		String msg = context.getOrginalField();

		String msgVersion = "A001";	//���İ汾
		sb.append(msgVersion);
		String targetSys = "01";	//Ŀ��ϵͳ
		sb.append(targetSys);
		String msgCode = "01";		//�����ʽ
		sb.append(msgCode);
		String protocolType = "01";		//Э������
		sb.append(protocolType);

		//��Ϊpab��������ʱ���̨��������ǲ���pab�������������id����д��
		String custCode = BServerParamData.getInstance().getParamsValue("PAB_SVR", BusinessConstantField.PAB_OUTSIDE_CUST_NO).getParavalue();
		sb.append(custCode);

		int msgLen = msg.getBytes().length;	//���㱨�ĳ���
		sb.append(String.format("%010d", msgLen));
		sb.append(String.format("%1$-6s", context.getSDO().TRANCODE));		//����������
		sb.append(String.format("%1$-5s", " "));	//����Ա
		String servertype = "02";							//��������    Ӧ��
		sb.append(servertype);
		String date = TimeUtil.getCurrentTime("yyyyMMdd");
		sb.append(date);		//��������
		String time = TimeUtil.getCurrentTime("hhmmss");
		sb.append(time);		//����ʱ��
		//��ȡ����ͷ��ˮ��
		String flowno = context.getFieldStr(BusinessConstantField.PAB_MSGHEAD_FLOWNO, CommonContext.SCOPE_GLOBAL);
		sb.append(flowno);
		sb.append("000000");		//��Ӧ�����Ҫ���������000000
		sb.append(String.format("%1$-100s", " "));		//��Ӧ��Ϣ
		sb.append("0");									//��������־
		sb.append("000");								//�������
		sb.append("0");									//ǩ����־
		sb.append("1");									//ǩ�����ݰ���ʽ
		sb.append(String.format("%1$-12s", "RSA-SHA1"));									//ǩ���㷨
		sb.append(String.format("%010d", 0));			//ǩ�����ݳ���
		sb.append("0");									//�Ƿ��и�������Ĭ����0�Ժ��б�Ҫ�ٸ�
//		if("0".equals(context.getFieldStr(BusinessConstantField.ISTRANSFERFILE, CommonContext.SCOPE_GLOBAL))){
//			sb.append("0");
//		}else if("1".equals(context.getFieldStr(BusinessConstantField.ISTRANSFERFILE, CommonContext.SCOPE_GLOBAL))){
//			sb.append("1");
//		}

		String msgHeader = sb.toString();
		logger.info("PAB msg header is :"+sb.toString()+" msg head length is "+msgHeader.getBytes().length);
		context.setOrginalField(msgHeader+msg);

		logger.info("PABCLISpecialHandler afterHandler exec succ");
		return context;
	}

	public static void main(String[] args) {


		int youNumber = 10;
	    // 0 ����ǰ�油��0
	    // 4 ������Ϊ4
	    // d �������Ϊ������
//	    String str = String.format("%080d", youNumber);     
	    String msg = "1234567890";
//	    System.out.println(msg.substring(3));
	    System.out.println(String.format("%1$-12s", "RSA-SHA1"));      
	    System.out.println(String.format("%1$-100s", " "));      
	  }         

}
