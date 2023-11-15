package edu.java.exception;

public class ExceptionTest3 {
	
	public static void main(String[] args) {	// main은 넘겨줄 곳이 없기 때문에 이곳에서 try-catch로 오류 해결
		try {
			a();								// try안에 메소드가 존재해야 함
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();				// printStackTrace: 오류가 발생한 순서를 알려줌
			System.out.println("예외발생!!! 퇴근마!!!");
		}
	}
	
	public static void a() throws NumberFormatException {	// 호출한 메소드 main에게 오류를 throws 함
		b();
	}
	
	public static void b() throws NumberFormatException {	// 호출한 메소드 a에게 오류를 throws 함
		int i = Integer.parseInt("천");
	}
	
}
