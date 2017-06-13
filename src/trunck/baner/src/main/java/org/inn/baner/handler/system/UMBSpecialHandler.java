package org.inn.baner.handler.system;

import com.ztkx.cbpay.business.constant.BusinessConstantField;
import com.ztkx.cbpay.business.constant.BusinessErrorCodeConstant;
import com.ztkx.cbpay.business.constant.BusinessMessageFormateConstant;
import com.ztkx.cbpay.container.HandlerException;
import com.ztkx.cbpay.container.initdata.ServerInfoData;
import com.ztkx.cbpay.container.javabean.ServerInfo;
import com.ztkx.cbpay.container.system.SystemHandler;
import com.ztkx.cbpay.container.util.ContextUtil;
import com.ztkx.cbpay.container.util.MessageUtil;
import com.ztkx.cbpay.platformutil.baseconfig.ConstantConfigField;
import com.ztkx.cbpay.platformutil.context.CommonContext;
import com.ztkx.cbpay.platformutil.flowno.FlowNoPoolManager;
import com.ztkx.cbpay.platformutil.time.TimeUtil;
import org.apache.log4j.Logger;

/**
 * ���׻�ͨ����ϵͳ���⴦����
 *
 * @author zhangxiaoyun 2016��2��2�� ����11:29:54
 *         <p>
 *         Company:ztkx
 *         </p>
 */
public class UMBSpecialHandler implements SystemHandler {
	private Logger logger = Logger.getLogger(UMBSpecialHandler.class);

	@Override
	public CommonContext beforeHandler(CommonContext context) throws HandlerException{
		logger.info("start exec UMBSpecialHandler beforeHandler");

		// ע�������ַ,���׻�ͨ����Ҫ������ʱע��ȡ����update by tianguangzhao  �˷������ڲ���ʱ
//		String serverCode = context.getSDO().serverCode;
//		String url_params = serverCode + ".do";
//		context.setFieldStr(ContainerConstantField.URL_PARAMS, url_params);

		try {
			// ע�빫������ͷ��ˮ��
			String merchantNo = context.getFieldStr(BusinessMessageFormateConstant.CASH_MERCHANT_NO,CommonContext.SCOPE_GLOBAL) == null ? "" : context.getFieldStr(
					BusinessMessageFormateConstant.CASH_MERCHANT_NO, CommonContext.SCOPE_GLOBAL);
			//����̨�����̻��źͱ��׻�ͨ�ӿ��е��ֶβ�����ͬ���˴���Ҫת��
			context.setFieldStr(BusinessMessageFormateConstant.UMB_MERCHANT_NO,merchantNo, CommonContext.SCOPE_GLOBAL);
			//ע�뽻����ˮ��
			String bunessFlowNo = merchantNo + TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE) + FlowNoPoolManager.getInstance().getSequence();
			context.setFieldStr(BusinessMessageFormateConstant.UMB_BUSS_FLOW_NO,bunessFlowNo, CommonContext.SCOPE_GLOBAL);
			//ע�뽻������
			String trandate = TimeUtil.getCurrentTime(BusinessConstantField.PLA_DATE_FORMATE);
			context.setFieldStr(BusinessMessageFormateConstant.UMB_TRAN_DATE, trandate,CommonContext.SCOPE_GLOBAL);
			//ע�뽻��ʱ��
			String trantime = TimeUtil.getCurrentTime(BusinessConstantField.PLA_TIME_FORMATE);
			context.setFieldStr(BusinessMessageFormateConstant.UMB_TRAN_TIME, trantime,CommonContext.SCOPE_GLOBAL);
			//ע�뱨������
			context.setFieldStr(BusinessMessageFormateConstant.UMB_MSG_TYP, "0001",CommonContext.SCOPE_GLOBAL);
			// ��ӡ����
			if (logger.isDebugEnabled()) {
			//	logger.debug("url_params = [" + url_params + "]");
				logger.debug("bunessFlowNo = [" + bunessFlowNo + "]");
				logger.debug("trandate = [" + trandate + "]");
				logger.debug("trantime = [" + trantime + "]");
			}
		} catch (Exception e) {
			//update by tianguangzhao 20160526 ע������룬���׳��쳣
			ContextUtil.setErrorCode(BusinessErrorCodeConstant.BUSS_PLA000520, context);
			logger.error("UMBSpecialHandler error !", e);
			throw new HandlerException(e);
		}
		logger.info("UMBSpecialHandler beforeHandler exec succ");
		return context;
	}

	@Override
	public CommonContext afterHandler(CommonContext context) {
		//���ݷ�����Ӧ���ж��Ƿ���Ҫʹ�ô�������ʽ
		try{
			String serverId = context.getSDO().serverId;
			ServerInfo server = ServerInfoData.getInstance().getServerInfo(serverId);
			String fieldName =  server.getRes_code();

			//��ʼԤ�������ȡ��Ӧ���ֶ�
			byte[] msg = context.getByteArray(ConstantConfigField.ORIGINAL_MSG_BYTE_ARRAY, CommonContext.SCOPE_GLOBAL);
			String respCode = MessageUtil.getTranCode(msg,fieldName);
			logger.info("repcode is ["+respCode+"]");
			if(!respCode.equals("C000000000")){
				//�����Ӧ�벻�ǳɹ���Ӧ�룬���ý��׵Ĳ��Ϊ������
				context.getSDO().svrErrUnpack = serverId;
				logger.info("error unpack file is ["+serverId+"]");
			}
			
		}catch(Exception e){
			logger.error("UMBSpecial  after Handler error !", e);
		}
		return context;
	}

}
