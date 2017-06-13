package org.inn.baner.handler.data;

import com.ztkx.cbpay.business.bean.BVerificationCode;
import com.ztkx.cbpay.business.constant.BusinessConstantField;
import com.ztkx.cbpay.container.HandlerException;
import com.ztkx.cbpay.container.initload.AbstractDbMapper;
import com.ztkx.cbpay.platformutil.db.DBUtil;
import com.ztkx.cbpay.platformutil.db.c3p0.DataSourceUtil;
import com.ztkx.cbpay.platformutil.time.TimeUtil;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ������ɶ�B_VERIFICATION_CODE(������֤��� )�����ز���,��ʱδ�õ���
 *
 * @author tianguangzhao
 */
public class BVerificationCodeData extends AbstractDbMapper {

	static Logger logger = Logger.getLogger(BVerificationCodeData.class);
	static String tableName = "B_VERIFICATION_CODE";

	/**
	 * ��������֤����Ϣ�������
	 *
	 * @param verificationCode
	 *            ��װ�ж�����֤�����Ϣ��ʵ����
	 * @throws Exception
	 */
	public void insert(BVerificationCode verificationCode)throws HandlerException {

		if (logger.isDebugEnabled()) {
			logger.debug("start insert VerificationCode values is ["
					+ verificationCode.toString() + "]");
		}
		String sqlStatement = "insert into "
				+ tableName
				+ " (VERJNLNO,MBLNO,VERCHNL,VERBIZTYP,VERCODE,PRDTIME,STATUS,TMSMP,PRDDATE) values(?,?,?,?,?,?,?,?,?)";

		try {
			//��ȡʱ���
			String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);
			LinkedHashMap map = new LinkedHashMap();
			List<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
			map.put("VERJNLNO", verificationCode.getVerJnlNo());
			//֧��ϵͳ��ˮ��Ӧ���ɱ��׻�ͨ�ṩ
			map.put("MBLNO", verificationCode.getMblNo());
			map.put("VERCHNL",  verificationCode.getVerChnl());
			map.put("VERBIZTYP", verificationCode.getVerBizTyp());
			map.put("VERCODE", verificationCode.getVerCode());
			map.put("PRDTIME", verificationCode.getPrdTime());
			map.put("STATUS", BVerificationCode.CODE_SATUS_DISABLE);
			map.put("TMSMP", time);
			map.put("PRDDATE", verificationCode.getPrdDate());
			list.add(map);
			batchExecuteUpdate(sqlStatement, list);

			if (logger.isDebugEnabled()) {
				logger.debug(" insert  table  sucess !");
			}
		} catch (HandlerException e) {
			logger.error("insert  table data error !", e);
			/**update by tianguangzhao 2016/4/27 ����ع������ϲ㴦��
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error("connection rollback error !");
				throw new HandlerException(e1);
			}*/
			throw e;
		}
	}

	/**
	 * ��ѯ���ݿ����Ƿ��Ѿ����ڸ��û�����֤��,��ʱδ�õ�!
	 *
	 * @param mblNo
	 *            �ͻ���ʶ��
	 * @param verBizTyp
	 *            ҵ������
	 * @return
	 * @throws Exception
	 */
	public boolean checkSMSExist(String mblNo, String verBizTyp) throws HandlerException {
		boolean flag = false;
		// ��ȡ����
		getConnection();
		String sqlStatement = "select count(1) as NUM from  " + tableName + "  where  MBLNO = ? and VERBIZTYP = ? and STATUS = ? ";
		try {
			LinkedHashMap map = new LinkedHashMap();
			map.put("MBLNO", mblNo);
			//֧��ϵͳ��ˮ��Ӧ���ɱ��׻�ͨ�ṩ
			map.put("VERBIZTYP",verBizTyp);
			map.put("STATUS", BVerificationCode.CODE_SATUS_NOMARL);
		   List<Map<String,String>> list = executeQuery(sqlStatement, map);
			if(list !=null&&list.size()!=0){
				Map<String,String> resultMap = list.get(0);
				String num = resultMap.get("NUM");
				if (num != null && !num.equals("0")) {
					flag = true;
				}
			}
			if (logger.isDebugEnabled()) {
				logger.debug(" select  table "+tableName+" sucess ! Exist flag =[" + flag + "]");
			}
		} catch (HandlerException e) {
			logger.error("select data from table "+tableName+" exception !", e);
			throw e;
		} finally {
			relaceResource();
		}
		return flag;
	}

	/**
	 * �����ϴ���֤���״̬ΪʧЧ
	 *
	 * @param mblNo
	 *            �û���ʶ
	 * @param verBizTyp
	 *            ��֤���͡�֧����ʵ����֤
	 */
	public int updateStatus(String mblNo, String verBizTyp,String status) throws HandlerException {
		int count =0;
		String sqlStatement = "update  " + tableName + "  set  STATUS = ? , TMSMP = ? where  MBLNO = ? and VERBIZTYP = ? ";
		try {
			String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);
			LinkedHashMap map = new LinkedHashMap();
			List<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
			map.put("STATUS", status);
			//֧��ϵͳ��ˮ��Ӧ���ɱ��׻�ͨ�ṩ
			map.put("TMSMP", time);
			map.put("MBLNO", mblNo);
			map.put("VERBIZTYP", verBizTyp);
			list.add(map);
			count=batchExecuteUpdate(sqlStatement, list);

			if (logger.isDebugEnabled()) {
				logger.debug(" update  table "+tableName+" sucess !");
			}
		} catch (HandlerException e) {
			logger.error("update  table "+tableName+" exception !", e);
			throw e;
		}
		return count;
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
	 * �·���֤��ɹ�����ݽ�����ˮ�ţ���������
	 * @param flowNo ������ˮ�ţ���Ӧ���е�VERJNLNO�ֶ�
	 * @return
	 * @throws HandlerException
	 */
	public int updateStatusByFlowNo(String flowNo) throws HandlerException {
		int count =0;
		String sqlStatement = "update  " + tableName + "  set  STATUS = ? , TMSMP = ? , PRDDATE = ? , PRDTIME = ? where VERJNLNO = ? ";
		try {
			LinkedHashMap map = new LinkedHashMap();
			List<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
			//��֤�ɹ�֮���������������֤��Ϣ״̬
			map.put("STATUS", BVerificationCode.CODE_SATUS_NOMARL);
			String time = TimeUtil.getCurrentTime(BusinessConstantField.TIMESTAMP_FORMATE);
			map.put("TMSMP", time);
			
			String prddate =TimeUtil.getCurrentTime(BusinessConstantField.PLA_DATE_FORMATE);
			String prdtime =TimeUtil.getCurrentTime(BusinessConstantField.PLA_TIME_FORMATE);
			map.put("PRDDATE", prddate);
			map.put("PRDTIME", prdtime);
			
			map.put("VERJNLNO", flowNo);
			list.add(map);
			count=batchExecuteUpdate(sqlStatement, list);		
			if (logger.isDebugEnabled()) {
				logger.debug(" update  table "+tableName+" sucess !");
			}
		} catch (HandlerException e) {
			logger.error("update  table "+tableName+" exception !", e);
			throw e;
		}
		return count;
	}
	
}
