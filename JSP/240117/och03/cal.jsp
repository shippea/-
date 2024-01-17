<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 지시자 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	int num1 = Integer.parseInt(request.getParameter("num1"));
	int num2 = Integer.parseInt(request.getParameter("num2"));
	out.println("<h1>사칙연산</h1>");
%>

<h1> 사칙연산 </h1>
덧셈 : <%= num1+num2 %> <p>
뺄셈 : <%= num1-num2 %> <p>
곱셈 : <%= num1*num2 %> <p>
나눗셈 : <%= num1/num2 %> <p>

</body>
</html>