<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%
	if(session.getAttribute("ValidMem") == null ) {
		%>
		<jsp:forward page="login.jsp" />
		<%
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>main.jsp</title>
</head>
<body>
	<%
		String name = (String)session.getAttribute("name");
		String id = (String)session.getAttribute("id");	
	%>
	
	<h1><%= name %>님 로그인하셨습니다. <%= id %>회원님 반갑습니다.</h1>
	<form action="logout.jsp" method="post">
		<input type="submit" value="로그아웃">
		<input type="button" value="정보수정" onclick="javascript:window.location='modify.jsp'">
	
	</form>
</body>
</html>