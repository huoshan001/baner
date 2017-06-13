package org.inn.baner.handler.data;

import com.ztkx.cbpay.business.bean.BPaymentFlow;
import com.ztkx.cbpay.business.constant.BusinessConstantField;
import com.ztkx.cbpay.container.HandlerException;
import com.ztkx.cbpay.container.initload.AbstractDbMapper;
import com.ztkx.cbpay.container.service.ServiceException;
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
 * ������ˮ������࣬
 * @author tianguangzhao
 *update by tianguangzhao 2016/4/11 ��ÿ��updateʱ������ʱ�������¼������ʱ�䣡
 */
public class BPaymentFlowData extends AbstractDbMapper {

	Logger logger = Logger.getLogger(BPaymentFlowData.class);
	static String tableName = "B_PAYMENT_FLOW";

	// ����ʱ����ʱ����ʱδ�õ����Ժ������������ʱ�������ݿ���ֱ�Ӷ��ˣ��Ƚ����׻�ͨ�����ļ��е����ݣ������������ݿ��У�Ȼ��Ա���ʱ��ͽ�����ˮ���е����ݽ��ж��ˡ�
	// 2016/4/29 tiangunagzhao ����ʱ���û�е����ݺͶ�������ݣ�Ȼ�����Ǽ�
	static String tableNameTmp = "B_PAYMENT_FLOW_TMP";

	/**
	 * ��ѯ������ˮ����
	 *
	 * @param merchantId
	 *            �̻���
	 * @param orderId
	 *            ������
	 * @return
	 * @throws ServiceException
	 */
	public boolean selectCount(String payorderid) throws HandlerException {

		if (logger.isDebugEnabled()) {
			logger.debug(" select count from " + tableName + " where payorderid = [" + payorderid + "]");
		}
		getConnection();
		String sql = "select count(1) as num from " + tableName + " where payorderid = ? ";

		boolean flag = false;
		try {
			psmt = DataSourceUtil.getPreparedStatement(connection, sql);
			psmt.setString(1, payorderid);
			rs = psmt.executeQuery();
			if (rs.next()) {
				String num = rs.getString("num");
				if (num != null && !num.equals("0")) {
					flag = true;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("exec sql error", e);
			throw new HandlerException(e);
		} finally {
			relaceResource();
		}
		return flag;
	}

	/**
	 * ������ˮ���в�����Ϣ
	 *
	 * @param bPaymentFlowBean
	 * @author tianguangzhao
	 * @throws HandlerException
	 * update by tianguangzhao 2016/04/11 ���� ��֧�����ʱ���ֶΡ�
	 */
	public void insertData(BPaymentFlow bPaymentFlowBean) throws HandlerException {

		String sqlStatement = "insert into "
				+ tableName
				+ " (PAYORDERID,TRANDATE,TRANTIME,TXNAMT,CURRENCY,MERCHANTID,ORDERID,ORDERTIME,CHANNEL,PAYTIME,PAYSTATUS,PURCHASERID,CHECKINGSTATUS,TMSMP��ORDERTDATE,PAYCARD,PAYDATE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);
			LinkedHashMap map = new LinkedHashMap();
			List<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
			map.put("PAYORDERID",bPaymentFlowBean.getPayorderid());
			map.put("TRANDATE",bPaymentFlowBean.getTrandate());
			map.put("TRANTIME",bPaymentFlowBean.getTrantime());
			map.put("TXNAMT",bPaymentFlowBean.getTxnamt());
			map.put("CURRENCY",bPaymentFlowBean.getCurrency());
			map.put("MERCHANTID",bPaymentFlowBean.getMerchantid());
			map.put("ORDERID",bPaymentFlowBean.getOrderid());
			map.put("ORDERTIME",bPaymentFlowBean.getOrdertime());
			map.put("CHANNEL",bPaymentFlowBean.getChannel());
			map.put("PAYTIME",bPaymentFlowBean.getPaytime());
			map.put("PAYSTATUS",bPaymentFlowBean.getPaystatus());
			map.put("PURCHASERID",bPaymentFlowBean.getPurchaserid());
			map.put("CHECKINGSTATUS",bPaymentFlowBean.getCheckingstatus());
			map.put("TMSMP",time);
			map.put("ORDERTDATE",bPaymentFlowBean.getOrderdate());
			map.put("PAYCARD",bPaymentFlowBean.getPaycard());
			map.put("PAYDATE",bPaymentFlowBean.getPaydate());
			list.add(map);
			batchExecuteUpdate(sqlStatement, list);
		   //	connection.commit();
			if (logger.isDebugEnabled()) {
				logger.debug(" insert " + tableName + " table  sucess !");
			}
		} catch (HandlerException e) {
			logger.error("insert " + tableName + " table data error !", e);
			// update by tianguangzhao 2016/4/27 ������ƽ����ϲ�
			// try {
			// connection.rollback();
			// } catch (SQLException e1) {
			// logger.error("connection roll back error !");
			// new HandlerException(e1);
			// }
			throw e;
		}
	}

/**
 * ����֧����ˮ�Ÿ���֧����ˮ���е�����
 * @param pa ֧����ˮ��
 * @param tranState ����״̬
 * @param paydate  ֧���������
 * @param paytime  ֧�����ʱ��
 * @return
 * @throws HandlerException
 */
	public int updatePayFlowStatus(String payorderid, String tranState,String paydate,String paytime) throws HandlerException {
		int count = 0;
		String sqlStatement = "update " + tableName + " set PAYSTATUS = ?, PAYDATE= ? ,PAYTIME = ? , TMSMP = ? where PAYORDERID = ?";
		try {
			LinkedHashMap map = new LinkedHashMap();
			List<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
			map.put("PAYSTATUS", tranState);
			map.put("PAYDATE", paydate);
			map.put("PAYTIME", paytime);
			//��������ʱ�����update by tianguangzhao 2016/4/11
			String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);
			map.put("TMSMP", time);
			map.put("PAYORDERID", payorderid);
			list.add(map);
			count = batchExecuteUpdate(sqlStatement, list);
			if (logger.isDebugEnabled()) {
				logger.debug("update table success !");
			}
		} catch (HandlerException e) {
			logger.error("update table " + tableName + " error !", e);
			throw e;
		}

		return count;
	}

