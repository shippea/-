package proxy;

// 임의의 Exception을 추가하려면 Exception 상속 class를 따로 생성하기
public class OverException extends Exception {
	
	@Override
	public String toString() {
		return ("숫자가 초과되었습니다");
	}
	
}
