<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>카테고리 리스트 페이지</title>
    <link rel="stylesheet" type="text/css" href="../resources/css/basic.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="../resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/category.css?v=8">
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
<p>카테고리관리 페이지</p>
<div class="subpage_view">
    <span id="cate_ea"></span>
    <form id="search">
    <span>
        <select class="p_select1" id="search_part" name="search_part">
            <option value="1" id="cate_name">카테고리명</option>
            <option value="2" id="cate_code">카테고리코드</option>
        </select>
        <input type="text" class="p_input1" id="search_word" name="search_word" placeholder="검색어를 입력해 주세요">
        <input type="submit" value="검색" title="카테고리 검색" class="p_submit">
    </span>
    </form>
</div>
<div class="subpage_view2" id="cate_list"></div>
<div class="subpage_view3">
    <ul class="pageing" id="paging">
        <li onclick="page_first()"><img src="../resources/ico/double_left.svg"></li>
        <li onclick="page_previous()"><img src="../resources/ico/left.svg"></li>
        <li onclick="page_next()"><img src="../resources/ico/right.svg"></li>
        <li onclick="page_last()"><img src="../resources/ico/double_right.svg"></li>
    </ul>
</div>
<div class="subpage_view4">
    <input type="button" value="카테고리 삭제" title="카테고리 삭제" class="p_button" id="del_button">
    <span style="float: right;">
    <input type="button" value="상품 리스트" title="상품 리스트" class="p_button p_button_color1">
    <input type="button" value="카테고리 등록" title="카테고리 등록" class="p_button p_button_color2">
    </span>
</div>
</section>
</main>
<%@include file="../copyright.jsp" %>
</body>
<script src="../resources/js/product/cate_list.js?v=<%= System.currentTimeMillis() %>"></script>
</html>