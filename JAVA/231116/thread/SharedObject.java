package edu.java.thread;

public class SharedObject {
	// thread들이 공유하는 공유객체
	private int sharedNum;
	// 1~30까지의 수
	private boolean isEvened;
	// 짝수가 출력되었는지 여부
	
	public SharedObject() {
		
	}

	public SharedObject(int sharedNum, boolean isEvened) {
		super();
		this.sharedNum = sharedNum;
		this.isEvened = isEvened;
	}

	public int getSharedNum() {
		return sharedNum;
	}

	public void setSharedNum(int sharedNum) {
		this.sharedNum = sharedNum;
	}

	public boolean isEvened() {
		return isEvened;
	}

	public void setEvened(boolean isEvened) {
		this.isEvened = isEvened;
	}

	@Override
	public String toString() {
		return "SharedObject [sharedNum=" + sharedNum + "]";
	}
	
	

}
