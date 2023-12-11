package board;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import jdbctest.OracleConnection;

public class BoardTest6 {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	
	public BoardTest6() {
		
	try {
			
			conn = OracleConnection.getConnection();
			String sql =  " select * from board ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			// resultset에 받은 query정보를 불러들임
			rsmd = rs.getMetaData();
			
			System.out.println(rsmd.getColumnCount());
			System.out.println(rsmd.getColumnLabel(2));
			System.out.println(rsmd.getColumnType(2));
			System.out.println(rsmd.getColumnTypeName(2));
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			OracleConnection.closeConnection(conn, stmt, rs);
		}

	}
	
	public static void main(String[] args) {
		
		new BoardTest6();
		
	
	}

}
