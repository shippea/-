package edu.java.thread;

public class ThreadExer2 {
	
	/* #1. 실습1 - 다음 내용대로 출력하기
	 * 1) 10보다 작은 홀수가 먼저 출력된 후 
	 * 2) 홀수와 짝수는 같이 출력되어야함
	 * 3) 짝수 20이 출력된 후 영문자 출력*/
	
	public static void main(String[] args) {
		
		Thread oddThread = new OddThread();
		Thread evenThread = new EvenThread();
		Thread charThread = new CharThread();

		oddThread.start();
		
	} //main
} //class
