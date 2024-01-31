package com.oracle.oBootBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import com.oracle.oBootBoard.dto.BDto;

public class JdbcDao implements BDao {
	
	// JDBC 사용
	private final DataSource dataSource;
	
	public JdbcDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private Connection getConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}

	@Override
	public ArrayList<BDto> boardList(){
		ArrayList<BDto> bList = new ArrayList<BDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("jdbcDao boardList strat...");
		String sql = "select * from mvc_board order by bGroup desc, bStep asc";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			System.out.println("BDao query-->"+sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bList.add(new BDto(rs.getInt(1), rs.getString(2), rs.getString(3),
									rs.getString(4), rs.getTimestamp(5), rs.getInt(6), 
									rs.getInt(7), rs.getInt(8), rs.getInt(9)));
			}
			
		} catch (SQLException e) {
			System.out.println("list dataSource -->"+e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
 			} catch (SQLException sqle) {
 				sqle.printStackTrace();
 			}
		}
		
		return bList;
	}

	@Override
	public void write(String bName, String bTitle, String bContent) {
		// 1. Insert into mvc_board
		// 2. preparedStatemt 방식
		// 3. mvc_board_seq
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = " insert into mvc_board values (mvc_board_seq.nextval,?,?,?) ";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
	}

	@Override
	public BDto contentView(int bId) {
		return null;
	}
	

}
