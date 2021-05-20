package kr.or.ddit.basic.session;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/sessionLogin.do")
public class SessionLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GET방식으로 요청하면 무조건 로그인 폼으로 이동하기 - forward방식
		RequestDispatcher rd = 
			request.getRequestDispatcher("/basic/04/sessionLogin.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String userid = request.getParameter("userid"); // userid값 가져오기
		String pass = request.getParameter("pass");		// pass값 가져오기
		
		System.out.println(userid + ", " + pass);
		// id: admin, pass: 1234  ==> 사용가능 회원
		if(userid!=null && pass!=null) {
			if("admin".equals(userid) && "1234".equals(pass)) { // 로그인 성공
				HttpSession session = request.getSession();
				session.setAttribute("loginId", userid); // 세션에 로그인 Id 저장
			}
		}
		
		RequestDispatcher rd = 
				request.getRequestDispatcher("/basic/04/sessionLogin.jsp");
		rd.forward(request, response);
		
	}

}
