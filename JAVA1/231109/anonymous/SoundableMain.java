package edu.java.anonymous;

public class SoundableMain {
	public static void main(String[] args) {
		
		Soundable cat = new Cat();
		
		printSound(new Dog("강아지","멍멍"));
		printSound(new Cat("야옹이","야옹"));
		printSound(cat);
	}
	
	
	
	public static void printSound(Soundable soundable) {

		
		/*	// instanceof 사용해보기
		 * Soundable who = new Soundable() { public String sound() { if (soundable
		 * instanceof Dog) return soundable.sound(); else if (soundable instanceof Cat)
		 * return soundable.sound(); else return ""; }
		 * 
		 * public String name() { return soundable.name(); } };
		 * 
		 */		
		
		System.out.println(
				soundable.name() + "는 " + soundable.sound() + " 소리를 냅니다!");
		
		
	}

}
