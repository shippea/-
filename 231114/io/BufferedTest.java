package edu.java.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class BufferedTest {
	
	public static void main(String[] args) {
		
		InputStream is = null;
		OutputStream os = null;
		InputStreamReader isr = null;
		OutputStreamWriter osw = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		// 입출력시 효율적인 buffered 입출력
		
		try {
			is = new FileInputStream("C:/data/img6.jpg");	
			// 해당 경로의 파일을 불러옴
			isr = new InputStreamReader(is);				
			// 파일을 byte -> 문자 단위로 변환해 읽음
			br = new BufferedReader(isr);					
			// 문자 단위로 변환해 읽은 문서를 한 줄 씩 불러들일 수 있게 됨
			
			br = new BufferedReader(
					new InputStreamReader(
							new FileInputStream("C:/data/img6.jpg")
							)
					);
			// 위에 3줄짜리 is -> isr -> br 코드를 1줄화 시킴
			// -> 한번만 쓸 거라 객체명을 선언하지 않고 익명객체로 사용
			
			bw = new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream("C:/data/diary3.txt")
					)
				);
						
			while(true) {
				String dataLine = br.readLine();
				// 파일을 문자단위로 변환하여 받은 데이터를 한 줄씩 읽은 것을 dataLine에 저장
				if (dataLine==null) break;
				// 문자단위로 변환한 데이터를 더 이상 읽을 것이 없으면 중단
				System.out.println(dataLine);				
				bw.write(dataLine);
				// dataLine에 저장한 데이터를 쓰고 diary3에 저장
				// -> 문자단위로 출력할때 bufferedWriter -> FileWriter만 해서 출력가능
			}
			
		} catch(FileNotFoundException fnfe){
			fnfe.printStackTrace();			
		} catch(IOException ioe) {
			ioe.printStackTrace();		
		}finally {
			try {
				br.close();
				bw.flush();	bw.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		
		}
	}

}
