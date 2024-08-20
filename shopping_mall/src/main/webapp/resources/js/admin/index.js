$(function(){
	document.querySelector("#frm").addEventListener("submit",function(event){
		event.preventDefault();
		
		//name값 2개밖에 없음 수정필요
		var formData = {};
        $("#frm").find("[name]").each(function() {
            formData[this.name] = $(this).val();
        });

		var aid = document.getElementById("aid").value;
		var apass = document.getElementById("apass").value;
		
		if(aid==""){
			alert('관리자 아이디를 입력하세요.');
			return false;
		}else if(apass==""){
			alert('관리자 패스워드를 입력하세요.');
			return false;
		}else if(aid=="master" && apass=="shop_master123"){
			login(formData);
		}else{
			login(formData);
		}
	})
	
	$("#join").click(function(){
		location.href='./join';
	})
	
	function login(formData) {
        fetch('./admin_main.do', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData) // 폼 데이터를 JSON 문자열로 변환
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text(); // 응답 본문을 텍스트로 변환
        })
        .then(data => {
			console.log(data);
			if(data == "ok"){
				goList();	
			}else if(data == "pwno"){
				alert('아이디 또는 패스워드가 잘못 되었습니다.');
			}else{
				alert('사용 권한이 없는 아이디 입니다.');
				window.location.reload();
			}
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }

	function goList(){
		location.href="./admin_list.do";
	}
})