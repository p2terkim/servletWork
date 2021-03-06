package kr.or.ddit.basic.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RequestTest02.do")
public class RequestTest02 extends HttpServlet {
   private static final long serialVersionUID = 1L;
 
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      request.setCharacterEncoding("UTF-8");
      
      //form에서 보내온 값을 받는다.
      int num1 = Integer.parseInt(request.getParameter("num1"));
      int num2 = Integer.parseInt(request.getParameter("num2"));
      String op = request.getParameter("op");
      
      //계산 결과가 저장될 변수 선언
      double result = 0;
      boolean calcOk = true; 	//계산 성공 여부를 저장하는 변수 선언
      
      switch(op) {
      
      		case "+" : result = num1 + num2; break;
      		case "-" : result = num1 - num2; break;
      		case "*" : result = num1 * num2; break;
      		case "/" : 
      			if(num2 != 0) {
      				
      				result = (double)num1 / num2; 
      			} else {
      				calcOk = false;
      			}
      			break;
      		case "%" : 
      			if(num2 != 0) {
      				
      				result = num1 % num2; 
      			} else {
      				calcOk = false;
      			}
      			break;
      }
      
      response.setCharacterEncoding("UTF-8");
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<html><head><meta charset='utf-8'>");
      out.println("<title>Request 객체연습2</title></head>");
      out.println("<body>");
      out.println("<h2>계산 결과</h2><hr>");
      out.printf("%d %s %d = ", num1, op, num2);
      if(calcOk == true) {
    	  out.println(result);
      }else {
    	  out.println("계산불능(0으로 나누기)");
      }
      out.println("</body></html>");
   }


   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}