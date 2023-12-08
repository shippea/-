package edu.java.exception;

public class AgeUnder19Exception extends Exception {	// 새로운 exception을 만들 때 exception을 상속받아야 제대로 작동
	
	@Override
	public String toString() {
		return "19세 미만 관람불가!!";
	}
	


}
