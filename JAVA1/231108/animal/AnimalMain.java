package edu.java.animal;

public class AnimalMain {
	public static void main(String[] args) {

		
		// 공통의 타입으로 받기 위해 Able로 모든 able 인터페이스들을 묶어줌
		Able lion = new Lion(); 
		Able eagle = new Eagle();
		printName(lion);
		printName(eagle);
	}
		
		public static void printName(Able able) {
			Animal who = (Animal) able;		// new로 새로운 객체를 생성한 것이 아니라 Lion에서 생성한 객체에서 받은 객체참조변수 lion을 able로 받아 쓰는 것이기 떄문에 문제 x
			System.out.println(who.name);
			System.out.println(((Animal) able).sort);
			System.out.println(who.call());
			who.walk();
		}
	
}
