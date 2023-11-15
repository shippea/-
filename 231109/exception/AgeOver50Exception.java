package edu.java.exception;

public class AgeOver50Exception extends Exception {
	
	@Override
	public String toString() {
		return ("50세 이상 관람불가!!");
	}

}
