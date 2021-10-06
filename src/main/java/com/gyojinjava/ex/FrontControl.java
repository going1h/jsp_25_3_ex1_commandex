package com.gyojinjava.ex;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontControl
 */
@WebServlet("*.do")
public class FrontControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		
		if(command.equals("/membersAll.do")) {
			
			response.setContentType("text/html; charset=EUC-KR");
			PrintWriter writer = response.getWriter();
			writer.println("<html><head></head><body>");
			
			Service service = new MembersAllService();
			
			ArrayList<MemberDTO> dtos = service.excute(request, response);
			
			for(int i=0 ; i < dtos.size();i++) {
				 MemberDTO dto = dtos.get(i);
				 String id = dto.getId();
				 String pw = dto.getPw();
				 String name = dto.getName();
				 String email = dto.getEmail();
				 Timestamp rdate = dto.getRdate();
				 String address = dto.getAddress();
				 
				 writer.println("아이디 : " + id + "<br>");
				 writer.println("비밀번호 : " + pw + "<br>");
				 writer.println("이름 : " + name + "<br>");
				 writer.println("E-mail : " + email + "<br>");
				 writer.println("가입일 : " + rdate.toLocalDateTime() + "<br>");
				 writer.println("주소 : " + address + "<br>");
				 
				 writer.println("<hr>");
				 
				 
			}			
			
			
			
			writer.println("</body></html>");
			
		}
	}
	
}
