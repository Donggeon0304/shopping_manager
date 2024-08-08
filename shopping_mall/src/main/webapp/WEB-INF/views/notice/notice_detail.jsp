<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 상세보기 페이지</title>
    <script src="https://cdn.ckeditor.com/4.22.1/standard/ckeditor.js"></script>
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
<section>
    <p>공지사항 상세보기 페이지</p>
<form id="frm" enctype="multipart/form-data">
<div class="write_view">
<ul>
    <li>공지사항 여부</li>
    <li>
        <label class="label_notice"><em class="cbox"><input type="checkbox" name="notice_use" ${notice.notice_use == "Y" ? checked : ""}></em> 공지 출력</label> ※ 공지출력을 체크할 시 해당 글 내용은 최상단에 노출 되어 집니다.
    </li>
</ul>
<ul>
    <li>공지사항 제목</li>
    <li>
        <input type="text" class="notice_input1" name="notice_title" value="${notice.notice_title}" readonly> ※ 최대 150자까지 입력이 가능
    </li>
</ul>
<ul>
    <li>글쓴이</li>
    <li>
        <input type="text" class="notice_input2" name="notice_writer" value="${notice.notice_writer}" readonly> ※ 관리자 이름이 노출 됩니다.       
    </li>
</ul>
<ul>
    <li>첨부파일</li>
    <li>
        <input type="text" name="nfile" value="첨부파일" readonly>       
    </li>
</ul>
<ul class="ul_height">
    <li>공지내용</li>
    <li>
        <textarea class="notice_input3" name="notice_text" placeholder="공지내용을 입력하세요!" readonly>${notice.notice_text}</textarea>
    </li>
</ul>
</div>
</form>
<div class="board_btn">
    <button class="border_del">공지목록</button>
    <button class="border_add">공지등록</button>
</div>
</section>
</main>
<%@include file="../copyright.jsp" %>
</body>
<script src="../resources/js/notice_detail.js?v=<%= System.currentTimeMillis() %>"></script>
</html>