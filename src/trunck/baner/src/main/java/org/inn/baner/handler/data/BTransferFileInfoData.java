package org.inn.baner.handler.data;

import com.ztkx.cbpay.business.bean.BTransferFileInfo;
import com.ztkx.cbpay.container.HandlerException;
import com.ztkx.cbpay.container.initload.AbstractDbMapper;
import com.ztkx.cbpay.platformutil.db.DBUtil;
import com.ztkx.cbpay.platformutil.db.c3p0.DataSourceUtil;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.LinkedHashMap;

public class BTransferFileInfoData extends AbstractDbMapper{


	Logger logger = Logger.getLogger(BTransferFileInfoData.class);
	String sql = "insert into b_transfer_file_info (transfer_date, transfer_time, "
			+ "seqbatchno, transfer_type, filetype, count, filename, randompwd,hashvalue,"
			+ "signvalue, filestatus) "
			+ "values (?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
	static String tableName = "b_transfer_file_info";
	
	/**
	 * @param TRANSFER_DATE
	 * @param FILENAME
	 * @param SEQBATCHNO
	 * @return
	 * 2016��3��28�� ����6:13:27
	 * @author zhangxiaoyun
	 * @throws HandlerException
	 */
	public BTransferFileInfo getTransferFile(String TRANSFER_DATE,String FILENAME,String SEQBATCHNO) throws HandlerException{
		String sqlStatement = "select * from b_transfer_file_info t where t.transfer_date=? and t.seqbatchno=? and t.filename=?";

		logger.info("sql ["+sqlStatement+"]");
		if (logger.isDebugEnabled()) {
			logger.debug("start select table  "+tableName+" TRANSFER_DATE = ["+ TRANSFER_DATE + "] and FILENAME = [" + FILENAME + "] SEQBATCHNO = ["+SEQBATCHNO+"]");
		}
		BTransferFileInfo bTransferFileInfo = null;
		// ��ȡ����
		getConnection();
		try {
			psmt = DataSourceUtil.getPreparedStatement(connection, sqlStatement);
			psmt.setString(1, TRANSFER_DATE);
			psmt.setString(2, SEQBATCHNO);
			psmt.setString(3, FILENAME);
			rs = psmt.executeQuery();

			if(rs.next()){
				bTransferFileInfo = new BTransferFileInfo();
				bTransferFileInfo.setCount(rs.getInt("COUNT"));
				bTransferFileInfo.setTransferDate(rs.getString("TRANSFER_DATE"));
				bTransferFileInfo.setTransferTime(rs.getString("TRANSFER_TIME"));
				bTransferFileInfo.setSeqbatchno(rs.getString("SEQBATCHNO"));
				bTransferFileInfo.setTransferType(rs.getString("TRANSFER_TYPE"));
				bTransferFileInfo.setFiletype(rs.getString("FILETYPE"));
				bTransferFileInfo.setFilename(rs.getString("FILENAME"));
				bTransferFileInfo.setRandompwd(rs.getString("RANDOMPWD"));
				bTransferFileInfo.setHashvalue(rs.getString("HASHVALUE"));
				bTransferFileInfo.setSignvalue(rs.getString("SIGNVALUE"));
				bTransferFileInfo.setFilestatus(rs.getString("FILESTATUS"));
			}
		} catch (SQLException e) {
			logger.error("select " + tableName + " data exception !", e);
			throw new HandlerException(e);
		} finally {
			relaceResource();

		}

		return bTransferFileInfo;

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
	 * ����BBuyExgCtrl����
	 * @param bBuyExgCtrl
	 * @throws HandlerException
	 * 2016��3��15�� ����4:55:29
	 * @author zhangxiaoyun
	 */
	public void insertData(BTransferFileInfo bTransferFileInfo) throws HandlerException {
		logger.debug("insert into B_TRANSFER_FILE_INFO is begin ..");
		logger.info("sql[" + sql + "]");
		if(logger.isDebugEnabled()){
			logger.debug("B_TRANSFER_FILE_INFO data is ["+bTransferFileInfo+ "]");
		}
		try {
			psmt = DataSourceUtil.getPreparedStatement(connection, sql);
			psmt.setString(1, bTransferFileInfo.getTransferDate());
			psmt.setString(2, bTransferFileInfo.getTransferTime());
			psmt.setString(3, bTransferFileInfo.getSeqbatchno());
			psmt.setString(4, bTransferFileInfo.getTransferType());
			psmt.setString(5, bTransferFileInfo.getFiletype());
			psmt.setInt(6, bTransferFileInfo.getCount());
			psmt.setString(7, bTransferFileInfo.getFilename());
			psmt.setString(8, bTransferFileInfo.getRandompwd());
			psmt.setString(9, bTransferFileInfo.getHashvalue());
			psmt.setString(10, bTransferFileInfo.getSignvalue());
			psmt.setString(11, bTransferFileInfo.getFilestatus());
			psmt.execute();
		} catch (Exception e) {
			logger.error("exec inser error", e);
			throw new HandlerException(e);
		}
	}

}
