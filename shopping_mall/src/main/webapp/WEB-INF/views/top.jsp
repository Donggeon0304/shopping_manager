<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	HttpSession hs = request.getSession();
	if(hs.getAttribute("admin")==null || hs.getAttribute("admin")==""){
		out.print("<script>alert('비정상적인 접근입니다.'); location.href='../admin/index';</script>");
	}
%>
<script src="../resources/js/jquery.js"></script>
<header class="headercss">
    <div class="header_div">
        <p><img src="../resources/img/logo.png" class="logo_sm"><a href="../admin/admin_list.do"> ADMINISTRATOR</a></p>
        <p><%=hs.getAttribute("admin") %> <a href="#">[개인정보 수정]</a> <a href="#" id="logout">[로그아웃]</a></p>
    </div>
</header>
<script>
$(()=>{
	$("#logout").click(function(){
		$.ajax({
			url:"/admin/admin_logout.do?ck=y",
			type:"GET",
			success:function(response){
				reload();
			},
			error:function(error){
				alert("알수없는 오류로 인하여 로그아웃에 실패하였습니다.");
			}
		})
	})
})
function reload(){
	location.href="../admin/index";
}
</script>