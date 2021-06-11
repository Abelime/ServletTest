<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${msg}
	<form action="" method="post">
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
				<td>${nt.title }</td>
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
				<td>${nt.content}</td>
			</tr>
			<tr>
				<td colspan=2>
				<a href="update?id=${nt.id}&q=${q}&f=${f}"><input type="button" value="수정"></a>
				<input type="button" onclick="location.href='list'" value="취소"></td>
			</tr>
		</table>
		<div>
			<input type="button" onclick="location.href='/board/contents/list?p=${p}&q=${q}&f=${f}'" value="목록">
		</div>
	</form>
</body>
</html>