<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%@ include file="/include/header.jsp" %>

<div class="container">
	<h1>서버 오류 입니다! 다시 시도해주세요</h1>
	<p>
		${exception}
	</p>
</div>
</body>
</html>