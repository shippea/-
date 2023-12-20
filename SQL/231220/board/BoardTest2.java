package board;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbctest.OracleConnection;

// board bean과 list를 이용하여 insert, select 해보기 
public class BoardTest2 {
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.createStatement();
			int result = 0;
			
			// 1.insert
			// board class 객체생성이 table 행1개 추가랑 같음
			Board board = new Board(0, "강감찬", "제목100", "내용100");
			String insertSql = " insert into board values (seq_board.nextval,'";
			insertSql+=board.getBwriter()+ "','";
			insertSql+=board.getBtitle()+ "','";
			insertSql+=board.getBcontent()+ "')";
			result = stmt.executeUpdate(insertSql);
			if (result>0) System.out.println("입력성공!");
			
			// 2.select
			List<Board> boardList = new ArrayList<Board>();
			String selectSql = " select bid,bwriter,btitle,bcontent from board ";
			rs = stmt.executeQuery(selectSql);
			
			while(rs.next()) {
				Board board2 = new Board();
				board2.setBid(rs.getInt("bid"));
				board2.setBwriter(rs.getString("bwriter")+"**");
				board2.setBtitle(rs.getString("btitle")+"**");
				board2.setBcontent(rs.getString("bcontent")+"**");
				boardList.add(board2);
			}
			
			if(boardList!=null) {
				for(Board board3: boardList) {
					System.out.println(
							board3.toString());
				}
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			OracleConnection.closeConnection(conn, stmt, rs);
		}
		
		
	} // main

} // class
