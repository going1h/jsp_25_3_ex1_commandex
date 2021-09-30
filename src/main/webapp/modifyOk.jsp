<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.gyojinjava.ex.*" %>
<% request.setCharacterEncoding("EUC-KR"); %>

<jsp:useBean id="dto" class="com.gyojinjava.ex.MemberDTO" scope="page"/>
<jsp:setProperty name="dto" property="*"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>modifyOk.jsp</title>
</head>
<body>
	<%
		String id = (String)session.getAttribute("id");
		dto.setId(id);
	
		MemberDAO dao = new MemberDAO();
		int ri = dao.updateMember(dto);
		
		if(ri==1){
	%>
			<script language="javascript">
				alert("회원정보수정이 완료되었습니다.");
				document.location.href="main.jsp"
			</script>
	<%	
		} else {
	%>
			<script language="javascript">
				alert("회원정보수정이 실패하였습니다.");
				history.go(-1);
			</script>
	<%	
		}
	%>
	
</body>
</html>