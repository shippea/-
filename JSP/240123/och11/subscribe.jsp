<%@page import="och11.MemberDao"%>
<%@page import="javax.naming.Context"%>
<%@page import="och11.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="dbError.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String chk = "";
	String agree = request.getParameter("agree");
	if (agree.equals("Y")) {
		String id = (String)session.getAttribute("id");
		String password = (String)session.getAttribute("password");
		String name = (String)session.getAttribute("name");
		System.out.println("Subscribe id->" + id);
		System.out.println("Subscribe password->" + password);
		System.out.println("Subscribe name->" + name);
		
		// 1. Member1 TBL --> DTO Setting	
		MemberDto member = new MemberDto();
		member.setId(id);
		member.setName(name);
		member.setPassword(password);
		
		// 2. MemberDao Insert
		MemberDao md = new MemberDao();
		int result = md.insert(member);
		
		if(result>0) chk="success";
		else chk = "fail";
		
	} else chk = "fail";
	
	
	
	session.invalidate();
	out.print("invalidate() 적용 후에도 " + session.getId() + "<br>");
	response.sendRedirect("result.jsp?chk=" + chk);

%>

</body>
</html>