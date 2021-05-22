<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Upload 연습</title>
</head>
<body>
<h1>File Upload 연습</h1>
<h3>파일 선택 창에서 드래그나 'CTRL + Click' 또는 'Shift + Click'로 여러개의 파일 선택 가능</h3>
<!-- 
	파일을 전송할 때 <form>태그 구성하는 방법
	1. method는 반드시 'post'이어야 한다.
	2. enctype 속성에 'multipart/form-data'를 반드시 지정해야한다.
 -->


<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/file/FileUpload.do">
<table border="1">
<tr>
	<td>ID : </td>
	<td><input type="text" name="memid"></td>
</tr>
<tr>
	<td>파일업로드1 : </td>
	<td><input type="file" name="fileup1" multiple></td>
</tr>
<tr>
	<td>파일업로드2 : </td>
	<td><input type="file" name="fileup2"></td>
</tr>
<tr>
	<td colspan="2" style="text-align:center;"><input type="submit" value="파일 업로드하기"></td>
</tr>
</table>
<br><hr><br>
   <a href ="<%=request.getContextPath()%>/file/UploadFileList.do">전체 Upload파일 보기</a>

	
</form>
</body>
</html>