<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <title>Shop Bag</title>
    <meta charset="utf-8" />
    <link href="../resources/css/index.css" rel="stylesheet" />
    <link href="../resources/css/subpage.css" rel="stylesheet" />
    <link href="../resources/css/agree.css?v=1" rel="stylesheet" />
    <link href="../resources/css/join.css?v=1" rel="stylesheet" />
  </head>

  <body>
    <%@ include file="./topmenu.jsp" %>
 <main>
    <div class="products">
    <h3>MEMBER_JOIN</h3>
    <div class="sub_view">
   
    <div class="joinview">     
    <h3>회원가입</h3>
	<span class="join_info">
    <ol>
    <li>기본정보 </li>
    <li>※ 표시는 반드시 입력하셔야 하는 항목 입니다.</li>
    </ol>
    </span>
    <form id="frm">
    <ul class="join_ul">
    <li>※ 아이디</li>
    <li>
    <input type="text" class="join_in1" name="user_id"> <input type="button" value="중복확인" class="join_btn1" id="idcheck">
    </li>
    <li>※ 비밀번호</li>
    <li>
    <input type="password" class="join_in1 join_in2" name="user_password"> ※ 최소 6자 이상 입력하셔야 합니다.
    </li>
    <li>※ 비밀번호 확인</li>
    <li>
    <input type="password" class="join_in1 join_in2" name="user_password2"> ※ 동일한 패스워드를 입력하세요.
    </li>
    <li>※ 이름</li>
    <li>
    <input type="text" class="join_in1" name="user_name">
    </li>
    <li>※ 이메일</li>
    <li>
    <input type="text" class="join_in1" id="email" name="user_email"> <input type="button" id="send" value="이메일 인증" class="join_btn1"> ※ 입력하신 이메일을 확인해 주세요.
    </li>
    <li>※ 인증번호</li>
    <li>
    <input type="text" class="join_in1 join_in3" id="verification_code" maxlength="8"> ※ 8자리 인증번호를 입력하세요.
    </li>
    <li>※ 전화번호</li>
    <li>
    <input type="text" class="join_in1 join_in2" name="user_tel" maxlength="11"> ※ 숫자만 입력하세요
    </li>
    <li>※ 이벤트 메일 수신</li>
    <li>
    <label><input type="checkbox" class="join_ck1" name="event_email" value="Y"> 정보/이벤트 메일 수신에 동의합니다.</label>
    </li>
    <li>※ 이벤트 SMS 수신</li>
    <li>
    <label><input type="checkbox" class="join_ck1" name="event_sms" value="Y"> 정보/이벤트 SMS 수신에 동의합니다.</label>
    </li>
    </ul>
	</form>
    <div class="btn_center_box">
    <button type="button" id="btnNextStep" class="btn_join">회원가입</button>
    </div>
    </div>
    </div>
    </div>
</main>
<%@include file="./bottom.jsp" %>
  </body>
  <script src="../resources/js/member/join.js?v=<%= System.currentTimeMillis() %>"></script>
</html>