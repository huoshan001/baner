package org.inn.baner.handler.data;

import com.ztkx.cbpay.business.bean.BBuyExgDet;
import com.ztkx.cbpay.business.constant.BusinessConstantField;
import com.ztkx.cbpay.container.HandlerException;
import com.ztkx.cbpay.container.initload.AbstractDbMapper;
import com.ztkx.cbpay.platformutil.db.DBUtil;
import com.ztkx.cbpay.platformutil.db.c3p0.DataSourceUtil;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

public class BBuyExgDetData extends AbstractDbMapper{

	Logger logger = Logger.getLogger(BBuyExgDetData.class);
	
	
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
	 * �������빺����ϸ
	 * @param bBuyExgDet
	 * @throws HandlerException
	 * 2016��3��15�� ����6:03:13
	 * @author zhangxiaoyun
	 */
	public void batchInsertData(List<BBuyExgDet> list) throws HandlerException{
		logger.debug("insert into b_buy_exg_ctrl is begin ..");
		String sql = "insert into b_buy_exg_det (buybatno, tradeno, tran_amt, client_no, type, tran_code, payer_global_id, payer_name,"
				+ " trade_type, country_code,buydate) values  ( ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? , ?,?)";
		logger.info("sql[" + sql + "]");
		logger.info("params list is "+list);
		psmt = DataSourceUtil.getPreparedStatement(connection, sql);
		try {
			for (int i = 0; i < list.size(); i++) {
				BBuyExgDet bBuyExgDet = list.get(i);
				if(logger.isDebugEnabled()){
					logger.debug( i + " b_buy_exg_ctrl data is["+bBuyExgDet+ "]");
				}
				preparePreparedStatement(bBuyExgDet);
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

	private void preparePreparedStatement(BBuyExgDet bBuyExgDet)
			throws SQLException {
		psmt.setString(1, bBuyExgDet.getBuybatno());
		psmt.setString(2, bBuyExgDet.getTradeno());
		psmt.setBigDecimal(3, bBuyExgDet.getTranAmt());
		psmt.setString(4, bBuyExgDet.getClientNo());
		psmt.setString(5, bBuyExgDet.getType());
		psmt.setString(6, bBuyExgDet.getTranCode());
		psmt.setString(7, bBuyExgDet.getPayerGlobalId());
		psmt.setString(8, bBuyExgDet.getPayerName());
		psmt.setString(9, bBuyExgDet.getTradeType());
		psmt.setString(10, bBuyExgDet.getCountryCode());
		psmt.setString(11, bBuyExgDet.getBuydate());
	}

	/**
	 * �������빺����ϸ
	 * @param bBuyExgDet
	 * @throws HandlerException
	 * 2016��3��15�� ����6:03:13
	 * @author zhangxiaoyun
	 */
	public void insertData(BBuyExgDet bBuyExgDet) throws HandlerException{
		logger.debug("insert into b_buy_exg_ctrl is begin ..");
		String sql = "insert into b_buy_exg_det (buybatno, tradeno, tran_amt, client_no, type, tran_code, payer_global_id, payer_name,"
				+ " trade_type, country_code) values  ( ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? , ?)";
		logger.info("sql[" + sql + "]");
		if(logger.isDebugEnabled()){
			logger.debug("b_buy_exg_ctrl data is["+bBuyExgDet+ "]");
		}
		try {
			psmt = DataSourceUtil.getPreparedStatement(connection, sql);
			
			preparePreparedStatement(bBuyExgDet);
			
			psmt.execute();
			
		} catch (Exception e) {
			logger.error("exec inser error", e);
			throw new HandlerException(e);
		}
	}
}
