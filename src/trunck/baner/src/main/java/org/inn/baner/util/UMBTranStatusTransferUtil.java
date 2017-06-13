package org.inn.baner.util;

import com.ztkx.cbpay.business.constant.BusinessConstantField;

/**
 * ���׻�ͨ��״̬����ҷ�״̬��ת���Ĺ����࣬����ʱʹ�ã��Ժ��Ż���
 * @author tianguangzhao
 *
 */
public class UMBTranStatusTransferUtil {

	/**
	 * ���ݽ�����ת���������׻�ͨ�Ľ����룬ת��Ϊ���ǵĽ�����
	 * @param UMBTranStatus ,���׻�ͨ��״̬��
	 * @return
	 */
	public static String TransferToOurStatusByCode(String UMBTranStatus){

		if (UMBTranStatus == null || UMBTranStatus.equals("")) {
			// ���׻�ͨ���ص�״̬��Ϊ��
			return null;
		} else if (UMBTranStatus.equals(BusinessConstantField.UMB_TRADING_INIT)) {
			// ���׳�ʼ��
			return BusinessConstantField.TRADING_INIT;
		} else if (UMBTranStatus.equals(BusinessConstantField.UMB_TRADING_PROCESSING)) {
			// ���׽�����
			return BusinessConstantField.TRADING_PROCESSING;
		} else if (UMBTranStatus.equals(BusinessConstantField.UMB_TRADING_SUCCESS)) {
			// ���׳ɹ�
			return BusinessConstantField.TRADING_SUCCESS;
		} else if (UMBTranStatus.equals(BusinessConstantField.UMB_TRADING_FAILED)) {
			// ����ʧ��
			return BusinessConstantField.TRADING_FAILED;
		} else {
			// ���׻�ͨ���ص�״̬������
			return null;
		}
	}

	/**
	 * ���ݱ��׻�ͨ�Ľ���״̬������ת��Ϊ�ҷ���Ӧ��״̬��,��ʱʹ�ú����޸�
	 * @param desInfo ���׻�ͨ�Ľ���״̬����
	 * @return
	 */
	public static String TransferToOurStatusByDES(String desInfo){

		if (desInfo == null || desInfo.equals("")) {
			// ���׻�ͨ���ص�״̬��Ϊ��
			return null;
		} else if (desInfo.equals("����ɹ�")) {
			// ���׳�ʼ��
			return BusinessConstantField.TRADING_INIT;
		} else if (desInfo.equals("������")) {
			// ���׽�����
			return BusinessConstantField.TRADING_PROCESSING;
		} else if (desInfo.equals("���׳ɹ�")) {
			// ���׳ɹ�
			return BusinessConstantField.TRADING_SUCCESS;
		} else if (desInfo.equals("����ʧ��")) {
			// ����ʧ��
			return BusinessConstantField.TRADING_FAILED;
		} else {
			// ���׻�ͨ���ص�״̬������
			return null;
		}
	}
}
}
