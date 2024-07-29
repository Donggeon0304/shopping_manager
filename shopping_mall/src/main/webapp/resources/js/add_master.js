$(document).ready(function(){
	var idck = false;
	$("#idck").click(function(){
		if($("#aid").val() == ""){
			alert("아이디를 입력하세요");
		}else if($("#aid").val() == "master"){
			alert("사용할 수 없는 아이디 입니다.");
		}else{
			$.ajax({
				url:'./admin_idck.do',
				type:'POST',
				data:{aid:$("#aid").val()},
				success:function(response){
					if(response == "ok"){
						console.log(response);
						idck = true;
					}else{
						console.log(response);
						alert("이미 사용중인 아이디 입니다.");
					}
				}
			})		
		}
	})
	
	$("#join").click(function(){
		var atel = document.getElementsByName("atel");
		
		if($("#aid").val() == ""){
			alert("아이디를 입력하세요");
		}else if(!idck){
			alert("아이디 중복체크 하세요");
		}else if($("#apass").val() == ""){
			alert("패스워드를 입력하세요");
		}else if($("#apass2").val() == ""){
			alert("패스워드 확인을 입력하세요");
		}else if($("#aname").val() == ""){
			alert("이름을 입력하세요");
		}else if($("#aemail").val() == ""){
			alert("이메일을 입력하세요");
		}else if(atel[0].value=="" || atel[1].value=="" || atel[2].value==""){
			alert("휴대폰번호를 입력하세요")
		}else if($("#ateam").val() == ""){
			alert("담당자 부서를 선택하세요");
		}else if($("#aposition").val() == ""){
			alert("담당자 직책을 선택하세요");
		}else{
			// 폼 데이터를 수집
            var formData = {};
            $("#frm").find("input[name]").each(function() {
                formData[this.name] = $(this).val();
            });
                
			// JSON 문자열로 변환
            var jsonData = JSON.stringify(formData);

			$.ajax({
				url:'./admin_join.do',
				type:'post',
				contentType: 'application/json',
                data: jsonData,
				dataType:'text',
				success:function(response){
					join();
				},
				error:function(error){
					console.log(error);
				}
			})		
		}
	})
})
function join(){
	alert("회원가입을 요청하였습니다.");
	location.href="./index.jsp";
}