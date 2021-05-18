package kr.or.ddit.basic.cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieLoginServlet.do")
public class CookieLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// userid, pass, chkid 정보 가져오기
		String userId = request.getParameter("userid");
		String pass = request.getParameter("pass");
		String chkId = request.getParameter("chkid");
		
		// 일단 쿠키 객체 생성
		Cookie userCookie = new Cookie("ID", userId);
		
		// 체크 박스의 체크 여부에 따라 쿠키를 저장한다.
		if(chkId!=null) {  // 체크박스가 체크 되었을 때  (체크박스는 체크된 데이터만 전송된다.)
			response.addCookie(userCookie);  // 쿠키 저장
		}else {  // 체크박스가 해제되었을 때
			userCookie.setMaxAge(0);
			response.addCookie(userCookie);  // 유지시간을 0으로 해서 다시 추가하면 쿠키가 삭제된다.
		}
		
		
		// 로그인 성공 여부 검사 (id==>test, pass==>1234)
		if(userId!=null && pass!=null) {
			if("test".equals(userId) && "1234".equals(pass) ) {  // 로그인 성공
				response.sendRedirect(request.getContextPath() + "/basic/03/cookieMain.jsp");
			}else {  // 로그인 실패
				response.sendRedirect(request.getContextPath() + "/basic/03/cookieLogin.jsp");
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
