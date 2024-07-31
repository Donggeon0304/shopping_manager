<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navcss">
    <div class="nav_div">
        <ol>
        	<%if(hs.getAttribute("admin") !=null && hs.getAttribute("admin").equals("최고 관리자")){ %>
            <li title="쇼핑몰 관리자 리스트" onclick="list()">쇼핑몰 관리자 리스트</li>
            <%}%>
            <li title="쇼핑몰 회원관리">쇼핑몰 회원관리</li>
            <li title="쇼핑몰 상품관리" onclick="product()">쇼핑몰 상품관리</li>
            <li title="쇼핑몰 기본설정" onclick="setting()">쇼핑몰 기본설정</li>
        </ol>
    </div>
</nav>
<script>
function list(){
	location.href="../admin/admin_list.do";
}
function product(){
	location.href="../product/product_list.do";
}
function setting(){
	location.href="../admin/siteinfo.do";
}
</script>