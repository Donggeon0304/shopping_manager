document.querySelector("#frm").addEventListener("submit",function(){
	event.preventDefault();
	
	var aid = document.getElementById("aid").value;
	var apass = document.getElementById("apass").value;
	if(aid==""){
		alert('관리자 아이디를 입력하세요.');
		return false;
	}else if(apass==""){
		alert('관리자 패스워드를 입력하세요.');
		return false;
	}else if(aid=="master" && apass=="shop_master123"){
		frm.method="post";
		frm.action="./admin_main.do";
		frm.submit();
	}else{
		frm.method="post";
		frm.action="./admin_main.do";
		//frm.submit();
	}
})

$(function(){
	$("#join").click(function(){
		location.href='./add_master.jsp';
	})
})