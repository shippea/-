package edu.java.collection;

public class Student {		// ctrl + shift + f =  자동정렬
							// VO(Value Object) = DO(Data Object) = DTO(Data Transfer Object) = Javabeans
							// -> private 멤버변수, public 생성자, public getter&setter

	private String sno; // 학번
	private String name; // 성명
	private int kor; // 국어점수
	private int eng; // 영어점수
	private int math; // 수학점수

	public Student() {

	}


	public Student(String sno, String name, int kor, int eng, int math) {	// Source - Generate Constructor using fields
		super();
		this.sno = sno;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
	

	public String getSno() {												// Source - Generate Getters and Setters
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}
	
	@Override
	public String toString() {								// Source - Generate toString
		return "Student [sno=" + sno + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math + "]";
	}


}
