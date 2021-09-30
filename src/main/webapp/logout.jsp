<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>logout.jsp</title>
</head>
<body>
	<%
		String name = (String)session.getAttribute("name");	
	%>
	<h1><%= name %>님 로그아웃 되셨습니다.</h1>
	
	<%
		session.invalidate();//모든 세션이 삭제	
	%>
	<a href ="login.jsp">회원 로그인</a>
</body>
</html>