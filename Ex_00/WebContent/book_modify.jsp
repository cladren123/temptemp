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
    
    <h1>도서 정보 수정 페이지</h1>
    <form action="main" method="post" >
    	<input type="hidden" name="action" value="update">
	    <table border="table table-hover">
	    <tr>
	        <th>내용</th>
	        <th>항목</th>
	    </tr>
	    <tr>
	        <td>도서번호</td>
	        <td><input type="text" id="title" name="isbn" value="${book.isbn}" readonly></td>
	    </tr>
	    <tr>
	        <td>도서제목</td>
	        <td><input type="text" id="title" name="title" value="${book.title}"></td>
	    </tr>
	    <tr>
	        <td>저자</td>
	        <td><input type="text" id="author" name="author" value="${book.author}"></td>
	    </tr>
	    <tr>
	        <td>가격</td> 
	        <td><input type="number" id="price" name="price" value="${book.price}"></td>
	    </tr>
	    <tr>
	        <td>설명</td>
	        <td><textarea id="desc" id="desc" name="desc">${book.desc}</textarea></td>
	    </tr>
	    <tr>
			<td><input type="submit" value="수정하기"></td>
		</tr>
	    </table>
   	</form>


	
<a href="./regist.jsp">추가등록</a>
<a href="./index.jsp">홈으로</a>
<a href="./main?action=list">목록으로</a><br>

</body>
</html>