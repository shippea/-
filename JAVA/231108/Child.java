package edu.java;

public class Child extends Parent{
	
	int height;
	int weight;
	
	Child(){		
		this(190,80);
	}
	
	Child(int height, int weight){
		super (180,90);
		this.height = height;
		this.weight = weight;		
	}


	

	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
	@Override
	public int myHeight() {
		return (this.height);
		
	}
	
	public int selfWeight() {
		return (this.weight);
	}

	

}
	