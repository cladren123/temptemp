<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%@ include file = "/include/header.jsp" %>
    
    <c:if test="${!empty book }">
	    <h1>도서 등록 결과</h1>
	    
	    <table border="2">
	    <tr>
	        <th>내용</th>
	        <th>항목</th>
	    </tr>
	    <tr>
	        <td>저자</td>
	        <td>${book.author}</td>
	    </tr>
	    <tr>
	        <td>가격</td> 
	        <td>${book.price}</td>
	    </tr>
	    <tr>
	        <td>설명</td>
	        <td>${book.desc}</td>
	    </tr>
	    <tr>
	        <td>도서번호</td>
	        <td>${book.isbn}</td>
	    </tr>
	    </table>
    </c:if>
    
	<c:if test="${empty book}">
    	<h3> 등록된 도서 없어요</h3>
	</c:if>
	
<a href="regist.jsp">추가등록</a>
<a href="index.jsp">홈으로</a><br>

</body>
</html>