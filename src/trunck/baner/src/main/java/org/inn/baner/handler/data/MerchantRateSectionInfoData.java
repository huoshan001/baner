package org.inn.baner.handler.data;

import com.ztkx.cbpay.business.bean.BMerchantRateSectionInfo;
import com.ztkx.cbpay.business.constant.BusinessConstantField;
import com.ztkx.cbpay.container.HandlerException;
import com.ztkx.cbpay.container.initload.AbstractDbMapper;
import com.ztkx.cbpay.platformutil.db.DBUtil;
import com.ztkx.cbpay.platformutil.db.c3p0.DataSourceUtil;
import com.ztkx.cbpay.platformutil.time.TimeUtil;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * �����̻�������ϸ��Ϣ���data�࣬�˱��д��
 *
 * @author tianguangzhao
 *
 */
public class MerchantRateSectionInfoData extends AbstractDbMapper {
	Logger logger = Logger.getLogger(MerchantRateSectionInfoData.class);
	static String tableName = "B_MERCHANT_RATE_SECTION_INFO";

	/**
	 * ������ִ��update����
	 *
	 * @param sqlStatement
	 *            ��Ҫִ�е�sql���
	 * @param map
	 *            sql�е��ʺŲ���ֵ ���mapΪ��Ĭ�ϰ���ͳ��statement��ʽִ��sql
	 * @return �޸ĵļ�¼�� 2016��3��14�� ����2:19:21
	 * @author zhangxiaoyun
	 * @throws HandlerException
	 */
	public int batchExecuteUpdate(String sqlStatement,List<LinkedHashMap<String, String>> list) throws HandlerException {

		logger.info("sql[" + sqlStatement + "]");
		logger.info("params list is " + list);
		int res = 0;
		try {
			if (list != null && list.size() > 0) {
				// ���list����null����psmt�ķ�ʽ����
				psmt = DataSourceUtil.getPreparedStatement(connection,
						sqlStatement);

				LinkedHashMap<String, String> map = null;
				if (list.size() == 1) {
					map = list.get(0);
					// ��ѯ�����в���ע�뵽psmt��
					DBUtil.preparePsmt(map, psmt);
					// ִ��sql���
					res = psmt.executeUpdate();
				} else {
					// ��������
					for (int i = 0; i < list.size(); i++) {
						map = list.get(i);
						// ��ѯ�����в���ע�뵽psmt��
						DBUtil.preparePsmt(map, psmt);
						psmt.addBatch();
						if ((i + 1) % BusinessConstantField.BATCHNUM == 0) {
							res = res + psmt.executeBatch().length;
						}
					}
					// ����ڲ�����
					res = res + psmt.executeBatch().length;
				}
			} else {
				// ����stmt�ķ�ʽ����
				stmt = DataSourceUtil.getStatement(connection);
				res = stmt.executeUpdate(sqlStatement); // ִ��sql���
			}
		} catch (SQLException e) {
			logger.error("execute sqlstatement exception", e);
			throw new HandlerException(e);
		}
		return res;
	}

	/**
	 * ִ�в���sql��䷵�ز�ѯ���
	 *
	 * @param sqlStatement
	 *            ��Ҫִ�е�sql���
	 * @param map
	 *            sql�е��ʺŲ���ֵ ���mapΪ��Ĭ�ϰ���ͳ��statement��ʽִ��sql
	 * @return 2016��3��14�� ����2:19:21
	 * @author zhangxiaoyun
	 * @throws HandlerException
	 */
	public List<Map<String, String>> executeQuery(String sqlStatement,LinkedHashMap<String, String> map) throws HandlerException {

		getConnection();
		logger.info("sql[" + sqlStatement + "]");
		logger.info("params is " + map);
		List<Map<String, String>> resList = null;
		try {
			if (map != null) {
				// ���map����null����psmt�ķ�ʽ����
				psmt = DataSourceUtil.getPreparedStatement(connection,
						sqlStatement);
				DBUtil.preparePsmt(map, psmt);
				// ִ��sql���
				rs = psmt.executeQuery();
			} else {
				// ����stmt�ķ�ʽ����
				stmt = DataSourceUtil.getStatement(connection);
				rs = stmt.executeQuery(sqlStatement);
			}
			// �����յĲ�ѯ���ת��Ϊlist
			resList = DBUtil.resConvertList(rs);
			if (resList.size() <= 0) {
				resList = null;
			}
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
	 * ���ӼƷ�ģ���ȡ�������·������
	 *
	 * @param merchantRateInfo
	 * @throws HandlerException
	 */
	public void insertMerchantRateDetailInfo(List<BMerchantRateSectionInfo> merchantRateSectionInfos, String merchantNo) throws HandlerException {

		String sql = "insert into "
				+ tableName
				+ " (MERCHANTNO,RULEID,BEGININPUT,ENDINPUT,SECCHARGEMODE,SECCHARGEAMT,SECCHARGERATIO,TMSMP)  values (?,?,?,?,?,?,?,?) ";
		List<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
		for (BMerchantRateSectionInfo merchantRateSectionInfo : merchantRateSectionInfos) {
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("MERCHANTNO", merchantNo);
			map.put("RULEID", merchantRateSectionInfo.getRuleId());
			map.put("BEGININPUT", merchantRateSectionInfo.getBeginInput());
			map.put("ENDINPUT", merchantRateSectionInfo.getEndInput());
			map.put("SECCHARGEMODE", merchantRateSectionInfo.getSecChargeMode());
			map.put("SECCHARGEAMT", merchantRateSectionInfo.getSecChargeAmt());
			map.put("SECCHARGERATIO",
					merchantRateSectionInfo.getSecChargeRatio());
			map.put("TMSMP", TimeUtil
					.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE));
			list.add(map);
		}

		// �����������뷽���������ݲ������
		batchExecuteUpdate(sql, list);
	}
}
