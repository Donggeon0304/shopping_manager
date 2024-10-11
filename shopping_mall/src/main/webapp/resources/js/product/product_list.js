var ck = "";
var pl = document.getElementById("product_list");
var pagehtml = document.getElementById("paging");
var a = "";
var ea = "";
var c = "";
var pageNumber = 1;
var part = (Number)(getQueryParam("part")) || 1;
var word = getQueryParam("word") || "";
var page = getQueryParam("page") || 1;

document.getElementById("search_word").value = word;

if (part == 1) {
	document.getElementById("product_name").selected = true;
} else if (part == 2) {
	document.getElementById("product_code").selected = true;
} else {
	document.getElementById("product_cate").selected = true;
}

//URL의 파라미터값 리턴
function getQueryParam(param) {
	const urlParams = new URLSearchParams(window.location.search);
	return urlParams.get(param);
}

document.addEventListener("DOMContentLoaded", function() {
	document.querySelector(".p_button_color1").addEventListener("click", () => {
		location.href = './product_write.do';
	})
	document.querySelector(".p_button_color2").addEventListener("click", () => {
		location.href = './cate_write.do';
	})

	window.paging = function(page) {
		products(page, part, word);
	}
	window.openPopup = function(url) {
		window.open(url, 'popupWindow', 'width=600,height=400,scrollbars=yes');
		return false
	}
	window.modify = function(idx) {
		alert('수정은 아직 미구현 기능입니다.')
		return false;
	}

	//db 데이터 받기
	var size = 10; //출력 데이터 갯수
	function products(page, part, word) {
		fetch(`./product_list_ajax?size=${size}&page=${page}&part=${part}&word=${word}`, {
			method: 'GET'
		})
			.then(response => response.json())
			.then(data => {
				a = data[0]; //products 데이터 리스트
				c = data[2]; //product_file 데이터 리스트
				ea = data[1]; //총 데이터 갯수
				pageNumber = Number(Math.ceil(ea / size)); //페이지 갯수
				document.getElementById("product_ea").innerHTML = "등록된 상품 " + ea + "건";

				//페이징 기존 요소 제외 나머지 요소 제거
				while (pagehtml.children.length > 4) {
					pagehtml.removeChild(pagehtml.children[2]);
				}

				//상품
				pl.innerHTML = "";
				var ul2 = document.createElement("ul");
				ul2.innerHTML = `
		        <li><input type="checkbox" name="allck" class="allck"></li>
		        <li>코드</li>
		        <li>이미지</li>
		        <li>상품명</li>
		        <li>카테고리 분류</li>
		        <li>판매가격</li>
		        <li>할인가격</li>
		        <li>할인율</li>
		        <li>재고현황</li>
		        <li>판매유/무</li>
		        <li>품절</li>
		        <li>비고</li>
			`;
				pl.appendChild(ul2);

				if (ea == 0) {
					//페이징 번호
					var li = document.createElement("li");
					li.setAttribute("onclick", `paging(1)`);
					li.innerHTML = `<a style="color:red; font-weight: bold;">1</a>`;
					var second = pagehtml.children[2];
					pagehtml.insertBefore(li, second);

					//상품 리스트
					var ul = document.createElement("ul");
					ul.innerHTML += `
				<li style="width: 100%;">등록된 카테고리가 없습니다.</li>
				`;
					pl.appendChild(ul);
				} else {
					//페이징 번호
					for (var f = 1; f <= pageNumber; f++) {
						var li = document.createElement("li");
						li.setAttribute("onclick", `paging(${f})`);
						if (f == page) {
							li.innerHTML = `<a style="color:red; font-weight: bold;">${f}</a>`;
						} else {
							li.innerHTML = f;
						}

						var second = pagehtml.children[f + 1];
						pagehtml.insertBefore(li, second);
					}
					//상품 리스트
					for (var f = 0; f < a.length; f++) {
						var files = c[f];
						var ul = document.createElement("ul");
						ul.innerHTML += `
						<li><input type="checkbox" class="ck" name="ck" data-del="${a[f].pidx}"></li>
				        <li>${a[f].pcode}</li>
						`
						let files_li = ``;
						if (c[f]) {
							// file1_url이 존재하는 경우
							if (c[f].file1_url) {
								files_li += `
						                    <a href="${c[f].file1_url}" target="_blank" style="text-decoration: none; color: blue; font-weight: bold;" onclick="return openPopup(this.href);" title="${c[f].file1_name}">
						                        ,파일2
						                    </a>
						            `;
							}

							// file2_url이 존재하는 경우
							if (c[f].file2_url) {
								files_li += `
						                    <a href="${c[f].file2_url}" target="_blank" style="text-decoration: none; color: blue; font-weight: bold;" onclick="return openPopup(this.href);" title="${c[f].file2_name}">
						                        ,파일3
						                    </a>
						            `;
							}
						}
						ul.innerHTML += `	
						<li>${files ? `<a href="${c[f].mfile_url}" target="_blank" style="text-decoration: none; color: blue; font-weight: bold;" onclick="return openPopup(this.href)"; title="${c[f].mfile_name}";>첨부파일</a>` : `없음`}
							${files_li}
						</li>
						`
						ul.innerHTML += `
				        <li>${a[f].pname}</li>
				        <li>${a[f].cate}</li>
				        <li>${a[f].price}</li>
				        <li>${a[f].pdc_price}</li>
				        <li>${a[f].pdc_per}%</li>
				        <li>${a[f].pstock}</li>
				        <li>${a[f].puse}</li>
				        <li>${a[f].psold_out}</li>
				        <li>-</li>
						`;
						pl.appendChild(ul);
					}
					//<li><a style="color:brown;" onclick="modify(${a[f].pidx})">수정</a></li>
				}
				initializeEventListeners();
				history.replaceState({}, '', location.pathname + `?page=${page}&part=${part}&word=${word}`);
			})
			.catch((error) => {
				console.log(error);
			})
	}

	products(page, part, word);

	//fetch작동 후 이벤트 리스너 실행
	function initializeEventListeners() {
		var all = document.querySelector(".allck");
		ck = document.querySelectorAll(".ck");

		//체크박스 선택
		all.addEventListener("click", function() {
			var allck = this.checked;
			ck.forEach(function(a) {
				a.checked = allck;
			})
		})

		ck.forEach(function(a) {
			a.addEventListener("click", function() {
				ckUpdate();
			});
		})

		function ckUpdate() {
			var allck = true;
			ck.forEach(function(input) {
				if (!input.checked) {
					allck = false;
				}
			})
			all.checked = allck;
		}
	}


	//검색
	document.querySelector("#search").addEventListener("submit", function(event) {
		event.preventDefault();
		part = this.search_part.value;
		word = this.search_word.value;
		products(1, part, word);
	})
	//다음 페이지
	window.page_next = function() {
		var pageParam = (Number)(getQueryParam("page"));
		if (pageParam < pageNumber) {
			pageParam = pageParam + 1;
			products(pageParam, part, word);
		}
	}
	//이전 페이지
	window.page_previous = function() {
		var pageParam = (Number)(getQueryParam("page"));
		if (pageParam > 1) {
			pageParam = pageParam - 1;
			products(pageParam, part, word);
		}
	}
	//처음 페이지
	window.page_first = function() {
		products(1, part, word);
	}
	//마지막 페이지
	window.page_last = function() {
		products(pageNumber, part, word);
	}

	//카테고리 삭제
	document.querySelector("#del_button").addEventListener("click", function() {
		var cked = false;
		ck.forEach(function(a) {
			if (a.checked) {
				cked = true;
			}
		});
		if (cked) {
			if (confirm('삭제시 해당 데이터는 복구되지 않습니다.\n정말 삭제하시겠습니까?')) {
				const delSelected = Array.from(document.querySelectorAll(".ck:checked"))
					.map(checkbox => checkbox.getAttribute("data-del"));

				fetch('./product_del.do', {
					method: 'POST',
					headers: {
						'content-Type': 'application/json'
					},
					body: JSON.stringify(delSelected)
				})
					.then(response => response.text())
					.then(data => {
						if (data === "ok") {
							alert("삭제 성공하였습니다.")
							window.location.reload();
						} else {
							alert("삭제 실패하였습니다.")
						}
					})
					.catch(() => {
						alert("삭제 실패하였습니다.")
					})
			}
		} else {
			alert("선택된 사항이 없습니다.");
		}
	});
});