	/**
	 * ��ѯ���е�����
	 *
	 * @param sqlStatement
	 *            ��Ҫ��ѯ��sql���
	 * @param map
	 *            ����
	 * @return ����Ҫ���list����
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
	 * ����֧����ˮ�Ż�ȡ֧����ˮ��Ϣ
	 *
	 * @param payorderid
	 *            ,֧����ˮ��
	 * @return
	 * @throws HandlerException
	 */
	public List<Map<String, String>> getPaymentFlowByPayorderid(String payorderid) throws HandlerException {
		String sqlStatement = "select * from  " + tableName + " where PAYORDERID = ?  ";
		LinkedHashMap map = new LinkedHashMap();
		map.put("PAYORDERID", payorderid);
		return executeQuery(sqlStatement, map);
	}

	/**
	 * ���ݽ������ڻ�ȡ������ˮ��Ϣ�����ڶ���ʱ�������������ڽ��ж���
	 *
	 * @param trandate
	 *            ,��������
	 * @return
	 * @throws HandlerException
	 */
	public List<Map<String, String>> getPaymentFlowByTrandate(String trandate) throws HandlerException {
		String sqlStatement = "select * from  " + tableName + " where TRANDATE = ?  ";
		LinkedHashMap map = new LinkedHashMap();
		map.put("TRANDATE", trandate);
		return executeQuery(sqlStatement, map);
	}

	/**
	 * ���ݽ������ں��̻��Ż�ȡ������ˮ��Ϣ�����ڶ���ʱ�������������ڽ��ж���
	 *
	 * @param trandate
	 *            ,��������
	 * @return
	 * @throws HandlerException
	 */
	public List<Map<String, String>> getInfoByTrandateAndMerchantNo(String trandate,String merchantNo) throws HandlerException {
		String sqlStatement = "select * from  " + tableName + " where TRANDATE = ? and  MERCHANTID = ? ";
		LinkedHashMap map = new LinkedHashMap();
		map.put("TRANDATE", trandate);
		map.put("MERCHANTID", merchantNo);
		return executeQuery(sqlStatement, map);
	}

