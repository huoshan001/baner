package org.inn.baner.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
/**
 * gzip ����ѹ���ͽ�ѹ���Ĺ�����
 * @author tianguangzhao
 *
 */
public class GzipUtil {

	/**
	 * ��ѹ����
	 *
	 * @param sourBytes����ѹ����byte[]
	 * @return ѹ����� byte[]
	 * @throws IOException
	 */
	public static byte[] compress(byte[] sourBytes) throws IOException {
		if (sourBytes == null || sourBytes.length == 0) {
			return null;
		} else {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(out);
			gzip.write(sourBytes);
			gzip.close();
			return out.toByteArray();
		}
	}

	/**
	 * ��ѹ��������ѹ�����У���װ��list
	 *
	 * @param bytes ����ѹ��byte[]
	 * @return ��ѹ���byte[]
	 * @throws IOException
	 */
	public static List<String> uncompressToList(byte[] bytes) throws IOException {

		List<String> list = new ArrayList<String>();
		String row = "";
		if (bytes == null || bytes.length == 0) {
			return null;
		} else {
			ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			GZIPInputStream gunzip = new GZIPInputStream(in);
			InputStreamReader inputStreamReader = new InputStreamReader(gunzip);
			BufferedReader bufferReader = new BufferedReader(inputStreamReader);
			while ((row = bufferReader.readLine()) != null) {
				list.add(row);
			}
			return list;
		}
	}

	/**
	 * ��ԭbyte[]��ѹΪ�ַ���
	 * @param bytes ����ѹ��byte[]
	 * @return ��ѹ��õ���byte[]
	 * @throws IOException
	 */
	public static byte[] uncompressToBytes (byte[] bytes) throws IOException{
		//�������Ĳ���Ϊ�գ���ֱ�ӷ���null
		if (bytes == null || bytes.length == 0) {
          return null;
		} else {
			try {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				ByteArrayInputStream in = new ByteArrayInputStream(bytes);
				GZIPInputStream gunzip = new GZIPInputStream(in);
				byte[] buffer = new byte[256];
				int n;
				while ((n = gunzip.read(buffer)) >= 0) {
					out.write(buffer, 0, n);
				}
				// toString()ʹ��ƽ̨Ĭ�ϱ��룬Ҳ������ʽ��ָ����toString(GBK)
				return out.toByteArray();
			} catch (IOException e) {
				throw e;
			}
		}
	}
}
