<%@page import="java.sql.Timestamp"%>
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
	//String id = dto.getId();
	//out.println(idr);
	
	dto.setRdate(new Timestamp(System.currentTimeMillis())) ;//서버의 현재시간을 추출
	
	MemberDAO dao = MemberDAO.getInstance();
	//MemberDAO dao = new MemberDAO();
	//int ri = dao.insertMember(dto);//ri값이 반환됨 성공1 실패 0
	
	if(dao.confirmId(dto.getId()) == MemberDAO.MEMBER_EXISTENT) {
	%>
	
		<script language="javascript">
			alert("입력하신 아이디가 이미 존재합니다.");
			history.back();
		</script>
	<%	
	} else {
		int ri = dao.insertMember(dto);//ri값이 반환됨 성공1 실패 0
		if(ri == MemberDAO.MEMBER_JOIN_SUCCESS) {
			session.setAttribute("id", dto.getId());
	%>
			
			<script language="javascript">
				alert("회원가입을 축하합니다! 로그인 해주세요.");
				document.location.href="login.jsp";
			</script>
	<%	
		} else {
			
	%>
			
			<script language="javascript">
				alert("회원가입에 실패했습니다. 재가입해주세요.");
				document.location.href="login.jsp";
			</script>
	<%	
			
		}
	}
	
	%>
</body>
</html>