package abstractfactory;

import java.util.Vector;

public abstract class Tray extends Item {
	
	private Item tray;
	// Item을 상속받았지만 Item을 가지고 있음 -> compose 관계
	
	public Item getTray() {
		return tray;
	}
	
	
	@Override
	public abstract String makeHTML();
	
	public abstract void add();

}
