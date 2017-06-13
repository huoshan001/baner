package org.inn.baner.handler.data;

import com.ztkx.cbpay.business.bean.BCheckStatus;
import com.ztkx.cbpay.business.constant.BusinessConstantField;
import com.ztkx.cbpay.container.HandlerException;
import com.ztkx.cbpay.container.initload.AbstractDbMapper;
import com.ztkx.cbpay.platformutil.db.DBUtil;
import com.ztkx.cbpay.platformutil.db.c3p0.DataSourceUtil;
import com.ztkx.cbpay.platformutil.time.TimeUtil;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
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
public class BCheckStatusData extends AbstractDbMapper {

	Logger logger = Logger.getLogger(BCheckStatusData.class);
	// ��������ı���
	private static final String tableName = " B_CHECK_STATUS ";
	private static String sqlStatement = "insert into " + tableName + " (TRANDATE,ACCOUNTDATE,CHECKTYPE,"
			+ "STATUS,TRANTIME,FILENAME,TMSMP,JNLNO,CHECKNL,ISHANDLED) values (?,?,?,?,?,?,?,?,?,?)";


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
	public void insertRecord(BCheckStatus bucl) throws HandlerException {
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


	/**
	 *
	 * @param accountdate	ҵ������
	 * @param checktype		��������
	 * @param checknl		��������
	 * @return
	 * @throws HandlerException
	 */
	public BCheckStatus getRecord(String accountdate,String  checktype,String checknl) throws HandlerException {
		try {
			//��������ʱ�����update by tianguangzhao 2016/4/11
			String sqlstatement = "select * from b_check_status t where t.accountdate=? and t.checktype=? and t.checknl=?";
			psmt = DataSourceUtil.getPreparedStatement(connection, sqlstatement);
			psmt.setString(1, accountdate);
			psmt.setString(2, checktype);
			psmt.setString(3, checknl);
			psmt.executeQuery();
			rs = psmt.executeQuery();
			List<BCheckStatus> list = rsConvertToBean(rs);
			if (logger.isDebugEnabled()) {
				logger.debug("insert table " + tableName + " success !");
			}
			if(list.size()>0)
				return list.get(0);
			else{
				return null;
			}
		} catch (Exception e) {
			logger.error("exec sql error", e);
			throw new HandlerException(e);
		}
	}

	private void prePreparedStatement(BCheckStatus bucl) throws SQLException {
		psmt.setString(1, bucl.getTrandate());
		psmt.setString(2, bucl.getAccountdate());
		psmt.setString(3, bucl.getChecktype());
		psmt.setString(4, bucl.getStatus());
		psmt.setString(5, bucl.getTrantime());
		psmt.setString(6, bucl.getFilename());
		Timestamp stamp = new Timestamp(TimeUtil.getCurrentTime());
		psmt.setTimestamp(7, stamp);
		psmt.setString(8, bucl.getJnlno());
		psmt.setString(9, bucl.getChecknl());
		psmt.setString(10, bucl.getIsHandled());
	}

	private List<BCheckStatus> rsConvertToBean(ResultSet rs) throws SQLException {
		List<BCheckStatus> list = new ArrayList<BCheckStatus>();
		while(rs.next()){
			BCheckStatus bean = new BCheckStatus();
			bean.setTrandate(rs.getString(1));
			bean.setAccountdate(rs.getString(2));
			bean.setChecktype(rs.getString(3));
			bean.setStatus(rs.getString(4));
			bean.setTrantime(rs.getString(5));
			bean.setFilename(rs.getString(6));
			bean.setTmsmp(rs.getTimestamp(7));
			bean.setJnlno(rs.getString(8));
			bean.setChecknl(rs.getString(9));
			bean.setIsHandled(rs.getString(10));
			list.add(bean);
		}
		return list;
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
		} catch (HandlerException e) {
			// update by tiangunagzhao 2016/4/27 �������ϲ㴦��
			// try {
			// connection.rollback();
			// } catch (Exception e1) {
			// logger.error("connection rollback error !", e1);
			// throw new HandlerException(e1);
			// }

			logger.error("exec sql error", e);
			throw e;
		}
		return count;

	}

	/**
	 * ���ݶ��˵�ҵ�����ںͶ����������¶���״̬
	 * @param accountdate
	 * @param checknl	����״̬
	 * @param status
	 * @param checktype ��������
	 * @return
	 * @throws HandlerException
	 */
	public int updateRecord(String accountdate, String checknl,String status,String checktype) throws HandlerException {
		int count = 0;
		try {

			String sqlStatement = "update  " + tableName + " set STATUS =? , TMSMP = ? where ACCOUNTDATE = ? and CHECKNL=? and checktype=?";
			psmt = DataSourceUtil.getPreparedStatement(connection,sqlStatement);
			psmt.setString(1, status);
			psmt.setTimestamp(2, TimeUtil.getTimeStamp());
			psmt.setString(3, accountdate);
			psmt.setString(4, checknl);
			psmt.setString(5, checktype);
			count = psmt.executeUpdate();

			if (logger.isDebugEnabled()) {
				logger.debug("update table " + tableName + " success !");
			}
		} catch (Exception e) {
			logger.error("exec sql error", e);
			throw new HandlerException(e);
		}
		return count;
	}

	/**
	 * ����������ѯ��������,����console������ֹ�����ʱ������������ѯ
	 *
	 * @param jnlno
	 *            ������������ˮ��
	 * @return
	 * @throws HandlerException
	 */
	public List<Map<String, String>> getPaymentFlowByJnlno(String jnlno) throws HandlerException {
		String sqlStatement = "select * from  " + tableName+ " where JNLNO = ? ";
		LinkedHashMap map = new LinkedHashMap();
		map.put("JNLNO", jnlno);
		return executeQuery(sqlStatement, map);
	}

	/**
	 * �ֹ�������ɺ󣬸�����ǰ����־��Ϣ������ʶΪ���Ѵ���
	 * @param accountdate
	 * @return
	 * @throws HandlerException
	 */
	public int updateBeforeRecordIsHandled(String accountdate)throws HandlerException {
		int count = 0;
		try {

			String sqlStatement = "update  " + tableName + " set ISHANDLED = ? , TMSMP = ? where ACCOUNTDATE = ? and STATUS = ?";
			psmt = DataSourceUtil.getPreparedStatement(connection,sqlStatement);
			psmt.setString(1, BCheckStatus.HANDLED_TRUE);
			psmt.setTimestamp(2, TimeUtil.getTimeStamp());
			psmt.setString(3, accountdate);
			psmt.setString(4, BCheckStatus.STATUS_ERROR);
			count = psmt.executeUpdate();
			
			if (logger.isDebugEnabled()) {
				logger.debug("update table " + tableName + " success !");
			}
		} catch (Exception e) {
			logger.error("exec sql error", e);
			throw new HandlerException(e);
		}
		return count ;
	}
}
