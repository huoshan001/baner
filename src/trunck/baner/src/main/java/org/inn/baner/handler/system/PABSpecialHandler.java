package org.inn.baner.handler.system;


import com.ztkx.transplat.container.HandlerException;
import com.ztkx.transplat.container.initdata.ServerInfoData;
import com.ztkx.transplat.container.javabean.ServerInfo;
import com.ztkx.transplat.container.system.SystemHandler;
import com.ztkx.transplat.platformutil.baseconfig.ConstantConfigField;
import com.ztkx.transplat.platformutil.context.CommonContext;
import com.ztkx.transplat.platformutil.flowno.FlowNoPoolManager;
import com.ztkx.transplat.platformutil.time.TimeUtil;
import org.apache.log4j.Logger;
import org.inn.baner.initdata.BServerParamData;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * ƽ������ϵͳ���⴦����
 * @author zhangxiaoyun
 * 2016��3��17�� ����5:37:57
 * <p>Company:ztkx</p>
 */
public class PABSpecialHandler implements SystemHandler {
	private Logger logger = Logger.getLogger(PABSpecialHandler.class);

	@Override
	public CommonContext beforeHandler(CommonContext context) throws HandlerException{
		logger.info("start exec PABSpecialHandler beforeHandler");

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
		String custCode = BServerParamData.getInstance().getParamsValue(context.getSDO().serverId, BusinessConstantField.PAB_OUTSIDE_CUST_NO).getParavalue();
		sb.append(String.format("%1$-20s", custCode));

		int msgLen =0;
		try {
			msgLen = msg.getBytes(context.getSDO().enCodeing).length;
		} catch (UnsupportedEncodingException e) {
			logger.error("get msg length error",e);
			context.setErrorCode(BusinessErrorCodeConstant.BUSS_PLA000520);
			throw new HandlerException("get msg length error");
		}	//���㱨�ĳ���
		sb.append(String.format("%010d", msgLen));
		sb.append(context.getSDO().serverCode);		//��������
		sb.append(String.format("%1$-5s", " "));	//����Ա
		String servertype = "01";							//����
		sb.append(servertype);
		String date = TimeUtil.getCurrentTime("yyyyMMdd");
		sb.append(date);		//��������
		String time = TimeUtil.getCurrentTime("hhmmss");
		sb.append(time);		//����ʱ��
		String flowno = date+time+ FlowNoPoolManager.getInstance().getSequence();
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

		logger.info("PABSpecialHandler beforeHandler exec succ");
		return context;
	}

	/**
	 * ��������Ӧ���ĵ�ǰ222λ�ص�
	 */
	@Override
	public CommonContext afterHandler(CommonContext context) throws HandlerException{
		logger.info("start exec PABSpecialHandler afterHandler");

		byte[] msg = context.getByteArray(ConstantConfigField.ORIGINAL_MSG_BYTE_ARRAY, CommonContext.SCOPE_GLOBAL);
		try{
			byte[] msgHead = Arrays.copyOfRange(msg, 0, 222);

			String tranDate = new String(Arrays.copyOfRange(msgHead, 53, 61));
			logger.info("msg head tranDate ["+tranDate+"]");
			context.setFieldStr(BusinessConstantField.PAB_TRAN_DATE, tranDate, CommonContext.SCOPE_GLOBAL);

			byte[] resCode = Arrays.copyOfRange(msgHead, 87, 93);
			String resCodeStr = new String(resCode);
			context.setFieldStr(BusinessConstantField.PAB_RES_CODE, resCodeStr, CommonContext.SCOPE_GLOBAL);

			byte[] resMsg = Arrays.copyOfRange(msgHead, 93, 193);
			String resMsgStr = new String(resMsg,context.getSDO().enCodeing);
			context.setFieldStr(BusinessConstantField.PAB_RES_MSG, resMsgStr, CommonContext.SCOPE_GLOBAL);
//			String msgHeadStr = new String(msgHead,context.getSDO().enCodeing);
			context.setByteArray(BusinessConstantField.PAB_MSG_HEAD, msgHead, CommonContext.SCOPE_GLOBAL);
			byte[] msgBody =Arrays.copyOfRange(msg, 222, msg.length);
			String msgBodyStr = new String(msgBody,context.getSDO().enCodeing);
			logger.info("message body is ["+msgBodyStr+"]");
			context.setOrginalField(msgBodyStr);
			logger.info("resCode is ["+resCodeStr+"] resMsg is ["+resMsgStr+"]");
			String serverId = context.getSDO().serverId;

			ServerInfo info =  ServerInfoData.getInstance().getServerInfo(serverId);
			context.setFieldStr(info.getRes_code(), resCodeStr, CommonContext.SCOPE_GLOBAL);
			context.setFieldStr(info.getRes_msg(), resMsgStr, CommonContext.SCOPE_GLOBAL);
			if(!resCodeStr.trim().equals("000000")){
				throw new Exception("communicate with pab_svr faile");
			}
		}catch(Exception e){
			logger.error("PABCLISpecialHandler exec exception",e);
			context.setErrorCode(BusinessErrorCodeConstant.BUSS_PLA000520);
			throw new HandlerException(e);
		}
		logger.info("PABSpecialHandler afterHandler exec succ");
		return context;
	}

	public static void main(String[] args) {
	    int youNumber = 10;
	    // 0 ����ǰ�油��0
	    // 4 ������Ϊ4
	    // d �������Ϊ������
//	    String str = String.format("%080d", youNumber);     
	    String msg = "1234567890";
	    System.out.println("["+String.format("%1$-9s", "123456789")+"]");
	    System.out.println(msg.substring(3));
	    System.out.println("["+String.format("%1$-12s", "RSA-SHA1")+"]");      
	    System.out.println(String.format("%1$-100s", " "));      
	  }         

}
