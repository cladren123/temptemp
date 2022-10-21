<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	
<script type="text/javascript">
	function bookList() {
		f.action="./main?action=list";
		f.submit();
	}
	
	function remove() {
		if (confirm("정말 삭제하시겠습니까?")) {
			f.action="./main?action=delete&isbn=${book.isbn}";
			f.submit();
		}
	}
	
	function modify() {
		f.action="./main?action=modify&isbn=${book.isbn}";
		f.submit();
	}
	
</script>
</head>
<body>
    <%@ include file = "/include/header.jsp" %>
    
    <c:if test="${!empty book }">
	    <h1>도서 상세 페이지</h1>
	    <form name="f" action="" method="post" >
	    	<input type="hidden" name="isbn" value="${book.isbn}">
		    <table border="table table-hover">
		    <tr>
		        <th>내용</th>
		        <th>항목</th>
		    </tr>
		    <tr>
		        <td>도서번호</td>
		        <td>${book.isbn}</td>
		    </tr>
		    <tr>
		        <td>도서제목</td>
		        <td>${book.title}</td>
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
		    </table>
    	</form>
    </c:if>
    
	<c:if test="${empty book}">
    	<h3> 등록된 도서 없어요</h3>
	</c:if>
	
<a href="./regist.jsp">추가등록</a>
<a href="./index.jsp">홈으로</a>
<a href="./main?action=list">목록으로</a><br>

	<table class="table">
		<tr>
			<td><input type="button" value="수정" onclick="modify()"></td>
			<td><input type="button" value="삭제" onclick="remove()"></td>
			<td><input type="button" value="목록" onclick="bookList()"></td>
		</tr>
	</table>

</body>
</html>