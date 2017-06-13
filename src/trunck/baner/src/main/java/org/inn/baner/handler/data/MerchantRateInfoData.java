package org.inn.baner.handler.data;

import com.ztkx.cbpay.business.bean.BMerchantRateInfo;
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
 * �����̻����ʱ��data��
 *
 * @author tianguangzhao
 *
 */
public class MerchantRateInfoData extends AbstractDbMapper {
	Logger logger = Logger.getLogger(MerchantRateInfoData.class);
	static String tableName = "B_MERCHANT_RATE_INFO";

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

		relaceResource();
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
			getConnection();
		}
		return resList;

	}

	/**
	 * ���ӼƷ�ģ���ȡ�������·������,�˴�δ�޸ģ��Ժ�ע�����������
	 *
	 * @param merchantRateInfo
	 * @throws HandlerException
	 */
	public void insertMerchantRateInfo(List<BMerchantRateInfo> merchantRateInfos) throws HandlerException {
		// Ϊ��ȷ����ͬһ��������
		MerchantRateSectionInfoData merchantRateSectionData = new MerchantRateSectionInfoData();
		try {
			// �ر��Զ��ύ����
			connection.setAutoCommit(false);
			merchantRateSectionData.setConnection(connection);

			String sql = "insert into "
					+ tableName
					+ " (ID,MERCHANTNO,ROUNDINGMODE,CHARGEMODE,OVERSTRATEGY,CHARGECYCLE,BEGINAMT,ENDAMT,TRANTYPE,TRANCODE,PAYCHANNELCODE,BANKCARDCSTTYPE,BANKCARDTYPE,BANKCODE,CURTYPE,CHARGESTATESTR,STATE,WEIGHT,AVAILBEGINTIME,AVAILENDTIME,SECCHARGETYPE,TMSMP)  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			List<LinkedHashMap<String, String>> rateList = new ArrayList<LinkedHashMap<String, String>>();
			for (BMerchantRateInfo merchantRateInfo : merchantRateInfos) {
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				map.put("ID", merchantRateInfo.getId());
				map.put("MERCHANTNO", merchantRateInfo.getMerchantNo());
				map.put("ROUNDINGMODE", merchantRateInfo.getRoundingMode());
				map.put("CHARGEMODE", merchantRateInfo.getChargeMode());
				map.put("OVERSTRATEGY", merchantRateInfo.getOverStrategy());
				map.put("CHARGECYCLE", merchantRateInfo.getChargeCycle());
				map.put("BEGINAMT", merchantRateInfo.getBeginAmt());
				map.put("ENDAMT", merchantRateInfo.getEndAmt());
				map.put("TRANTYPE", merchantRateInfo.getTranType());
				map.put("TRANCODE", merchantRateInfo.getTranCode());
				map.put("PAYCHANNELCODE", merchantRateInfo.getPayChannelCode());
				map.put("BANKCARDCSTTYPE",merchantRateInfo.getBankCardCstType());
				map.put("BANKCARDTYPE", merchantRateInfo.getBankCardType());
				map.put("BANKCODE", merchantRateInfo.getBankCode());
				map.put("CURTYPE", merchantRateInfo.getCurType());
				map.put("CHARGESTATESTR", merchantRateInfo.getChargeStateStr());
				map.put("STATE", merchantRateInfo.getState());
				map.put("WEIGHT", merchantRateInfo.getWeight());
				map.put("AVAILBEGINTIME", merchantRateInfo.getAvailBeginTime());
				map.put("AVAILENDTIME", merchantRateInfo.getAvailEndTime());
				map.put("SECCHARGETYPE", merchantRateInfo.getSecChargeType());
				map.put("TMSMP",TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE));
				rateList.add(map);

				// �Ƚ������շ�������Ϣ�������ݿ���
				List<BMerchantRateSectionInfo> listSections = merchantRateInfo.getFeechargerulesections();
				merchantRateSectionData.insertMerchantRateDetailInfo(listSections,merchantRateInfo.getMerchantNo());
			}

			// �����������뷽���������ݲ������
			batchExecuteUpdate(sql, rateList);
			// ������������ȷ���֮���ֶ��ύ
			connection.commit();
		} catch (SQLException e) {
			logger.error(" merchant rate info insert into database error !", e);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(" connection roll back  error !", e);
				throw new HandlerException(e);
			}
			// ���쳣�׳�
			throw new HandlerException(e);
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				logger.error(" connetion set AutoCommit error !", e);
				throw new HandlerException(e);
			}
			// �ͷ���Դ
			merchantRateSectionData.getConnection();
		}

	}
}
