package edu.java.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileCreator {
	
	public static void main(String[] args) {
		
		/* 고용량 dummy file 만들어보기 */
		
		OutputStream os = null;
		
		try {
			os = new FileOutputStream("C:/data/bigfile.dat");
			byte[] buffer = new byte[4096];
			
			for (int i=0; i<buffer.length; i++) {
				buffer[i] = (byte)1;
			}
			// 각 배열 index에 1byte씩 넣어주기
			
			for (int i=0; i<1500000; i++) {
				os.write(buffer);
			}
			// 만든 배열을 150000번 읽음
				
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

}
