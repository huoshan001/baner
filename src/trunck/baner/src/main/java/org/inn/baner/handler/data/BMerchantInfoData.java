package org.inn.baner.handler.data;

import com.ztkx.cbpay.container.HandlerException;
import com.ztkx.cbpay.container.initload.AbstractDbMapper;
import com.ztkx.cbpay.platformutil.db.DBUtil;
import com.ztkx.cbpay.platformutil.db.c3p0.DataSourceUtil;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BMerchantInfoData extends AbstractDbMapper {

	Logger logger = Logger.getLogger(BMerchantInfoData.class);

	/**
	 * �����̻�id��ѯ�̻���Ϣ
	 *
	 * @param merchantId
	 * @return 2016��3��15�� ����3:20:46
	 * @author zhangxiaoyun update by tianguangzhao 2016/3/16 ����valid �Ƿ���ñ�ʶ
	 * @throws HandlerException
	 */
	public List<Map<String, String>> getBMerchantInfo(String merchantId) throws HandlerException {

		String sqlStatement = "select * from B_MERCHANT_INFO where MERCHANTID=? and VALID ='0' ";
		getConnection();
		logger.info("sql[" + sqlStatement + "]");
		logger.info("MERCHANTID [" + merchantId + "]");
		List<Map<String, String>> resList = null;
		try {
			psmt = DataSourceUtil
					.getPreparedStatement(connection, sqlStatement);
			// ��ѯ�����в���ע�뵽psmt��
			psmt.setString(1, merchantId);
			// ִ��sql���
			rs = psmt.executeQuery();
			resList = DBUtil.resConvertList(rs);
		} catch (SQLException e) {
			logger.error("execute sqlstatement exception", e);
			throw new HandlerException(e);
		} finally {
			// �ͷ���Դ
			relaceResource();
		}
		return resList;
	}

	/**
	 * ��ȡ���������̻�����Ϣ�����ڵ��üƷ�ģ���̻����ʲ�ѯ����
	 *
	 * @return ��װ���̻���Ϣ��list
	 * @throws HandlerException
	 */
	public List<Map<String, String>> getAllMerchantInfo()
			throws HandlerException {
		String sqlStatement = "select * from B_MERCHANT_INFO where VALID ='0' ";
		getConnection();
		logger.info("sql[" + sqlStatement + "]");
		List<Map<String, String>> resList = null;
		try {
			psmt = DataSourceUtil
					.getPreparedStatement(connection, sqlStatement);
			// ִ��sql���
			rs = psmt.executeQuery();
			resList = DBUtil.resConvertList(rs);
		} catch (SQLException e) {
			logger.error("execute sqlstatement exception", e);
			throw new HandlerException(e);
		} finally {
			// �ͷ���Դ
			relaceResource();
		}
		return resList;

	}
}
