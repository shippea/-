package command;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

public class Calculator {
	
		Command command;

	
	// BufferedReader로 데이터를 받아오기 때문에 String 형태로 받음
	public float calc(String str) {
		
		// Map의 하위인 prop을 사용 (key,value)값을 이용
		Properties prop = new Properties();
		
		StringTokenizer st = new StringTokenizer(str);
		// nextToken: spacebar 입력전의 값을 받아옴
		int i = Integer.parseInt(st.nextToken());
		int j = Integer.parseInt(st.nextToken());
		String opr = st.nextToken();

		try {
			prop.load(new FileReader("D:/eclipse_workspace/DesignPattern/src/command/command.properties"));
			
			// opr = prop의 key로 해당 key getProperty했을 때 해당하는 value값이 나옴
			// 그 값이 class를 불러오는 것이기 때문에 class 타입으로 받아줌
			Class cl = Class.forName((String)prop.getProperty(opr));
			
			// class타입으로 받아온 class의 객체를 생성하기 위해선 newInstance 사용
			cl.newInstance();
			command = (Command) cl.newInstance();
			
		} catch (IllegalAccessException iae) {
			iae.printStackTrace();
		} catch (InstantiationException ie) {
			ie.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	
		return command.calc(i, j);
		
		
	}
	
	

}
