<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "com.gyojinjava.ex.*" %>
<% request.setCharacterEncoding("EUC-KR"); %>
<jsp:useBean id="dto" class="com.gyojinjava.ex.MemberDTO"/>
<jsp:setProperty name="dto" property="*" />


    
  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>joinOk.jsp</title>
</head>
<body>
	<%
	//String idr = dto.getId();
	//out.println(idr);
	MemberDAO dao = new MemberDAO();
	int ri = dao.insertMember(dto);//ri값이 반환됨 성공1 실패 0
	
	if(ri == 1) {
		out.println("회원가입을 축하드립니다");
	}
	
	%>
</body>
</html>