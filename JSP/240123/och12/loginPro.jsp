<%@page import="org.apache.catalina.ant.DeployTask"%>
<%@page import="och12.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- service -->
<%
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	
	MemberDao md = MemberDao.getInstance();
	
	
	int result = md.check(id, passwd);
	if (result == 1) {
		session.setAttribute("id", id);
		response.sendRedirect("main.jsp");
	} else if (result == 0) {
%>	
	<script type="text/javascript">
		alert("암호 몰라");
		history.go(-1);
	</script>
	
<% } else { %>

	<script type="text/javascript">
		alert("User 미존재");
		histroy.go(-1);
	</script>
	
<% } %>

</body>
</html>