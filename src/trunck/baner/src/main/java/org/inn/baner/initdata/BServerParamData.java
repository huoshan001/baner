package org.inn.baner.initdata;


import com.ztkx.transplat.container.initload.AbstractLoadInit;
import com.ztkx.transplat.platformutil.baseconfig.ConstantConfigField;
import com.ztkx.transplat.platformutil.db.DBUtil;
import com.ztkx.transplat.platformutil.db.c3p0.DataSourceUtil;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BServerParamData extends AbstractLoadInit {

	static Logger logger = Logger.getLogger(BServerParamData.class);
	String tableName = "B_SERVER_PARAM";
	private static BServerParamData instance = null;

	public static BServerParamData getInstance() {
		if (instance == null) {
			instance = new BServerParamData();
		}
		return instance;
	}

	private BServerParamData() {
		instance = this;
	}
	/**
	 * ���ݷ���id���������ƻ�ȡ����ֵ
	 * @param serverId
	 * @param currency_type
	 * @return
	 * 2016��3��29�� ����2:49:45
	 * @author zhangxiaoyun
	 */
	public BServerParam getParamsValue(String serverId,String paramName){
		return (BServerParam) data.get(serverId+ ConstantConfigField.TABLE_PRI_KEY_SEPARATOR+paramName);
	}

	/**
	 * ���ݷ���ϵͳ�ͱ��ֻ�ȡ��Ӧ���ֵ�pia�˺�
	 * @param serverId
	 * @param currency_type
	 * @return
	 * 2016��3��29�� ����2:49:45
	 * @author zhangxiaoyun
	 */
	public BServerParam getPIAAccount(String serverId,String currency_type) throws HandlerException{
		currency_type = currency_type+"_"+"PIA_ACCOUNT";
		return (BServerParam) data.get(serverId+ConstantConfigField.TABLE_PRI_KEY_SEPARATOR+currency_type);
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

	@Override
	public boolean extracted(Map target) throws HandlerException {
		logger.info("start load "+tableName+" table data...");
		String sql = "select * from "+tableName+" where USEFLG='1' ";
		boolean res = false;
		if(logger.isDebugEnabled()){
			logger.debug(tableName+" init sql["+sql+"]");
		}
		stmt = DataSourceUtil.getStatement(connection);
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				BServerParam bServerParam = new BServerParam();
				bServerParam.setServerid(rs.getString("SERVERID"));
				bServerParam.setParaname(rs.getString("PARANAME"));
				bServerParam.setParavalue(rs.getString("PARAVALUE"));
				bServerParam.setParamdesc(rs.getString("PARAMDESC"));
				target.put(bServerParam.getServerid()+ConstantConfigField.TABLE_PRI_KEY_SEPARATOR+bServerParam.getParaname(), bServerParam);
			}
			logger.info("load init table ["+tableName+"] success size is ["+target.size()+"]");
			res = true;
		} catch (SQLException e) {
			res = false;
			logger.error("init CONVERT_RESCODE table data exception",e);
			throw new HandlerException("init CONVERT_RESCODE table data exception");
		}
		return res;
	}
	
	

}
