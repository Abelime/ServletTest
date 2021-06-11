<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="update" method="post">
	<input type="hidden" value="${nt.id}" name="id">
		<table border=1>
			<tr>
				<td colspan=2>게시판 종류 : ${nt.getBoard()}</td> 
			</tr>
			<tr>
				<th>글쓴이</th>
				<td>${nt.writeid }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="${nt.title }"></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${nt.hit }</td>
			</tr>
			<tr>
				<th>등록일</th>
				<td>${nt.regdate }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="" cols="" name="content">${nt.content}</textarea></td>
			</tr>
			<tr>
				<td colspan=2><input type="submit" value="수정">
				<input type="button" onclick="location.href='list'" value="취소"></td>
			</tr>
		</table>
	</form>
</body>
</html>