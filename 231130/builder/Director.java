package builder;

public class Director {
	
	Builder builder;
	
	public Director(Builder builder) {
		this.builder = builder;
	}
	
	public void construct() {
		builder.makeItems();
		builder.makeString();
		builder.makeTitle();
	}

}
