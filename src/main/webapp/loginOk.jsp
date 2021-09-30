<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "com.gyojinjava.ex.*" %>
<% request.setCharacterEncoding("EUC-KR"); %>




<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>loginOk.jsp</title>
</head>
<body>
	<%
		request.setCharacterEncoding("EUC-KR");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
	
		MemberDAO dao = new MemberDAO();
		int checkNum = dao.userCheck(id, pw);
		
		if (checkNum == -1) { // 아이디 없음
	%>			
			<script language="javascript">
				alert("입력하신 ID는 존재하지 않는 ID입니다.");
				history.back();
			</script>
	<%	
			
		} else if(checkNum == 0) { // 비번 틀림
	%>			
			<script language="javascript">
				alert("비밀번호가 틀립니다. 다시 확인 후 입력해주세요.");
				history.back();
			</script>
	<%	
		} else if(checkNum == 1 ) { //로그인 성공
			String name = dao.getMemberName(id);
			session.setAttribute("name", name);
			//out.println(name);
			response.sendRedirect("main.jsp");
		}
	
	%>
</body>
</html>