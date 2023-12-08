package edu.java;

public class Parent {
	
	int height;
	int weight;
	
	Parent(){
		this(180,90);
	}
	
	Parent(int height, int weight){
		this.height = height;
		this.weight = weight;		
	}

	
	public int myHeight() {
		return (this.height);
	}
	
	public int myWeight() {
		return (this.weight);
	}

}
