package edu.java.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class HashMapTest {
	
	public static void main(String[] args) {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("사과", 100);		// 원래는 new Interger(100)으로 boxing 해야되지만 이제는 autoboxing이 되므로 100만 써도 됨
		map.put("딸기", 80);		
		map.put("복숭아", 60);		
		map.put("망고", 40);		
		map.put("파인애플", 20);		
		
		int a = map.hashCode();
		
		System.out.println(a);
		
		int b = map.size();
		
		System.out.println(b);
		
		System.out.println(map.values());		//  순서가 없어서 무작위로 출력
	
		Set ketSet = map.keySet();				// keyset으로 put된 것들을 묶어줌
		Iterator it = ketSet.iterator();		// keyset에 저장된 객체를 한 번씩 불러옴
		
		while(it.hasNext()) {
			String nextItem = (String)it.next();
			System.out.println(nextItem);
		}
		
//		Map<String, Integer> map2 = new SortedMap<String, Integer>();
		
		
	
	}
}
