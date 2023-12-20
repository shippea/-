package callable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import jdbctest.OracleConnection;

// # 실습1. 부서아이디를 입력하면 부서내 직원 수를 반환하는 function을 생성
// # 실습2. 오라클에서 생선한 function을 호출해서 결과를 반환하는 java 코드작성
public class CallableTest3 {
	
	Connection conn;
	CallableStatement cstmt;
	ResultSet rs;
	String sql;
	int result;
	
	CallableTest3(){
		try {
			conn = OracleConnection.getConnection();
			sql = " { ?=call FUNC_EMP_CNT(?) } ";
			cstmt = conn.prepareCall(sql);
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setInt(2, 90);
			cstmt.executeUpdate();
			System.out.println(cstmt.getInt(1));
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			OracleConnection.closeConnection(conn, cstmt, rs);
		}
	}
	
	public static void main(String[] args) {
		new CallableTest3();
	} // main
} // class
