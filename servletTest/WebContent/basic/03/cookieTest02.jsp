<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키 카운터 연습</title>

</head>
<body>
<!-- 쿠키변수명은 count로 한다. -->
<a href="<%=request.getContextPath() %>/CookieCountServlet.do">cookie count 증가하기</a>
<a href="<%=request.getContextPath() %>/CookieCountDelServlet.do">cookie count 초기화하기</a>

</body>
</html>