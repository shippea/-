package edu.java.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectStreamTest {
	
	public static void main(String[] args) {
		
		
		/* #3. IO실습3 - person1.obj 파일을 읽어서 Person객체로 만든 후 
		 * 				person1_copy.obj로 직렬화하기 */
		
		Person person1 = new Person("111111-1111111", "홍길동", 30);
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fos = new FileOutputStream("C:/data/person1.obj");
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(person1);
			oos.flush();
			
			fis = new FileInputStream("C:/data/person1.obj");
			ois = new ObjectInputStream(fis);
			
			Person person2 = (Person)ois.readObject();
			System.out.println(person2);
			
			fos = new FileOutputStream("C:/data/person2.obj");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(person2);
			oos.flush();
			
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		} finally {
			
			try {
				ois.close();
				oos.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}	//main

}	// class
