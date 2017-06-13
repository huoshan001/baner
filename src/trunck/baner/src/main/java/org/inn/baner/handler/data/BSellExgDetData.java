package org.inn.baner.handler.data;

import com.ztkx.cbpay.business.bean.BSellExgDet;
import com.ztkx.cbpay.business.constant.BusinessConstantField;
import com.ztkx.cbpay.container.HandlerException;
import com.ztkx.cbpay.container.initload.AbstractDbMapper;
import com.ztkx.cbpay.platformutil.db.DBUtil;
import com.ztkx.cbpay.platformutil.db.c3p0.DataSourceUtil;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BSellExgDetData extends AbstractDbMapper {

	Logger logger = Logger.getLogger(BSellExgDetData.class);
	String tableName = "B_SELL_EXG_DET";
	String inserSql = "insert into b_sell_exg_det (paybatno, paydate, pay_seqno, merchantid, payerid,"
			+ " payeraccount, payername, remit_ccy, remit_amt, orderid, payeename, "
			+ "payeecountrycode, pay_type, tran_code, tran_desc, is_ref, contract_no, "
			+ "invoice_no, applicant, applicant_tel, stamp) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
	 * �������빺����ϸ
	 * @param bBuyExgDet
	 * @throws HandlerException
	 * 2016��3��15�� ����6:03:13
	 * @author zhangxiaoyun
	 */
	public void batchInsertData(List<BSellExgDet> list) throws HandlerException{
		logger.debug("insert into b_buy_exg_ctrl is begin ..");
		logger.info("sql[" + inserSql + "]");
		logger.info("params list is "+list);
		psmt = DataSourceUtil.getPreparedStatement(connection, inserSql);
		try {
			for (int i = 0; i < list.size(); i++) {
				BSellExgDet sellExgDet = list.get(i);
				if(logger.isDebugEnabled()){
					logger.debug( i + tableName+" data is["+sellExgDet+ "]");
				}
				preparePreparedStatement(sellExgDet);
				psmt.addBatch();
				if((i+1)%BusinessConstantField.BATCHNUM == 0){
					psmt.executeBatch();
				}
			}
			//���һ�µ�
			psmt.executeBatch();
		} catch (Exception e) {
			logger.error("batch exec inser error", e);
			throw new HandlerException(e);
		}
	}



	/**
	 * ����BSellExgDet����
	 * @param bSellExgDet
	 * @throws HandlerException
	 * 2016��3��15�� ����4:55:29
	 * @author zhangxiaoyun
	 */
	public void insertData(BSellExgDet bSellExgDet) throws HandlerException {
		logger.debug("insert into "+tableName+" is begin ..");
		logger.info("sql[" + inserSql + "]");
		if(logger.isDebugEnabled()){
			logger.debug(tableName+" data is ["+bSellExgDet+ "]");
		}
		try {
			psmt = DataSourceUtil.getPreparedStatement(connection, inserSql);
			preparePreparedStatement(bSellExgDet);
			psmt.execute();
		} catch (Exception e) {
			logger.error("exec inser error", e);
			throw new HandlerException(e);
		}
	}

	private void preparePreparedStatement(BSellExgDet bSellExgDet)
			throws SQLException {
		psmt.setString(1, bSellExgDet.getPaybatno());
		psmt.setString(2, bSellExgDet.getPaydate());
		psmt.setString(3, bSellExgDet.getPaySeqno());
		psmt.setString(4, bSellExgDet.getMerchantid());
		psmt.setString(5, bSellExgDet.getPayerid());
		psmt.setString(6, bSellExgDet.getPayeraccount());
		psmt.setString(7, bSellExgDet.getPayername());
		psmt.setString(8, bSellExgDet.getRemitCcy());
		psmt.setBigDecimal(9, bSellExgDet.getRemitAmt());
		psmt.setString(10, bSellExgDet.getOrderid());
		psmt.setString(11, bSellExgDet.getPayeename());
		psmt.setString(12, bSellExgDet.getPayeecountrycode());
		psmt.setString(13, bSellExgDet.getPayType());
		psmt.setString(14, bSellExgDet.getTranCode());				//���ױ���
		psmt.setString(15, bSellExgDet.getTranDesc());				//���׸���
		psmt.setString(16, bSellExgDet.getIsRef());					//�Ƿ�˰��
		psmt.setString(17, bSellExgDet.getContractNo());			//�м۽��
		psmt.setString(18, bSellExgDet.getInvoiceNo());				//�Żݺ���
		psmt.setString(19, bSellExgDet.getApplicant());				//�̻���ϵ��
		psmt.setString(20, bSellExgDet.getApplicantTel());			//�̻���ϵ�˵绰
		Timestamp timestamp = new Timestamp((bSellExgDet.getStamp()==null?new Date():bSellExgDet.getStamp()).getTime());
		psmt.setTimestamp(21, timestamp);				//ʱ���
	}
}
