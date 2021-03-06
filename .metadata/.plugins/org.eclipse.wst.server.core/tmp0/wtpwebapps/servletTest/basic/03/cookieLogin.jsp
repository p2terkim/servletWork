<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
'id 기억하기' 체크박스를 체크한 후 'Login'버튼을 누르면 입력했던 'ID'를 쿠키에 
저장하고, 쿠키에 'ID'값이 저장되어 있으면 'ID'입력 창에 쿠키에 저장되었던 'ID'가
나타나도록 하고, 체크박스는 체크된 상태로 유지되도록 한다.

체크박스를 해제한 후 로그인을 하면 쿠키에 저장된 'ID'를 삭제하고 체크박스도 
해제된 상태가 되도록 한다.

현재 로드인 성공 ID와 PassWord는 'test', '1234' 이고
로그인이 성공하면 'cookieMain.jsp'로 이동하고 실패하면
'cookieLogin.jsp'로 이동하도록 한다.

로그인처리, 쿠키처리를 하는 서블릿의 패턴 URL은 '/CookieLoginServlet.do'로 한다.

%% 쿠키는 JSP문서에서도 똑같은 방법으로 사용할 수 있다. %%


팀 구성원 역할

PL  ==> 프로젝트 리더 (팀장) , 전체 프로젝트를 관장한다.
TA  ==> 새로운 기술을 익혀 팀원들에게 학습시킨다.
AA  ==> 프로젝트 환경을 구축한다. (패키지 구조 작성등)
DA  ==> DB관련 책임자
UA  ==> 화면 구성 및 디자인을 담당한다.
 -->
<%
// 쿠키값으로 id값 가져오기
String cookieUserId = "";  // 쿠키값이 저장될 변수
String chk = ""; 	// 쿠키 박스 체크용 변수

Cookie[] cookies = request.getCookies();
if(cookies!=null){
	for(Cookie cookie : cookies){
		if("ID".equals(cookie.getName())){
			cookieUserId = cookie.getValue();  // 쿠키에 저장된 id값 가져오기
			chk = "checked";
		}
	}
}

%>

<form action="<%=request.getContextPath()%>/CookieLoginServlet.do" method="post">
<table style="border:1px solid; margin:0 auto;">
<tr>
	<td>ID : </td>
	<td><input type="text" name="userid" value="<%=cookieUserId %>" placeholder="ID 입력하세요"></td>
</tr>
<tr>
	<td>Pass : </td>
	<td><input type="password" name="pass" placeholder="PassWord 입력하세요"></td>
</tr>
<tr>
	<td colspan="2"><input type="checkbox" name="chkid" <%=chk %>  value="check">ID 기억하기</td>
</tr>
<tr>
	<td colspan="2" style="text-align:center;"><input type="submit" value="Login"></td>
</tr>
</table>
</form>
</body>
</html>