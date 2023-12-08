package edu.java.anonymous;

public class Cat implements Soundable {
	
	
	
	String name;
	String sound;
	
	Cat(){
		this("냥냥이","냥냥");
	}
	
	Cat(String name, String sound){
		this.name = name;
		this.sound = sound;
	}

	@Override
	public String sound() {
		return this.sound;
	}

	@Override
	public String name() {
		return this.name;
	}

}
