package serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import domain.WebMember;
import service.WebMemberDAO;
import service.WebMemberService;
import util.ShareInstance;

public class WebMemberServiceImpl implements WebMemberService {
	
		//자기 자신의 참조를 넘기기 위한 변수
		private static WebMemberService webMemberService;
		//데이터베이스 연결 변수
		private Connection con;
		//DAO 참조 변수
		private WebMemberDAO webMemberDAO;
		
		private WebMemberServiceImpl() {
			//데이터베이스 연결 변수 가져오기
			con = ShareInstance.getInstance().con;
			webMemberDAO = WebMemberDAOImpl.getInstance();
		}
		
		public static WebMemberService getInstance() {
			if(webMemberService == null) {
				webMemberService = new WebMemberServiceImpl();
			}
			return webMemberService;
		}

		@Override
		public JSONObject idcheck(HttpServletRequest request) {
			JSONObject jsonObject = null;
			//파라미터 읽기
			String id = request.getParameter("id");
			//DAO 메소드호출
			String result = webMemberDAO.idcheck(id);
			
			jsonObject = new JSONObject();
			//result가 null 이면 중복이 아니고 null이 아니면 중복
			if(result == null) {
				jsonObject.put("result", "success");
			}else {
				jsonObject.put("result", "fail");
				}
			return jsonObject;
		}

		@Override
		public JSONObject nicknamecheck(HttpServletRequest request) {
			JSONObject jsonObject = null;
			//파라미터 읽기
			String nickname = request.getParameter("nickname");
			//DAO 메소드호출
			String result = webMemberDAO.nicknamecheck(nickname);
			
			jsonObject = new JSONObject();
			//result가 null 이면 중복이 아니고 null이 아니면 중복
			if(result == null) {
				jsonObject.put("result", "success");
			}else {
				jsonObject.put("result", "fail");

			}
			return jsonObject;
		}

		@Override
		public int insertuser(HttpServletRequest request) {
			int result = -1;
			
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String nickname = request.getParameter("nickname");
			
			WebMember webMember = new WebMember();
			webMember.setId(id);
			//비밀번호를 그대로 저장
			//webMember.setPw(pw);
			
			//비밀번호를 암호화 해서 저장
			webMember.setPw(BCrypt.hashpw(pw, BCrypt.gensalt(10)));
			
			webMember.setNickname(nickname);
			
			result = webMemberDAO.insertuser(webMember);
			
			
			//Commit과 Rollback은 Database 작업이지만
			//DAO에서 하는 것이 아니고 Service에서 수행 합니다.
			//하나의 거래는 Service가 처리하기 때문입니다.	
			try{
				con.commit();
			}catch(Exception e) {
				System.out.println("삽입완료:"+e.getMessage());
			}
			
			
			
			return result;
		}

		@Override
		public WebMember login(HttpServletRequest request) {
		 WebMember webMember = null;
			 
		 String id = request.getParameter("id");
		 String pw = request.getParameter("pw");
		 
		 //DAO 메소드 호출
		 webMember = webMemberDAO.login(id.toUpperCase().trim());
		 if(webMember != null) {
			 //로그인 성공- 암호화된 비밀번호와 일치하는지 확인
			 if(BCrypt.checkpw(pw, webMember.getPw())) {
				 //비밀번호는 저장할 필요가 없기 때문에 삭제
				 webMember.setPw(null);
			 }else {
				 webMember = null;
			 }
			 
		 }
		 //로그인에 성공하면 세션에 id와 그 이외 필요한 정보를 저장
		 //로그인 된 경우는 session에 webMember에 데이터가 저장
		 //로그인 안된 경우는 session에 webMember 가 null
		 request.getSession().setAttribute("webMember", webMember);
		 
			return webMember;
		}
	}
	
	
	

