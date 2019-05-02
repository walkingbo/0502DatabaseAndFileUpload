package serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

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
			webMember.setPw(pw);
			webMember.setNickname(nickname);
			
			result = webMemberDAO.insertuser(webMember);
			
			return result;
		}
	}
	
	
	

