var ck ="";
var nl = document.getElementById("notice_list");
var pagehtml = document.getElementById("paging");
var a = "";
var ea = "";
var pageNumber = 1;
var page = getQueryParam("page") || 1;
var pageea = 1;

//URL의 파라미터값 리턴
function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

document.addEventListener("DOMContentLoaded",function(){
	
	document.querySelector(".border_add").addEventListener("click",()=>{
		location.href = './notice_write';
	})
	
	window.paging = function(page){
		notice(page);
	}
	window.detail = function(nidx){
		localStorage.setItem("page",getQueryParam("page"));
		location.href="./notice_view?nidx="+nidx;
	}
	
	//db 데이터 받기
	var size = 5; //출력 데이터 갯수
	function notice(page){
		fetch(`./notice_list_ajax?size=${size}&page=${page}`,{
			method:'GET'
		})
		.then(response => response.json())
		.then(data => {
			a = data[0]; //notice 데이터 리스트
			c = data[2]; //notice_file 데이터 리스트 
			d = data[3]; //notice_use == "Y" 데이터 리스트 
			ea = data[1]; //총 데이터 갯수
			pageNumber = Number(Math.ceil(ea/size)); //페이지 갯수
			
			if(lsea != ea){
				localStorage.setItem("ea",ea);
				var lsea = localStorage.getItem("ea");
			}
			pageea = lsea - ((page - 1) * size);
			
			//페이징 기존 요소 제외 나머지 요소 제거
			while (pagehtml.children.length > 4) {
			    pagehtml.removeChild(pagehtml.children[2]);
			}
			
			//카테고리
			nl.innerHTML = "";
			var ul2 = document.createElement("ul");
			ul2.innerHTML = `
		        <li><input type="checkbox" class="allck"></li>
		        <li>NO</li>
		        <li>제목</li>
		        <li>글쓴이</li>
		        <li>날짜</li>
		        <li>조회</li>
			`;
			nl.appendChild(ul2);
			
			if(ea == 0){				
				//페이징 번호
				var li = document.createElement("li");
				li.setAttribute("onclick", `paging(1)`);
				li.innerHTML = `<a style="color:red; font-weight: bold;">1</a>`;
				var second = pagehtml.children[2];
				pagehtml.insertBefore(li,second);
				
				//공지사항 리스트
				var ol = document.createElement("ol");
				ol.setAttribute("class","none_text");
				ol.innerHTML += `
				<li>등록된 공지 내용이 없습니다.</li>
				`;
				nl.appendChild(ol);	
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
				//공지사항 최상단
				for(var f=0; f<ea; f++){
					if(d[f].notice_use == "Y"){
						var ol = document.createElement("ol");
						ol.innerHTML += `
						<li><input type="checkbox" class="ck" data-del="${d[f].nidx}"></li>
				        <li><a style="color: black; font-size: 10px; font-weight: bold; background-color: white; padding: 3px; border-radius: 5px; border: 2px solid black; text-decoration: none;">필독공지</a></li>
				        <li onclick="detail(${d[f].nidx})">${d[f].notice_title}</li>
					    <li>${d[f].notice_writer}</li>
				        <li>${d[f].notice_date}</li>
				        <li>${d[f].notice_count}</li>
						`;
						nl.appendChild(ol);
					}
				}
				//공지사항 리스트
				for(var f=0; f<a.length; f++){
					var ol = document.createElement("ol");
					ol.innerHTML += `
					<li><input type="checkbox" class="ck" data-del="${a[f].nidx}"></li>
				       <li>${pageea}</li>
				       <li onclick="detail(${a[f].nidx})">${a[f].notice_title}</li>
					   <li>${a[f].notice_writer}</li>
				       <li>${a[f].notice_date}</li>
				       <li>${a[f].notice_count}</li>
					`;
					pageea--;
					nl.appendChild(ol);
				}
			}
			initializeEventListeners();
        	history.replaceState({}, '', location.pathname + `?page=${page}`);
		})
		.catch((error)=>{
			console.log(error);
		})
	}
	
	notice(page);
	
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
	
	//다음 페이지
	window.page_next = function(){
		var pageParam = (Number)(getQueryParam("page"));
		if(pageParam < pageNumber){
			pageParam = pageParam + 1;
			notice(pageParam);
		}
	}
	//이전 페이지
	window.page_previous = function(){
		var pageParam = (Number)(getQueryParam("page"));
		if(pageParam > 1){
			pageParam = pageParam - 1;	
			notice(pageParam);
		}
	}
	//처음 페이지
	window.page_first = function(){
		notice(1);
	}
	//마지막 페이지
	window.page_last = function(){
		notice(pageNumber);
	}
			
	//공지사항 삭제
	document.querySelector(".border_del").addEventListener("click",function(){
		var cked = false;
		ck.forEach(function(a){
			if(a.checked){
				cked = true;				
			}
		});
		if(cked){
			if(confirm('삭제시, 해당 공지사항 데이터는 복구 되지 않습니다.\n정말 삭제하시겠습니까?')){
				const delSelected = Array.from(document.querySelectorAll(".ck:checked"))
			    .map(checkbox => checkbox.getAttribute("data-del"));
				fetch('./notice_remove',{
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