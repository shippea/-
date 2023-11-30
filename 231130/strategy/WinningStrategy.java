package strategy;

public class WinningStrategy extends Strategy {
	
	boolean won;
	int prevHand;

	@Override
	public Hand nextHand() {
		return null;
	}

	@Override
	public boolean study() {
		return false;
	}

}
