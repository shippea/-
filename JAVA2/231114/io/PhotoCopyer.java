package edu.java.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PhotoCopyer {

	public static void main(String[] args) {
		
		// "C:/data/lunch.png"
		
		// 어떤 타입의 데이터인지 입력인지 출력인지 먼저 결정
		
		InputStream is = null;
		OutputStream os = null;
		// is를 try 안에 선언했으므로 변수를 인식하지 x -> 밖에서 선언하기
		
		try {
			is = new FileInputStream ("C:/data/bigfile.dat");
			// InputStream is = new FileInputStream ("C:/data/lunch.png"); 
			// 입력시 오류가 발생 -> try - catch로 예외처리
			os = new FileOutputStream ("C:/data/bigfile_copy.dat");
			byte[] buffer = new byte[32768];
			// buffer를 직접 테스트해서 최적의 buffer값 설정 -> 일반적으로 1024 사용
			// 1024일때 65211
			// 2048일때 27968
			// 4096일때 23100
			// 8192일때 20356
			// 16384일때 18587
			// 32768일때 27082 21192
			
			long startTime = System.currentTimeMillis();
			
			while(true) {
				int readByteNum = is.read(buffer);
				// IOException 발생 -> try - catch로 예외처리
				
				if (readByteNum == -1) break;
				// 더 이상 읽은 byte가 없을 때 멈춤
				 os.write(buffer);
				 os.flush();
			}
			
			long endTime = System.currentTimeMillis();
			
			System.out.println(endTime - startTime);
			// currentTimeMillis를 문구 앞뒤로 사용해 나오는 시간차이를 통해 걸리는 시간 측정 가능 
			
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {		
			try {
				is. close();
				os. close();
				// IOException 발생 -> try - catch로 예외처리
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		
	
		
	
		
		
	}
}
