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
<!-- Member2 member = new Member2()와 동일 -->
<jsp:useBean id="member2" class="och12.Member2"></jsp:useBean>
<!-- parameter 이름이 같을 때 *로 한번에 받을 수 있음 -->
<jsp:setProperty property="*" name="member2"/>
<%
	MemberDao md = MemberDao.getInstance();
	int result = md.insert(member2);
	if (result > 0) {
%>
	<script type="text/javascript">
		alert("회원가입 성공");
		location.href="loginForm.jsp";
	</script>
<% } else { %>
	<script type="text/javascript">
		alert("회원가입 실패");
		location.href="joingForm.jsp";
	</script>
<% } %>

</body>
</html>