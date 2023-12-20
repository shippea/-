package callable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import jdbctest.OracleConnection;

// 프로시져 불러오기
public class CallableTest {
	
	Connection conn;
	CallableStatement cstmt;
	// select 된 것들의 cursor 역할
	ResultSet rs;
	String sql;
	int result;
	
	CallableTest() {
		
		try {
			conn = OracleConnection.getConnection();
			// 만들어둔 프로시져, 함수를 불러오는 strig sql
			// 불러올 프로시져,함수명 뒤에 파라미터 개수만큼 ?을 붙여줌
			sql = " {call PROC_CAll(?,?) } ";
			
			cstmt = conn.prepareCall(sql);
			// 1번 ?에 100의 값을 넣어줌 (1번 ?은 in이므로 set으로 변경할 수 있음)
			//			 -> PROC_CALL 프로시져의 P_EMPID 파라미터에 100을 던져줌
			cstmt.setInt(1, 100);
			// Types.INTEGER -> oracle과 type이 다르기 때문에 type을 mapping 해줌
			//						(out 되어 java가 받는 값만 mapping해주면 됨)
			// type mapping한 값을 PROC_CALL 프로시져의 P_DEPTID 파라미터에 값을 던져줌
			cstmt.registerOutParameter(2, Types.INTEGER);
			
			/*  Oracle에서
				number -> Integer, Long
				char, varchar2 -> Strin
				date -> Timestamp
			 								*/
			
			// CallableStatement 실행 -> 등록한 값들을 가지고 dbeaver에게 갔다와서 값을 받아옴
			cstmt.executeUpdate();
			// out되는 값만 get 할 수 있음
			result = cstmt.getInt(2);
	
		/* 	1. set, register -> (in되어 oracle이 받는 값은 set으로 값 설정, 
		  							out되어 java가 받는 값들은 register로 type mapping)  
		   	2. executeUpdate -> oracle에게 값을 주고받음 
		   	3. get -> 주고받고 out되어 java가 받을 값들을 가져옴 
		  																	 		*/
			System.out.println(result);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			OracleConnection.closeConnection(conn, cstmt, rs);
		}
	}
	public static void main(String[] args) {
		new CallableTest();
	}
} // class
