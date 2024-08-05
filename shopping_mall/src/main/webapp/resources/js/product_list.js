document.addEventListener("DOMContentLoaded",function(){
	document.querySelector(".p_button_color1").addEventListener("click",()=>{
		location.href = './product_write.do';
	})
	document.querySelector(".p_button_color2").addEventListener("click",()=>{
		location.href = './cate_list.do';
	})
	
	var ck = document.querySelectorAll(".ck");
	var allck = document.querySelector(".allck");
	
	ck.forEach(function(a){
		a.addEventListener("click",function(){
			ckUpdate();
		})
	})
	
	function ckUpdate(){
		var ckd = true;
		ck.forEach(function(input){
			if(!input.checked){
				ckd = false;
			}
		})
		allck.checked = ckd;
	}
	
	allck.addEventListener("click",function(){
		var ckd = this.checked;
		ck.forEach(function(input){
			input.checked = ckd;
		})
	})
	
	document.querySelector("#del_button").addEventListener("click",function(){
		var cked = false;
		ck.forEach(function(a){
			if(a.checked){
				cked = true;				
			}
		})
		if(cked){
			if(confirm('삭제시 해당 데이터는 복구되지 않습니다.\n정말 삭제하시겠습니까?')){
				const delSelected = Array.from(document.querySelectorAll(".ck:checked"))
			    .map(checkbox => checkbox.getAttribute("data-del"));
			
				fetch('./product_del.do',{
					method:'POST',
					headers: {
						'content-Type' : 'application/json'
					},
					body : JSON.stringify(delSelected)
				})
				.then(response => response.text())
				.then(data => {
					console.log(data);
					if(data === "ok"){
						alert("삭제 성공하였습니다.")
						window.location.reload();
					}else{
						alert("삭제 실패하였습니다.")
					}
				})
				.catch(() =>{
					alert("삭제 실패하였습니다.")
				})
			}
		}else{
			alert("선택된 사항이 없습니다.");
		}
	})
	
	document.querySelector("#search").addEventListener("submit",function(event){
		event.preventDefault();
		const search_part = this.search_part.value;
		const search_word = this.search_word.value;
		if(search_word == ""){
			alert("검색어를 입력해 주세요");
		}else{
			location.href="./product_list.do?search_part="+search_part+"&search_word="+search_word;
		}
	})
	
})


