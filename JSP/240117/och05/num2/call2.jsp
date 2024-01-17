<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>연산결과 오류시 error Page 이동</h2>
<%
	try {
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));

		out.println(num1 + " + " + num2 + " = " + (num1+num2) + "<p>");
		out.println(num1 + " - " + num2 + " = " + (num1-num2) + "<p>");
		out.println(num1 + " * " + num2 + " = " + (num1*num2) + "<p>");
		out.println(num1 + " / " + num2 + " = " + (num1/num2) + "<p>");
	} catch (Exception e) { 
		// page 이동 (url이 바뀌진 않음)
		RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
		rd.forward(request,response);
	}
%>

</body>
</html>