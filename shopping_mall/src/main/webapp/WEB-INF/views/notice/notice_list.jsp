<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 리스트 페이지</title>
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
    <p>공지사항 관리페이지</p>
    <div class="subpage_view" id="notice_list"></div>
    <div class="board_btn">
        <button class="border_del">공지삭제</button>
        <button class="border_add">공지등록</button>
    </div>
    <div class="border_page">
        <ul class="pageing" id="paging">
            <li onclick="page_first()"><img src="../resources/ico/double_left.svg"></li>
            <li onclick="page_previous()"><img src="../resources/ico/left.svg"></li>
            <li onclick="page_next()"><img src="../resources/ico/right.svg"></li>
            <li onclick="page_last()"><img src="../resources/ico/double_right.svg"></li>
        </ul>
    </div>
</section>
</main>
<%@include file="../copyright.jsp" %>
</body>
<script src="../resources/js/notice_list.js?v=<%= System.currentTimeMillis() %>"></script>
</html>