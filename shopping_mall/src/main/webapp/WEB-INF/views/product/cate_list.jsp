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
    <span>등록된 카테고리 0건</span>
    <form id="frm1">
    <span>
        <select class="p_select1">
            <option>카테고리명</option>
            <option>카테고리코드</option>
        </select>
        <input type="text" class="p_input1" placeholder="검색어를 입력해 주세요">
        <input type="submit" value="검색" title="카테고리 검색" class="p_submit">
    </span>
    </form>
</div>
<form id="frm2">
<div class="subpage_view2">
    <ul>
        <li><input type="checkbox" class="allck"></li>
        <li>분류코드</li>
        <li>대메뉴 코드</li>
        <li>대메뉴명</li>
        <li>소메뉴 코드(사용안함)</li>
        <li>소메뉴명(사용안함)</li>
        <li>사용 유/무</li>
        <li>관리</li>
    </ul>
    <c:choose>
    <c:when test="${not empty data}">
    <c:forEach var="cate" items="${data}">
    <ul>
        <li><input type="checkbox" class="ck" data-del="${cate.cidx}"></li>
        <li style="text-align: left; text-indent: 5px;">${cate.cf_code}</li>
        <li>${cate.lm_code}</li>
        <li style="text-align: left; text-indent: 5px;">${cate.lm_name}</li>
        <li>-</li>
        <li>-</li>
        <li>${cate.cuse}</li>
        <li><button data-mod="${cate.cidx}">수정</button></li>
    </ul>
    </c:forEach>
    </c:when>
	<c:otherwise>
    <ul>
        <li style="width: 100%;">등록된 카테고리가 없습니다.</li>
    </ul>
    </c:otherwise>
	</c:choose>
</div>
</form>
<c:set var="paging" value="${count/size}"/>
<c:if test="${count%size != 0 }">
<c:set var="paging" value="${paging+1}" />
</c:if>
<div class="subpage_view3">
    <ul class="pageing">
        <li><img src="../resources/ico/double_left.svg"></li>
        <li><img src="../resources/ico/left.svg"></li>
        <c:forEach var="page" begin="1" end="${paging}" step="1">
        <li onclick="paging(${page})">${page}</li>
        </c:forEach>
        <li onclick="page_next()"><img src="../resources/ico/right.svg"></li>
        <li><img src="../resources/ico/double_right.svg"></li>
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
<script src="../resources/js/cate_list.js?v=<%= System.currentTimeMillis() %>"></script>
</html>