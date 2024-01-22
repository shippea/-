<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
  // Home Work 1
  // 조건 1 : deptno가지고 dept Table 조회 -> createStatement
  String deptno = request.getParameter("deptno");
  String driver = "oracle.jdbc.driver.OracleDriver";
  String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
  String sql = "select * from dept where deptno="+deptno;
  Class.forName(driver);
  Connection conn = DriverManager.getConnection(url, "scott", "tiger");
  Statement stmt = conn.createStatement();
  ResultSet rs = stmt.executeQuery(sql);
  if (rs.next()) {
	  // column 명을 주거나 순서를 줘도 됨
	  String dname = rs.getString("dname");
	  String loc = rs.getString(3);
	  out.println("부서코드 : " + deptno + "<p>");
	  out.println("부서명 : " + dname + "<p>");
	  out.println("근무지 : " + loc + "<p>");
  } else out.println("그런 부서 없어요");
  
  rs.close();
  stmt.close();
  conn.close();

%>
</body>
</html>