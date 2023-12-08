package edu.java.thread;

public class ThreadExer3 {
	
	/* #2. 실습2 - 공유객체를 사용해서 0~30까지 순차적으로 출력하기 */
	
	// 공유객체를 사용 
	
	public static void main(String[] args) {

		SharedObject so = new SharedObject(0,true);
		// 공유할 객체 생성
		
		OddThread oddThread = new OddThread();
		EvenThread evenThread = new EvenThread();
		
		oddThread.setSharedObject(so);
		evenThread.setSharedObject(so);
		// oddThread, evenThread에 공유 객체 설정
		
		oddThread.start();
		evenThread.start();
		
	} //main
} //class
