package edu.java.exception;

public class ExceptionTest2 {
	
	public static void main(String[] args) {
		
		// #1. OutofMemory 에러를 내보자

		int count=0;
		
		/*
		 * while(true) { ExceptionTest2 e2 = new ExceptionTest2(); Object obj = new
		 * Object(); System.out.println(++count + ": " + e2 + obj ); }
		 * 
		 */
		

		  a();
		
	}

		// #2. StackOverflow 에러를 내보자
	
	public static void a() {
		b();
	}
	public static void b() {
		a();
	}

}
