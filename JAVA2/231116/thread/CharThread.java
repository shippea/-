package edu.java.thread;

public class CharThread extends Thread {
	
	@Override
	public void run() {
	
		for(int i=65; i<91; i++) {
			System.out.println("CharThread: " +(char)i);
			try {
				Thread.sleep(1);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	} //run
} //class
