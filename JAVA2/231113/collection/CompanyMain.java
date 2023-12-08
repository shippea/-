package edu.java.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CompanyMain {
	
	/* #2. 회사 2개를 생성하고 회사 2개의 2022년 수익합계와 2023년 수익합계를 아래와 같이 출력하시오. 
	 * 		ABC회사와 DEF회사의 2022년 총 수익은 _____입니다.
	 * 		ABC회사와 DEF회사의 2023년 총 수익은 _____입니다.*/
	
	
	public static void main(String[] args) {
		
		Company abc = new Company("001", "ABC", 100, 150);
		Company def = new Company("002", "DEF", 120, 110);
		
		Map<String, Company> map = new HashMap<String, Company>();
		
		map.put(abc.getSsn(), abc);
		map.put(def.getSsn(), def);
		
		List<Integer> al = new ArrayList<Integer>();
		
		al.add(0);
		al.add(0);
		
		int size = al.size();

		System.out.println(map.entrySet());			// map.entrySet: map이 갖고있는 배열을 보여줌
													// -> [001=Company [ssn=001, name=ABC, income2022=100, income2023=150], 002=Company [ssn=002, name=DEF, income2022=120, income2023=110]]
		
		Iterator it = map.entrySet().iterator();	// map.entrySet의 배열을 순서대로 저장 
		
//		System.out.println(it.next());				// 첫 번째 배열 출력 -> 001=Company [ssn=001, name=ABC, income2022=100, income2023=150]
//		System.out.println(it.next());				// 그 다음 배열 출력 -> 002=Company [ssn=002, name=DEF, income2022=120, income2023=110]
		
		while(it.hasNext()) {
			Map.Entry<String, Company> entry = (Map.Entry<String, Company>) it.next();		

			System.out.println(entry.getValue());	// -> Company [ssn=001, name=ABC, income2022=100, income2023=150]
													//    Company [ssn=002, name=DEF, income2022=120, income2023=110]
			
			Company company = (Company) entry.getValue();	// key값을 제거한 value값만 제공하기 위해 Map.Entry 형변환 후 getValue를 Object로 형변환
						
			al.set(0, al.get(0)+company.getIncome2022());	// company에 넣어준 value 중 필요한 요소만 arraylist에 저장
			al.set(1, al.get(1)+company.getIncome2023());
		}
		
		for(int i=0; i<size; i++) {
				System.out.println("ABC회사와 DEF회사의 " + (2022+i)  + " 년 총 수익은 " + al.get(i) + " 입니다.");
		}
				
			
		
	}
	

}
