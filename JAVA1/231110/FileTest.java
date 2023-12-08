package edu.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileTest {
	
	public static void main(String[] args)	{
		File f = new File("C:/memopad/test.txt");
		
		if(!f.exists()) {					// f라는 파일이 존재하는지 
			try {
				f.createNewFile();
			} catch(IOException ioe) {
				ioe.printStackTrace();
			}
			
			FileWriter fw = null;			// 문자를 쓰는 기능
			try {
				fw = new FileWriter(f);		// f 파일에 문자를 쓰자
				fw.write("안녕하세요!");		// 쓸 내용 작성
			} catch(IOException ioe) {
			ioe.printStackTrace();
			} finally {
				try{
					fw.close();
			} catch(IOException ioe) {
				ioe.printStackTrace();
			}
			}
		}
	}
}

		