package edu.java.thread;

public class EvenThread extends Thread{
	
	EvenThread evenThread;
	
	private SharedObject sharedObject;
	// thread가 공유객체를 가지도록 변수설정 (has a)
	
	public SharedObject getSharedObject() {
		return sharedObject;
	}

	public void setSharedObject(SharedObject sharedObject) {
		this.sharedObject = sharedObject;
	}

//	@Override
//	public void run() {
//			for(int i=2; i<=30; i+=2){
//				if (i==20) new CharThread().start();
//				System.out.println("EvenThread: "+i);
//				try {
//					Thread.sleep(1);
//				} catch (InterruptedException ie) {
//					ie.printStackTrace();
//				}
//			}
//	} //run

	/* 선생님 코드 */
	public void run() {
		while(true) {
			if(sharedObject.isEvened()) {
				// sharedObject: 짝수일때만 실행
				try {
					synchronized(sharedObject) {
			// 동기화블럭: 공유객체에 대해 락을 획득 -> EvenThread가 SharedObject를 독점
					System.out.println("EvenThread: "
							+ sharedObject.getSharedNum());
			
					sharedObject.setSharedNum
						(sharedObject.getSharedNum()+1);
					sharedObject.setEvened(false);
					// 홀수를 출력할 차례
					if(sharedObject.getSharedNum()==30) interrupt();
					// 30에서 종료하기 위해서 InterreuptedException 강제발생
					}
					Thread.sleep(10);
				} catch (InterruptedException ie) {
					System.out.println("EvenThread 종료!");
					break;
				}
			}
		}
	}
} //class
