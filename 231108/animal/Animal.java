package edu.java.animal;


// 추상클래스이기 때문에 객체를 생성할 수 x
public abstract class Animal implements Walkable, Runnable, Flyable, Soundable {
	
	// 구현할 의무가 없어서 빈 공백으로 냅둬도 오류 발생 x -> 구현단계에서는 빈 공백은 오류 발생 (구현할 의무가 있어서)	
	
	String sort;
	String name;
	
	@Override
	public void walk() {
		System.out.println("걷는다");
	}
	
	@Override
	public void run() {
		System.out.println("뛴다");
	}
	
	@Override
	public void fly() {
		 System.out.println("난다");
	}
	
	@Override
	public void sound() {
		System.out.println("소리낸다");
	}

	// 모든 메소드를 기본구현해놔도 추상클래스가 될 수 있음 -> 나중에 추상메소드가 들어올 것을 대비하기 위해 가상의 추상메소드를 만들어놓음?
	
	public String call() {
		return (this.sort + " / " + this.name);
	}
}
