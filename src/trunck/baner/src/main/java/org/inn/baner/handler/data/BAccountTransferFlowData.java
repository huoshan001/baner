package org.inn.baner.handler.data;


import com.ztkx.transplat.container.HandlerException;
import com.ztkx.transplat.container.initload.AbstractTMJDBC;
import com.ztkx.transplat.platformutil.db.DBUtil;
import com.ztkx.transplat.platformutil.db.c3p0.DataSourceUtil;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * �����˻���ת��Ϣ�����ݵ���
 *
 * @author tianguangzhao
 *
 */
public class BAccountTransferFlowData extends AbstractTMJDBC {

	Logger logger = Logger.getLogger(BAccountTransferFlowData.class);
	static String tableName = "B_ACCOUNT_TRANSFER_FLOW";
	// ����ʱ�õ�����ʱ��
	static String tableNameTmp = "B_ACCOUNT_TRANSFER_FLOW_TMP";

	/**
	 * ���ô˷��������˻���ת�Ľ��,�����˻���ת����
	 *
	 * @param transferFlowNo���˻���ת���κ�
	 * @param satus
	 * @param payTxnDate
	 * @param paytxnTime
	 * update by tianguangzhao 2016/4/27 ȥ��֧��������ˮ�� �����׻�ͨ���ص���ˮ��(���ڱ��׻�ͨ������)
	 * @return
	 * @throws HandlerException
	 */
	public int updateTransInfo(String transferBatchNo, String satus, String payTxnDate,String paytxnTime) throws HandlerException {
		int count = 0;
		String sql = "update " + tableName + " set ACTTRAFSTATUS= ? ,PAYTXNDATE =? , PAYTXNTIME = ? ,TMSMP = ? where  TRANSFERBATCHNO = ?";
		try {
			LinkedHashMap map = new LinkedHashMap();
			map.put("ACTTRAFSTATUS", satus);
			String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);
			map.put("PAYTXNDATE", payTxnDate);
			map.put("PAYTXNTIME", paytxnTime);
			map.put("TMSMP", time);
			map.put("TRANSFERBATCHNO", transferBatchNo);
			count = executeUpdate(sql, map);
			if (logger.isDebugEnabled()) {
				logger.debug(" update table success !");
			}
		} catch (HandlerException e) {
			logger.error("exec sql error", e);
			throw e;
		}
		return count;
	}

	/**
	 * ����֧����ˮ��Ϣ
	 *
	 * @param acountTransferFlowList
	 * @throws HandlerException
	 */
	public void insertRecord(List<BAccountTransferFlow> acountTransferFlowList)throws HandlerException {
		String sqlStatement = "insert into "+ tableName
				+ " (TRANSFERBATCHNO,TXNDATE,TXNTIME,CURRENCY,TXNAMT,PAYACCOUNTNO,TRANSFERFLG,ACTTRAFSTATUS,PAYTXNDATE,PAYTXNTIME,BUYBATNO,PAYBATNO,TMSMP,CHECKINGSTATUS,ORDERID,BAK,EXRATE,RECACCOUNTNO,TRANSTYPE,TRANSFERFLOWNO,PAYCUSTOMERNO,RECCUSTOMERNO) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			List<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
			// ѭ���������
			for (BAccountTransferFlow acountTransferFlow : acountTransferFlowList) {
				LinkedHashMap map = new LinkedHashMap();
				map.put("TRANSFERBATCHNO", acountTransferFlow.getTransferBatchNo());
				map.put("TXNDATE", acountTransferFlow.getTxnDate());
				map.put("TXNTIME", acountTransferFlow.getTxnTime());
				map.put("CURRENCY", acountTransferFlow.getCurrency());
				map.put("TXNAMT", acountTransferFlow.getTxnAmt());
				map.put("PAYACCOUNTNO", acountTransferFlow.getPayAccountNo());
				map.put("TRANSFERFLG", acountTransferFlow.getTransferFlg());
				map.put("ACTTRAFSTATUS", acountTransferFlow.getActTrafStatus());
				map.put("PAYTXNDATE", acountTransferFlow.getPayTxnDate());
				map.put("PAYTXNTIME", acountTransferFlow.getPaytxnTime());
				map.put("BUYBATNO", acountTransferFlow.getBuyBatNo());
				map.put("PAYBATNO", acountTransferFlow.getPayBatNo());
				// ��ȡʱ���
				String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);
				map.put("TMSMP", time);
				map.put("CHECKINGSTATUS",acountTransferFlow.getCheckingStatus());
				map.put("ORDERID", acountTransferFlow.getOrderid());
				map.put("BAK", acountTransferFlow.getBak());
				map.put("EXRATE", acountTransferFlow.getExrate());
				map.put("RECACCOUNTNO", acountTransferFlow.getRecAccountNo());
				map.put("TRANSTYPE", acountTransferFlow.getTransType());
				map.put("TRANSFERFLOWNO", acountTransferFlow.getTransferFlowNo());
				//PAYCUSTOMERNO,RECCUSTOMERNO, update by tianguangzhao 2016/5/9 �����ո���ͻ���
				map.put("PAYCUSTOMERNO", acountTransferFlow.getPayCustomerNo());
				map.put("RECCUSTOMERNO", acountTransferFlow.getRecCustomerNo());
				list.add(map);
			}
			int count = batchExecuteUpdate(sqlStatement, list);
			if (logger.isDebugEnabled()) {
				logger.debug("insert table " + tableName + " success ! insert count =[" + count + "] !");
			}
		} catch (HandlerException e) {
			logger.error("exec sql error", e);
			throw new HandlerException(e);
		}
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
				psmt = DataSourceUtil.getPreparedStatement(connection,sqlStatement);
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
	 * ���ݽ�����ˮ�Ż�ȡ�˻���ת��Ϣ
	 *
	 * @param transferFlowNo,��ת���κ�
	 * @return
	 * @throws HandlerException
	 */
	public List<Map<String, String>> getAcountTransferFlowInfoByTransferBatchNo(String transferBatchNo) throws HandlerException {
		LinkedHashMap map = new LinkedHashMap();
		String sqlStatement = "select distinct ACTTRAFSTATUS  from " + tableName + " where TRANSFERBATCHNO = ? ";
		map.put("TRANSFERFLOWNO", transferBatchNo);
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
	public int executeUpdate(String sqlStatement,LinkedHashMap<String, String> map) throws HandlerException {

		logger.info("sql[" + sqlStatement + "]");
		logger.info("params map is " + map);
		int res = 0;
		try {
			if (map != null && map.size() > 0) {
				// ���list����null����psmt�ķ�ʽ����
				psmt = DataSourceUtil.getPreparedStatement(connection,sqlStatement);
				// ��ѯ�����в���ע�뵽psmt��
				DBUtil.preparePsmt(map, psmt);
				// ִ��sql���
				res = psmt.executeUpdate();
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
	 * ���׻�ͨ�����˻���ת����󣬸��±��е���Ϣ
	 *
	 * @param transferBatchNo
	 *            ��ת��ˮ��
	 * @param tranDate
	 *            ��ת����
	 * @param tranTime
	 *            ��תʱ��
	 * @param status
	 *            �˻���ת״̬
	 * @throws HandlerException
	 */
	public int updateAcountTransferResult(String transferBatchNo, String tranDate,String tranTime, String status)
			throws HandlerException {
		LinkedHashMap map = new LinkedHashMap();
		String sqlStatement = "update " + tableName + " set ACTTRAFSTATUS= ? , PAYTXNDATE = ? , PAYTXNTIME = ?  , TMSMP = ? where  TRANSFERBATCHNO = ?";
		map.put("ACTTRAFSTATUS", status);
		map.put("PAYTXNDATE", tranDate);
		map.put("PAYTXNTIME", tranTime);
		String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);
		map.put("TMSMP", time);
		map.put("TRANSFERBATCHNO", transferBatchNo);
		return executeUpdate(sqlStatement, map);
	}

	/**
	 * ���ݽ������ڣ���ȡ���촦������н���
	 *
	 * @param txndate
	 *            ,��������
	 * @return
	 * @return
	 * @throws HandlerException
	 */
	public List<Map<String, String>> getTransFlowByTrandate(String txndate) throws HandlerException {
		LinkedHashMap map = new LinkedHashMap();
		String sqlStatement = "select * from " + tableName + " where TXNDATE = ? ";
		map.put("TXNDATE", txndate);
		return executeQuery(sqlStatement, map);

	}

	/**
	 * �����������ڽ�������ˮ���еĽ�����Ϣ��Ϊ��ʼ����,���ڶ��˿�ʼʱ
	 *
	 * @param accountDate
	 * @throws HandlerException
	 */
	public int updateStatusBytxnDate(String txndate, String status) throws HandlerException {
		String sqlStatement = "update  " + tableName + " set CHECKINGSTATUS = ? ��TMSMP = ? where TXNDATE = ?  ";
		LinkedHashMap map = new LinkedHashMap();
		map.put("CHECKINGSTATUS", status);
		String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);
		map.put("TMSMP", time);
		map.put("TXNDATE", txndate);
		return executeUpdate(sqlStatement, map);
	}

	/**
	 * �Ƚ����ݿ��е����ݣ����ж���,���ҷ�����Ϊ׼
	 *
	 * @param accountDate
	 * @return
	 * @throws HandlerException
	 */
	public int checkingAccountTransFlowByOurData (String txndate) throws HandlerException {
		int i = -1;
		if (logger.isDebugEnabled()) {
			logger.debug(" checking account trans flow start ! trandate = [" + txndate + "] !");
		}

		LinkedHashMap map = new LinkedHashMap();
		String sqlStatement = "select count(1) as num from "
				+ tableName
				+ " a  where a.txndate = ? and NOT EXISTS (select b.TRANSFERFLOWNO from "
				+ tableNameTmp
				+ " b where a.txndate = b.txndate , a.accountno = b.accountno  , a.TRANSFERFLOWNO = b.TRANSFERFLOWNO  , a.transferflg = b.transferflg  , a.currency = b.currency  , a.paytxnretcode = b.paytxnretcode  , a.accountname = b.accountname  )";
		map.put("txndate", txndate);
		try {
			List<Map<String, String>> list = executeQuery(sqlStatement, map);
			if (list == null || list.size() == 0) {
				i = 0;
			} else {
				Map<String, String> sesultMap = list.get(0);
				String num = sesultMap.get("num");
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
	 *
	 * @param accountDate
	 * @return
	 * @throws HandlerException
	 */
	public int checkingAccountTransFlowByUMB(String trandate) throws HandlerException {
		int i = -1;
		if (logger.isDebugEnabled()) {
			logger.debug(" checking account trans flow start ! date = ["
					+ trandate + "]");
		}

		LinkedHashMap map = new LinkedHashMap();
		String sqlStatement = "select count(1) as num from "
				+ tableNameTmp
				+ " b  where  NOT EXISTS (select a.TRANSFERFLOWNO from "
				+ tableName
				+ " a where a.txndate = ? and a.txndate = b.txndate  , a.accountno = b.accountno  , a.TRANSFERFLOWNO = b.TRANSFERFLOWNO  , a.transferflg = b.transferflg  , a.currency = b.currency  , a.paytxnretcode = b.paytxnretcode  , a.accountname = b.accountname)";
		map.put("txndate", trandate);
		try {
			List<Map<String, String>> list = executeQuery(sqlStatement, map);
			Map<String, String> sesultMap = list.get(0);
			String num = sesultMap.get("num");
			i = Integer.parseInt(num);
		} catch (HandlerException e) {
			logger.error("checking pay flow error !", e);
			throw e;
		}

		return i;
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
	public int batchExecuteUpdate(String sqlStatement,List<LinkedHashMap<String, String>> list) throws HandlerException {

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
	 * ���ݽ������ڣ���ȡ����˺źͿͻ���
	 *
	 * @param txndate
	 *            ,��������
	 * @return
	 * @return
	 * @throws HandlerException
	 */
	public List<Map<String, String>> getPayerAccoutNoByTrandateAndStatus(String txndate,String status) throws HandlerException {
		LinkedHashMap map = new LinkedHashMap();
		String sqlStatement = "select distinct PAYACCOUNTNO , PAYCUSTOMERNO from " + tableName + " where TXNDATE = ? and CHECKINGSTATUS = ? ";
		map.put("TXNDATE", txndate);
		map.put("CHECKINGSTATUS", status);
		return executeQuery(sqlStatement, map);

	}
/**
 * ���ݽ������ںͶ���״̬��ȡ������Ϣ
 * @param txndate
 * @param status
 * @return
 * @throws HandlerException
 */
	public List<Map<String, String>> getCheckInfoByTrandateAndAccountNo(String txndate,String status,String acctNo) throws HandlerException {
		LinkedHashMap map = new LinkedHashMap();
		String sqlStatement = "select * from " + tableName + " where TXNDATE = ? and CHECKINGSTATUS = ? and PAYACCOUNTNO = ?";
		map.put("TXNDATE", txndate);
		map.put("CHECKINGSTATUS", status);
		map.put("PAYACCOUNTNO", acctNo);
		return executeQuery(sqlStatement, map);

	}

	/**
	 * ��ѯ�����մ���ĳ�ض���ת״̬�еĽ���
	 * @param status
	 * @return
	 * @throws HandlerException
	 */
		public List<Map<String, String>> getRecentlyInfoBystatus(String status) throws HandlerException {
			LinkedHashMap map = new LinkedHashMap();
			String sqlStatement = "select * from " + tableName + " where TXNDATE >= (select LASTACDT from PLAT_DATE_PARAM t ) and  ACTTRAFSTATUS = ?";
			map.put("ACTTRAFSTATUS", status);
			return executeQuery(sqlStatement, map);

		}
}
