package org.inn.baner.handler.data;

import com.ztkx.cbpay.business.bean.BSellExgCtrl;
import com.ztkx.cbpay.container.HandlerException;
import com.ztkx.cbpay.container.initload.AbstractDbMapper;
import com.ztkx.cbpay.platformutil.db.DBUtil;
import com.ztkx.cbpay.platformutil.db.c3p0.DataSourceUtil;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class BSellExgCtrlData extends AbstractDbMapper {

	Logger logger = Logger.getLogger(BSellExgCtrlData.class);
	private static String tableName = "B_SELL_EXG_CTRL";
	private static String sql = "insert into b_sell_exg_ctrl (paybatno, paydate, paytime, quotechnl, remit_ccy, "
			+ "remit_amt, cost_type, payee_acct_no, payee_client_name, payee_address, payer_acct_no, "
			+ "payer_client_name, payer_address, payee_acct_open_branch_id, remark, clear_bank_bic, "
			+ "process_msg, process_status, fail_reason, chkstatus, stamp,filename,"
			+ "BUSS_STATUS,TOT_NUM) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)";

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


	public List<BSellExgCtrl> executeQueryResBean(String sqlStatement,
			LinkedHashMap<String, String> map) throws HandlerException {

		getConnection();
		logger.info("sql[" + sqlStatement + "]");
		logger.info("params is " + map);
		List<BSellExgCtrl> resList = null;
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
	 * ������ִ��update����
	 * @param sqlStatement		��Ҫִ�е�sql���
	 * @param map	sql�е��ʺŲ���ֵ      ���mapΪ��Ĭ�ϰ���ͳ��statement��ʽִ��sql
	 * @return �޸ĵļ�¼��
	 * 2016��3��14�� ����2:19:21
	 * @author zhangxiaoyun
	 * @throws HandlerException
	 */
	public int executeUpdate(String sqlStatement,LinkedHashMap<String,String> map) throws HandlerException{

		logger.info("sql["+sqlStatement+"]");
		logger.info("params map is "+map);
		int res = 0;
		try{
			if(map != null && map.size()>0){
				//���list����null����psmt�ķ�ʽ����
				psmt = DataSourceUtil.getPreparedStatement(connection, sqlStatement);
				//��ѯ�����в���ע�뵽psmt��
				DBUtil.preparePsmt(map,psmt);
				//ִ��sql���
				res = psmt.executeUpdate();
			}else{
				//����stmt�ķ�ʽ����
				stmt = DataSourceUtil.getStatement(connection);
				res = stmt.executeUpdate(sqlStatement);	//ִ��sql���
			}
		}catch(SQLException e){
			logger.error("execute sqlstatement exception",e);
			throw new HandlerException(e);
		}
		return res;
	}


	/**
	 * ����BSellExgCtrl����
	 * @param bSellExgCtrl
	 * @throws HandlerException
	 * 2016��3��15�� ����4:55:29
	 * @author zhangxiaoyun
	 */
	public void insertData(BSellExgCtrl bSellExgCtrl) throws HandlerException {
		logger.debug("insert into b_buy_exg_ctrl is begin ..");
		logger.info("sql[" + sql + "]");
		if(logger.isDebugEnabled()){
			logger.debug(tableName+" data is ["+bSellExgCtrl+ "]");
		}
		try {
			psmt = DataSourceUtil.getPreparedStatement(connection, sql);
			preparePreparedStatement(bSellExgCtrl);				//������
			psmt.execute();
		} catch (Exception e) {
			logger.error("exec inser error", e);
			throw new HandlerException(e);
		}
	}

	/**
	 * ����rs���ض����list
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private List<BSellExgCtrl> resConvertList(ResultSet rs) throws SQLException {
		List<BSellExgCtrl> list = new ArrayList<BSellExgCtrl>();

		while(rs.next()){
			BSellExgCtrl bBuyExgCtrl = new BSellExgCtrl();
			bBuyExgCtrl.setPaybatno(rs.getString(1));
			bBuyExgCtrl.setPaydate(rs.getString(2));
			bBuyExgCtrl.setPaytime(rs.getString(3));
			bBuyExgCtrl.setQuotechnl(rs.getString(4));
			bBuyExgCtrl.setRemitCcy(rs.getString(5));
			bBuyExgCtrl.setRemitAmt(rs.getBigDecimal(6));
			bBuyExgCtrl.setCostType(rs.getString(7));
			bBuyExgCtrl.setPayeeAcctNo(rs.getString(8));
			bBuyExgCtrl.setPayeeClientName(rs.getString(9));
			bBuyExgCtrl.setPayeeAddress(rs.getString(10));
			bBuyExgCtrl.setPayerAcctNo(rs.getString(11));
			bBuyExgCtrl.setPayerClientName(rs.getString(12));
			bBuyExgCtrl.setPayerAddress(rs.getString(13));
			bBuyExgCtrl.setPayeeAcctOpenBranchId(rs.getString(14));
			bBuyExgCtrl.setRemark(rs.getString(15));
			bBuyExgCtrl.setClearBankBic(rs.getString(16));
			bBuyExgCtrl.setProcessMsg(rs.getString(17))	;//�м۽��
			bBuyExgCtrl.setProcessStatus(rs.getString(18));//�Żݺ���
			bBuyExgCtrl.setFailReason(rs.getString(19));//�����˺�
			bBuyExgCtrl.setChkstatus(rs.getString(20));
			bBuyExgCtrl.setStamp(rs.getTimestamp(21));//�������
			bBuyExgCtrl.setFileName(rs.getString(22));//������
			bBuyExgCtrl.setBuss_status(rs.getString(23));
			bBuyExgCtrl.setTot_num(rs.getInt(24));
			list.add(bBuyExgCtrl);
		}
		return list;
	}
	
	private void preparePreparedStatement(BSellExgCtrl bSellExgCtrl)
			throws SQLException {
		psmt.setString(1, bSellExgCtrl.getPaybatno());
		psmt.setString(2, bSellExgCtrl.getPaydate());
		psmt.setString(3, bSellExgCtrl.getPaytime());
		psmt.setString(4, bSellExgCtrl.getQuotechnl());
		psmt.setString(5, bSellExgCtrl.getRemitCcy());
		psmt.setBigDecimal(6, bSellExgCtrl.getRemitAmt());
		psmt.setString(7, bSellExgCtrl.getCostType());
		psmt.setString(8, bSellExgCtrl.getPayeeAcctNo());
		psmt.setString(9, bSellExgCtrl.getPayeeClientName());
		psmt.setString(10, bSellExgCtrl.getPayeeAddress());
		psmt.setString(11, bSellExgCtrl.getPayerAcctNo());
		psmt.setString(12, bSellExgCtrl.getPayerClientName());
		psmt.setString(13, bSellExgCtrl.getPayerAddress());	
		psmt.setString(14, bSellExgCtrl.getPayeeAcctOpenBranchId());
		psmt.setString(15, bSellExgCtrl.getRemark());
		psmt.setString(16, bSellExgCtrl.getClearBankBic());					
		psmt.setString(17, bSellExgCtrl.getProcessMsg());					
		psmt.setString(18, bSellExgCtrl.getProcessStatus());				
		psmt.setString(19, bSellExgCtrl.getFailReason());
		psmt.setString(20, bSellExgCtrl.getChkstatus());
		psmt.setTimestamp(21, new Timestamp((bSellExgCtrl.getStamp()==null?new Date():bSellExgCtrl.getStamp()).getTime()));
		psmt.setString(22, bSellExgCtrl.getFileName());
		psmt.setString(23, bSellExgCtrl.getBuss_status());
		psmt.setInt(24, bSellExgCtrl.getTot_num());
	}
}
