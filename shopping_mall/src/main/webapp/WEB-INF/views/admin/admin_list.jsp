<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>쇼핑몰 관리자 리스트</title>
    <link rel="stylesheet" type="text/css" href="../resources/css/basic.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="../resources/css/main.css?v=1">
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
    <p>신규등록 관리자</p>
    <ol class="new_admin_title2">
        <li>NO</li>
        <li>관리자명</li>
        <li>아이디</li>
        <li>전화번호</li>
        <li>이메일</li>
        <li>담당부서</li>
        <li>담당직책</li>
        <li>가입일자</li>
        <li>승인여부</li>
    </ol>
    <c:if test="${result==null }">
    <ol class="new_admin_none">
        <li>신규 등록된 관리자가 없습니다.</li>
    </ol>
    </c:if>
    <c:forEach var="list" items="${result}" varStatus="status">
    <ol class="new_admin_lists2">
        <li>${status.index+1}</li>
        <li>${list.aname}</li>
        <li>${list.aid}</li>
        <li>${list.atel}</li>
        <li>${list.aemail}</li>
        <li>${list.ateam}</li>
        <li>${list.aposition}</li>
        <li>${list.ajoin_date}</li>
        <li>
        	<c:choose>
	        	<c:when test="${list.ause == 'N' }">
	            <input type="button" value="승인" class="new_addbtn1" title="승인" data-aidx="${list.aidx}">
	            </c:when>
	            <c:otherwise>
	            <input type="button" value="미승인" class="new_addbtn2" title="미승인" data-aidx="${list.aidx}">
	            </c:otherwise>
            </c:choose>
        </li>
    </ol>
    </c:forEach>
</section>
<section></section>
<section></section>
</main>
<%@include file="../copyright.jsp" %>
</body>
<script src="../resources/js/admin/admin_list.js?v=<%= System.currentTimeMillis() %>"></script>
</html>