<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String[] col={"red","orange","yellow","green","blue","navy","violet","black"};
	// n은 8미만 숫자
	int n = (int)(Math.random() * 8);
	// color1.jsp    gugu1.jsp    response.jsp
	String pgm = request.getParameter("pgm") + ".jsp";
	
	// RequestDispatcher = form 없이 이동 page를 지정하는 객체 선언
	// rd로 page 이동 시 url이 바뀌진 않음
	RequestDispatcher rd = request.getRequestDispatcher(pgm);
	
	if(pgm.equals("color1.jsp")) {
		// color.jsp 파일의 color 변수에 col[n] 값을 던져줌
		request.setAttribute("color", col[n]);
		// rd = request.getRequestDispatcher("color1.jsp");
	} else if (pgm.equals("gugu1.jsp")) {
		// 2단 ~ 9단
		// gugu1.jsp 파일의 num 변수에 n+2 값을 던져줌
		request.setAttribute("num", n+2);
		// rd = request.getRequestDispatcher("gugu1.jsp");
	} else if (pgm.equals("response.jsp")) {
	}
	
	// 이동할 page에 이동하도록 명령하는 메소드
	rd.forward(request, response);

%>
</body>
</html>