	/**
	 * ���ݽ�����ˮ�Ż�ȡ�����ʵĽ�����ˮ��Ϣ�����ڶ���ʱ�������������ڽ��ж���
	 *
	 * @param trandate
	 *            ,��������
	 * @return
	 * @throws HandlerException
	 */
	public List<Map<String, String>> getMerchantNosByTrandateAndStatus(String trandate,String status) throws HandlerException {
		String sqlStatement = "select distinct MERCHANTID from  " + tableName + " where TRANDATE = ?  and CHECKINGSTATUS =? ";
		LinkedHashMap map = new LinkedHashMap();
		map.put("TRANDATE", trandate);
		map.put("CHECKINGSTATUS", status);
		return executeQuery(sqlStatement, map);
	}

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
	public int batchExecuteUpdate(String sqlStatement,
			List<LinkedHashMap<String, String>> list) throws HandlerException {

		logger.info("sql[" + sqlStatement + "]");
		logger.info("params list is " + list);
		int res = 0;
		try {
			if (list != null && list.size() > 0) {
				// ���list����null����psmt�ķ�ʽ����
				psmt = DataSourceUtil.getPreparedStatement(connection,sqlStatement);
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
	 * ���ݽ������ڸ������ݿ��н���֧����ˮ��״̬
	 *
	 * @param accountDate
	 * @param status
	 * @return
	 * @throws HandlerException
	 */
	public int updateStatusByTrandate(String trandate, String finalstatus) throws HandlerException {
		int count = 0;
		//�˴�����trandate���и��£��Ժ�ע���޸�
		try {
			String sqlStatement = "update  " + tableName + " set CHECKINGSTATUS = ? , TMSMP = ? where TRANDATE = ? ";
			LinkedHashMap map = new LinkedHashMap();
			List<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
			map.put("CHECKINGSTATUS", finalstatus);
			//��������ʱ�����update by tianguangzhao 2016/4/11
			String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);
			map.put("TMSMP", time);
			map.put("TRANDATE", trandate);
			list.add(map);
			count = batchExecuteUpdate(sqlStatement, list);
		} catch (HandlerException e) {
			logger.error("update error !", e);
			throw e;
		}
		return count;
	}


	/**
	 * �Ƚ����ݿ��е����ݣ����ж���,���ҷ�����Ϊ׼
	 * ��ʱδ�õ�
	 * @param accountDate
	 * @return
	 * @throws HandlerException
	 */
	public int checkingPayFlowByOurData(String accountdate) throws HandlerException {
		int i = -1;
		if (logger.isDebugEnabled()) {
			logger.debug(" checking pay flow start ! accountdate = [" + accountdate + "]");
		}

		LinkedHashMap map = new LinkedHashMap();
		String sqlStatement = "select count(1) as NUM from "
				+ tableName
				+ " a  where a.accountdate = ? and NOT EXISTS (select b.orderid from "
				+ tableNameTmp
				+ " b where  a.ordertdate= b.ordertdate and a.orderid = b.orderid and a.txnamt = b.txnamt and a.currency  = b.currency and a.paystatus = b.paystatus)";
		map.put("accountdate", accountdate);
		try {
			List<Map<String, String>> list = executeQuery(sqlStatement, map);
			if (list == null || list.size() == 0) {
				i = 0;
			} else {
				Map<String, String> sesultMap = list.get(0);
				String num = sesultMap.get("NUM");
				i = Integer.parseInt(num);
			}
		} catch (HandlerException e) {
			logger.error("checking pay flow error !", e);
			throw e;
		}

		return i;
	}

	/**
	 * �Ƚ����ݿ��е����ݣ����ж��ˣ����յ�������Ϊ׼
	 * ��ʱδ�õ�
	 * @param accountDate
	 * @return
	 * @throws HandlerException
	 */
	public int checkingPayFlowByUMB(String accountdate) throws HandlerException {
		int i = -1;
		if (logger.isDebugEnabled()) {
			logger.debug(" checking pay flow start ! accountdate = ["
					+ accountdate + "]");
		}

		LinkedHashMap map = new LinkedHashMap();
		String sqlStatement = "select count(1) as NUM from "
				+ tableNameTmp
				+ " b  where  NOT EXISTS (select a.orderid from "
				+ tableName
				+ " a where a.accountdate = ? and b.ordertdate= a.ordertdate and a.orderid = b.orderid  and a.txnamt = b.txnamt and a.currency  = b.currency and a.paystatus = b.paystatus)";
		map.put("accountdate", accountdate);
		try {
			List<Map<String, String>> list = executeQuery(sqlStatement, map);
			Map<String, String> sesultMap = list.get(0);
			String num = sesultMap.get("NUM");
			i = Integer.parseInt(num);
		} catch (HandlerException e) {
			logger.error("checking pay flow error !", e);
			throw e;
		}

		return i;
	}

	/**
	 * ����֧��״̬��ȡ֧����ˮ��Ϣ�����ڶ�ʱ���񣬲�ѯ֧��״̬ctb002
	 *
	 * @param payorderid
	 *            ,֧����ˮ��
	 * @return
	 * @throws HandlerException
	 * update by tianguangzhao 2016/5/20 ����ʱ����ˣ�ֻ��ѯ ����ͽ���Ľ���!
	 */
	public List<Map<String, String>> getRecentlyInfoByPaystatus(String paystatus)
			throws HandlerException {
		String sqlStatement = "select * from  " + tableName + " where PAYSTATUS = ?  and TRANDATE >= (select LASTACDT from PLAT_DATE_PARAM t )  ";
		LinkedHashMap map = new LinkedHashMap();
		map.put("PAYSTATUS", paystatus);
		return executeQuery(sqlStatement, map);
	}
}
