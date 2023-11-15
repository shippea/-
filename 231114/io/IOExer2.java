package edu.java.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class IOExer2 {
	
	public static void main(String[] args) {
		
		/* #2. IO실습2 - 다음을 포함하는 텍스트를 생성하고 읽어서 화면에 출력하기
		 * 단) Buffering을 사용하세요
		 * poem.txt
		 * 죽는 날까지 하늘을 우러러
		 * 한 점 부끄럼이 없기를
		 * 그 다음 몰라...	*/
		
		Writer writer = null;
		BufferedWriter bw = null;
		BufferedReader br = null;
		
		
		try {
			String filePath = "C:/data/poem.txt";
			writer = new FileWriter(filePath);			
			// 경로를 중복 사용하므로 경로를 변수로 저장해서 사용
			bw = new BufferedWriter(writer);
			
			bw.write("죽는 날까지 하늘을 우러러");
			bw.newLine();	// 엔터키 기능
			bw.write("한 점 부끄럼이 없기를");
			bw.newLine();
			bw.write("그 다음 몰라...");
			bw.flush();		// buffered 사용할때는 flush 필수
			
			br = new BufferedReader(
					new FileReader(filePath)
					);
			
			while(true) {
				String lineData = br.readLine();
				if (lineData == null) break;
				System.out.println(lineData);
			}
			
		} catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch(IOException ioe){
			ioe.printStackTrace();
		}finally {
			try {
				bw.close();
				br.close();
			} catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}	// main
}	// class
