<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.gyojinjava.ex.*" %>
<% request.setCharacterEncoding("EUC-KR"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>modify.jsp</title>
<script language="JavaScript" src="members.js" charset="UTF-8"></script>
</head>
<body>
	<%
		String id = (String)session.getAttribute("id");
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.getMember(id);	
	%>
	<h1>회원 정보 수정 후 수정 버튼을 눌러주세요</h1>
	<form action="modifyOk.jsp" method="post" name="reg_frm">
		아이디 : <%= dto.getId() %></br>		
		비밀번호 : <input type="password" name="pw" size="20"></br>
		비밀번호 확인: <input type="password" name="pw_check" size="20"></br>
		이름: <%= dto.getName() %></br>
		이메일: <input type="text" name="email" size="30" value="<%= dto.getEmail() %>"></br>
		주소: <input type="text" name="address" size="60" value="<%= dto.getAddress() %>"></br>
		<input type="button" value="수정" onclick="updateInfoConfirm()">
		<input type="reset" value="취소" onclick="javascript:window.location='login.jsp'">
	
	</form>
</body>
</html>