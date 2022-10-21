<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSAFY 도서 관리</title>
</head>
<body>
	<%@ include file="/include/header.jsp" %>
	
	<div class="container">
		<ul>
			<li><a href="./regist.jsp">도서등록</a></li>
			<li><a href="./main?action=list">도서목록</a></li> <!-- 데이터가 필요함(서블릿거치기), JSP만 요청하면 데이터 볼 수 없음 -->
			<li><a href="./list.jsp">도서목록X</a></li> <!-- 데이터가 필요함(서블릿거치기), JSP만 요청하면 데이터 볼 수 없음 -->
		</ul>
	</div>
	

</body>
</html>