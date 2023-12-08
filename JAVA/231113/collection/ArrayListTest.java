package edu.java.collection;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {
	
	public static void main(String[] args) {
		
		List<String> al = new ArrayList<String>();		    // ArrayList<t> : t 타입의 객체만 받음
															// List al = new Arraylis -> 무조건 상위 타입으로 받아주기 (List)
														 	// Ctrl + Shift + O = import 생성
				
		al.add("사과");
		al.add("딸기");
		al.add("바나나");
		al.add("파인애플");
		al.add("망고");
	
		int a1 = al.size();
		Object b = al.toArray();
			
		
		
		
		System.out.println(a1);
		System.out.println(b);
		System.out.println(al.toArray());
		
		for(int i=0; i<a1; i++){
			String str = al.get(i);
			System.out.println(i + ":" + str);
		}
		
		String c = al.get(3);
		System.out.println(c);
		
		al.remove(2);
		int a2 = al.size();
		
		for(int i=0; i<a2; i++){
			String str = al.get(i);
			System.out.println(i + ":" + str);
		}
		
		
		
		
		
	}

}
