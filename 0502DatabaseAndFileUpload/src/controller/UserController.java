package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.WebMember;
import service.WebMemberService;
import serviceimpl.WebMemberServiceImpl;

//클라이언트의 요청을 처리할 수 있는 클래스

//처리할 수 있는 URL 패턴 설정

@WebServlet("/user/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   private WebMemberService webMemberService;
    public UserController() {
        super();
       webMemberService = WebMemberServiceImpl.getInstance();
    }

	//get 요청을 처리하는 메소드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//동일한 요청 URL을 전송 방식을 이용해서 구분하고자 하는 경우
		//request.getMethod() 를 이용해서 구분합니다.	
		
		//요청 URL에서 공통 부분을 제외한 URL 추출
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length()+1);
		
		//포워딩에 사용할 변수
		RequestDispatcher dispatcher = null;
		//요청처리
		switch(command) {
		case "user/register":
			//get 방식으로 요청이 오면
			if(request.getMethod().equals("GET")) {
				//단순 페이지 이동은 forwarding
				//디렉토리 패턴을 사용할 때는 디렉토리가 결과 페이지에 포함되기 때문에 
				//디렉토리를 제거하고자 하면 ../로 시작
				dispatcher = request.getRequestDispatcher("../member/register.jsp");
				dispatcher.forward(request, response);
			}else {
				System.out.println("회원가입 요청처리");
				int result = webMemberService.insertuser(request);
				if(result>0) {
					response.sendRedirect("login");
				}else {
					response.sendRedirect("register");

				}
			}
			break;
			
		case "user/idcheck":
			//서비스 메소드 호출
			JSONObject jsonObject =
				webMemberService.idcheck(request);
			//메소드 수행 결과를 request에 저장
			request.setAttribute("result", jsonObject);
			//출력할 결과 페이지로 포워딩
			dispatcher = request.getRequestDispatcher("../member/idcheck.jsp");
			dispatcher.forward(request, response);
			break;
			
		case "user/nicknamecheck":
			//서비스 메소드 호출
			JSONObject jsonObject1 =
				webMemberService.nicknamecheck(request);
			//메소드 수행 결과를 request에 저장
			request.setAttribute("result", jsonObject1);
			//출력할 결과 페이지로 포워딩
			dispatcher = request.getRequestDispatcher("../member/nicknamecheck.jsp");
			dispatcher.forward(request, response);
			break;
			
		case "user/login":	
			if(request.getMethod().contentEquals("GET")) {
			//출력할 결과 페이지로 포워딩
			dispatcher = request.getRequestDispatcher("../member/login.jsp");
			dispatcher.forward(request, response);
			}else {
				WebMember webMember =
						webMemberService.login(request);
				if(webMember == null) {
					//로그인에 실패했을 때는 로그인 페이지로 이동
					response.sendRedirect("login");
				}else {
					//로그인 성공했을때는 	
					response.sendRedirect("content");
				}
			}
			
			break;
			
		case "user/content":
			//출력할 결과 페이지로 포워딩
			dispatcher = request.getRequestDispatcher("../member/content.jsp");
			dispatcher.forward(request, response);
			
			break;
			
		case "user/logout":
			request.getSession().invalidate();
			response.sendRedirect("login");
			
			break;
			
			
		}
		
	}

	//post 요청을 처리하는 메소드-doGEt을 호출해서 post 요청도 doGet에서 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
