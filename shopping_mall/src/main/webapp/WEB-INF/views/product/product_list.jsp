<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품관리 페이지</title>
    <link rel="stylesheet" type="text/css" href="../resources/css/basic.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="../resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/product.css?v=5">
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
<p>상품관리 페이지</p>
<div class="subpage_view">
    <span>등록된 상품 0건</span>
    <span>
        <form>
        <select class="p_select1">
            <option>상품명</option>
            <option>상품코드</option>
        </select>
        <input type="text" class="p_input1" placeholder="검색어를 입력해 주세요">
        <input type="submit" value="검색" title="상품검색" class="p_submit">
        </form>
    </span>
</div>
<div class="subpage_view2">
    <ul>
        <li><input type="checkbox"></li>
        <li>코드</li>
        <li>이미지</li>
        <li>상품명</li>
        <li>카테고리 분류</li>
        <li>판매가격</li>
        <li>할인가격</li>
        <li>할인율</li>
        <li>재고현황</li>
        <li>판매유/무</li>
        <li>품절</li>
        <li>관리</li>
    </ul>
   	<c:choose>
	   	<c:when test="${not empty products}">
		   	<c:forEach var="product" items="${products}" varStatus="productStatus"> 
		   		<c:set var="file" value="${files[productStatus.index]}"/>
			    <ul>
			        <li><input type="checkbox"></li>
			        <li>${product.pcode}</li>
			        <li><img src="${file.mfile_url}" title="${file.mfile_name}" width="100"></li>
			        <li>${product.pname}</li>
			        <li>${product.cate}</li>
			        <li>${product.price}</li>
			        <li>${product.pdc_price}</li>
			        <li>${product.pdc_per}%</li>
			        <li>${product.pstock}</li>
			        <li>${product.puse}</li>
			        <li>${product.psold_out}</li>
			        <li>관리</li>
			    </ul>
		    </c:forEach>
	    </c:when>
	    <c:otherwise>
		    <ul>
		        <li style="width: 100%;">등록된 상품이 없습니다.</li>
		    </ul>
	    </c:otherwise>
    </c:choose>
</div>
<div class="subpage_view3">
    <ul class="pageing">
        <li><img src="../resources/ico/double_left.svg"></li>
        <li><img src="../resources/ico/left.svg"></li>
        <li>1</li>
        <li><img src="../resources/ico/right.svg"></li>
        <li><img src="../resources/ico/double_right.svg"></li>
    </ul>
</div>
<div class="subpage_view4">
    <input type="button" value="선택상품 삭제" title="선택상품 삭제" class="p_button">
    <span style="float: right;">
    <input type="button" value="신규상품 등록" title="신규상품 등록" class="p_button p_button_color1">
    <input type="button" value="카테고리 등록" title="카테고리 등록" class="p_button p_button_color2">
    </span>
</div>
</section>
</main>
<%@include file="../copyright.jsp" %>
</body>
<script src="../resources/js/product_list.js?v=<%= System.currentTimeMillis() %>"></script>
</html>