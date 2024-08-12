document.addEventListener("DOMContentLoaded", function() {
	let idCheck = false;
	document.querySelector("#send").addEventListener("click",function(){
		const email = document.getElementById("email").value;
		if(email === ""){
			alert("이메일을 입력하세요.");
		}else{
			fetch("./send-code?email="+email,{
				method:"GET"
			})
			.then(response => response.text())
			.then(data => {
				if(data === "ok"){
					alert("인증번호가 발송 되었습니다.");
				}else{
					alert("잘못된 이메일주소 입니다.");				
				}
			})
			.catch(error => {
				console.log(error);
			})
		}
	})
	
	document.querySelector("#idcheck").addEventListener("click",()=>{
		const id = frm.user_id.value;
		const idPossible = /^[a-zA-Z0-9]+$/;
		if(id === ""){
			alert('아이디를 입력하세요.');
		}else if(!idPossible.test(id)){
			alert("아이디는 영어와 숫자만 가능합니다.");
		}else{
			fetch("./check-id?id="+id,{
				method:"GET"
			})
			.then(response => response.text())
			.then(data => {
				if(data === "ok"){
					idCheck = true;
					alert("사용할 수 있는 아이디 입니다.");
				}else{
					alert("이미 사용중인 아이디 입니다.");				
				}
			})
			.catch(error => {
				console.log(error);
			})
		}
	})
	
	document.querySelector("#btnNextStep").addEventListener("click", () => {
		const code = document.getElementById("verification_code").value;
		if(idCheck === false){
			alert("아이디 중복체크를 하세요.");
		}else if(frm.user_password.value === ""){
			alert("비밀번호를 입력하세요.");
		}else if(frm.user_password.value.length < 6){
			alert("비밀번호는 최소 6자 입니다.");
		}else if(frm.user_password.value !== frm.user_password2.value){
			alert("비밀번호가 다릅니다.");
		}else if(frm.user_name.value === ""){
			alert("이름을 입력하세요.");
		}else if(code === ""){
			alert("인증번호를 입력하세요.");
		}else if(frm.user_tel.value === ""){
			alert("전화번호를 입력하세요.");
		}else if(isNaN(frm.user_tel.value)){
			alert("전화번호는 숫자만 입력하세요.");
		}else{
			let formData = new FormData(frm);
			formData.set('code',code);
			
			fetch("./join",{
				method:"POST",
				body: formData
			})
			.then(response => response.text())
			.then(data => {
				if(data === "ok"){
					alert("회원가입이 완료되었습니다.");
					window.location.href="./login";
				}else if(data === "no"){
					alert("인증번호가 다릅니다.");				
				}else{
					alert("회원가입에 실패하였습니다.");
				}
			})
			.catch(error => {
				console.log(error);
			})
		}
    });
});