package och11;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.mysql.cj.protocol.Resultset;

//DB CRUD 관련된 DML 처리 Logic
public class MemberDao {
	//1. 선언 (Member 변수)
	Connection conn = null;
	
	private Connection getUserConn() throws SQLException {
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
			
			// 2. 선언된 것을 가지고 연결
			conn = ds.getConnection();
		} catch (NamingException ne) {
			ne.printStackTrace();
			System.out.println(ne.getMessage());
		}
		return conn;
	}
	
	// insert Method 생성
	// getConnection 사용
	// parameter -> MemberDto
	// return -> int
	// PreparedStatement
	
	public int insert (MemberDto member) throws SQLException {
		getUserConn();
		String sql = "insert into member1 values (%s, %s, %s)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, sql);
		pstmt.setString(2, sql);
		pstmt.setString(3, sql);
		
		return 0;
		
	}

}
