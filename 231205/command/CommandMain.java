package command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandMain {
	
	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Calculator cal = new Calculator();
		
		try {
			System.out.println("입력: ");
			System.out.println("결과: "+cal.calc(br.readLine()));
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		
		
	}

}
