package edu.java.thread;

public class ThreadExer1 {
	
	public static void main(String[] args) {
		// main을 실행한 순간 하나의 메인thread 생성 
		// -> thread가 하나면 단일 thread, 2개 이상이면 멀티 thread
		
		/* thread1: runnable을 구현해서 작업thread를 만듬 */
		Runnable oddPrinter1 = new OddPrinter1();
		// 작업thread의 객체를 생성
		Thread thread1 = new Thread(oddPrinter1);
		// 메인thread에 작업thread의 run 메소드를 overriding
		thread1.start();
		// start: 요청 -> start 가능할 때 시작됨 (무조건 시작된다는 의미 x)
		// 작업thread 객체생성 ~ start 요청까지 메인thread 코드 위에 위치해야 정상작동
		
		/* thread2: thread class를 상속해서 작업thread를 만듬*/
		Thread thread2 = new OddPrinter2();
		thread2.start();
		
		/* Thread 구현한 클래스의 익명객체 */
		Thread thread3 = new Thread() {			
			@Override
			public void run() {
				for (int i=1; i<100; i+=2) {
					System.out.println("OddPrinter3 thread: " + i);
					try {
						Thread.sleep(500);
					} catch (InterruptedException ie) {
						ie.printStackTrace();
					}
				}
			}
		};
		thread3.start();		
		
		thread1.setName("A");
		thread2.setName("B");
		System.out.println(thread1.getName());
		System.out.println(thread2.getName());
		// thread에 이름을 주고 불러들임
		
		for (int i=2; i<102; i=i+2) {
			System.out.println("main thread: " + i);
			try {
				Thread.sleep(200);	//ms씩 멈춤
				// 메인thread이므로 따로 thread를 상속받거나 runnable을 구현할 필요 x
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}	//main
}	//class
