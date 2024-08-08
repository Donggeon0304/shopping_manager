document.addEventListener("DOMContentLoaded",function(){
	document.querySelector(".border_del").addEventListener("click",()=>{
		var page = localStorage.getItem("page");
		location.href='./notice_list?page='+page;
	})
})