<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 내용 확인 페이지</title>
    <link rel="stylesheet" type="text/css" href="../resources/css/basic.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/login.css?v=10">
    <link rel="stylesheet" type="text/css" href="../resources/css/main.css?v=10">
    <link rel="stylesheet" type="text/css" href="../resources/css/notice.css?v=10">
    <link rel="icon" href="../resources/img/logo.png" sizes="128x128">
    <link rel="icon" href="../resources/img/logo.png" sizes="64x64">
    <link rel="icon" href="../resources/img/logo.png" sizes="32x32">
    <link rel="icon" href="../resources/img/logo.png" sizes="16x16">
</head>
<body>
<%@ include file="../top.jsp" %>
<%@ include file="../Qmenu.jsp" %>
<main class="maincss">
<input type="hidden" id="nidx" value="${notice.nidx}">
<section>
    <p>공지사항 확인 페이지</p>
<div class="write_view">
<ul>
    <li>공지사항 제목</li>
    <li>
       <input type="text" class="notice_input1" name="notice_title" value="${notice.notice_title}" readonly>
    </li>
</ul>
<ul>
    <li>글쓴이</li>
    <li>
    	<input type="text" class="notice_input2" name="notice_writer" value="${notice.notice_writer}" readonly>
    </li>
</ul>
<ul>
    <li>첨부파일</li>
    <li>
       <a href="${notice_file.nfile_url}" target="_blank" style="text-decoration: none; color: blue; font-weight: bold;" onclick="return openPopup(this.href);">
		  ${notice_file.nfile_name}
		</a>
    </li>
</ul>
<ul class="ul_height">
    <li>공지내용</li>
    <li>
        <div class="notice_input3" style="overflow-y: auto;">${notice.notice_text}</div>
    </li>
</ul>
</div>
<div class="board_btn">
    <button class="border_del">공지목록</button>
    <button class="border_add">공지수정</button>
</div>
</section>
</main>
<%@include file="../copyright.jsp" %>
</body>
<script src="../resources/js/notice/notice_view.js?v=<%= System.currentTimeMillis() %>"></script>
</html>