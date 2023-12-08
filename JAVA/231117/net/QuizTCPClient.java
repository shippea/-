package edu.java.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizTCPClient {

	final static String TCPSERVER_IP = "172.30.1.5";
	final static int TCPSERVER_PORT = 3786;
	
	public static void main(String[] args) {
		
		Socket socket =null;
		OutputStream os = null;
		InputStream is = null;
		Scanner sc = new Scanner(System.in);
		
		try {
			socket = new Socket(TCPSERVER_IP, TCPSERVER_PORT);
			os = socket.getOutputStream();
			is = socket.getInputStream();
			
			List<String> answerList = new ArrayList<String>();
			answerList.add(0, "1+1= ?");
			answerList.add(1, null);
			answerList.add(2, "2+2= ?");
			answerList.add(3, null);
			answerList.add(4, "3+3= ?");
			answerList.add(5, null);
			answerList.add(6, "4+4= ?");
			answerList.add(7, null);
			answerList.add(8, "5+5= ?");
			answerList.add(9, null);
			answerList.add(10, "6+6= ?");
			answerList.add(11, null);
			answerList.add(12, "7+7= ?");
			answerList.add(13, null);
			answerList.add(14, "8+8= ?");
			answerList.add(15, null);
			answerList.add(16, "9+9= ?");
			answerList.add(17, null);
			answerList.add(18, "10+10= ?");
			answerList.add(19, null);
			
			int listSize = answerList.size();
			
			byte[] data = new byte[1024];
			int dataNum = is.read(data);
			String messageFromServer = new String(data, 0, dataNum);
			System.out.println(messageFromServer);
			
			for(int i=0; i<listSize; i+=2) {
//				dataNum = is.read(data);
//				messageFromServer = new String(data, 0, dataNum);
//				System.out.println(messageFromServer);

				
				if (messageFromServer==answerList.get(i)) {
					String answer = sc.nextLine();
					os.write(answerList.set(i+1, answer).getBytes());
					os.flush();
					dataNum = is.read(data);
					messageFromServer = new String(data, 0, dataNum);
					System.out.println(messageFromServer);
				}
			}
			
			dataNum = is.read(data);
			messageFromServer = new String(data, 0, dataNum);
			System.out.println(messageFromServer);
			
		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (IndexOutOfBoundsException iobe) {
			iobe.printStackTrace();
		} finally {
			try {
				is.close();
				os.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} catch (NullPointerException npe) {
				npe.printStackTrace();
			}
		}
		
		
	} // main
} // class
