package serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.WebMember;
import service.WebMemberDAO;
import util.ShareInstance;

//데이터베이스 로직을 처리하는 메소드가 구현된 클래스 - Repository	
public class WebMemberDAOImpl implements WebMemberDAO {
	//자기 자신의 참조를 넘기기 위한 변수
	private static WebMemberDAO webMemberDAO;
	//데이터베이스 연결 변수
	private Connection con;
	//SQL 실행 변수 - 프레임워크를 이용할 때는 필요 없음
	private PreparedStatement pstmt;
	
	private WebMemberDAOImpl() {
		//데이터베이스 연결 변수 가져오기
		con = ShareInstance.getInstance().con;
		
	}
	
	public static WebMemberDAO getInstance() {
		if(webMemberDAO == null) {
			webMemberDAO = new WebMemberDAOImpl();
		}
		return webMemberDAO;
	}

	@Override
	public String idcheck(String id) {
		String result = null;
		try {
			pstmt = 
				con.prepareStatement("select id from webmember where id=?");
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				result= rs.getString("id");
			}
			
		}catch(Exception e) {
			System.out.println("id 중복검사:"+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			}catch(Exception e) {
				
			}
		}
		return result;
	}

	@Override
	public String nicknamecheck(String nickname) {
		String result = null;
		try {
			pstmt = 
				con.prepareStatement("select id from webmember where nickname=?");
			pstmt.setString(1, nickname);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				result= rs.getString("nickname");
			}
			
		}catch(Exception e) {
			System.out.println(" nickname 중복검사:"+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			}catch(Exception e) {
				
			}
		}
		return result;
	}

	@Override
	public int insertuser(WebMember webMember) {
		
		int result = -1;
		try {
			pstmt = 
				con.prepareStatement("insert into webmember(id,pw,nickname) values(?,?,?)");
			pstmt.setString(1, webMember.getId());
			pstmt.setString(2, webMember.getPw());
			pstmt.setString(3, webMember.getNickname());

			result= pstmt.executeUpdate();
				
		}catch(Exception e) {
			System.out.println("회원가입:"+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			}catch(Exception e) {
				
			}
		}
		return result;
	}

	
}
