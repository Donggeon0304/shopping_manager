document.addEventListener("DOMContentLoaded",function(){
	document.querySelector("#frm1").addEventListener("submit",function(event){
		event.preventDefault();
		
		const id = frm1.id.value;
		const password = frm1.password.value;
		const idSave = frm1.idSave.checked ? 'Y' : 'N';
		if(id === ""){
			alert('아이디를 입력하세요.');
			return false;
		}else if(password === ""){
			alert('비밀번호를 입력하세요.');
			return false;
		}else{
			fetch('./user_login?id='+id+'&password='+password+'&idSave='+idSave,{
				method:'GET'
			})
			.then(response => response.text())
			.then(data => {
				if(data === "ok"){
					alert('로그인 되셨습니다.');
					location.href='login';
					//window.location.href='./main';
				}else if(data === "no"){
					alert('아이디 또는 비밀번호가 잘못되었습니다.');
				}else{
					alert('해당 고객님은 현재 계정이 정지된 상황 입니다. 고객센터에 문의하세요');
				}
			})
			.catch(error => {
				console.log(error);
				alert('아이디 또는 비밀번호가 잘못되었습니다.')
			})
		}
	})
})
function join(){
	location.href="./agree";
}