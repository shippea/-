package edu.java.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class QuizTCPServer {
	
	final static int SERVER_PORT = 3786;
	
	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		InputStream is = null;
		OutputStream os = null;

		try {
			while(true) {
				Socket socket = serverSocket.accept();
				is = socket.getInputStream();
				os = socket.getOutputStream();
				
				List<String> quizList = new ArrayList<String>();
				quizList.add(0, "1+1= ?");
				quizList.add(1, "2");
				quizList.add(2, "2+2= ?");
				quizList.add(3, "4");
				quizList.add(4, "3+3= ?");
				quizList.add(5, "6");
				quizList.add(6, "4+4= ?");
				quizList.add(7, "8");
				quizList.add(8, "5+5= ?");
				quizList.add(9, "10");
				quizList.add(10, "6+6= ?");
				quizList.add(11, "12");
				quizList.add(12, "7+7= ?");
				quizList.add(13, "14");
				quizList.add(14, "8+8= ?");
				quizList.add(15, "16");
				quizList.add(16, "9+9= ?");
				quizList.add(17, "18");
				quizList.add(18, "10+10= ?");
				quizList.add(19, "20");
				
				int listSize = quizList.size();
				int rightNum = 0;
				
				byte[] data = new byte[1024];
				int dataNum=0;
				String messageFromClient = new String(data, 0, dataNum);
				
				String welcome = "퀴즈프로그램을 시작합니다.";
				os.write(welcome.getBytes());
				os.flush();
				
				for(int i=0; i<listSize; i+=2) {
					
					os.write(quizList.get(i).getBytes());
					os.flush();
					
					dataNum = is.read(data);
					messageFromClient = new String(data, 0, dataNum);
					System.out.println(messageFromClient);
					
					if (messageFromClient == quizList.get(i+1)){
						os.write("정답입니다.".getBytes());
						os.flush();
						rightNum ++;
					}
					else {
						os.write("틀렸습니다.".getBytes());
						os.flush();
					}
//					try {
//						Thread.sleep(10000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
				}
				
				String countAnnounce = ((listSize/2) + "문제 중 " + rightNum + "문제 정답입니다.");
				os.write(countAnnounce.getBytes());
				os.flush();				
				
			
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch(IndexOutOfBoundsException iobe) {
			iobe.printStackTrace();
		} finally {
			try {
				is.close();
				os.close();
			} catch(IOException ioe) {
				ioe.printStackTrace();
			} catch(NullPointerException npe) {
				npe.printStackTrace();
			}
		}
		
//		catch (InterruptedException ie) {
//			ie.printStackTrace();
//		}
	} // main

} // class
