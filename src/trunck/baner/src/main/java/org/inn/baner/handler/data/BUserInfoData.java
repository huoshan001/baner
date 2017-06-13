package org.inn.baner.handler.data;

import com.ztkx.cbpay.business.bean.BUserInfo;
import com.ztkx.cbpay.business.constant.BusinessConstantField;
import com.ztkx.cbpay.container.HandlerException;
import com.ztkx.cbpay.container.initload.AbstractDbMapper;
import com.ztkx.cbpay.platformutil.db.DBUtil;
import com.ztkx.cbpay.platformutil.db.c3p0.DataSourceUtil;
import com.ztkx.cbpay.platformutil.time.TimeUtil;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * �����û���Ϣ����ȡ����
 *
 * @author tianguangzhao
 *
 */
public class BUserInfoData extends AbstractDbMapper {

	Logger logger = Logger.getLogger(BUserInfoData.class);
	static String tableName = "B_USER_INFO";

	/**
	 * ���û���Ϣ�������
	 *
	 * @param userInfo
	 * @throws HandlerException
	 */
	public void insert(BUserInfo userInfo) throws HandlerException {
		// ��ȡ����
		getConnection();

		String sqlStatement = "insert into " + tableName
				+ " (MERCHANTID,PURCHASERID,NICKNAME,USRSTS,REALNMFLG,REALNAME,IDTYP,IDNO,REGDATE,REGTIME,REGCHNL,EMAIL,TMSMP,TELNUM) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
            //��ȡʱ���
			String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);

			LinkedHashMap map = new LinkedHashMap();
			map.put("MERCHANTID",userInfo.getMerchantid());
			map.put("PURCHASERID",userInfo.getPurchaserid());
			map.put("NICKNAME",userInfo.getNickName());
			map.put("USRSTS",BUserInfo.USER_STATUS_DISABLE);
			map.put("REALNMFLG",BUserInfo.VERIFY_INIT);
			map.put("REALNAME",userInfo.getRealName());
			map.put("IDTYP",userInfo.getIdTyp());
			map.put("IDNO",userInfo.getIdNo());
			map.put("REGDATE",userInfo.getRegDate());
			map.put("REGTIME",userInfo.getRegTime());
			map.put("REGCHNL",userInfo.getRegChnl());
			map.put("EMAIL",userInfo.getEmail());
			map.put("TMSMP",time);
			map.put("TELNUM",userInfo.getTelnum());
		    executeUpdate(sqlStatement, map);

