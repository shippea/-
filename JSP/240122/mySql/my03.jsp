<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
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
	String dno = request.getParameter("dno");
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/scottdb?serverTimezone=UTC";
	String sql = "select * from division wher dno=" +dno;
	Class.forName(driver);
	
	Connection conn = DriverManager.getConnection(url, "root", "mysql");
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	
	if (rs.next()) {
		String dname = rs.getString("dname");
		String phone = rs.getString(3);
		String position = rs.getString(4);
		request.setAttribute("dno", dno);
		request.setAttribute("dname", dname);
		request.setAttribute("phone", phone);
		request.setAttribute("position", position);
	} else out.println("부서없음");

	rs.close();
	stmt.close();
	conn.close();
	
	RequestDispatcher rd = request.getRequestDispatcher("my03Result.jsp");
	rd.forward(request, response);
%>

</body>
</html>