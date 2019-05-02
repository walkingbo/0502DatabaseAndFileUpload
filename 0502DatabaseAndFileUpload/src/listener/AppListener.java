package listener;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import util.ShareInstance;

//웹 애플리케이션이 시작할 때 와  종료 될 때 호출되는 메소드를 소유한 클래스
@WebListener
public class AppListener implements ServletContextListener {

 
    public AppListener() {
       
    }
	//웹 애플리케이션이 종료 될 때 호출되는 메소드
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	try {
    		if(ShareInstance.getInstance().con !=null) {
    			ShareInstance.getInstance().con.close();
    		}
    	}catch(Exception e) {
    		System.out.println("데이터베이스 연결해제 예외:"+e.getMessage());
			e.printStackTrace();
    	}
       
    }

	//웹 애플리 케이션이 시작될  때 호출되는 메소드
    public void contextInitialized(ServletContextEvent arg0)  { 
         //Connection Pool을 이용하는 방식으로 Connection 생성
    	//데이터베이스 연결 객체 생성
		try {
		Context context = new InitialContext();
    	DataSource ds = (DataSource)context.lookup("java:comp/env/DBConn");
		ShareInstance.getInstance().con = ds.getConnection();
		//데이터베이스 자동 commit을 해제하고 manual commit으로 설정
		ShareInstance.getInstance().con.setAutoCommit(false); 
		}catch (Exception e) {
		System.out.println("데이터베이스 연결예외:"+e.getMessage());
			e.printStackTrace();
		}
    }
	
}
