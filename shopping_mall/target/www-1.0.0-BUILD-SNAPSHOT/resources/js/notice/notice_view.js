function openPopup(url) {
            window.open(url, 'popupWindow', 'width=600,height=400,scrollbars=yes');
            return false;  // 링크의 기본 동작을 방지
        }
document.addEventListener("DOMContentLoaded",function(){
	document.querySelector(".border_del").addEventListener("click",()=>{
		var page = localStorage.getItem("page");
		location.href='./notice_list?page='+page;
	})
})