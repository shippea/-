package edu.java.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	
	final static int SERVER_PORT = 3785;
	// port번호: 0~1023 제외 (system이 이미 사용) ~65534까지 중에서 하나
	final static String MESSAGE_FROM_SERVER = "Hello Client!";
	// 서버에서 client에게 보낼 문자설정
	
	public static void main(String[] args) {
		
		 ServerSocket serverSocket = null;
		 // 서버는 ServerSocket이 필요함
		 // ServerSocket은 클라이언트에게 할당할 Socket을 관리하는 역할
		 // 서버는 클라이언트를 socket으로 구분함

		 try {
			 serverSocket = new ServerSocket(SERVER_PORT);
			 // 설정한 포트를 사용하는 서버소켓 생성
		 } catch (IOException ioe) {
			 ioe.printStackTrace();
		 }
		 
		 InputStream is = null;
		 // 클라이언트 -> 서버로 보내는 byte스트림
		 OutputStream os = null;
		 // 서버 -> 클라이언트로 보내는 byte스트림
		
		 try {
			 while(true) {
			// while(true)로 서버가 계속 살아있는 상태로 만듬
				 System.out.println("socket 대기");
				 Socket socket = serverSocket.accept();
				 // 서버소켓의 accept메소드는 Blocking메소드 -> 작업이 끝날때까지 사용
				 // accept메소드가 호출되면 클라이언트의 연결을 대기
				 // 클라이언트와 통신할 서버소켓
				 
				 System.out.println("host: " + socket.getInetAddress() + " 연결성공!");
				 is = socket.getInputStream();	// 클라이언트 -> 서버로 보내는 byte스트림
				 os = socket.getOutputStream();	// 서버 -> 클라이언트로 보내는 byte스트림
				 // 클라이언트와 연결되면 클라이언트에 할당한 소켓의 입출력스트림을 얻을 수 있음
				 
				 byte[] data = new byte[16];
				 // 16byte 버퍼 생성
				 int n = is.read(data);
				 // 읽어들인 바이트 수를 반환 -> 0 ~ 받은 바이트 수 n까지 범위를 지정하기 위해서 
				 final String messageFromClient = new String(data, 0 , n);
				 // 클라이언트에게 받은 byte스트림을 문자열로 변환
				 System.out.println("message from clinet:" + messageFromClient);
				 
				 os.write(MESSAGE_FROM_SERVER.getBytes());
				 // 서버에서 클라이언트에게 byte배열로 보냄
				 os.flush();
				 // OutputStream은 flush를 해줘야 클라이언트에게 전송됨
				 }
		 } catch(IOException ioe) {
			 ioe.printStackTrace();
		 } finally {
			 try {
				 is.close();
				 os.close();
			 } catch(IOException ioe) {
				 ioe.printStackTrace();
			 }
		 }
		
		 
	} // main
} // class
