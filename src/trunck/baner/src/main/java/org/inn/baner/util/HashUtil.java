package org.inn.baner.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * �˹��������ڼ���hashֵ��������Ӧ���ַ���
 * @author tianguangzhao
 *
 */
public class HashUtil {

	static Logger logger = Logger.getLogger(HashUtil.class);

	/**
	 * ͨ��SHA����hashֵ
	 *
	 * @param data ԭ����
	 * @param type  hashcode����
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] getSHA(String data, String encode,String type) throws Exception {
		byte[] bytes = null;
		if (data == null || data.equals("")) {
			return bytes;
		} else {
			try {
				MessageDigest messageDigest = MessageDigest.getInstance(type);
				// ����Ҫ���ܵ��ַ���
				if (StringUtils.isEmpty(encode)) {
					messageDigest.update(data.getBytes());
				} else {
					messageDigest.update(data.getBytes(encode));
				}
				// �õ����ͽ��
				bytes = messageDigest.digest();
			} catch (NoSuchAlgorithmException e) {
				logger.error("type error !",e);
				throw e;
			} catch (UnsupportedEncodingException e) {
				logger.error("encode error ! ",e);
				throw e;
			}
		}

		return bytes;
	}

	
}
