document.addEventListener("DOMContentLoaded",function(){
	const frm = document.querySelector('form');
	const input2 = document.querySelector('.cate_input2');
	const input3 = document.querySelector('.cate_input3');
	const button1 = document.querySelector('.p_button_color1');
	const button2 = document.querySelector('.p_button_color2');
	
	button1.addEventListener("click",()=>{
		location.href = './cate_list.do';
	})
	button2.addEventListener("click",()=>{
		if(!input2.value.trim()){
			alert('대메뉴 코드를 입력하세요.');
		}else if(!input3.value.trim()){
			alert('대메뉴명을 입력하세요.');
		}else{
			frm.method='post',
			frm.action='./cate_add.do',
			frm.submit();			
		}
	})
})

$(function(){
	$(".cate_input2").on("keyup",function(){
		$(".cate_input1").val($(this).val());
	})
})