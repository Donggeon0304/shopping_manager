var ck ="";
var cl = document.getElementById("cate_list");
var pagehtml = document.getElementById("paging");
var a = "";
var ea = "";
var pageNumber = 1;
var part = (Number)(getQueryParam("part")) || 1;
var word = getQueryParam("word") || "";
var page = getQueryParam("page") || 1;

document.getElementById("search_word").value = word;

if(part == 1){
	document.getElementById("cate_name").selected = true;
}else{
	document.getElementById("cate_code").selected = true;
}

//URL의 파라미터값 리턴
function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

document.addEventListener("DOMContentLoaded",function(){
	
	document.querySelector(".p_button_color1").addEventListener("click",()=>{
		location.href = './product_list.do';
	})
	document.querySelector(".p_button_color2").addEventListener("click",()=>{
		location.href = './cate_write.do';
	})
	
	window.paging = function(page){
		categories(page,part,word);
	}
	
	//db 데이터 받기
	var size = 10; //출력 데이터 갯수
	function categories(page,part,word){
		fetch(`./cate_list_ajax?size=${size}&page=${page}&part=${part}&word=${word}`,{
			method:'GET'
		})
		.then(response => response.json())
		.then(data => {
			a = data[0]; //JSON 데이터 리스트
			ea = data[1]; //총 데이터 갯수
			pageNumber = Number(Math.ceil(ea/size)); //페이지 갯수
			
			document.getElementById("cate_ea").innerHTML = "등록된 카테고리 "+ea+"건";
			
			//페이징 기존 요소 제외 나머지 요소 제거
			while (pagehtml.children.length > 4) {
			    pagehtml.removeChild(pagehtml.children[2]);
			}
			
			//카테고리
			cl.innerHTML = "";
			var ul2 = document.createElement("ul");
			ul2.innerHTML = `
		        <li><input type="checkbox" class="allck"></li>
		        <li>분류코드</li>
		        <li>대메뉴 코드</li>
		        <li>대메뉴명</li>
		        <li>소메뉴 코드</li>
		        <li>소메뉴명</li>
		        <li>사용 유/무</li>
		        <li>비고</li>
			`;
			cl.appendChild(ul2);
			
			if(ea == 0){				
				//페이징 번호
				var li = document.createElement("li");
				li.setAttribute("onclick", `paging(1)`);
				li.innerHTML = `<a style="color:red; font-weight: bold;">1</a>`;
				var second = pagehtml.children[2];
				pagehtml.insertBefore(li,second);
				
				//카테고리 리스트
				var ul = document.createElement("ul");
				ul.innerHTML += `
				<li style="width: 100%;">등록된 카테고리가 없습니다.</li>
				`;
				cl.appendChild(ul);	
			}else{
				//페이징 번호
				for(var f=1; f<=pageNumber; f++){
					var li = document.createElement("li");
					li.setAttribute("onclick", `paging(${f})`);
					if(f == page){
						li.innerHTML = `<a style="color:red; font-weight: bold;">${f}</a>`;
					}else{
						li.innerHTML = f;
					}
					
					var second = pagehtml.children[f+1];
					pagehtml.insertBefore(li,second);
				}
				//카테고리 리스트
				for(var f=0; f<a.length; f++){
					var ul = document.createElement("ul");
					ul.innerHTML += `
					<li><input type="checkbox" class="ck" data-del="${a[f].cidx}"></li>
			        <li style="text-align: left; text-indent: 5px;">${a[f].cf_code}</li>
				    <li>${a[f].lm_code}</li>
			        <li style="text-align: left; text-indent: 5px;">${a[f].lm_name}</li>
			        <li>${a[f].sm_code}</li>
			        <li style="text-align: left; text-indent: 5px;">${a[f].sm_name}</li>
			        <li>${a[f].cuse}</li>
			        <li>-</li>
					`;
					cl.appendChild(ul);
					//<li><button data-mod="${a[f].cidx}">수정</button></li>
				}
			}
			initializeEventListeners();
        	history.replaceState({}, '', location.pathname + `?page=${page}&part=${part}&word=${word}`);
		})
		.catch((error)=>{
			console.log(error);
		})
	}
	
	categories(page,part,word);
	
	//fetch작동 후 이벤트 리스너 실행
	function initializeEventListeners(){
		var all = document.querySelector(".allck");
		ck = document.querySelectorAll(".ck");	
		
		//체크박스 선택
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
	}
	
	document.querySelector("#search").addEventListener("submit",function(event){
		event.preventDefault();
		part = this.search_part.value;
		word = this.search_word.value;
		if(word == ""){
			categories(1,part,word);
		}else{
			categories(1,part,word);
		}
	})
	
	//다음 페이지
	window.page_next = function(){
		var pageParam = (Number)(getQueryParam("page"));
		if(pageParam < pageNumber){
			pageParam = pageParam + 1;
			categories(pageParam,part,word);
		}
	}
	//이전 페이지
	window.page_previous = function(){
		var pageParam = (Number)(getQueryParam("page"));
		if(pageParam > 1){
			pageParam = pageParam - 1;	
			categories(pageParam,part,word);
		}
	}
	//처음 페이지
	window.page_first = function(){
		categories(1,part,word);
	}
	//마지막 페이지
	window.page_last = function(){
		categories(pageNumber,part,word);
	}
			
	//카테고리 삭제
	document.querySelector("#del_button").addEventListener("click",function(){
		var cked = false;
		ck.forEach(function(a){
			if(a.checked){
				cked = true;				
			}
		});
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
	});
});