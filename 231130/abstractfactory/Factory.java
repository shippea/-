package abstractfactory;

public abstract class Factory {
	
	private static Factory factory;	
	
	private Factory() {

	}
	//singletone 방식 사용 (private 멤버변수, 생성자)
	
	public static Factory getFactory() {
		return factory;
	}
	
	public abstract Link createLink();
	public abstract Tray createTray();
	public abstract Page createPage();
	
}
