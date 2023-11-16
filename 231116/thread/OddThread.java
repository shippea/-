package edu.java.thread;


/* 1~30의 정수 중 홀수만 출력하는 thread */
public class OddThread extends Thread {
	
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
//
//		for(int i=1;i <30; i+=2) {
//			if (i==9) new EvenThread().start();
//			System.out.println("OddThread: " +i);
//			if (i==1) new EvenThread().start();
//			try {
//					sharedNum.setSharedNum().wait();
//				}
//				Thread.sleep(1);
//			} catch (InterruptedException ie) {
//				ie.printStackTrace();
//			}
//		}
//	} //run
//	
	/* 선생님 코드*/
	public void run() {
		
		while(true) {
			if(!sharedObject.isEvened()) {
				// !sharedObject: 홀수일때만 실행
				try {
					synchronized(sharedObject) {
					System.out.println("OddThread: "
							+ sharedObject.getSharedNum());
					sharedObject.setSharedNum
						(sharedObject.getSharedNum()+1);
					sharedObject.setEvened(true);
					if(sharedObject.getSharedNum()==30) break;
					}
					Thread.sleep(10);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		}
	}

} //class
