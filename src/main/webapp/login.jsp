<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>login.jsp</title>
</head>
<body>
	<form action="loginOk.jsp" method="post">
		아이디:<input type="text" name="id" value=""><br/>
		비밀번호 : <input type="password" name="pw"><br/>
		<input type="submit" value="로그인"><br/>
		아직 가입하지 않으신 분은 아래 회원가입 버튼을 눌러 회원가입을 해주세요.<br/>
		<input type="button" value="회원가입" onclick="javascript:window.location='join.jsp'">
	
	
	</form>
</body>
</html>