package board;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import jdbctest.OracleConnection;

// insert, update, delete, list, select를 메소드에 넣어 사용해보기
public class BoardTest5 {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private String sql; private int result; private Board board;
	private ResultSet rs;
	private Properties prop;
	
	// insert, update, delete, list, select 메소드를 prop에 넣어 불러들임
	public BoardTest5() {
		conn = OracleConnection.getConnection();
		prop = new Properties();
		try {
			prop.load(new FileReader(new File(
					"D:/eclipse_workspace/JDBCTest/src/prop/sql.properties"))
			);
			
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	public static void main(String[] args) {
		BoardTest5 boardTest5 = new BoardTest5();
		
		try {
			// boardTest5에서 선언한 변수 받아오기 위해 객체 생성
			List<Board> boardList = boardTest5.listBoard(); // list
			
			int insertResult = boardTest5.insertBoard(		// insert
					new Board(0,"작성자","제목","내용")
					);
			
			int updateResult = boardTest5.updateBoard(		// update
					new Board(42, "1111","수정제목","수정내용")
					);
			
			int deleteResult = boardTest5.deleteBoard(42);	// delete
			
			
			Board board = boardTest5.selectBoard(11); 		// select
			System.out.println(board);
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			OracleConnection.closeConnection(boardTest5.conn, boardTest5.pstmt, boardTest5.rs);
		}
		
	} // main
	
	// 1. list 
	List<Board> listBoard () throws SQLException {
		sql = prop.getProperty("LIST_SQL");
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		List<Board> boardList = new ArrayList<Board>();
		
		while(rs.next()) {
			board = new Board(
					rs.getInt("bid"),rs.getString("bwriter"),rs.getString("btitle"),rs.getString("bcontent"));
			boardList.add(board);
		}
		
		return boardList;
	}
	
	// 2. select
	Board selectBoard (int bid) throws SQLException {

		sql = prop.getProperty("SELECT_SQL");
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bid);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			board = new Board(
					rs.getInt("bid"),rs.getString("bwriter"),rs.getString("btitle"),rs.getString("bcontent"));
		}
		
		return board;
	}
	
	// 3. insert
	int insertBoard (Board board) throws SQLException {
		
		sql = prop.getProperty("INSERT_SQL");
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, board.getBwriter());
		pstmt.setString(2, board.getBtitle());
		pstmt.setString(3, board.getBcontent());
		result = pstmt.executeUpdate();
		
		return result;
	}
	
	// 4. update
	int updateBoard (Board board) throws SQLException {
		sql = prop.getProperty("UPDATE_SQL");
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, board.getBwriter());
		pstmt.setString(2, board.getBtitle());
		pstmt.setString(3, board.getBcontent());
		pstmt.setInt(4, board.getBid());
		
		result = pstmt.executeUpdate();
		
		return result;
	}
	
	// 5. delete
	int deleteBoard (int bid) throws SQLException {
		sql = prop.getProperty("DELETE_SQL");
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,bid);
		result = pstmt.executeUpdate();
		return result;
	}
} // class
