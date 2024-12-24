<%@ page  contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%
	String cp = request.getContextPath();
	System.out.println("request.getRemoteAddr()==>" + request.getRemoteAddr());
	System.out.println("request.getRequestURI()==>" + request.getRequestURI());
	StringBuffer sbDomain = new StringBuffer();
	sbDomain.append(request.getScheme()).append("://").append(request.getServerName()).append(":").append(request.getServerPort()).append(request.getContextPath());
	System.out.println("-->" + sbDomain.toString());
	//response.sendRedirect(cp + "/main.do");
%>


	
</head>
<body>

<h3>
<a href="<%=cp %>/list.action">게시판</a>
<a href="<%=cp %>/board.action">포털</a>
<a href="<%=cp %>/main.do">메인</a>
<a href="<%=cp %>/test.do">test</a>
<a href="<%=cp %>/form.do">form</a>
</h3>

</body>
</html>