<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/CookieAddServlet.do">Cookie 정보 저장하기</a><br><br>

<a href="<%=request.getContextPath()%>/CookieReadServlet.do">저장된 Cookie 정보 확인하기</a><br><br>

<a href="<%=request.getContextPath()%>/CookieDeleteServlet.do">저장된 Cookie 정보 삭제하기</a><br><br>

</body>
</html>