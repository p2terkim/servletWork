<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 세션의 값을 확인하여 로그인 정보를 가져온다 -->
<%
//세션 객체 생성
HttpSession sessionLog = request.getSession();

//세션값 확인
String status = (String)sessionLog.getAttribute("status");
String id = (String)sessionLog.getAttribute("userid");

	if(status == null){
		if(id == null){ id="";}
	%>
	
<!-- 로그인이 안되었을 때 보여줄 내용 -->
<form action="<%=request.getContextPath()%>/SessionLogin_HW.do"  method="post">
<table style="border:1px solid; margin:0 auto;">
<tr>
	<td>ID : </td>
	<td><input type="text" name="userid" value="<%=id %>" placeholder="ID 입력하세요"></td>
</tr>
<tr>
	<td>Pass : </td>
	<td><input type="password" name="pass" placeholder="PassWord 입력하세요"></td>
</tr>

<tr>
	<td colspan="2" style="text-align:center;"><input type="submit" value="Login"></td>
</tr>
</table>
</form>
	
	<%
}else{
%>
<!-- 로그인이 되었을때 보여줄 내용 -->
<h2><%=id %>님 반갑습니다.</h2><br><br> 
<a href='<%= request.getContextPath()%>/SessionLogout_HW.do'>로그아웃</a>
<%
}
		
%>

</body>
</html>