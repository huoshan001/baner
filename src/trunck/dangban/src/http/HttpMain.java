package http;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class HttpMain {
	//private static final String xmlpath = "bin/http/util/httpResource.xml";
	public String home_dir = "/ztkx/cbpay/dangban/http";
	public String lib_dir = "lib";
	public static void main(String[] args) {
		HttpMain httpmain = new HttpMain();
		System.out.println("HttpMain loadjar is begin");
		//����ϵͳ����
		httpmain.getStartupParams();
		//����ǰ׼��������libĿ¼������jar��
		File[] fileList = httpmain.prestartup(httpmain.lib_dir);
				
		//��ȡ��ǰclassloader
		ClassLoader loader = httpmain.getCurrentClassLoader();
		//��libĿ¼�µ�����jar�����ص�classloader��
		try {
			URLClassLoader urlLoader = httpmain.loadJarFile(loader,fileList);
			System.out.println("jar load end");
			Class<?> clazz = urlLoader.loadClass("http.ResManage");
			Method md = clazz.getMethod("manage", null);
			md.invoke(clazz.newInstance(), null);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	/**
	 * ��ȡ��������
	 * ��ʼ��home_dir��lib_dir
	 */
	public  void getStartupParams(){
		lib_dir = home_dir+File.separator+lib_dir;
	}
	/**
	 * ����ǰ׼��
	 */
	public File[]  prestartup(String libPath){
		System.out.println("lib_dir["+libPath+"]");
		File f = new File(libPath);
		 File[] list = f.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".jar");
			}
		});
		
		return list;
	}
	
	private ClassLoader getCurrentClassLoader() {
		return this.getClass().getClassLoader();
	}
	
	private URLClassLoader loadJarFile(ClassLoader loader,File[] jarFile) throws  MalformedURLException {
		URL[] urls = new URL[jarFile.length];
		for (int i = 0; i < jarFile.length; i++) {
			File f = jarFile[i];
			System.out.println(f.getAbsolutePath());
			urls[i]=f.toURI().toURL();
		}
		URLClassLoader clssLoader = new URLClassLoader(urls, loader);
		return clssLoader;
	}
}
