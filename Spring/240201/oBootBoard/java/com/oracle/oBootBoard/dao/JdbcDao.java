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
		String sql = " insert into mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, bDate)"
				+ "	 values (mvc_board_seq.nextval,?,?,?,0,mvc_board_seq.currval,0,0,sysdate) ";
		
		try {
			conn = getConnection();
			System.out.println("BDao write query-->" + sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("write dataSource-->" + e.getMessage());
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
		upHit(bId);
		BDto bDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from mvc_board where bId=?";
		
		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bDto = new BDto(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getTimestamp(5), rs.getInt(6), 
						rs.getInt(7), rs.getInt(8), rs.getInt(9));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} 
		}
		System.out.println("JdbcDao bDto.getbName->" +bDto.getbName());
		return bDto;
	}

	@Override
	public void modify(int bId, String bName, String bTitle, String bContent) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		String sql = "update mvc_board set bName=?, bTitle=?, bContent=? where bId=?";
		
		try {
			conn = getConnection();
			System.out.println("BDao modify query->" +sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, bId);
			
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
	
	private void upHit(int bId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;		
		String sql = "update mvc_board set bHit=bHit+1 where bId=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
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
	public BDto reply_view(int bId) {
		BDto bDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from mvc_board where bId=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) { 
				bDto = new BDto(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getTimestamp(5), rs.getInt(6), 
						rs.getInt(7), rs.getInt(8), rs.getInt(9)); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		return bDto;
	}

	@Override
	public void reply(int bId, String bName, String bTitle, String bContent, int bGroup, int bStep, int bIndent) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		int result = 0;
		String sql = " insert into mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent)"
				+ "	 values (mvc_board_seq.nextval,?,?,?,0,?,?,?) ";
		replyShape(bGroup, bStep);
		System.out.println(bGroup);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, bGroup);
			pstmt.setInt(5, bStep+1);
			pstmt.setInt(6, bIndent+1);
			
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
	
	private void replyShape(int bGroup, int bStep) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "update mvc_board set bStep = bStep+1 "
					+ "where bGroup=? and bStep>?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bGroup);
			pstmt.setInt(2, bStep);
			
			int result = pstmt.executeUpdate();
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
	public void delete(int bId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete mvc_board where bId=? ";
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			
			pstmt.executeUpdate();
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
	
	
	
}
