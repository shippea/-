package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbctest.OracleConnection;

// board 생성자로 board table의 value값 설정해보기
public class BoardTest4 {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = OracleConnection.getConnection();
			
			// 실습) BoardTest3의 코드를 Board를 사용하는 코드로 변환
			
			Board board = new Board(0, "board유관순", "board제목을넣겠다", "board내용을넣겠다");
		
			sql = " insert into board values (seq_board.nextval,?,?,?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBwriter());
			pstmt.setString(2, board.getBtitle());
			pstmt.setString(3, board.getBcontent());
			
			result = pstmt.executeUpdate();
			if (result>0) System.out.println("입력성공!");
			
			Board board2 = new Board(0, "UUU유관순", "UUU제목을넣겠다", "UUU내용을넣겠다");
			// 2.update(실습)
			sql = " update board set bwriter=?, btitle=?, bcontent=? where bid in(23,24,25,26,27) ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board2.getBwriter());
			pstmt.setString(2, board2.getBtitle());
			pstmt.setString(3, board2.getBcontent());
			result = pstmt.executeUpdate();
			if (result>0) System.out.println("수정성공");
			
			// 3.list
			sql = " select bid, bwriter, btitle, bcontent from board ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			List<Board> boardList = new ArrayList<Board>();
			
			while(rs.next()) {
				Board board3 = new Board(rs.getInt("bid"),rs.getString("bwriter"),rs.getString("btitle"),rs.getString("bcontent"));
				boardList.add(board3);
			}
			
			// while문에서 add로 받아온 list 출력시 lamda식 사용
			boardList.forEach(boardLamda ->{
				System.out.println(boardLamda);
			});
			
			// 4.select (한건만 조회)
			
			sql = " select * from board where bid=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 29);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board(rs.getInt("bid"),rs.getString("bwriter"),rs.getString("btitle"),rs.getString("bcontent"));
				System.out.println(board);
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			OracleConnection.closeConnection(conn, pstmt, rs);
		}
	}

}
