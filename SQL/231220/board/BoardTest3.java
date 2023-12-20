package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbctest.OracleConnection;

// preparedstatement의 set메소드로 insert update 해보기
public class BoardTest3 {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		
		try {
			conn = OracleConnection.getConnection();
			
			// 1.insert
			
			sql = " insert into board values (seq_board.nextval,?,?,?) ";
			// prepareStatement는 sql을 넣은 후에 선언
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "유관순");
			pstmt.setString(2, "제목을넣겠다");
			pstmt.setString(3, "내용을넣겠다");
			result = pstmt.executeUpdate();
			if (result>0) System.out.println("입력성공!");
			
			// 2.update(실습)
			sql = " update board set bwriter=?, btitle=?, bcontent=? where bid in(19,20,21,22) ";
//			sql += "where bid=?";
			pstmt = conn.prepareStatement(sql);
			// where bid=?으로 따로 set해줄수도 있음
//			pstmt.setInt(4, 9);
			
			pstmt.setString(1, "수정된이름입니다");
			pstmt.setString(2, "수정된제목입니다");
			pstmt.setString(3, "수정된내용입니다");
			result = pstmt.executeUpdate();
			if (result>0) System.out.println("수정성공");
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			OracleConnection.closeConnection(conn, pstmt, null);
		}
		
		
		
	} 

}
