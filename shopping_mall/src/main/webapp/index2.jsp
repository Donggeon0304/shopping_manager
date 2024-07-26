<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="frm">
아이디 : <input type="text" name="mid">
<input type="button" value="전송" onclick="post_page()">
</form>
</body>
<script>
function post_page(){
	frm.method="post";
	frm.action="./loginok.do";
	frm.submit();	
}
</script>
</html>