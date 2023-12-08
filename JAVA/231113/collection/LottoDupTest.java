package edu.java.collection;

import java.util.HashSet;
import java.util.Set;

public class LottoDupTest {
	
	public static void main(String[] args) {
		
		Object noDup7Num = noDup();
		System.out.println(noDup7Num);
	}

	/* 1~45 중에서 중복되지 않는 7개의 숫자를 리턴 */
	static Object noDup() {
		
		Set<Integer> num = new HashSet<Integer>();
		
		while(true) {
			num.add((int)(Math.random()*45)+1);			
			if(num.size()==7) break;					// num 7번째까지 1~45를 random하게 부여
		}		
		return  num;
	}
}
