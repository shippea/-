package edu.java.io;

import java.io.Serializable;

public class Person implements Serializable {			
	// Serializable을 implements 해줘야 직렬화해서 네트워크로 보낼 수 있음
	// -> marker interface: 안에 기능은 없지만 자격을 주는 interface
	
	public static final long serialVersionUID			
		= 751056429351716387L;
	// 네트워크 식별자 생성 (네트워크 상에서 구분 짓기 위해서 필요)
	
	// javabeans 형태로 데이터형식 만듬
	private String ssn;		// 주민등록번호
	private String name;	// 성명
	private int age;		// 나이
	
	public Person(String ssn, String name, int age) {
		super();
		this.ssn = ssn;
		this.name = name;
		this.age = age;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [ssn=" + ssn + ", name=" + name + ", age=" + age + "]";
	}
	
	

}
