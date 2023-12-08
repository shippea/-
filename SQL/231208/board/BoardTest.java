package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbctest.OracleConnection;

// insert, update, delete 사용해보기
public class BoardTest {
	
	public static void main(String[] args) {
		ResultSet rs = null;
		Connection conn = null;
		Statement stmt = null;
		
		try {
		// OracleConnection class에서 connection 해둔 것으 불러들임
		conn = OracleConnection.getConnection();
		stmt = conn.createStatement();
		int result = 0;

		// 1.insert
		
		String insertSql
			= " insert into board values (seq_board.nextval,'홍길동','제목1','내용1') ";
		result = stmt.executeUpdate(insertSql);
		if (result>0) System.out.println("입력 성공!");
		
		
		// 2.update
		
		String updateSql
			= " update board set bcontent='수정된 내용1' where bid=1 ";
		result = stmt.executeUpdate(updateSql);
		if (result>0) System.out.println("수정 성공!");
		
		// 3.delete
		
		String deleteSql
			= " delete board where bid=4 ";
		result = stmt.executeUpdate(deleteSql);
		if (result>0) System.out.println("삭제 성공!");
		
		
		// 실습: 데이터 3건 입력하고 리스트를 출력해보자!

		
		for (int i=1; i<=3; i++) {
			insertSql = " insert into board values (seq_board.nextval,'학생"+i+"','제목"+i+"','내용"+i+"') ";
			result = stmt.executeUpdate(insertSql);
			if (result>0) System.out.println("입력성공! - " +i);
		}
		
		
		for (int i=1; i<=3; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append(" insert into board values (seq_board.nextval");
			sb.append(",'학생"+i+"'");
			sb.append(",'제목"+i+"'");
			sb.append(",'내용"+i+"') ");
			insertSql = sb.toString();
			result = stmt.executeUpdate(insertSql);
		}
		
		String listSql = (" select bid, bwriter, btitle, bcontent from board ");
		rs = stmt.executeQuery(listSql);
		
		while(rs.next()) {
			System.out.println(
					rs.getInt("bid")+","+
					rs.getString("bwriter")+","+
					rs.getString("btitle")+","+
					rs.getString("bcontent")
			);
			
		}
		
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			OracleConnection.closeConnection(conn, stmt, rs);
		}
		
	}

}
