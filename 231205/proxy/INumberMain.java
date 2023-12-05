package proxy;

import java.util.Scanner;

import org.omg.CORBA.Any;

public class INumberMain {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			while(true) {
				// proxy를 먼저 지나가기 때문에 proxy 객체를 생성
				INum inum = new INumberProxy();
				
				int i = sc.nextInt();

				inum.print(i);
				
				// 임의의 조건에 따라 exception을 발생시킴
				if(i>10) {
					throw new OverException();
				}
			}
		} catch (OverException oe) {
			System.out.println(oe.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		
	}


	
}



