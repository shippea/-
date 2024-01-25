package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDao {
	private static BoardDao instance;
	
	private BoardDao() {}
	
	public static BoardDao getInstance() {
		if (instance == null) {	
			instance = new BoardDao();		
		}
		return instance;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)
				ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		}catch(Exception e) { 
			System.out.println(e.getMessage());	
		}
		return conn;
	}

	public int getTotalCnt() throws SQLException  {
		Connection conn = null;	
		Statement stmt= null; 
		ResultSet rs = null;    
		int tot = 0;
		String sql = "select count(*) from board";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) tot = rs.getInt(1);
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (stmt != null) stmt.close();
			if (conn !=null) conn.close();
		}
		return tot;
		
	}
	
	public List<Board> boardList (int startRow, int endRow) throws SQLException {
		
		List<Board> list = new ArrayList<Board>();
		
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select *\r\n"
				+ "from (select rownum rn, a.*\r\n"
				+ "      from (select * \r\n"
				+ "            from board \r\n"
				+ "            order by ref desc, re_step) a)\r\n"
				+ "where rn between ? and ?";
		
//		String sql = "select * from (select rownum rn, a.* from (select * from board order by ref desc, re_step) a) where between ? and ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Board board = new Board();
				board.setNum(rs.getInt(2));
				board.setWriter(rs.getString(3));
				board.setSubject(rs.getString(4));
				board.setContent(rs.getString(5));
				board.setEmail(rs.getString(6));
				board.setReadcount(rs.getInt(7));
				board.setPasswd(rs.getString(8));
				board.setRef(rs.getInt(9));
				board.setRe_step(rs.getInt(10));
				board.setRe_level(rs.getInt(11));
				board.setIp(rs.getString(12));
				board.setReg_date(rs.getDate(13));
				list.add(board);
				
			}		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return list;
	}
	
	public Board select (int num) throws SQLException {
		Board board = new Board();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from board where num=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				board.setNum(rs.getInt(1));
				board.setSubject(rs.getString(3));
				board.setWriter(rs.getString(2));
				board.setReg_date(rs.getDate(12));
				board.setReadcount(rs.getInt(6));
				board.setIp(rs.getString(11));
				board.setEmail(rs.getString(5));
				board.setContent(rs.getString(4));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return board;
		
	}
	
	public int readCount (int num) throws SQLException { 
		Board board = new Board();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "update board set readcount=? where num=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(2, num);
			rs = pstmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return board.getReadcount();
	}
}
