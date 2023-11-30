package strategy;

public class Player {
	
	String name;
	Strategy strategy;
	int winCount;
	int loseCount;
	int gameCount;
	
	
	public void nextHand() {
		strategy.nextHand();
	}
	
	public void win() {
		nextHand();
	}
	
	public void lose() {
		nextHand();
	}
	
	public void even() {
		nextHand();
	}

}
