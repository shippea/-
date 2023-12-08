package builder;

public abstract class Builder implements java.util.stream.DoubleStream.Builder {
	
	public abstract String makeTitle ();
	public abstract String makeString ();
	public abstract String makeItems ();
	
	public abstract void getResult();
	
}
