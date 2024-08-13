document.addEventListener("DOMContentLoaded",function(){
	document.querySelectorAll(".new_addbtn1").forEach(function(btn){
		btn.addEventListener("click",function(){
			const uidx = btn.getAttribute("data-idx");
			const user_use = '휴면';
			fetch('./user_use?user_use='+user_use+'&uidx='+uidx,{
				method:"GET"
			})
			.then(response => response.text()
			)
			.then(data => {
				if(data === "ok"){
					alert('변경되었습니다.');
					window.location.reload();
				}
			})
			.catch(error => {
				console.log(error);
				alert('에러가 발생하였습니다.');
			})
		})
	})
	document.querySelectorAll(".new_addbtn2").forEach(function(btn){
		btn.addEventListener("click",function(){
			const uidx = btn.getAttribute("data-idx");
			const user_use = '정상';
			fetch('./user_use?user_use='+user_use+'&uidx='+uidx,{
				method:"GET"
			})
			.then(response => response.text()
			)
			.then(data => {
				if(data === "ok"){
					alert('변경되었습니다.');
					window.location.reload();
				}
			})
			.catch(error => {
				console.log(error);
				alert('에러가 발생하였습니다.');
			})
		})
	})
	
	
	
	
	document.querySelector("#terms_btn1").addEventListener("click",function(){
		var frm = document.forms['frm'];
		var formData = new FormData(frm);
		fetch("./use_terms",{
			method:"post",
			body:formData
		})
		.then(response => response.text())
		.then(data => {
			console.log(data);
			if(data === "ok"){
				alert("수정이 완료되었습니다.");
				window.location.reload();
			}else{
				alert("수정에 실패하였습니다.");
			}
		})
		.catch(error=>{
			console.log(error);
			alert("에러가 발생하였습니다.");
		})
	})
	document.querySelector("#terms_btn2").addEventListener("click",function(){
		var frm = document.forms['frm'];
		var formData = new FormData(frm);
		fetch("./personal_terms",{
			method:"post",
			body:formData
		})
		.then(response => response.text())
		.then(data => {
			if(data === "ok"){
				alert("수정이 완료되었습니다.");
				window.location.reload();
			}else{
				alert("수정에 실패하였습니다.");
			}
		})
		.catch(error=>{
			console.log(error);
			alert("에러가 발생하였습니다.");
		})
	})
})