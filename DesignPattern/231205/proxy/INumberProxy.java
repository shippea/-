package proxy;

public class INumberProxy implements INum {

	INum inum;

	@Override
	public void print(int i) {
		if (i>5) {
			// 임의의 조건에 따라 본인 객체를 불러옴
			new INumber().print(i);
		} else {
			System.out.println("proxy 숫자: "+i);
		}
	}
}
