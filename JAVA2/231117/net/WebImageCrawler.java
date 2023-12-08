package edu.java.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WebImageCrawler {
	
	public static void main(String[] args) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		URL url = null;
		URLConnection urlConn = null;
		
		try {
			url = new URL("http://www.boannews.com/media/upFiles/34_15_.jpg");
			urlConn = url.openConnection();
			
			bis = new BufferedInputStream(urlConn.getInputStream());
			
			bos = new BufferedOutputStream(
					new FileOutputStream("C:/data/WebImageCrawler.jpg"));
			
			byte[] imgByte = new byte[1024];
			// byte 데이터를 넣어줄 객체 생성
			int readedByte = 0;
			// 데이터를 넣은 범위를 지정할 변수			
			
			while((readedByte=bis.read(imgByte))!=-1) {
				// input으로 데이터를 읽고
				bos.write(imgByte,0,readedByte);
			}
			
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				bis.close();
				bos.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			
		}
		
	}
	

}
