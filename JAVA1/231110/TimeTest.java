package edu.java;

public class TimeTest {
	
	public static void main(String[] args) {
		
		long before = System.currentTimeMillis();

		
		for(int i=0; i<10000; i++) {
			System.out.println(i);
			System.out.println("");
		}
		
		long after = System.currentTimeMillis();	// before - after 사이에 실행할 것을 넣어두고 걸리는시간 알아보기
		
		System.out.println(after-before);
				
	}

}
