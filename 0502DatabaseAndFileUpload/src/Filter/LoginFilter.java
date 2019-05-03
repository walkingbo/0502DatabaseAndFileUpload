package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//필터가 동작할 URL 요청
//추가하고자 하면 {"/user/content/", 요청 추가} 으로 변경
@WebFilter("/user/content")
public class LoginFilter implements Filter {

  
    public LoginFilter() {
       
    }


	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//로그인 정보를 확인하기 위해서 Session을 찾아 옵니다.	
		HttpSession session = ((HttpServletRequest)request).getSession();
		//로그인이 되어 있지 않으면
		if(session.getAttribute("webMember") == null){
			// user/login으로 리다이렉트
			HttpServletRequest req = (HttpServletRequest)request;
			HttpServletResponse res = (HttpServletResponse)response;
			res.sendRedirect(req.getContextPath()+"/user/login");
			return;
			
		}
		
		
		//이 메소드를 수행하면 Controller에게 넘어갑니다.
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
