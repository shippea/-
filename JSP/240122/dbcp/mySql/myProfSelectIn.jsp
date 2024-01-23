<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="och10.Professor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.oracle.wls.shaded.org.apache.bcel.generic.ARRAYLENGTH"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	ArrayList<Professor> al = new ArrayList<Professor>();
	Context ctx = new InitialContext();
	DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MySql");
	String sql = "select profno, name from professor";
	
	Connection conn = ds.getConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	
	while (rs.next()) {
		Professor prof = new Professor();
		prof.setProfno(rs.getInt(1));
		prof.setName(rs.getString(2));
		al.add(prof);
	}
	
	request.setAttribute("al", al);
%>
<h2>보고싶은 교수 번호를 선택하세요</h2>
<form action="myProfSelect.jsp">
	<select name="pno">
		<c:forEach var="professor" items="${al }">
			<option value="${professor.profno }"> ${professor.profno } ${professor.name }</option>
		</c:forEach>
	</select>
	<input type="submit" vlaue="선택완료">
</form>

</body>
</html>