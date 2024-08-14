document.addEventListener("DOMContentLoaded",function(){
	const frm = document.querySelector('form');
	const button1 = document.querySelector('.p_button_color1');
	const button2 = document.querySelector('.p_button_color2');
	
	button1.addEventListener("click",()=>{
		location.href = './cate_list.do';
	})
	button2.addEventListener("click",()=>{
		const cf_code = document.querySelector('.cate_input1').value.trim();
		const lm_code = document.querySelector('.cate_input2').value.trim();
		const lm_name = document.querySelector('.cate_input3').value.trim();
		const checked = document.getElementById("onlyLmCk").checked;
		if(!cf_code){
			alert('분류 코드를 입력하세요.');
		}else if(isNaN(cf_code)){
			alert('분류 코드는 숫자만 입력 가능합니다.');
		}else if(cf_code.length < 2){
			alert('분류 코드는 2자리 이상부터 가능합니다.');
		}else if(!lm_code){
			alert('대메뉴 코드를 입력하세요.');
		}else if(isNaN(lm_code)){
			alert('대메뉴 코드는 숫자만 입력 가능합니다.');
		}else if(lm_code.length < 2){
			alert('대메뉴 코드는 2자리 이상부터 가능합니다.');
		}else if(!lm_name){
			alert('대메뉴명을 입력하세요.');
		}else if(!checked){
			if(sm_code.value == ""){
				alert('소메뉴 코드를 입력하세요.')
			}else if(sm_code.value.length < 2){
				alert('소메뉴 코드는 2자리 이상부터 가능합니다.');
			}else if(sm_name.value == ""){
				alert('소메뉴명을 입력하세요.');
			}else{
				frm.method='post';
				frm.action='./cate_add.do';
				frm.submit();
			}
		}else{
			frm.method='post';
			frm.action='./cate_add.do';
			frm.submit();
		}
	})
	var cf_code = document.getElementById("cf_code");
	var lm_code = document.getElementById("lm_code");
	var sm_code = document.getElementById("sm_code");
	var sm_name = document.getElementById("sm_name");
	document.querySelectorAll('.cate_input2').forEach(function(input){
		input.addEventListener("keyup",function(){
			let cfCode = "";
			cfCode += lm_code.value + sm_code.value;
			cf_code.value = cfCode;
			
		})
	})
	document.querySelector("#onlyLmCk").addEventListener("click",function(){
		const checked = this.checked;
		if(checked){
			sm_code.disabled = true;
			sm_name.disabled = true;
			sm_code.value = "";
			sm_name.value = "";
			cf_code.value = lm_code.value
		}else{
			sm_code.disabled = false;
			sm_name.disabled = false;
		}
	})
})