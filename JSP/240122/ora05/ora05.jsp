<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="och10.Emp"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String sql = "select empno, ename, job, sal from emp";
	Class.forName(driver);
	Connection conn = DriverManager.getConnection(url, "scott", "tiger");
	ArrayList<Emp> al = new ArrayList<Emp>();
	
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	
/* 	7369	SMITH	CLERK	800
	7499	ALLEN	SALESMAN	1600
	7521	WARD	SALESMAN	1250
	7566	JONES	MANAGER	2975
	7654	MARTIN	SALESMAN	1250
	7698	BLAKE	MANAGER	2850
	7782	CLARK	MANAGER	2450
	7788	SCOTT	ANALYST	3000
	7839	KING	PRESIDENT	5000
	7844	TURNER	SALESMAN	1500
	7876	ADAMS	CLERK	1100
	7900	JAMES	CLERK	950
	7902	FORD	ANALYST	3000
	7934	MILLER	CLERK	1300 */
	
	
	if (rs.next()) { 
		do {
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob(rs.getString(3));
			emp.setSal(rs.getInt(4));
			al.add(emp);
		} while(rs.next());
		request.setAttribute("al", al);
		rs.close();
		stmt.close();
		conn.close();
		/* RequestDispatcher rd = request.getRequestDispatcher("ora05Result.jsp"); */
		RequestDispatcher rd = request.getRequestDispatcher("ora05Result2.jsp");
		rd.forward(request, response);
	} else {
		out.println("데이터가 없습니다");
		stmt.close();
		conn.close();
	}
	
	
%>

</body>
</html>