package org.inn.baner.handler.data;

import com.ztkx.cbpay.business.bean.BBuyExgCtrl;
import com.ztkx.cbpay.business.constant.BusinessConstantField;
import com.ztkx.cbpay.container.HandlerException;
import com.ztkx.cbpay.container.initload.AbstractDbMapper;
import com.ztkx.cbpay.platformutil.db.DBUtil;
import com.ztkx.cbpay.platformutil.db.c3p0.DataSourceUtil;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BBuyExgCtrlData extends AbstractDbMapper {

	Logger logger = Logger.getLogger(BBuyExgCtrlData.class);

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
	public List<BBuyExgCtrl> executeQueryResBean(String sqlStatement,
			LinkedHashMap<String, String> map) throws HandlerException {

		getConnection();
		logger.info("sql[" + sqlStatement + "]");
		logger.info("params is " + map);
		List<BBuyExgCtrl> resList = null;
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
	 * ����rs���ض����list
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private List<BBuyExgCtrl> resConvertList(ResultSet rs) throws SQLException {
		List<BBuyExgCtrl> list = new ArrayList<BBuyExgCtrl>();

		while(rs.next()){
			BBuyExgCtrl bBuyExgCtrl = new BBuyExgCtrl();
			bBuyExgCtrl.setBuybatno(rs.getString(1));
			bBuyExgCtrl.setBuydate(rs.getString(2));
			bBuyExgCtrl.setBuytime(rs.getString(3));
			bBuyExgCtrl.setQuotechnl(rs.getString(4));
			bBuyExgCtrl.setBecif(rs.getString(5));
			bBuyExgCtrl.setTotNum(rs.getShort(6));
			bBuyExgCtrl.setSaleCcy(rs.getString(7));
			bBuyExgCtrl.setBuyCcy(rs.getString(8));
			bBuyExgCtrl.setBuySellFlag(rs.getString(9));
			bBuyExgCtrl.setTotalAmt(rs.getBigDecimal(10));
			bBuyExgCtrl.setSpotFlag(rs.getString(11));
			bBuyExgCtrl.setRegisterDate(rs.getString(12));
			bBuyExgCtrl.setPrice(rs.getBigDecimal(13));
			bBuyExgCtrl.setClientExchangeRate(rs.getBigDecimal(14));	//�ͻ��ɽ�����
			bBuyExgCtrl.setDiscountType(rs.getString(15));
			bBuyExgCtrl.setDisAmt(rs.getBigDecimal(16));
			bBuyExgCtrl.setAmt(rs.getBigDecimal(17))	;//�м۽��
			bBuyExgCtrl.setTranAmt(rs.getBigDecimal(18));//�Żݺ���
			bBuyExgCtrl.setSellAcctNo(rs.getString(19));//�����˺�
			bBuyExgCtrl.setBuyAcctNo(rs.getString(20));
			bBuyExgCtrl.setSaleAmount(rs.getBigDecimal(21));//�������
			bBuyExgCtrl.setBuyAmount(rs.getBigDecimal(22));//������
			bBuyExgCtrl.setRateTime(rs.getString(23));
			bBuyExgCtrl.setExceedFlag(rs.getString(24));
			bBuyExgCtrl.setProcessStatus(rs.getString(25));
			bBuyExgCtrl.setProcessMsg(rs.getString(26));
			bBuyExgCtrl.setTxnSts(rs.getString(27));
			bBuyExgCtrl.setChkSts(rs.getString(28));
			list.add(bBuyExgCtrl);
		}
		return list;
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
	 * ������ִ��update����
	 * @param sqlStatement		��Ҫִ�е�sql���
	 * @param map	sql�е��ʺŲ���ֵ      ���mapΪ��Ĭ�ϰ���ͳ��statement��ʽִ��sql
	 * @return �޸ĵļ�¼��
	 * 2016��3��14�� ����2:19:21
	 * @author zhangxiaoyun
	 * @throws HandlerException
	 */
	public int batchExecuteUpdate(String sqlStatement,List<LinkedHashMap<String,String>> list) throws HandlerException{

		logger.info("sql["+sqlStatement+"]");
		logger.info("params list is "+list);
		int res = 0;
		try{
			if(list != null && list.size()>0){
				//���list����null����psmt�ķ�ʽ����
				psmt = DataSourceUtil.getPreparedStatement(connection, sqlStatement);

				LinkedHashMap<String,String> map = null;
				if(list.size()==1){
					map = list.get(0);
					//��ѯ�����в���ע�뵽psmt��
					DBUtil.preparePsmt(map,psmt);
					//ִ��sql���
					res = psmt.executeUpdate();
				}else{
					//��������
					for (int i = 0; i < list.size(); i++) {
						map = list.get(i);
						//��ѯ�����в���ע�뵽psmt��
						DBUtil.preparePsmt(map,psmt);
						psmt.addBatch();
						if((i+1)%BusinessConstantField.BATCHNUM == 0){
							res = res + psmt.executeBatch().length;
						}
					}
					//����ڲ�����
					res = res + psmt.executeBatch().length;
				}
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
	 * ����BBuyExgCtrl����
	 * @param bBuyExgCtrl
	 * @throws HandlerException
	 * 2016��3��15�� ����4:55:29
	 * @author zhangxiaoyun
	 */
	public void insertData(BBuyExgCtrl bBuyExgCtrl) throws HandlerException {
		logger.debug("insert into b_buy_exg_ctrl is begin ..");
		String sql = "insert into b_buy_exg_ctrl (buybatno, buydate, buytime, quotechnl, becif, tot_num, sale_ccy, buy_ccy, buy_sell_flag, "
				+ "total_amt, spot_flag, register_date, price, client_exchange_rate, discount_type, dis_amt, amt, tran_amt, sell_acct_no, "
				+ "buy_acct_no, sale_amount, buy_amount, RATE_TIME, exceed_flag, process_status, process_msg, txn_sts, chk_sts) values ( ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? , ?)";
		logger.info("sql[" + sql + "]");
		if(logger.isDebugEnabled()){
			logger.debug("b_buy_exg_ctrl data is ["+bBuyExgCtrl+ "]");
		}
		try {
			psmt = DataSourceUtil.getPreparedStatement(connection, sql);
			psmt.setString(1, bBuyExgCtrl.getBuybatno());
			psmt.setString(2, bBuyExgCtrl.getBuydate());
			psmt.setString(3, bBuyExgCtrl.getBuytime());
			psmt.setString(4, bBuyExgCtrl.getQuotechnl());
			psmt.setString(5, bBuyExgCtrl.getBecif());
			psmt.setShort(6, bBuyExgCtrl.getTotNum());
			psmt.setString(7, bBuyExgCtrl.getSaleCcy());
			psmt.setString(8, bBuyExgCtrl.getBuyCcy());
			psmt.setString(9, bBuyExgCtrl.getBuySellFlag());
			psmt.setBigDecimal(10, bBuyExgCtrl.getTotalAmt());
			psmt.setString(11, bBuyExgCtrl.getSpotFlag());
			psmt.setString(12, bBuyExgCtrl.getRegisterDate());
			psmt.setBigDecimal(13, bBuyExgCtrl.getPrice());
			psmt.setBigDecimal(14, bBuyExgCtrl.getClientExchangeRate());	//�ͻ��ɽ�����
			psmt.setString(15, bBuyExgCtrl.getDiscountType());
			psmt.setBigDecimal(16, bBuyExgCtrl.getDisAmt());
			psmt.setBigDecimal(17, bBuyExgCtrl.getAmt());					//�м۽��
			psmt.setBigDecimal(18, bBuyExgCtrl.getTranAmt());					//�Żݺ���
			psmt.setString(19, bBuyExgCtrl.getSellAcctNo());				//�����˺�
			psmt.setString(20, bBuyExgCtrl.getBuyAcctNo());
			psmt.setBigDecimal(21, bBuyExgCtrl.getSaleAmount());				//�������
			psmt.setBigDecimal(22, bBuyExgCtrl.getBuyAmount());				//������
			psmt.setString(23, bBuyExgCtrl.getRateTime());
			psmt.setString(24, bBuyExgCtrl.getExceedFlag());
			psmt.setString(25,bBuyExgCtrl.getProcessStatus());
			psmt.setString(26,bBuyExgCtrl.getProcessMsg());
			psmt.setString(27, bBuyExgCtrl.getTxnSts());
			psmt.setString(28, bBuyExgCtrl.getChkSts());
			psmt.execute();
		} catch (Exception e) {
			logger.error("exec inser error", e);
			throw new HandlerException(e);
		}
	}
}
