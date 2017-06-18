package org.inn.baner.handler.data;


import com.ztkx.transplat.container.HandlerException;
import com.ztkx.transplat.container.initload.AbstractTMJDBC;
import com.ztkx.transplat.platformutil.db.DBUtil;
import com.ztkx.transplat.platformutil.db.c3p0.DataSourceUtil;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BMerchantPermissionData extends AbstractTMJDBC {

	Logger logger = Logger.getLogger(BMerchantPermissionData.class);
	private String tableName = " b_merchant_permission ";
	

	/**
	 * �����̻�id��ѯ�̻���Ϣ
	 *
	 * @param merchantId
	 * @return 2016��3��15�� ����3:20:46
	 * @author zhangxiaoyun update by tianguangzhao 2016/3/16 ����valid �Ƿ���ñ�ʶ
	 * @throws HandlerException
	 */
	public List<Map<String, String>> getBMerchantPermission(String merchantId,String tranCode)
			throws HandlerException {

		String sqlStatement = "select * from "+tableName+" where MERCHANTID=? and trancode=? and VALID ='0' ";
		getConnection();
		logger.info("sql[" + sqlStatement + "]");
		logger.info("MERCHANTID ["+merchantId+"] tranCode["+tranCode+"]");
		List<Map<String, String>> resList = null;
		try {
			psmt = DataSourceUtil.getPreparedStatement(connection, sqlStatement);
			// ��ѯ�����в���ע�뵽psmt��
			psmt.setString(1, merchantId);
			psmt.setString(2, tranCode);
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
