/*
 * Transaction: 작업단위(묶음), transaction은 commit 전까지가 하나의 transaction
 * 				-> insert, select, update... 등을 수행한 한 묶음 (commit 전까지)
 * Commit: 데이터베이스에 Transaction작업을 반영
 * 		1. DDL: 수행 즉시 commit됨 (RollBack 불가) 예) create, alter, drop, truncate...
 * 		2. DML commit전까지는 transaction이 완료되지 x (데이터베이스에 반영되지 않음) 
 * 											   예) select, insert, update, delete, merge...
 * RollBack: 현재 Transation을 초기화 (데이터베이스에 작업을 반영하지 않음)
 * Auto Commit: true로 설정시 SQL구문을 수행할 때마다 즉시 commit (DML이라도 바로 commit됨)
 * 
 * 데이터베이스 transation 처리 순서
 * 	1) conn.setAutoCommit(false);  - auto commit을 방지
 * 	2) DML 구문들을 작성/수행 (필요하면 SavaPoint 지정 -> 해당 지점까지만 수행하도록)
 *  3) 문제가 없으면 Commit, 문제가 있으면 RollBack 
 */

package board;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import jdbctest.OracleConnection;

public class BoardTest7 {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private Savepoint sp1;
	private Savepoint sp2;
	
	public BoardTest7(){
		BoardTest5 bt5 = new BoardTest5();
		
		// BoardTest5의 connection을 연결해줌
		conn = bt5.getConnection();

		// transaction 처리 순서 연습하기
		try {
			// autocommit 여부 화인
			System.out.println(conn.getAutoCommit());
			
			// 1. transaction 처리를 위해 autocommit을 false로 설정
			conn.setAutoCommit(false);
			
			
			// 2. 필요한 sql구문들을 수행
			Board board = new Board(0,"작성자","제목","내용");
			bt5.insertBoard(board);
			
			sp1 = conn.setSavepoint("sp1");
			
			Board board2 = new Board(0,"","수정제목","수정내용");
			bt5.updateBoard(board2);
			
			sp2 = conn.setSavepoint("sp2");
			
			bt5.deleteBoard(58);

			// 3. commit
			conn.commit();
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			try {
				// 4. 작업 중 예외가 발생하면 rollback
				conn.rollback();
			} catch (SQLException sqle2) {
				sqle2.printStackTrace();
			}
		} finally {
			try {
				// 5. setAutoCommit을 true로 설정
				conn.setAutoCommit(true);
				
				// java.sql.SQLRecoverableException: 접속 종료
				// -> close를 마지막에 선언하지 않으면 나타나는 에러
				OracleConnection.closeConnection(conn, stmt, rs);
				
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new BoardTest7();
	} // main

} // class
