package edu.java.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class IOExer1 {
	
	/* #1. IO 실습1
	 * 1. diary.txt 파일을 생성하고 오늘 일기를 쓰세요
	 * 2. diary.txt 파일을 읽어 diary.copy.txt파일로 복사하세요 */
	
	public static void main(String[] args) {
		
		 Reader r1 = null;
		 Writer w = null;
		 // txt 파일을 입출력하므로 문자타입을 주고받을 수 있는 Reader, Writer 사용
		 
		 try {
			 r1 = new FileReader("C:/data/diary.txt");
			 w = new FileWriter("C:/data/diary_copy.txt");
			 
			 char[] buffer = new char[100];
			 
			 long startTime = System.currentTimeMillis();
			 
			 while(true) {
				 int realNum = r1.read(buffer);
				 if (realNum == -1) break;
				 w.write(buffer);
				 w.flush();
			 }
			 
			 long endTime = System.currentTimeMillis();
			 
			 System.out.println(endTime - startTime);
			 
		 } catch (FileNotFoundException fnfe) {
			 fnfe.printStackTrace();
		 } catch (IOException ioe) {
			 ioe.printStackTrace();
		 } finally {
			 try {
				 r1.close();
				 w.close();
			 } catch (IOException ioe) {
				 ioe.printStackTrace();
			 }
		 }
	}
}
