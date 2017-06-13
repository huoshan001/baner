package org.inn.baner.handler.data;

import com.ztkx.cbpay.business.bean.BCheckErrorList;
import com.ztkx.cbpay.business.constant.BusinessConstantField;
import com.ztkx.cbpay.container.HandlerException;
import com.ztkx.cbpay.container.initload.AbstractDbMapper;
import com.ztkx.cbpay.platformutil.db.DBUtil;
import com.ztkx.cbpay.platformutil.db.c3p0.DataSourceUtil;
import com.ztkx.cbpay.platformutil.time.TimeUtil;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * �����ͱ��׻�ͨ���˵���־��
 *
 * @author tianguangzhao
 *
 */
public class BCheckErrorListData extends AbstractDbMapper {

	Logger logger = Logger.getLogger(BCheckErrorList.class);
	// ��������ı���
	private static final String tableName = " B_CHECK_ERROR_LIST ";
	private static String sqlStatement = "insert into b_check_error_list (ACCOUNTDATE, chkchnl, orderid,"
			+ " chnljnlno, chkerrtyp, chkerrdealtyp, chkerrdealsts, errcancelmark, dealdate, stamp,"
			+ " trandate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
	public List<Map<String, String>> executeQuery(String sqlStatement,
			LinkedHashMap<String, String> map) throws HandlerException {

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
	 * �������֮�󣬽�����������ݿ���
	 *
	 * @param BCheckStatus
	 *            ��ʵ���࣬�����м�¼��Ϣ
	 * @throws HandlerException
	 */
	public void insertRecord(BCheckErrorList bucl) throws HandlerException {
		try {
			//��������ʱ�����update by tianguangzhao 2016/4/11
			psmt = DataSourceUtil.getPreparedStatement(connection, sqlStatement);
			prePreparedStatement(bucl);
			psmt.execute();
			if (logger.isDebugEnabled()) {
				logger.debug("insert table " + tableName + " success !");
			}
		} catch (Exception e) {
			logger.error("exec sql error", e);
			throw new HandlerException(e);
		}
	}

	private void prePreparedStatement(BCheckErrorList bucl) throws SQLException {
		psmt.setString(1, bucl.getAccountdate());
		psmt.setString(2, bucl.getChkchnl());
		psmt.setString(3, bucl.getOrderid());
		psmt.setString(4, bucl.getChnljnlno());
		psmt.setString(5, bucl.getChkerrtyp());
		psmt.setString(6, bucl.getChkerrdealtyp());
		psmt.setString(7, bucl.getChkerrdealsts());
		psmt.setString(8, bucl.getErrcancelmark());
		psmt.setString(9, bucl.getDealdate());
		Timestamp stamp = new Timestamp(TimeUtil.getCurrentTime());
		psmt.setTimestamp(10, stamp);
		psmt.setString(11, bucl.getTrandate());
	}

	/**
	 * �ֶ�����ڶ��ζ���֮�󽫽���������ݿ���
	 *
	 * @param BCheckStatus
	 *            ��ʵ���࣬�����м�¼��Ϣ
	 * @throws HandlerException
	 */
	public int updateRecord(String jnlno, String status) throws HandlerException {
		int count = 0;
		try {

			String sqlStatement = "update  " + tableName + " set STATUS =? , TMSMP = ? where JNLNO = ? ";
			LinkedHashMap map = new LinkedHashMap();
			List<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
			map.put("STATUS", status);
			// ���´���ʱ��
			String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);
			map.put("TMSMP", time);
			map.put("JNLNO", jnlno);
			list.add(map);
			count = batchExecuteUpdate(sqlStatement, list);

			if (logger.isDebugEnabled()) {
				logger.debug("update table " + tableName + " success !");
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e1) {
				logger.error("connection rollback error !", e1);
				throw new HandlerException(e1);
			}

			logger.error("exec sql error", e);
			throw new HandlerException(e);
		}
		return count;

	}
}
