<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- jstl의 core 라이브러리를 사용하기 위해 taglib를 이용한다. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSAFY 도서 관리</title>
<script>
function f1() {
	return confirm("삭제하겠습니까?");
}
</script>
<style>
#user-list {
	border-collapse: collapse;
	width: 100%;
}

#user-list td, #user-list th {
	border: 1px solid black;
}
</style>
</head>
<body>
	<%--분리한 header.jsp 가져오기 --%>
	<%@ include file="./include/header.jsp"%>
	
	<div class="container">
		<h1>도서목록</h1>
		<form action="${pageContext.request.contextPath}/main?action=list" method="post">
	        <table class="table">
	          <tr>
	             <td>
	                <select name="condition">
	                     <option value="title">도서제목</option>
	                     <option value="author">저자</option>
	                </select>
	                <input type="text" name="keyword">
	                <input type="submit" value="검색">
	             </td>
	          </tr>
	        </table>
	    </form>
		<form action="./main?action=delete" method="post">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>번호</th>
					<th>ISBN</th>
					<th>제목</th>
					<th>가격</th>
					<th><input type="submit" value="삭제" onclick="return f1()"></th>
				</tr>
			</thead>
			<tbody>
				<%-- request 영역에 등록된 자료를 반복문을 이용해 출력한다. 테이블의 구성은 위 thead 참고 --%>
				<c:forEach items="${books}" var="book" varStatus="vs">
					<tr>
						<td>${vs.count}</td>
						<td>${book.isbn}</td>
						<td><a href="./main?action=view&isbn=${book.isbn}">${book.title}</a></td>
						<td>${book.price}</td>
						<td><input type="checkbox" name="isbn" value="${book.isbn}"></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</form>
		<hr>
		<a href="index.jsp">홈으로</a><br>
		<input type="button" value="Home1" onclick="javascript:location.href='./index.jsp'">
		<input type="button" value="Home2" onclick="javascript:location.href='/index.jsp'"> <!-- Error : 반 절대 경로가 되어 contextPath가 사라짐 -->
		<input type="button" value="Home3" onclick="javascript:location.href='${contextPath}/index.jsp'"> <!-- Error -->
		<input type="button" value="Home4" onclick="javascript:location.href='${pageContext.request.contextPath}/index.jsp'"> 
	</div>
	
</body>
</html>
