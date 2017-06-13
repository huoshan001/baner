package org.inn.baner.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class FileUtil {
	/**
	 * �����������ַ����ķ�ʽд�ļ�
	 * @param fileName
	 * @param content
	 * @param encoding
	 * @throws IOException
	 * 2016��3��25�� ����9:43:24
	 * @author zhangxiaoyun
	 */
	public static void writeFile(String fileName,List<String> content,String encoding) throws IOException{
		BufferedWriter bw = null;
		try{
			bw =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName),encoding));
			for (int i = 0; i < content.size(); i++) {
				bw.write(content.get(i));

				if(i<(content.size()-1)){
					//���һ�в�д�س�����
					bw.newLine();
				}
			}
		}finally{
			if(bw!=null){
				bw.flush();
				bw.close();
			}
		}
	}


	/**
	 * �����������ַ����ķ�ʽд�ļ�
	 * @param fileName
	 * @param content
	 * @param encoding
	 * @throws IOException
	 * 2016��3��25�� ����9:43:24
	 * @author zhangxiaoyun
	 */
	public static void writeFile(String fileName,List<String> content) throws IOException{
		BufferedWriter bw = null;
		try{
			bw =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
			for (int i = 0; i < content.size(); i++) {
				bw.write(content.get(i));

				if(i<(content.size()-1)){
					//���һ�в�д�س�����
					bw.newLine();
				}
			}
		}finally{
			if(bw!=null){
				bw.flush();
				bw.close();
			}
		}
	}
//	public static void main(String[] args) throws IOException {
//		List<String> content = new ArrayList<String>();
//		String name = "����������������������";
//		StringBuilder sb  = new StringBuilder();
//		sb.append(new String(name.getBytes(),"GBK"));
//		content.add(sb.toString());
//		writeFile("test.txt",content,"GBK");
//	}
}
