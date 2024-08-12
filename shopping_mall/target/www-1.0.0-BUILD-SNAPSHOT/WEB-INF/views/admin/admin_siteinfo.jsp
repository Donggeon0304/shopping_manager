<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>쇼핑몰 기본설정</title>
    <script src="../resources/js/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="../resources/css/siteinfo/basic.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/siteinfo/login.css?v=2">
    <link rel="stylesheet" type="text/css" href="../resources/css/siteinfo/main.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/siteinfo/subpage.css?v=6">
    <link rel="icon" href="../resources/img/logo.png" sizes="128x128">
    <link rel="icon" href="../resources/img/logo.png" sizes="64x64">
    <link rel="icon" href="../resources/img/logo.png" sizes="32x32">
    <link rel="icon" href="../resources/img/logo.png" sizes="16x16">
</head>
<body>
<%@ include file="../top.jsp" %>
<%@ include file="../Qmenu.jsp" %>
<form id="frm">
    <main class="maincss">
        <section>
            <p>홈페이지 가입환경 설정</p>
            <div class="subpage_view">
                <ul class="info_form">
                    <li>홈페이지 제목</li>
                    <li>
                        <input type="text" name="siteinfo_dto.page_name" value="${site.page_name != null ? site.page_name : '' }" class="in_form1"> 
                    </li>
                </ul>    
                <ul class="info_form">
                    <li>관리자 메일 주소</li>
                    <li>
                        <input type="text" name="siteinfo_dto.admin_email" value="${site.admin_email != null ? site.admin_email : '' }" class="in_form2"> 
                        ※ 관리자가 보내고 받는 용도로 사용하는 메일 주소를 입력합니다.(회원가입,인증메일,회원메일발송 등에서 사용)
                    </li>
                </ul> 
                <ul class="info_form">
                    <li>포인트 사용 유/무</li>
                    <li class="checkcss">
                        <em><label><input type="radio" name="siteinfo_dto.point_use" value="Y" class="ckclass" ${site.point_use == 'Y' ? 'checked' : ''}>포인트 사용</label></em> 
                        <em><label><input type="radio" name="siteinfo_dto.point_use" value="N" class="ckclass" ${site.point_use == 'N' ? 'checked' : ''}>포인트 미사용</label></em>
                    </li>
                </ul>
                <ul class="info_form2" style="border-bottom:1px solid rgb(81, 61, 61);">
                    <li>회원가입시 적립금</li>
                    <li>
                        <input type="text" name="siteinfo_dto.join_point" class="in_form3" maxlength="5" value="${site.join_point != null ? site.join_point : '' }"> 점
                    </li>
                    <li>회원가입시 권한레벨</li>
                    <li>
                        <input type="text" name="siteinfo_dto.join_level" class="in_form3" maxlength="1" value="${site.join_level != null ? site.join_level : '' }"> 레벨
                    </li>
                </ul>
            </div>
            <p>홈페이지 기본환경 설정</p>
            <div class="subpage_view">
                <ul class="info_form2">
                    <li>회사명</li>
                    <li>
                        <input type="text" name="siteinfo_dto.com_name" class="in_form0" value="${site.com_name != null ? site.com_name : '' }"> 
                    </li>
                    <li>사업자등록번호</li>
                    <li>
                        <input type="text" name="siteinfo_dto.business_num" class="in_form0" value="${site.business_num != null ? site.business_num : '' }"> 
                    </li>
                </ul> 
                <ul class="info_form2">
                    <li>대표자명</li>
                    <li>
                        <input type="text" name="siteinfo_dto.ceo_name" class="in_form0" value="${site.ceo_name != null ? site.ceo_name : '' }"> 
                    </li>
                    <li>대표전화번호</li>
                    <li>
                        <input type="text" name="siteinfo_dto.ceo_tel" class="in_form0" value="${site.ceo_tel != null ? site.ceo_tel : '' }"> 
                    </li>
                </ul>
                <ul class="info_form2">
                    <li>통신판매업 신고번호</li>
                    <li>
                        <input type="text" name="siteinfo_dto.mob_num" class="in_form0" value="${site.mob_num != null ? site.mob_num : '' }"> 
                    </li>
                    <li>부가통신 사업자번호</li>
                    <li>
                        <input type="text" name="siteinfo_dto.vat_num" class="in_form0" value="${site.vat_num != null ? site.vat_num : '' }"> 
                    </li>
                </ul>
                <ul class="info_form2">
                    <li>사업장 우편번호</li>
                    <li>
                        <input type="text" name="siteinfo_dto.com_post" class="in_form0" value="${site.com_post != null ? site.com_post : '' }"> 
                    </li>
                    <li>사업장 주소</li>
                    <li>
                        <input type="text" name="siteinfo_dto.com_address" class="in_form2" value="${site.com_address != null ? site.com_address : '' }"> 
                    </li>
                </ul>
                <ul class="info_form2" style="border-bottom:1px solid rgb(81, 61, 61);">
                    <li>정보관리책임자명</li>
                    <li>
                        <input type="text" name="siteinfo_dto.info_name" class="in_form0" value="${site.info_name != null ? site.info_name : '' }"> 
                    </li>
                    <li>정보책임자 E-mail</li>
                    <li>
                        <input type="text" name="siteinfo_dto.info_email" class="in_form1" value="${site.info_email != null ? site.info_email : '' }"> 
                    </li>
                </ul>
            </div>
            <p>결제 기본환경 설정</p>
            <div class="subpage_view">
                <ul class="info_form2">
                    <li>무통장 은행</li>
                    <li>
                        <input type="text" id="pay_dto.bank" name="pay_dto.bank" class="in_form0" value="${pay.bank != null ? pay.bank : '' }"> 
                    </li>
                    <li>은행계좌번호</li>
                    <li>
                        <input type="text" id="pay_dto.account_num" name="pay_dto.account_num" class="in_form1" value="${pay.account_num != null ? pay.account_num : '' }"> 
                    </li>
                </ul>
                <ul class="info_form">
                    <li>신용카드 결제 사용</li>
                    <li class="checkcss">
                        <em><label><input type="radio" name="pay_dto.card_use" value="Y" class="ckclass" ${pay.card_use == 'Y' ? 'checked' : ''}> 사용</label></em> 
                        <em><label><input type="radio" name="pay_dto.card_use" value="N" class="ckclass" ${pay.card_use == 'N' ? 'checked' : ''}> 미사용</label></em> 
                        ※ 해당 PG사가 있을 경우 사용으로 변경합니다.
                    </li>
                </ul>
                <ul class="info_form">
                    <li>휴대폰 결제 사용</li>
                    <li class="checkcss">
                        <em><label><input type="radio" name="pay_dto.phone_use" value="Y" class="ckclass" ${pay.phone_use == 'Y' ? 'checked' : ''}> 사용</label></em> 
                        <em><label><input type="radio" name="pay_dto.phone_use" value="N" class="ckclass" ${pay.phone_use == 'N' ? 'checked' : ''}> 미사용</label></em> 
                        ※ 주문시 휴대폰 결제를 가능하게 할 것인지를 설정합니다.
                    </li>
                </ul>
                <ul class="info_form">
                    <li>도서상품권 결제사용</li>
                    <li class="checkcss">
                        <em><label><input type="radio" name="pay_dto.giftcard_use" value="Y" class="ckclass" ${pay.giftcard_use == 'Y' ? 'checked' : ''}> 사용</label></em> 
                        <em><label><input type="radio" name="pay_dto.giftcard_use" value="N" class="ckclass" ${pay.giftcard_use == 'N' ? 'checked' : ''}> 미사용</label></em> 
                        ※ 도서상품권 결제만 적용이 되며, 그 외에 상품권은 결제 되지 않습니다.
                    </li>
                </ul>
                <ul class="info_form2">
                    <li>결제 최소 포인트</li>
                    <li>
                        <input type="text" id="pay_dto.min_point" name="pay_dto.min_point" class="in_form0" maxlength="5" value="${pay.min_point != null ? pay.min_point : '' }"> 점
                    </li>
                    <li>결제 최대 포인트</li>
                    <li>
                        <input type="text" name="pay_dto.max_point" class="in_form0" maxlength="5" value="${pay.max_point != null ? pay.max_point : '' }"> 점
                    </li>
                </ul>
                <ul class="info_form">
                    <li>현금 영수증 발급사용</li>
                    <li class="checkcss">
                        <em><label><input type="radio" name="pay_dto.cash_receipt" value="Y" class="ckclass" ${pay.cash_receipt == 'Y' ? 'checked' : ''}> 사용</label></em> 
                        <em><label><input type="radio" name="pay_dto.cash_receipt" value="N" class="ckclass" ${pay.cash_receipt == 'N' ? 'checked' : ''}> 미사용</label></em> 
                        ※ 현금영수증 관련 사항은 PG사 가입이 되었을 경우 사용가능 합니다.
                    </li>
                </ul>
                <ul class="info_form2">
                    <li>배송업체명</li>
                    <li>
                        <input type="text" name="pay_dto.deli_name" class="in_form0" value="${pay.deli_name != null ? pay.deli_name : '' }"> 
                    </li>
                    <li>배송비 가격</li>
                    <li>
                        <input type="text" name="pay_dto.deli_price" class="in_form0" maxlength="7" value="${pay.deli_price != null ? pay.deli_price : '' }"> 원
                    </li>
                </ul>
                <ul class="info_form" style="border-bottom:1px solid rgb(81, 61, 61);">
                    <li>희망배송일</li>
                    <li class="checkcss">
                        <em><label><input type="radio" name="pay_dto.deli_date" value="Y" class="ckclass" ${pay.deli_date == 'Y' ? 'checked' : ''}> 사용</label></em> 
                        <em><label><input type="radio" name="pay_dto.deli_date" value="N" class="ckclass" ${pay.deli_date == 'N' ? 'checked' : ''}> 미사용</label></em> 
                        ※ 희망배송일 사용시 사용자가 직접 설정 할 수 있습니다.
                    </li>
                </ul>
            </div>
            <div class="btn_div">
                <button type="button" class="sub_btn1" title="설정 저장">설정 저장</button>
                <button type="button" class="sub_btn2" title="저장 취소">저장 취소</button>
            </div>
        </section>
        <section></section>
        <section></section>
    </main>
</form>

<%@include file="../copyright.jsp" %>
</body>
<script src="../resources/js/admin/admin_siteinfo.js?v=<%= System.currentTimeMillis() %>"></script>
</html>