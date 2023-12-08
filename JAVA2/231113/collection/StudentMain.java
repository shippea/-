package edu.java.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class StudentMain {
	public static void main(String[] args) {
		
		Student hong = new Student("std001", "홍길동", 95, 90, 80);
		Student kang = new Student("std002", "강감찬", 50, 30, 20);
		
		hong.setKor(100);	// 수정할부분이 있으면 set을 이용 
		hong.getKor();		// 가져올 때는 get 이용 (hong.kor은 private으로 설정했으므로 직접 부를 수 x)
		
		/* #1. 두 학생의 데이터를 Collection으로 만들어서 국어점수평균, 영어점수평균, 수학점수평균을 출력해보자 */
				
		Map<String, Student> map = new HashMap<String, Student>();	// 학번은 고유하고 나머지 값들은 중복이 될 수 있으므로 k에 학번 나머지에 v값 넣기
		
		map.put(hong.getSno(), hong);
		map.put(kang.getSno(), kang);
		
		System.out.println(map.entrySet());
		
		List<Integer> al = new ArrayList<Integer>();
		
		al.add(0);
		al.add(0);
		al.add(0);
		
		Iterator it = map.entrySet().iterator();
		
		while(it.hasNext()) {
			Map.Entry<String, Student> entry
			= (Map.Entry<String, Student>) it.next();
			
			Student student = (Student) entry.getValue();
			
			System.out.println(entry.getValue());
			
			al.set(0, al.get(0)+student.getKor());
			al.set(1, al.get(1)+student.getEng());
			al.set(2, al.get(2)+student.getMath());
		}
		
//		for(Integer in : al) {
//			System.out.println(in/map.size());
//		}
	}

}
