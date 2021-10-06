package com.gyojinjava.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO {
	
	public static final int MEMBER_NONEXISTENT = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	
	private static MemberDAO instance = new MemberDAO();//singleton pattern : MemberDAO객체를 1개만 만들고 공유해서 모두가 사용하게 함

	public MemberDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	public int insertMember(MemberDTO dto) {
		int ri=0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "insert into memberex values (?,?,?,?,?,?)";
		//ResultSet rs = null;
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getAddress());
			pstmt.setTimestamp(6, dto.getRdate());
			
			pstmt.executeUpdate();
			ri = MemberDAO.MEMBER_JOIN_SUCCESS;	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		return ri;
	}
	
	
	public int confirmId(String id) {
		int ri = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select id from memberex where id = ?";
				
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ri = MemberDAO.MEMBER_EXISTENT;
			} else {
				ri = MemberDAO.MEMBER_NONEXISTENT;
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return ri;
	}
	
	public int userCheck(String id, String pw) {
		int ri=0;
		String dbPw;//DB에서 가져온 password
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select pw from memberex where id = ?";
				
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				dbPw = rs.getString("pw");
				
				if(dbPw.equals(pw)) {
					ri = MemberDAO.MEMBER_LOGIN_SUCCESS;//로그인 성공 1
				} else {
					ri = MemberDAO.MEMBER_LOGIN_PW_NO_GOOD;// 아이디는 있으나 비번이 틀림 0
				}			
			
			
			} else {
				ri = MemberDAO.MEMBER_LOGIN_IS_NOT;// 아이디가 없음. 회원이 아님 -1
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public MemberDTO getMember(String id) {
		
				
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from memberex where id = ?";
		MemberDTO dto = new MemberDTO();
				
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setAddress(rs.getString("address"));
				dto.setRdate(rs.getTimestamp("rdate"));
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	
	public int updateMember(MemberDTO dto) {
		int ri = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "update memberex set pw=?, email=?, address=? where id=?";		
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			
			//pstmt.setString(1, dto.getId());
			pstmt.setString(1, dto.getPw());
			//pstmt.setString(3, dto.getName());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getAddress());
			pstmt.setString(4, dto.getId());
			
			ri = pstmt.executeUpdate();	//회원정보수정에 성공하면 ri=1 로 변경됨
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
		return ri;
		
	}
	
	public ArrayList<MemberDTO> membersAll() {
		
		ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from memberex";
		
		//MemberDTO dto = new MemberDTO();
				
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setAddress(rs.getString("address"));
				dto.setRdate(rs.getTimestamp("rdate"));
				dtos.add(dto);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return dtos;
	}
	
	
	private Connection getConnection() {
		
		Context context = null;
		DataSource dataSource = null; 
		Connection conn = null;
		
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return conn;
	}
}
