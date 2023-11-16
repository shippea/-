package edu.java.thread;

public class Person1Thread extends Thread {

	Person person1;
	
	Person1Thread(Person person){
		this.person1 = person;
	}
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			synchronized (person1.getAccount()) {
			// synchronized는 메소드 자체에 거는 것보다 동기화할 객체를 포함한 코드에
			// synchronized를 걸어야 동기화하고 싶은 것만 동기화하기 용이함
			// 메소드 자체에 걸면 동기화가 필요 없는 객체도 동기화가 될 수 있기 때문
				person1.getAccount().setMoney(
						person1.getAccount().getMoney()-1000
				);
			}
			System.out.println(person1.getName()+ "님 계좌잔액: " 
								+ person1.getAccount().getMoney());			
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ie) {
						ie.printStackTrace();
					}
		}	// for
	}	// run
}	// class
