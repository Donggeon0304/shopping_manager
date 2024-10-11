function openPopup(url) {
            window.open(url, 'popupWindow', 'width=600,height=400,scrollbars=yes');
            return false;  // 링크의 기본 동작을 방지
        }
document.addEventListener("DOMContentLoaded",function(){
	document.querySelector(".border_del").addEventListener("click",()=>{
		var page = localStorage.getItem("page");
		location.href='./notice_list?page='+page;
	})
	document.querySelector(".border_add").addEventListener("click",()=>{
		const nidx = parseInt(document.getElementById("nidx").value);
		location.href=`./notice_modify?nidx=${nidx}`;
	})
	/*document.querySelector(".border_modify").addEventListener("click",function(){
		if(confirm('삭제하시면 복구가 되지 않습니다.\n정말 삭제하시겠습니까?')){
			var nidx = this.getAttribute("data-del");
			fetch("./notice_view_remove?nidx="+nidx,{
				method:'GET'
			})
			.then(response => response.text())
			.then(data => {
				if(data === "ok"){
					alert('삭제 되었습니다.');
					location.href='./notice_list';
				}else{
					alert('삭제에 실패하였습니다.');
				}
			})
			.catch(error => {
				console.log(error);
				alert('에러로 인하여 삭제에 실패하였습니다.');
			})			
		}
	})
	*/
})