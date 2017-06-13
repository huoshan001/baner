package org.inn.baner.handler.data;

import com.ztkx.cbpay.business.bean.BMerchantOrder;
import com.ztkx.cbpay.business.constant.BusinessConstantField;
import com.ztkx.cbpay.business.constant.BusinessMessageFormateConstant;
import com.ztkx.cbpay.container.HandlerException;
import com.ztkx.cbpay.container.initload.AbstractDbMapper;
import com.ztkx.cbpay.platformutil.db.DBUtil;
import com.ztkx.cbpay.platformutil.db.c3p0.DataSourceUtil;
import com.ztkx.cbpay.platformutil.time.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BMerchantOrderData extends AbstractDbMapper {

	Logger logger = Logger.getLogger(BMerchantOrderData.class);
	private static final String tableName = " B_MERCHANT_ORDER ";


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
	public List<BMerchantOrder> executeQueryResBean(String sqlStatement,LinkedHashMap<String, String> map) throws HandlerException {

		getConnection();
		logger.info("sql[" + sqlStatement + "]");
		logger.info("params is " + map);
		List<BMerchantOrder> resList = null;
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
			resList = resConvertList(rs);
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
	 * ��ȡ������Ϣ������,��������Ѿ���������ѯ����������Ϊ��
	 * @param merchantId
	 * @param orderId
	 * @param orderdate
	 * @return
	 * @author zhangxiaoyun
	 * @throws HandlerException
	 */
	public BMerchantOrder getOrderInfoWithLock(String merchantId, String orderId, String orderdate)
			throws HandlerException {

		String sql = "select * from " + tableName+ " where merchantId=? and orderId = ? and orderdate = ? for update skip locked";
		logger.info("sql[" + sql + "]");
		logger.info("merchantId[" + merchantId + "] orderId[" + orderId+ "] orderdate[" + orderdate + "]");
		BMerchantOrder bMerchantOrder = null;
		try {
			psmt = DataSourceUtil.getPreparedStatement(connection, sql);
			psmt.setString(1, merchantId);
			psmt.setString(2, orderId);
			psmt.setString(3, orderdate);
			rs = psmt.executeQuery();
			List<BMerchantOrder> list = resConvertList(rs);
			if(list.size()>0){
				bMerchantOrder = list.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("exec sql error", e);
			throw new HandlerException(e);
		}
		return bMerchantOrder;
	}

	public int selectCount(String merchantId, String orderId, String orderdate)throws HandlerException {
		logger.info("merchantId[" + merchantId + "] orderId[" + orderId
				+ "] orderdate[" + orderdate + "]");
		String sql = "select count(1) from " + tableName
				+ " where MERCHANTID=? and ORDERID = ? and ORDERDATE = ?";
		logger.info("sql[" + sql + "]");
		int count = 0;
		try {
			psmt = DataSourceUtil.getPreparedStatement(connection, sql);
			psmt.setString(1, merchantId);
			psmt.setString(2, orderId);
			psmt.setString(3, orderdate);
			rs = psmt.executeQuery();
			if (rs.next()) {
				count = Integer.parseInt(rs.getString(1));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("exec sql error", e);
			throw new HandlerException(e);
		}
		return count;
	}

	public int insertData(BMerchantOrder bMerchantOrderBean)throws HandlerException {
		logger.debug("insert into " + tableName + " is begin ..");
		String sql = "insert into "
				+ tableName
				+ " (PAYJNLNO, PAYTIME, MERCHANTID, ORDERID, ORDERTIME, CLIENTIP, PURCHASERID, "
				+ "VALIDUNIT, VALIDNUM, ORDERDESC, ORDERAMOUNT, CURRENCY, MERCHANTPOUNDAGE, PURCHASEAMOUNT, CHANNEL, "
				+ "ACCEPTANCERATE, ACCEPTANCEMOUNT, ACCEPTANCYCURRENCY, ACCEPTANCYPOUNDAGE, ORDERSTATUS, PAGERETURNURL, "
				+ "OFFLINENOTIFYURL, PAYORDERID, BUYBATNO, BUYBATSTATUS, BUYBATRATE, UPORDOWNMOUNT, PAYBATNO, PAYBATSTATUS, "
				+ "MOUNTCHANGENO, MOUNTCHANGESTATUS, SETTLEBATNO, SETTLEBATSTATUS, USERID, ORDERDATE, TRADE_TYPE,BUYBATDATE,PAYCARD,"
				+ "PAYDATE,TRADE_CODE,IS_REF,PAYBATDATE,PRODUCTID,PRODUCTNAME,PRODUCTDESC,SHOWURL,RCVDATE,RCVTIME,TMSMP,INVOICENO) values   (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		logger.info("sql[" + sql + "]");
		int count = 0;
		try {
			psmt = DataSourceUtil.getPreparedStatement(connection, sql);
			psmt.setString(1, StringUtils.isNotBlank(bMerchantOrderBean
					.getPayjnlno()) ? bMerchantOrderBean.getPayjnlno() : "");
			psmt.setString(2, StringUtils.isNotBlank(bMerchantOrderBean
					.getPaytime()) ? bMerchantOrderBean.getPaytime() : "");
			psmt.setString(3, StringUtils.isNotBlank(bMerchantOrderBean
					.getMerchantid()) ? bMerchantOrderBean.getMerchantid() : "");
			psmt.setString(4, StringUtils.isNotBlank(bMerchantOrderBean
					.getOrderid()) ? bMerchantOrderBean.getOrderid() : "");
			psmt.setString(5, StringUtils.isNotBlank(bMerchantOrderBean
					.getOrdertime()) ? bMerchantOrderBean.getOrdertime() : "");
			psmt.setString(6, StringUtils.isNotBlank(bMerchantOrderBean
					.getClientip()) ? bMerchantOrderBean.getClientip() : "");
			psmt.setString(7, StringUtils.isNotBlank(bMerchantOrderBean
					.getPurchaserid()) ? bMerchantOrderBean.getPurchaserid() : "");
			psmt.setString(8, StringUtils.isNotBlank(bMerchantOrderBean
					.getValidunit()) ? bMerchantOrderBean.getValidunit() : "");
			psmt.setString(9, StringUtils.isNotBlank(bMerchantOrderBean
					.getValidnum()) ? bMerchantOrderBean.getValidnum() : "");
			psmt.setString(10, StringUtils.isNotBlank(bMerchantOrderBean
					.getOrderdesc()) ? bMerchantOrderBean.getOrderdesc() : "");
			psmt.setBigDecimal(11, bMerchantOrderBean.getOrderamount());
			psmt.setString(12, StringUtils.isNotBlank(bMerchantOrderBean
					.getCurrency()) ? bMerchantOrderBean.getCurrency() : "");
			psmt.setBigDecimal(13, bMerchantOrderBean.getMerchantpoundage());
			psmt.setBigDecimal(14, bMerchantOrderBean.getPurchaseamount());
			psmt.setString(15, StringUtils.isNotBlank(bMerchantOrderBean
					.getChannel()) ? bMerchantOrderBean.getChannel() : "");
			psmt.setBigDecimal(16, bMerchantOrderBean.getAcceptancerate());
			psmt.setBigDecimal(17, bMerchantOrderBean.getAcceptancemount());
			psmt.setString(
					18,
					StringUtils.isNotBlank(bMerchantOrderBean
							.getAcceptancycurrency()) ? bMerchantOrderBean
							.getAcceptancycurrency() : "");
			psmt.setBigDecimal(19, bMerchantOrderBean.getAcceptancypoundage());
			psmt.setString(20, StringUtils.isNotBlank(bMerchantOrderBean
					.getOrderstatus()) ? bMerchantOrderBean.getOrderstatus() : "");
			psmt.setString(21, StringUtils.isNotBlank(bMerchantOrderBean
					.getPagereturnurl()) ? bMerchantOrderBean.getPagereturnurl()
					: "");
			psmt.setString(
					22,
					StringUtils.isNotBlank(bMerchantOrderBean
							.getOfflinenotifyurl()) ? bMerchantOrderBean
							.getOfflinenotifyurl() : "");
			psmt.setString(23, StringUtils.isNotBlank(bMerchantOrderBean
					.getPayorderid()) ? bMerchantOrderBean.getPayorderid() : "");
			psmt.setString(24, StringUtils.isNotBlank(bMerchantOrderBean
					.getBuybatno()) ? bMerchantOrderBean.getBuybatno() : "");
			psmt.setString(25, StringUtils.isNotBlank(bMerchantOrderBean
					.getBuybatstatus()) ? bMerchantOrderBean.getBuybatstatus()
					: "");
			psmt.setBigDecimal(26, bMerchantOrderBean.getBuybatrate());
			psmt.setBigDecimal(27, bMerchantOrderBean.getUpordownmount());
			psmt.setString(28, StringUtils.isNotBlank(bMerchantOrderBean
					.getPaybatno()) ? bMerchantOrderBean.getPaybatno() : "");
			psmt.setString(29, StringUtils.isNotBlank(bMerchantOrderBean
					.getPaybatstatus()) ? bMerchantOrderBean.getPaybatstatus()
					: "");
			psmt.setString(30, StringUtils.isNotBlank(bMerchantOrderBean
					.getMountchangeno()) ? bMerchantOrderBean.getMountchangeno()
					: "");
			psmt.setString(
					31,
					StringUtils.isNotBlank(bMerchantOrderBean
							.getMountchangestatus()) ? bMerchantOrderBean
							.getMountchangestatus() : "");
			psmt.setString(32, StringUtils.isNotBlank(bMerchantOrderBean
					.getSettlebatno()) ? bMerchantOrderBean.getSettlebatno() : "");
			psmt.setString(
					33,
					StringUtils.isNotBlank(bMerchantOrderBean
							.getSettlebatstatus()) ? bMerchantOrderBean
							.getSettlebatstatus() : "");
			psmt.setString(34, StringUtils.isNotBlank(bMerchantOrderBean
					.getUserid()) ? bMerchantOrderBean.getUserid() : "");
			psmt.setString(35, StringUtils.isNotBlank(bMerchantOrderBean
					.getOrderdate()) ? bMerchantOrderBean.getOrderdate() : "");
			psmt.setString(36, StringUtils.isNotBlank(bMerchantOrderBean
					.getTradeType()) ? bMerchantOrderBean.getTradeType() : "");
			psmt.setString(37, StringUtils.isNotBlank(bMerchantOrderBean
					.getBuybatdate()) ? bMerchantOrderBean.getBuybatdate() : "");
			psmt.setString(38, StringUtils.isNotBlank(bMerchantOrderBean
					.getPaycard()) ? bMerchantOrderBean.getPaycard() : "");
			psmt.setString(39, StringUtils.isNotBlank(bMerchantOrderBean
					.getPaydate()) ? bMerchantOrderBean.getPaydate() : "");
			psmt.setString(40, StringUtils.isNotBlank(bMerchantOrderBean
					.getTradeCode()) ? bMerchantOrderBean.getTradeCode() : "");
			psmt.setString(41, StringUtils.isNotBlank(bMerchantOrderBean
					.getIsRef()) ? bMerchantOrderBean.getIsRef() : "");
			psmt.setString(42, StringUtils.isNotBlank(bMerchantOrderBean
					.getPaybatdate()) ? bMerchantOrderBean.getPaybatdate() : "");

			psmt.setString(43, StringUtils.isNotBlank(bMerchantOrderBean
					.getProductId()) ? bMerchantOrderBean.getProductId() : "");
			psmt.setString(44, StringUtils.isNotBlank(bMerchantOrderBean
					.getProductName()) ? bMerchantOrderBean.getProductName() : "");
			psmt.setString(45, StringUtils.isNotBlank(bMerchantOrderBean
					.getProductDesc()) ? bMerchantOrderBean.getProductDesc() : "");
			psmt.setString(46, StringUtils.isNotBlank(bMerchantOrderBean
					.getShowUrl()) ? bMerchantOrderBean.getShowUrl() : "");
			psmt.setString(47, StringUtils.isNotBlank(bMerchantOrderBean
					.getRcvDate()) ? bMerchantOrderBean.getRcvDate() : "");
			psmt.setString(48, StringUtils.isNotBlank(bMerchantOrderBean
					.getRcvTime()) ? bMerchantOrderBean.getRcvTime() : "");
			//����ʱ���
			String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);
			psmt.setString(49, time);
			psmt.setString(50, StringUtils.isNotBlank(bMerchantOrderBean
					.getInvoiceNo()) ? bMerchantOrderBean.getInvoiceNo() : "");
			count = psmt.executeUpdate();
			logger.debug("insert into " + tableName + " end orderid ["
					+ bMerchantOrderBean.getOrderid() + "]");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				logger.error("exec sql error", e);
				throw new HandlerException(e);
			}
			logger.error("exec sql error", e);
			throw new HandlerException(e);
		}
		return count;
	}

	/**
	 * ���¶���֧����ˮ�ź���������, �������׻�ͨ����֧������֮ǰ��������״̬��Ϊ֧����ʼ��״̬
	 *
	 * @param orderid
	 *            ��������
	 * @param merchantno
	 *            �̻���
	 * @param orderdate
	 *            ��������
	 * @throws HandlerException
	 * update by tianguangzhao 2016/4/12 ȥ��paytime��paytime����֧�����ʱ�䡣��ʱ���ò���
	 */
	public int updateOrderInfo(String orderid, String merchantno,String orderdate, String payorderid,String paycard, String status) throws HandlerException {
		int count = 0;
		String sqlStatement = "update   " + tableName
				+ " set  PAYORDERID = ? , PAYCARD = ? ,TMSMP = ? where  ORDERID = ?  and MERCHANTID = ?  and ORDERDATE = ? and ORDERSTATUS =?";
		try {
			//��ȡʱ���
			String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);
			LinkedHashMap map = new LinkedHashMap();
			List<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
			map.put("PAYORDERID", payorderid);
			map.put("PAYCARD", paycard);
			map.put("TMSMP", time);
			map.put("ORDERID", orderid);
			map.put("MERCHANTID", merchantno);
			map.put("ORDERDATE", orderdate);
			map.put("ORDERSTATUS", status);
			list.add(map);
			count=batchExecuteUpdate(sqlStatement, list);
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
	 * ���¶���״̬,����֧��״̬��ѯ���ף���ȡ������״̬֮�󣬸������ݿ��еĶ���״̬
	 *
	 * @param orderid
	 *            ������
	 * @param merchantno
	 *            �̻���
	 * @param trandate
	 *            ��������
	 * @param status
	 *            ����״̬
	 * @throws HandlerException
	 */
	public int updateOrderStatusByPayorderid(String payorderid,String merchantno, String status) throws HandlerException {
		if (logger.isDebugEnabled()) {
			logger.debug("start update table " + tableName + " merchantno = [" + merchantno + "]  and status = [" + status + "] and payorderid= ["
					+ payorderid + "]");
		}
		int count = 0;
		String sqlStatement = "update "+ tableName+ " set  ORDERSTATUS = ? ,TMSMP = ? where  PAYORDERID = ?  and MERCHANTID = ?  ";
		try {
			//��ȡʱ���
			String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);

			LinkedHashMap map = new LinkedHashMap();
			List<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
			map.put("ORDERSTATUS", status);
			map.put("TMSMP", time);
			map.put("PAYORDERID", payorderid);
			map.put("MERCHANTID", merchantno);
			list.add(map);
			count=batchExecuteUpdate(sqlStatement, list);

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
	 * ���¶�������,����֧�����ף������ñ��׻�ͨ���յ��ӿ�֮�󣬸��ݱ��׻�ͨ���ص����ݸ��¶������еĶ���״̬
	 *
	 * @param orderid
	 *            ������
	 * @param merchantno
	 *            �̻���
	 * @param orderdate
	 *            ��������
	 * @param status
	 *            ����״̬
	 * @throws HandlerException
	 */
	public int updateOrderInfoByOrderID(String orderid, String merchantno,String orderdate, String status, String payDate, String payTime,String payorderid) throws HandlerException {
		int count = 0;

		//�����߼��������ݿ�ʱ���밴�ս�����ˮ�Ÿ��£�Ϊ�˷�����ԣ���ʱȥ��,2016/4/25�ָ�
		String sqlStatement = "update " + tableName+ " set  ORDERSTATUS = ?  , PAYDATE =? , PAYTIME = ? ,TMSMP = ? where  ORDERID = ?  and MERCHANTID = ?  and ORDERDATE = ? and PAYORDERID = ? ";
		//String sqlStatement = "update   " + tableName+ " set  ORDERSTATUS = ?  , PAYDATE =? , PAYTIME = ? ,TMSMP = ? where  ORDERID = ?  and MERCHANTID = ?  and ORDERDATE = ? ";

		try {
			//��ȡʱ���
			String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);

			LinkedHashMap map = new LinkedHashMap();
			List<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
			map.put("ORDERSTATUS", status);
			map.put("PAYDATE", payDate);
			map.put("PAYTIME", payTime);
			map.put("TMSMP", time);
			map.put("ORDERID", orderid);
			map.put("MERCHANTID", merchantno);
			map.put("ORDERDATE", orderdate);
			map.put("PAYORDERID", payorderid);
			list.add(map);
			count=batchExecuteUpdate(sqlStatement, list);

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
	 * �����̻��ţ������ţ��������ڸ����˻���ת״̬�������˻���ת���׺Ͳ�ѯ�˻���ת���ף��˴�δ�õ����׻�ͨ���ص���Ϣ�����ڿ��Ǽ���
	 * @param orderid
	 * @param merchantno
	 * @param orderdate
	 * @param status
	 * @return
	 * @throws HandlerException
	 */
	public int updateMountChangeStatus(List<Map<String,String>> orderList,String status) throws HandlerException{
		int count = 0;
		String sqlStatement = "update  " + tableName+ " set  MOUNTCHANGESTATUS = ? ,TMSMP = ? where  ORDERID = ?  and MERCHANTID = ?  and ORDERDATE = ? ";

		try {
			//��ȡʱ���
			String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);
			List<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();

			for (Map<String, String> orderMap : orderList) {
				LinkedHashMap map = new LinkedHashMap();
				String merchantNo = orderMap.get(BusinessMessageFormateConstant.CTB_CTB003_MERCHANT_NO);
				String orderId = orderMap.get(BusinessMessageFormateConstant.CTB_CTB003_ORDER_NO);
				String orderDate = orderMap.get(BusinessMessageFormateConstant.CTB_CTB003_ORDER_DATE);
				map.put("MOUNTCHANGESTATUS", status);
				map.put("TMSMP", time);
				map.put("ORDERID", orderId);
				map.put("MERCHANTID", merchantNo);
				map.put("ORDERDATE", orderDate);
				list.add(map);
			}
			count=batchExecuteUpdate(sqlStatement, list);

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
	 * �����˻���ת��ˮ�Ÿ����˻���ת״̬�������˻���ת���׺Ͳ�ѯ�˻���ת����
	 * @param mountChangeNo
	 * @param status
	 * @return
	 * @throws HandlerException
	 */
	public int updateMountChangeStatus(String mountChangeNo, String status) throws HandlerException{
		int count = 0;
		String sqlStatement = "update " + tableName+ " set  MOUNTCHANGESTATUS = ? ,TMSMP = ? where  MOUNTCHANGENO = ?  ";

		try {
			//��ȡʱ���
			String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);

			LinkedHashMap map = new LinkedHashMap();
			List<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
			map.put("MOUNTCHANGESTATUS", status);
			map.put("TMSMP", time);
			map.put("MOUNTCHANGENO", mountChangeNo);
			list.add(map);
			count=batchExecuteUpdate(sqlStatement, list);

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
	 * �����̻��ţ������ţ��������ڸ����˻���ת��ת��ˮ��
	 * @param orderid
	 * @param merchantid
	 * @param orderdate
	 * @param mountChangeNo
	 * @returno
	 * @throws HandlerException
	 */
	public int updateMountChangeNo(String orderid, String merchantid, String orderdate, String mountChangeNo) throws HandlerException{
		int count = 0;
		String sqlStatement = "update " + tableName+ " set  MOUNTCHANGENO = ? ,TMSMP = ? where  ORDERID = ? and MERCHANTID = ?  and ORDERDATE = ? ";

		try {
			//��ȡʱ���
			String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);

			LinkedHashMap map = new LinkedHashMap();
			List<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
			map.put("MOUNTCHANGENO", mountChangeNo);
			map.put("TMSMP", time);
			map.put("ORDERID", orderid);
			map.put("MERCHANTID", merchantid);
			map.put("ORDERDATE", orderdate);
			list.add(map);
			count=batchExecuteUpdate(sqlStatement, list);

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
	 * ���ݶ����ţ��������ں��̻��ţ���ѯ������Ϣ
	 * @param merchantId
	 * @param orderId
	 * @param orderdate
	 * @return
	 * @author zhangxiaoyun
	 * @throws HandlerException
	 */
	public BMerchantOrder getOrderInfo(String merchantId, String orderId, String orderdate)throws HandlerException {
		//��ȡ����
		getConnection();
		String sql = "select * from " + tableName+ " where merchantId=? and orderId = ? and orderdate = ? ";
		logger.info("sql[" + sql + "]");
		logger.info("merchantId[" + merchantId + "] orderId[" + orderId+ "] orderdate[" + orderdate + "]");
		BMerchantOrder bMerchantOrder = null;
		try {
			psmt = DataSourceUtil.getPreparedStatement(connection, sql);
			psmt.setString(1, merchantId);
			psmt.setString(2, orderId);
			psmt.setString(3, orderdate);
			rs = psmt.executeQuery();
			List<BMerchantOrder> list = resConvertList(rs);
			if(list.size()>0){
				bMerchantOrder = list.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("exec sql error", e);
			throw new HandlerException(e);
		}finally{
			relaceResource();
		}
		return bMerchantOrder;
	}

	/**
	 * ���ݽ��������ʵ�������
	 * @param rs
	 * @return
	 * @throws HandlerException
	 */
	private List<BMerchantOrder> resConvertList(ResultSet rs) throws HandlerException{

		List<BMerchantOrder> list = new ArrayList<BMerchantOrder>();
		try {
			while (rs.next()) {
				BMerchantOrder bMerchantOrder = new BMerchantOrder();
				bMerchantOrder.setPayjnlno(rs.getString(1));
				bMerchantOrder.setPaytime(rs.getString(2));
				bMerchantOrder.setMerchantid(rs.getString(3));
				bMerchantOrder.setOrderid(rs.getString(4));
				bMerchantOrder.setOrdertime(rs.getString(5));
				bMerchantOrder.setClientip(rs.getString(6));
				bMerchantOrder.setPurchaserid(rs.getString(7));
				bMerchantOrder.setValidunit(rs.getString(8));
				bMerchantOrder.setValidnum(rs.getString(9));
				bMerchantOrder.setOrderdesc(rs.getString(10));
				bMerchantOrder.setOrderamount(rs.getBigDecimal(11));
				bMerchantOrder.setCurrency(rs.getString(12));
				bMerchantOrder.setMerchantpoundage(rs.getBigDecimal(13));
				bMerchantOrder.setPurchaseamount(rs.getBigDecimal(14));
				bMerchantOrder.setChannel(rs.getString(15));
				bMerchantOrder.setAcceptancerate(rs.getBigDecimal(16));
				bMerchantOrder.setAcceptancemount(rs.getBigDecimal(17));
				bMerchantOrder.setAcceptancycurrency(rs.getString(18));
				bMerchantOrder.setAcceptancypoundage(rs.getBigDecimal(19));
				bMerchantOrder.setOrderstatus(rs.getString(20));
				bMerchantOrder.setPagereturnurl(rs.getString(21));
				bMerchantOrder.setOfflinenotifyurl(rs.getString(22));
				bMerchantOrder.setPayorderid(rs.getString(23));
				bMerchantOrder.setBuybatno(rs.getString(24));
				bMerchantOrder.setBuybatstatus(rs.getString(25));
				bMerchantOrder.setBuybatrate(rs.getBigDecimal(26));
				bMerchantOrder.setUpordownmount(rs.getBigDecimal(27));
				bMerchantOrder.setPaybatno(rs.getString(28));
				bMerchantOrder.setPaybatstatus(rs.getString(29));
				bMerchantOrder.setMountchangeno(rs.getString(30));
				bMerchantOrder.setMountchangestatus(rs.getString(31));
				bMerchantOrder.setSettlebatno(rs.getString(32));
				bMerchantOrder.setSettlebatstatus(rs.getString(33));
				bMerchantOrder.setUserid(rs.getString(34));
				bMerchantOrder.setOrderdate(rs.getString(35));
				bMerchantOrder.setTradeType(rs.getString(36));
				bMerchantOrder.setBuybatdate(rs.getString(37));
				bMerchantOrder.setPaycard(rs.getString(38));
				bMerchantOrder.setPaydate(rs.getString(39));
				bMerchantOrder.setTradeCode(rs.getString(40));
				bMerchantOrder.setIsRef(rs.getString(41));
				bMerchantOrder.setPaybatdate(rs.getString(42));
				bMerchantOrder.setProductId(rs.getString(43));
				bMerchantOrder.setProductName(rs.getString(44));
				bMerchantOrder.setProductDesc(rs.getString(45));
				bMerchantOrder.setShowUrl(rs.getString(46));
				bMerchantOrder.setIsResponse(rs.getString(47));
				bMerchantOrder.setRcvTime(rs.getString(48));
				bMerchantOrder.setRcvDate(rs.getString(49));
				bMerchantOrder.setTmsmp(rs.getString(50));
				list.add(bMerchantOrder);
			}
		} catch (SQLException e) {
			logger.error("convert result to bean from resultset error !");
			throw new HandlerException(e);
		}
		return list;
	}
    /**
     * ��ȡ������Ϣ��У����״̬
     * @param merchantId �̻�id
     * @param orderId  ����id
     * @param orderdate ��������
     * @param status  ״̬
     * @return
     * @throws HandlerException
     */
	public List<Map<String,String>> getOrderInfoInStatus(String merchantId, String orderId, String orderdate,String status) throws HandlerException{
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("MERCHANTID", merchantId);
		map.put("ORDERID", orderId);
		map.put("ORDERDATE", orderdate);
		map.put("ORDERSTATUS", status);
		String sqlStatement = "select * from B_MERCHANT_ORDER where MERCHANTID=? and ORDERID = ? and ORDERDATE = ? and ORDERSTATUS = ? ";
		return executeQuery(sqlStatement, map);
	}
}
