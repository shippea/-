package strategy;


public class Hand {

	String[] hand = {"주먹", "가위", "보"};
	int handvalue;
	
	public Hand(String[] hand, int handvalue) {
		
		this.hand = hand;
		this.handvalue = handvalue;
	}
	
	public String getHand (int handvalue) {
		return null;
	}
	
	public boolean isStrongerThan() {
		return false;
	}
	
	public boolean isWeakerThan() {
		return false;
	}
	
	public int fight() {
		return handvalue;
	}
}
