package edu.java.exception;

public class AgeMain {
	
	public static void main(String[] args) {
		int age = 17;
		
		if(age<19) {
			try {
				throw new AgeUnder19Exception();		// throw: 예외를 발생시킴
			} catch(AgeUnder19Exception aue) {
				System.out.println(aue.toString());
			}
		}
		else if(age>=50) {
			try {
				throw new AgeOver50Exception();
			} catch(AgeOver50Exception aoe) {
				System.out.println(aoe.toString());
			}
		}

	}
}