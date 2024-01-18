<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>1부터 100까지 합  스클릿틀잇+Expression</h2>
<%
	int sum = Integer.parseInt(request.getAttribute("sum").toString());
	
%>
<%=sum %> <p>
</body>
</html>