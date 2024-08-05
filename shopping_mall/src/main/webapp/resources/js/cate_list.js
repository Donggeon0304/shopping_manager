document.addEventListener("DOMContentLoaded",function(){
	document.querySelector(".p_button_color1").addEventListener("click",()=>{
		location.href = './product_list.do';
	})
	document.querySelector(".p_button_color2").addEventListener("click",()=>{
		location.href = './cate_write.do';
	})
	
	//체크박스 선택
	var all = document.querySelector(".allck");
	var ck = document.querySelectorAll(".ck");
	
	all.addEventListener("click",function(){
		var allck = this.checked;
		ck.forEach(function(a){
			a.checked = allck;
		})
	})
	
	ck.forEach(function(a){
		a.addEventListener("click", function() {
            ckUpdate();
        });
	})

	function ckUpdate(){
		var allck = true;
		ck.forEach(function(input){
			if(!input.checked){
				allck = false;
			}
		})
		all.checked = allck;
	}
	
	document.querySelector("#del_button").addEventListener("click",function(){
		var cked = false;
		ck.forEach(function(a){
			if(a.checked){
				cked = true;				
			}
		})
		if(cked){
			if(confirm('정말 삭제하시겠습니까?')){
				const delSelected = Array.from(document.querySelectorAll(".ck:checked"))
			    .map(checkbox => checkbox.getAttribute("data-del"));
			
				fetch('./cate_del.do',{
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
						alert("삭제 성공하였습니다.")
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
})