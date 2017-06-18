package org.inn.baner.handler.data;



import com.ztkx.transplat.container.initload.AbstractTMJDBC;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

public class BForeignCurrencyRateData extends AbstractTMJDBC {

	Logger logger = Logger.getLogger(BForeignCurrencyRateData.class);
	
	
	/**
	 * B_FOREIGN_CURRENCY_RATE��Ĳ��뷽��
	 * @param bForeignCurrencyRate
	 * @throws HandlerException
	 * 2016��3��21�� ����4:49:52
	 * @author zhangxiaoyun
	 */
	public void insertData(BForeignCurrencyRate bForeignCurrencyRate) throws HandlerException {
		logger.debug("insert into B_FOREIGN_CURRENCY_RATE is begin ..");

		String sql = "insert into b_foreign_currency_rate (from_id, currency_code, recv_date, recv_time, "
				+ "transtime, transdate, cashbuyprice, exbuyprice, cashsellprice, exsellprice, exquotedate, "
				+ "exquotetime, e3rdpayno, price, direction_flag, tran_amt, client_exchange_rate, "
				+ "discount_type, dis_amt, amt) values  (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		logger.info("sql[" + sql + "]");

		if(logger.isDebugEnabled()){
			logger.debug("b_foreign_currency_rate data is ["+bForeignCurrencyRate+ "]");
		}
		try {
			psmt = DataSourceUtil.getPreparedStatement(connection, sql);

			psmt.setString(1, bForeignCurrencyRate.getFromId());			//�Ƽ�����
			psmt.setString(2, bForeignCurrencyRate.getCurrencyCode());		//���ִ���
			psmt.setString(3, bForeignCurrencyRate.getRecvDate());			//��������
			psmt.setString(4, bForeignCurrencyRate.getRecvTime());			//����ʱ��
			psmt.setString(5, bForeignCurrencyRate.getTranstime());			//���з���ʱ��
			psmt.setString(6, bForeignCurrencyRate.getTransdate());			//���з�������
			psmt.setBigDecimal(7, bForeignCurrencyRate.getCashbuyprice());
			psmt.setBigDecimal(8, bForeignCurrencyRate.getExbuyprice());	//�����
			psmt.setBigDecimal(9, bForeignCurrencyRate.getCashsellprice());
			psmt.setBigDecimal(10, bForeignCurrencyRate.getExsellprice());	//������
			psmt.setString(11, bForeignCurrencyRate.getExquotedate());		//�Ƽ�����
			psmt.setString(12, bForeignCurrencyRate.getExquotetime());		//�Ƽ�ʱ��
			psmt.setString(13, bForeignCurrencyRate.getE3rdpayno());
			psmt.setBigDecimal(14, bForeignCurrencyRate.getPrice());	//�г���
			psmt.setString(15, bForeignCurrencyRate.getDirectionFlag());
			psmt.setBigDecimal(16, bForeignCurrencyRate.getTranAmt());
			psmt.setBigDecimal(17, bForeignCurrencyRate.getClientExchangeRate());					//�ɽ�����
			psmt.setString(18, bForeignCurrencyRate.getDiscountType());					//�Żݺ���
			psmt.setBigDecimal(19, bForeignCurrencyRate.getDisAmt());				//�����˺�
			psmt.setBigDecimal(20, bForeignCurrencyRate.getAmt());
			psmt.execute();
		} catch (Exception e) {
			logger.error("exec inser error", e);
			throw new HandlerException(e);
		}
	}

	public String selectExsellPrice(String time,String currency) throws HandlerException{
		String sql = "select max(PRICE) from b_foreign_currency_rate where EXQUOTEDATE = ? and CURRENCY_CODE = ?";
		logger.info("sql["+sql+"]");
		String exsellPrice = null;
		try {
			psmt = DataSourceUtil.getPreparedStatement(connection, sql);
			logger.info("EXQUOTEDATE["+time+"]  CURRENCY_CODE["+currency+"]");
			psmt.setString(1, time);
			psmt.setString(2, currency);
			rs = psmt.executeQuery();
			if(rs.next()){
				exsellPrice = rs.getString(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("exec sql error",e);
			throw new HandlerException(e);
		}
		logger.info("PRICE["+exsellPrice+"]");
		return exsellPrice;
	}

	public Map<String, String> selectMaxExsellPrice(String time,String currency) throws HandlerException{
		String sql = "select PRICE,EXQUOTETIME from b_foreign_currency_rate where EXQUOTEDATE = ? and CURRENCY_CODE = ? and CLIENT_EXCHANGE_RATE is not null order by CLIENT_EXCHANGE_RATE desc";
		logger.info("sql["+sql+"]");
		List<Map<String, String>> resList = null;
		try {
			psmt = DataSourceUtil.getPreparedStatement(connection, sql);
			logger.info("EXQUOTEDATE["+time+"]  CURRENCY_CODE["+currency+"]");
			psmt.setString(1, time);
			psmt.setString(2, currency);
			rs = psmt.executeQuery();
			resList = DBUtil.resConvertList(rs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("exec sql error",e);
			throw new HandlerException(e);
		}
		if(resList!=null&&resList.size()>0){
			return resList.get(0);
		}
		return null;
	}


	public Map<String, String> getExRate(String channel,String exRateDate,String currency_code) throws HandlerException{
		String sql = "select * from b_foreign_currency_rate t where exquotedate='"+exRateDate+"' and currency_code='"+currency_code+"' and from_id='"+channel+"'";
		logger.info("sql["+sql+"]");
		List<Map<String, String>> resList = null;
		try {
			getConnection();
			stmt = DataSourceUtil.getStatement(connection);
			rs = stmt.executeQuery(sql);
			resList = DBUtil.resConvertList(rs);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("exec sql error",e);
			throw new HandlerException(e);
		}finally{
			relaceResource();
		}
		if(resList!=null&&resList.size()>0){
			return resList.get(0);
		}
		return null;
	}

	/**
	 * ��ȡ��С���ʵķ���
	 * @param exRateDate
	 * @param currency_code
	 * @return
	 * @throws HandlerException
	 */
	public String getMinRateSvr(String exRateDate,String currency_code) throws HandlerException{
		String sql = "select min(client_exchange_rate),from_id from b_foreign_currency_rate t where t.exquotedate='"+exRateDate+"' and t.currency_code='"+currency_code+"' group by t.from_id";
		logger.info("sql["+sql+"]");
		List<Map<String, String>> resList = null;
		try {
			getConnection();
			stmt = DataSourceUtil.getStatement(connection);
			rs = stmt.executeQuery(sql);
			resList = DBUtil.resConvertList(rs);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("exec sql error",e);
			throw new HandlerException(e);
		}finally{
			relaceResource();
		}
		if(resList!=null&&resList.size()>0){
			return resList.get(0).get("FROM_ID");
		}
		return null;
	}
}
