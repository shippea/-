package callable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import jdbctest.OracleConnection;
import oracle.jdbc.OracleTypes;

// 커서를 사용하는 프로시져를 불러오기
public class CallableTest4 {

	Connection conn;
	CallableStatement cstmt;
	// cursor는 여러행의 결과값을 받아오기 때문에 rs가 필요
	ResultSet rs;
	String sql;
	
	CallableTest4(){
		try {
			conn = OracleConnection.getConnection();
			sql = " { call PROC_CALL_CUR(?,?) } ";
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, 50);
			// 2번째 파라미터는 cursor type의 out 파라미터
			// oracle.jdbc.OracleTypes.CURSOR 타입을 사용
			//	(다른 db cursor 사용 시 다른 dbtype의 cursor와 mapping 해야됨)
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			// 결과 set을 받아오려면 execute를 사용
			cstmt.execute();
			// cursor을 java에서 값으로 받기 위해서는 object로 받고 rs로 변환하는 방법밖에 없음
			// (반환이 object이므로 resultset으로 명시적 형변환)
			rs = (ResultSet) cstmt.getObject(2);
			
			while (rs.next()) {
				System.out.println(
						rs.getInt("employee_id") + " " +
						rs.getString("first_name") + " " +
						rs.getString("last_name")
				);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			OracleConnection.closeConnection(conn, cstmt, rs);
		}
	} 
	
	public static void main(String[] args) {
		new CallableTest4();
	} // main
} // class
