package kr.or.ddit.basic.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SessionLogin_HW.do")
public class SessionLogin_HW extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		//PrintWriter out = response.getWriter();
		
		//유저 정보 가져오기
		String userId = request.getParameter("userid");
		String userPass = request.getParameter("pass");
		System.out.println(userId);
		System.out.println(userId);
				
		//session 객체 생성
		HttpSession session = request.getSession();
		
		//세션 저장
		session.setAttribute("userid", userId);
		
		//로그인 성공 여부 검사
		if(userId!=null && userPass!=null) {
			if("admin".equals(userId) && "1234".equals(userPass) ) {  // 로그인 성공
				session.setAttribute("status", "ok");
				response.sendRedirect(request.getContextPath() + "/basic/04_HW/sessionLogin.jsp");
					
			}else {  // 로그인 실패
				response.sendRedirect(request.getContextPath() + "/basic/04_HW/sessionLogin.jsp");
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