			if (logger.isDebugEnabled()) {
				logger.debug(" insert " + tableName + " table  sucess !");
			}
		} catch (HandlerException e) {
			logger.error("insert " + tableName + " table data error !", e);
			// update by tianguangzhao 2016/4/27 �����Ƿ�ع� �����ϲ�
			// try {
			// connection.rollback();
			// } catch (SQLException e1) {
			// logger.error("connection rollback error !");
			// throw new HandlerException(e1);
			// }
			throw e;
		}
	}

	/**
	 * ��ѯ���ݿ����Ƿ���ڸ��û�����Ϣ(��������״̬��),
	 *
	 * @param merchantid
	 *            �̻�id
	 * @param purchaserid
	 *            �û�id
	 * @return
	 * @throws HandlerException
	 * update by tianguangzhao 2016/4/18 ֻ����û��Ƿ���ڣ�������״̬������ʱ�̻���+�û��š����һ���û�ע���ˣ��´���ע��ʱҲ����ʹ�ò�ͬ���û���
	 */
	public boolean checkInfoExist(String merchantid, String purchaserid) throws HandlerException {

		boolean flag = false;

		// ��ȡ����
		getConnection();

		//String sqlStatement = "select count(1) as num from  " + tableName + "  where  MERCHANTID = ?  and PURCHASERID = ? and USRSTS = ? and REALNMFLG = ?";
		String sqlStatement = "select count(1) as num from  " + tableName + "  where  MERCHANTID = ?  and PURCHASERID = ? ";
		try {
			LinkedHashMap map = new LinkedHashMap();
			map.put("MERCHANTID", merchantid);
			map.put("PURCHASERID", purchaserid);
//			//�˴���ѯ���ڿ���״̬�Ŀ���Ϣ��
//			map.put("USRSTS", BUserInfo.USER_STATUS_NORMAL);
//			map.put("REALNMFLG", BUserInfo.VERIFY_PASS);
			List<Map<String, String>> list = executeQuery(sqlStatement, map);
			if(list !=null&&list.size()!=0){
				// ��ѯ���ݿ�ʱֻ��ѯ��count����ֻ��һ����¼
				Map<String, String> resultMap = list.get(0);
				int count = Integer.parseInt(resultMap.get("NUM"));
				// ����ҵ�����ͬһ�ſ�������ע�����Σ�����ֻ����һ����¼
				if (count == 1) {
					flag = true;
				}
			}


			if (logger.isDebugEnabled()) {
				logger.debug(" select " + tableName + " table  sucess ! Exist flag =[" + flag + "]");
			}
		} catch (HandlerException e) {
			logger.error("select " + tableName + " data exception !", e);
			throw e;
		} finally {
			relaceResource();

		}
		return flag;
	}

	/**
	 * ɾ���û���Ϣ����ʱδ�õ�
	 *
	 * @param merchantid
	 * @param purchaserid
	 * @throws SQLException
	 */
	public int userCancel(String merchantid, String purchaserid) throws HandlerException {
	    int count = 0;
		String sqlStatement = "update " + tableName + " set  USRSTS = ? ,TMSMP = ? where  MERCHANTID = ?  and PURCHASERID = ?  ";
		try {
			//��������ʱ�����update by tianguangzhao 2016/4/11
			String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);

			LinkedHashMap map = new LinkedHashMap();
			map.put("USRSTS",BUserInfo.USER_STATUS_DISABLE);
			map.put("TMSMP", time);
			map.put("MERCHANTID", merchantid);
			map.put("PURCHASERID", purchaserid);
			count= executeUpdate(sqlStatement, map);

			if (logger.isDebugEnabled()) {
				logger.debug(" update table " + tableName + " sucess ! ");
			}
		} catch (HandlerException e) {
			logger.error("update " + tableName + " data exception !", e);
			throw e;
		}
		return count;
	}

	/**
	 * �����̻��ź��û���ʾ�Ų�ѯ�û���Ϣ
	 *
	 * @param merchantid
	 * @param purchaserid
	 * @return
	 * @throws HandlerException
	 */
	public List<Map<String, String>> getUserInfo(String merchantid,String purchaserid) throws HandlerException {
		List<Map<String, String>> list = null;
		// ��ȡ����
		getConnection();

		String sqlStatement = "select * from  " + tableName + "  where  MERCHANTID = ?  and PURCHASERID = ? and USRSTS = ? ";
		try {
			LinkedHashMap map = new LinkedHashMap();
			map.put("MERCHANTID", merchantid);
			map.put("PURCHASERID", purchaserid);
			//�˴���ѯ���ڿ���״̬�Ŀ���Ϣ��
			map.put("USRSTS", BUserInfo.USER_STATUS_NORMAL);
		    list = executeQuery(sqlStatement, map);

			if (logger.isDebugEnabled()) {
				logger.debug(" select " + tableName + " table  sucess !");
			}
		} catch (HandlerException e) {
			logger.error("select " + tableName + " data exception !", e);
			throw e;
		} finally {
			relaceResource();
		}
		return list;
	}

	/**
	 * �����û���Ϣ��״̬��ʵ����֤ͨ��֮�󣬸����û���Ϣ���е�״̬
	 *
	 * @param merchantid
	 * @param purchaserid
	 */
	public int updateUserStatus(String merchantid, String purchaserid,String usrSts, String realnmflg) throws HandlerException {
		int result = 0;
		String sqlStatement = "update   " + tableName+ " set  USRSTS = ? , REALNMFLG = ? , TMSMP = ? where  MERCHANTID = ?  and PURCHASERID = ? ";

		try {
			//��������ʱ�����update by tianguangzhao 2016/4/11
			String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);
			LinkedHashMap map = new LinkedHashMap();
			//�޸��û�״̬
			map.put("USRSTS",usrSts);
			//�޸��û�ʵ����֤��ʾ
			map.put("REALNMFLG", realnmflg);
			map.put("TMSMP", time);
			map.put("MERCHANTID", merchantid);
			map.put("PURCHASERID", purchaserid);
			result= executeUpdate(sqlStatement, map);

			if (logger.isDebugEnabled()) {
				logger.debug(" update table " + tableName + " sucess ! ");
			}
		} catch (HandlerException e) {
			logger.error("update " + tableName + " data exception !", e);
			throw e;
		}
		return result;
	}

	/**
	 * �û��޸�ע����Ϣʱ���ã��������ݿ��е���Ϣ,����ÿͻ���ǰע��������·���ע�ᣬ���մ˴ε���Ϣ���µ����ݿ���
	 *
	 * @param userInfo
	 * @throws HandlerException
	 */
	public int updateUserInfo(BUserInfo userInfo) throws HandlerException {
		if (logger.isDebugEnabled()) {
			logger.debug("start update table " + tableName + " values is ["
					+ userInfo.toString() + "]");
		}

		int count = 0;

		String sqlStatement = " update " + tableName+ " set NICKNAME = ?, USRSTS=? , REALNMFLG=? , REALNAME=? , IDTYP=? , IDNO=? , REGDATE=? , REGTIME=? , REGCHNL=? , EMAIL=? , TMSMP=? , TELNUM=? where MERCHANTID=? and PURCHASERID= ?";
		try {
			// ��ȡʱ���
			String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);
			LinkedHashMap map = new LinkedHashMap();
			map.put("NICKNAME", userInfo.getNickName());
			map.put("USRSTS", BUserInfo.USER_STATUS_DISABLE);
			map.put("REALNMFLG", BUserInfo.VERIFY_INIT);
			map.put("REALNAME", userInfo.getRealName());
			map.put("IDTYP", userInfo.getIdTyp());
			map.put("IDNO", userInfo.getIdNo());
			map.put("REGDATE", userInfo.getRegDate());
			map.put("REGTIME", userInfo.getRegTime());
			map.put("REGCHNL", userInfo.getRegChnl());
			map.put("EMAIL", userInfo.getEmail());
			map.put("TMSMP", time);
			map.put("TELNUM", userInfo.getTelnum());
			map.put("MERCHANTID", userInfo.getMerchantid());
			map.put("PURCHASERID", userInfo.getPurchaserid());
			count = executeUpdate(sqlStatement, map);
			if (logger.isDebugEnabled()) {
				logger.debug(" update " + tableName + " table  sucess !");
			}
		} catch (HandlerException e) {
			logger.error("update " + tableName + " table data error !", e);
			/**update by tianguangzhao 2016/4/27 �ع��������ο���
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error("connection rollback error !");
				throw new HandlerException(e1);
			} */
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
				psmt = DataSourceUtil.getPreparedStatement(connection,
						sqlStatement);
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
}
