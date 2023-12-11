package jdbctest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

// oracle과 eclipse를 연동시켜주는 class
public class OracleConnection {
	
	
	// 데이터베이스 연결 인터페이스
	private static Connection conn;
	private static Properties prop;
	
	// 설정정보들을 properties file에 담고 객체를 생성해 사용
	static {
		prop = new Properties();
		try {
			prop.load(new FileReader(new File(
					"D:/eclipse_workspace/JDBCTest/src/prop/dbconn.properties"))
			);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {	
			ioe.printStackTrace();
		}
	}
	
	// Oracle JDBC URL: 연결할 오라클데이터베이스의 경로
	// 형식> jdbc:oracle:thin:@오라클서버아이피:오라클포트번호:SID
	// 현재는 Oracle URL이지만 db 회사마다 URL이 다름
	private static final String JDBC_URL 
		= prop.getProperty("JDBC_URL");
//		= "jdbc:oracle:thin:@localhost:1521:xe";
	
	// 오라클 데이터베이스 드라이버 클래스명
	private static final String JDBC_DRIVER
		= prop.getProperty("JDBC_DRIVER");
//		= "oracle.jdbc.driver.OracleDriver";
	
	// 계정명
	private static final String JDBC_USER
		= prop.getProperty("JDBC_USER");
//		= "shippear";
	
	// 계정 비밀번호
	private static final String JDBC_PWD
		= prop.getProperty("JDBC_PWD");
//		= "1234";
	
	public static Connection getConnection () {
		
		try {
			
			// 데이터베이스 드라이버클래스를 메모리에 로딩
			Class.forName(JDBC_DRIVER);
			
			// 데이터베이스 커넥션 획득 (conn이 null이 아니면 접속된 것)
			conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PWD);
			
			// 데이터베이스에 보낼 쿼리문 (오류나는 것을 방지하기 위해 공백 한 칸씩 만들기)
			String sql = " select employee_id, emp_name from employees ";
			
			// 쿼리 인터페이스
			Statement stmt = conn.createStatement();
			
			// 수행된 query 결과를 resultset에 넣음 (결과행집합에 대한 커서 획득)
			ResultSet rs = stmt.executeQuery(sql);
			
			// 커서를 한 행 한 행 이동시키면서 결과를 가져옴
			// 불러올 값의 데이터타입 확인 후 java 데이터타입에 맞춰서 갸
			// 예) number -> int, long / varchar -> string
			while(rs.next()) {
				int employeeId = rs.getInt("employee_id");
				String empName = rs.getString("emp_name");
//				System.out.println(employeeId + ", " + empName);
			}
			
			return conn;
			
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		} 
		
	} // getConnection
	
	// Connection, Statement, ResultSet도 반드시 close 작업필요!
	// close할떄 순서는 반대인 rs -> stmt -> conn 순서대로 진행
	public static void closeConnection(
			Connection conn, Statement stmt, ResultSet rs) {
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
		if (stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

} // class