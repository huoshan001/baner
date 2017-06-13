package org.inn.baner.handler.data;

import com.ztkx.cbpay.business.bean.BUserCard;
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
 * �������ڲ����û�����Ϣ��
 *
 *
 */
public class BUserCardData extends AbstractDbMapper {

	Logger logger = Logger.getLogger(BUserCardData.class);
	private static String tablename = "B_USER_CARD";

	/**
	 * �����̻��ź��û��ţ����Ҵ��ڿ���״̬�Ŀ���Ϣ
	 * @param merchantid
	 * @param usrid
	 * @return
	 * @throws HandlerException
	 */
	public List<Map<String, String>> getNormalCardInfo(String merchantid, String usrid)
			throws HandlerException {
		List<Map<String, String>> list = null;
		// ��ȡ����
		getConnection();
		String sqlStatement = "select * from  " + tablename + "  where  MERCHANTID = ?  and PURCHASERID = ? and VALID = ? order by tmsmp desc";
		try {
			LinkedHashMap map = new LinkedHashMap();
			map.put("MERCHANTID", merchantid);
			map.put("PURCHASERID", usrid);
			//�˴���ѯ���ڿ���״̬�Ŀ���Ϣ��
			map.put("VALID", BUserCard.VALID_NORMAL);
			 list = executeQuery(sqlStatement, map);

			if (logger.isDebugEnabled()) {
				logger.debug(" select " + tablename + " table  sucess !");
			}
		} catch (HandlerException e) {
			logger.error("select " + tablename + " data exception !", e);
			throw e;
		} finally {
			relaceResource();

		}
		return list;
	}
	/**
	 * ���û�����Ϣ���浽���ݿ���
	 *
	 * @param bUserCard
	 *            ,��Ӧb_user_card ���ʵ����
	 * @throws HandlerException
	 */
	public void insertUserCard(BUserCard bUserCard) throws HandlerException {
		// ��ȡ����
		// getConnection();
		String sqlStatement = "insert into " + tablename
				+ " (MERCHANTID,PURCHASERID,CARDNO,VALID,CREATEDATE,CREATETIME,BANKNAME,TMSMP) values(?,?,?,?,?,?,?,?)";
		try {
			LinkedHashMap map = new LinkedHashMap();
			map.put("MERCHANTID", bUserCard.getMerchantid());
			map.put("PURCHASERID",  bUserCard.getPurchaserId());
			map.put("CARDNO", bUserCard.getCardNo());
			//���β���ʱ״̬Ϊ�����ã���֤ͨ��֮��״̬��Ϊ����
			map.put("VALID", BUserCard.VALID_DISABLE);
			map.put("CREATEDATE", TimeUtil.getCurrentTime(BusinessConstantField.PLA_DATE_FORMATE));
			map.put("CREATETIME", TimeUtil.getCurrentTime(BusinessConstantField.PLA_TIME_FORMATE));
			map.put("BANKNAME", bUserCard.getBankName());
			map.put("TMSMP", TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE));
			executeUpdate(sqlStatement, map);
			if (logger.isDebugEnabled()) {
				logger.debug(" insert " + tablename + " table  sucess !");
			}
		} catch (HandlerException e) {
			logger.error("insert " + tablename + " table data error !", e);
			// update by tianguangzhao 2016/4/27 �Ƿ�ع�����ҵ������
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
	 * ��ѯ�û�����Ϣ�Ƿ����,�����û�ʵ����֤����
	 *
	 * @param merchantno
	 *            �̻���
	 * @param purchaserId
	 *            �û�id
	 * @param acctNo
	 *            �˺�
	 * @return String ,����û�����Ϣ�������򷵻�null�� ����û����ڷ���״̬
	 * @throws HandlerException
	 */
	public String checkUserCardStatus(String merchantid, String purchaserId,String acctNo) throws HandlerException {

		String status = null;
		// ��ȡ����
		getConnection();

		String sqlStatement = "select VALID from  " + tablename + "  where  MERCHANTID = ?  and PURCHASERID = ?  and CARDNO = ?";
		try {
			LinkedHashMap map = new LinkedHashMap();
			map.put("MERCHANTID", merchantid);
			map.put("PURCHASERID", purchaserId);
			map.put("CARDNO", acctNo);
			List<Map<String, String>> list = executeQuery(sqlStatement, map);
			if(list !=null&&list.size()!=0){
				if (list.size() > 1) {
					String message = "user card data error ! same card num has " + list.size() + " records !";
					logger.error(message);
					throw new HandlerException(message);
				}
				// ��ѯ���ݿ�ʱ���ֻ��һ����¼��������ֶ�����˵��ϵͳ��������
				Map<String, String> resultMap = list.get(0);
				status = resultMap.get("VALID");
			}
			if (logger.isDebugEnabled()) {
				logger.debug(" select " + tablename + " table  sucess ! user card status =[" + status + "]");
			}
		} catch (HandlerException e) {
			logger.error("select " + tablename + " data exception !", e);
			throw e;
		} finally {
			relaceResource();
		}
		return status;
	}

	/**
	 * ��ѯ���ݿ��еĿ���Ϣ����ȡ���������Ƶ���Ϣ,����֧�����׺��û�֧��ǰ��ʵ����֤
	 *
	 * @param merchantid
	 * @param usrid
	 * @param cardNum
	 * @return
	 * @throws HandlerException
	 */
	public List<Map<String, String>> getUserCardInfo(String merchantid,
			String usrid, String cardNum) throws HandlerException {
		List<Map<String, String>> list = null;
		// ��ȡ����
		getConnection();

		String sqlStatement = "select * from  " + tablename + "  where  MERCHANTID = ?  and PURCHASERID = ? and VALID = ? and CARDNO = ? ";
		try {
			LinkedHashMap map = new LinkedHashMap();
			map.put("MERCHANTID", merchantid);
			map.put("PURCHASERID", usrid);
			//�˴���ѯ���ڿ���״̬�Ŀ���Ϣ��
			map.put("VALID", BUserCard.VALID_NORMAL);
			map.put("CARDNO", cardNum);
			list = executeQuery(sqlStatement, map);

			if (logger.isDebugEnabled()) {
				logger.debug(" select " + tablename + " table  sucess !");
			}
		} catch (HandlerException e) {
			logger.error("select " + tablename + " data exception !", e);
			throw e;
		} finally {
			relaceResource();

		}
		return list;
	}

	/**
	 * ���¿���Ϣ���еĿ�״̬��������֤ͨ���󽫿���Ϣ��Ϊ���ã�����¼����ʱ��
	 *
	 * @param merchantid
	 *            �̻���
	 * @param purchaserid
	 *            �û���
	 * @param cardNum
	 *            ����
	 * @param status
	 *            ״̬
	 * @throws HandlerException
	 */
	public int updateUserCardStatus(String merchantid, String purchaserid,String cardNum, String status) throws HandlerException {
		// ��ȡ����
		int result = 0;
		String sqlStatement = "update " + tablename + " set VALID = ? ,TMSMP = ? where MERCHANTID = ?  and PURCHASERID = ? and CARDNO = ? ";

		try {
			LinkedHashMap map = new LinkedHashMap();
			map.put("VALID",status);
			map.put("TMSMP", TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE));
			map.put("MERCHANTID", merchantid);
			//���β���ʱ״̬Ϊ�����ã���֤ͨ��֮��״̬��Ϊ����
			map.put("PURCHASERID", purchaserid);
			map.put("CARDNO", cardNum);
			result= executeUpdate(sqlStatement, map);

			if (logger.isDebugEnabled()) {
				logger.debug(" update table " + tablename + " sucess ! ");
			}
		} catch (HandlerException e) {
			logger.error("update " + tablename + " data exception !", e);
			throw e;
		}
		return result;

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
