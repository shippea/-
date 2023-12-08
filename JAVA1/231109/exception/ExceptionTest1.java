package edu.java.exception;

public class ExceptionTest1 {
	
	public static void main(String[] args) {
		
		String str1 = "1000";
		int i1 = Integer.parseInt(str1);
		System.out.println(i1*2);	// 2000 출력
		
		/************************************************************/
		
		String str2 = "천";
		int i2 = 0;	// 지역변수는 초기화해서 사용
		
		try {
			i2 = Integer.parseInt(str2);	// i2를 try밖에서 선언해야 i2 값을 출력할 때도 변수로 인식
		} catch(NumberFormatException nfe) {	// Exception으로 잡으면 어떤 예외가 발생했는지 알 수 없기 때문에 최대한 예외를 나열해서 처리하기
			System.out.println(nfe);
		}
		
		System.out.println(i2*2);	// NumberFormatException 발생: 천을 숫자로 바꿀 수 없음
		
		/************************************************************/
		
		Object obj = null;
		
		try {
			System.out.println(obj.hashCode());		// NullPointerException 발생: obj를 null로 선언했기 때문 -> new Object로 참조값을 주면 해결
		} catch(NumberFormatException nfe) {
			System.out.println(nfe);
		} catch(NullPointerException npe) {
			System.out.println(npe);				
		}
		
		/************************************************************/
		
		
		try {
			System.out.println(obj.hashCode());		// 다중 catch: 위로 갈수록 더 하위 예외를 입력 -> Exception은 맨 아래로
			} catch(NumberFormatException nfe) {	// Exception 하나만 사용하는 것은 최대한 지양하기 -> 어떤 오류가 발생했는지 알 수 없음
				System.out.println(nfe);
			} catch(NullPointerException npe) {
				System.out.println(npe);				
			} catch(Exception ex) {
				System.out.println(ex);
			}
		
	
		
	} // main

}
