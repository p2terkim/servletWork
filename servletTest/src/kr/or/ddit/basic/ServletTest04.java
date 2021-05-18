package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servletTest04")
public class ServletTest04 extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setCharacterEncoding("UTF-8");
      
      /*
       * Servlet 클래스나 JSP 페이지의 환경에 관련된 정보는 javax.servlet.ServletContext 인터페이스 타입의
       * 객체를 이용해서 얻을 수 있다.
       * 
       * ServletContext객체의 메서드
       * - getServerInfo()메서드 : Servlet이 속하는 웹 서버의 종류를 반환한다.
       * - getMajorVersion()메서드 : 웹 컨테이너가 지원하는 Servlet규격서의 메이저 버전 반환
       * - getMinorVersion()메서드 : 웹 컨테이너가 지원하는 Servlet규격서의 마이너 버전 반환
       */
      
      // ServletContext객체 구하기
      ServletContext context = this.getServletContext();
      String serverInfo = context.getServerInfo();
      int majorVersion = context.getMajorVersion();
      int minorversion = context.getMinorVersion();
      
      String servletName = this.getServletName();
      
      response.setContentType("text/html; charset = UTF-8>");
      
      PrintWriter out = response.getWriter();
      
      out.println("<DOCTYPE html>");
      out.println("<html> <head> <meta charset ='utf'> ");
      out.println("<title> 웹 서버의 정보 </title> </head>");
      out.println("<body>");
      out.println("웹 서버의 종류(serverInfo) : " + serverInfo + "<br>");
      out.printf("servletName : %s<br> ", servletName);
      out.printf("지원하는 servlet버전 : %d%d", majorVersion, minorversion);
      
      
      /*
       * 웹 애플리케이션 초기화 파라미터(web.xml에 설정해서 사용한다.)
       * 특정 Servlet에 속하는 초기화 파라미터가 아니라 웹 애플리케이션 전체에 속하는 초기화 파라미터를 말한다.
       * 
       * web.xml 작성 방법
       * <web-app>
       *    <context-param>
       *       <param-name> 파라미터 이름1 </param-name>
       *       <param-value> 파라미터 값1 </param-value>
       * 
       *       <param-name> 파라미터 이름2 </param-name>
       *       <param-value> 파라미터 값2 </param-value>
       * 
       *       ...
       *    </context-param>
       * 
       * 초기화 파라미터값 가져오기
       * - Servlet에서는 ServletContext객체를 구하고, 이 객체의 getInitParameter()메서드를 호출해서 가져온다.
       * - JSP페이지에서는 application 내장  객체 변수의 getInitParameter()메서드를 호출해서 가져온다.
       * - getInitParameter()메서드로 가져온 값은 무조건 문자열로 가져온다.
       * 
       * </web-app>
       */
      
      out.println("<hr>");
      
      String uploadPath = context.getInitParameter("Upload_Path");
      out.printf("web.xml에 설정된 파라미터 : %s<br> ", uploadPath);
      
      out.println("<hr>");
      
      /*
       * 문제) 초기화 파라미터로 다음 값들을 등록해 놓고 이 값을 읽어와 합계를 구하는 프로그램을 작성하시오.
       *       첫 번째 파라미터 => 이름 : start, 값 : 1
       *       두 번째 파라미터 => 이름 : end, 값 : 10
       * 실행 결과 예시) 1부터 10까지의 합계 = 55
       */
      
      int start = Integer.parseInt(context.getInitParameter("start"));
      int end = Integer.parseInt(context.getInitParameter("end"));
      
      int sum = 0;
      for(int i = start; i <= end; i++) {
         sum +=i;
      }
      
      out.println(start + "부터 " + end + "까지의 합계 = " + sum + "<br>");
      out.printf("%d부터 %d까지의 합계 = %d <br> ", start, end, sum);
      
      
      
      out.println("</body></html>");

      
   }


   
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}