package callable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import jdbctest.OracleConnection;

// 함수 불러오기
public class CallableTest2 {
	
	Connection conn;
	CallableStatement cstmt;
	ResultSet rs;
	String sql;
	int result;
	
	CallableTest2() {
		
		try {
			conn = OracleConnection.getConnection();
			// 함수는 return 타입을 지정해줘야 하기 때문에 ?=call..형식으로 불러옴 
			sql = " { ?=call FUNC_CALL(?,?)} ";
			cstmt = conn.prepareCall(sql);
			// return 타입인 number을 mapping해줌
			//	-> return만 out되어 java에서 받는 값이기 때문에 type mapping이 필요
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setInt(2, 10);
			cstmt.setInt(3, 20);
			// set, register을 다 해준 후에 db에 값을 주고받기
			cstmt.executeUpdate();
			System.out.println(cstmt.getInt(1));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeConnection(conn, cstmt, rs);
		}
	}
	
	public static void main(String[] args) {
		new CallableTest2();
	}

}
