<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
.text-right {
	text-align: right;
}
</style>


<div class="container">
	<div>
		<h1 class="text-center">SSAFY 도서관리</h1>
	</div>
	
	<c:if test="${empty loginUser}">
		<div class="text-right">
			<form action="main" method="post">
				<input type="hidden" name="action" value="login">
				<input type="text" name="id" placeholder="아이디" value="${cookie.loginUser.value}"> <!-- loginUser라는 쿠키의 값 꺼내기 -->
				<input type="password" name="pass" placeholder="비밀번호">
				<input type="submit" value="로그인">
			</form>
		</div>
	</c:if>
	<c:if test="${!empty loginUser}">
		<div class="text-right">
			${loginUser.name} 님 반갑습니다.
			<a href="main?action=logout">로그아웃</a>
		</div>
	</c:if>
	
</div>

<script>
	let msg = "${msg}";
	if(msg) {
		alert(msg);
	}
</script>