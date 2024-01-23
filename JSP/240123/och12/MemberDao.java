package och12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// singleton + DBCP -> memory 절감 + DOS공격 예방
public class MemberDao {
	private static MemberDao instance;
	Connection conn = null;
	
	private MemberDao() {
		
	}
	
	public static MemberDao getInstance() {
		if(instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}
	
	private Connection getConnection() {
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return conn;
	}
	
	// loginPro.jsp
	public int check (String id, String passwd) throws SQLException {
		
		getConnection();

		String sql = "select passwd from member2 where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,id);
		ResultSet rs = pstmt.executeQuery();
		int result = -1;
		
		if(rs.next()) {
			result++;
			if(rs.getString(1).equals(passwd)){
				result++;
			}
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}

	// joinPro.jsp
	public int insert (Member2 member2) {
		return 1;
	}
}
