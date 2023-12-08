package edu.java.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
	
	final static String TCPSERVER_IP = "172.30.1.30";
	// cmd에서 ipconfig /all 하면 내 IP를 알 수 있음
	final static int TCPSERVER_PORT = 8888;
	// Client가 data를 받는 쪽 -> IP PORT는 client 것으로 저장
	final static String MESSAGE_FROM_CLIENT = "하위^^";
	// 클라이언트가 보낼 문자열
	
	public static void main(String[] args) {
		
		Socket socket = null;
		// 클라이언트 소켓 (서버가 가지는 소켓과 혼동 주의)
		OutputStream os = null;
		// 클라이언트가 서버에 보낼 byte스트림
		InputStream is = null;
		// 클라이언트가 서버로부터 받을 byte스트림
		
		
		try {
			socket = new Socket(TCPSERVER_IP, TCPSERVER_PORT );
			// 클라이언트 소켓 생성
			System.out.println("socket 연결");
			
			os = socket.getOutputStream();
			is = socket.getInputStream();
			
			os.write(MESSAGE_FROM_CLIENT.getBytes());
			// 서버에 메세지를 byte배열로 전송
			os.flush();
			
			byte[] data = new byte[16];
			// 서버가 보낸 byte스트림을 저장할 버퍼
			int n = is.read(data);
			final String messageFromServer = new String(data,0,n);
			System.out.println(messageFromServer);
		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
	   	} catch (IOException ioe) {
		 	ioe.printStackTrace();
		} finally {
			try {
				is.close();
				os.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} 
		}
		
		
	} // main

} // class
