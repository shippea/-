<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../dbError.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String empno = request.getParameter("empno");
	String sql = "select empno, ename, sal, hiredate from emp where empno=" + empno;
	
	// HW2
	// 1. DBCP 연동
	// 2. Emp DTO setter
	// 3. oraResult.jsp 이동

%>

</body>
</html>