function paging(page){
	window.location.href='./cate_list.do?page='+page;
}
function getQueryParam(param) {
    // 현재 URL의 쿼리 문자열을 파라미터로 설정
    const urlParams = new URLSearchParams(window.location.search);
    
    // 지정한 파라미터의 값을 반환
    return urlParams.get(param);
}
function page_next(){
	var pageParam = getQueryParam("page"); // 'page' 쿼리 파라미터의 값을 문자열로 가져옴
	var page = Number(pageParam); // 문자열을 숫자로 변환
	page = isNaN(page) ? 1 : page; // 변환이 실패할 경우 기본값 1을 설정
	page = page + 1; // 페이지 번호에 1을 추가
	
	window.location.href='./cate_list.do?page='+page;
}


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
		
		//카테고리 삭제
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
				
					fetch('./cate_del.do',{
						method:'POST',
						headers: {
							'content-Type' : 'application/json'
						},
						body : JSON.stringify(delSelected)
					})
					.then(response => response.text())
					.then(data => {
						if(data === "ok"){
							alert("삭제 성공하였습니다.")
							window.location.reload();
						}else if(data === "has"){
							alert("사용중인 카테고리 입니다.");
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
})