package abstractfactory;

public abstract class Link extends Item {
	
	private String url;
	
	@Override
	public abstract String makeHTML();
	

}
