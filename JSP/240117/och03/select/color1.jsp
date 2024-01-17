<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	// setAttribute로 값을 넘겨주기 때문에 getAttribute로 받아야함
	String color = request.getAttribute("color").toString();
%>
</head>

<body bgcolor= <%=color %>>
	

</body>
</html>