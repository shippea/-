package edu.java.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class HashSetTest {
	
	public static void main(String[] args) {
		
		Set<String> set = new HashSet<String>();
		
		set.add("사과");
		set.add("포도");
		set.add("딸기");
		set.add("수박");
		set.add("망고");
		
		int a = set.size();
		
		System.out.println(a);
		
		set.remove("딸기");
		int b = set.size();
		System.out.println(b);
		
		Iterator<String> it = set.iterator();		//
		
		try {
			while(it.hasNext()) {
				String nextItem = (String)it.next();
				System.out.println(nextItem);
			}			
		} catch (NoSuchElementException nsee) {
			System.out.println("엘리먼트 없음");
		}
		
	}


}
