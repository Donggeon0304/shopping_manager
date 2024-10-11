document.addEventListener("DOMContentLoaded",function(){
	document.querySelector(".border_del").addEventListener("click",()=>{
		location.href='./notice_list';
	})
	
	//파일 크기 계산
	function sizeCheck(event){
		const pfile = event.target.files;
		if(pfile[0].size > 2097152){
			alert('첨부파일은 2MB 이하만 가능합니다.');
			event.target.value = '';
		}
	}
	document.getElementById("nfile").addEventListener("change",function(event){
		sizeCheck(event);
	})
	
	
	CKEDITOR.on('instanceReady', function(event) {
		var editor = event.editor;
		document.querySelector(".border_add").addEventListener("click",()=>{
			var frm = document.forms['frm'];
			
			if(!frm.notice_title.value){
				alert("공지사항 제목을 입력하세요.");
			}else if(!editor.getData()){
				alert("공지사항 내용을 입력하세요.");
			}else{
				const nidx = parseInt(document.getElementById("nidx").value);
				var formData = new FormData(frm);
				formData.set('notice_text', editor.getData());
				
				fetch(`./notice_modify?nidx=${nidx}`,{
					method:'POST',
					body:formData
				})
				.then(response => response.text())
				.then(data => {
					if(data === 'ok'){
						alert('정상적으로 공지사항 등록이 완료 되었습니다.');
						location.href = './notice_list';
					}else{
						alert('공지사항 등록에 실패 하였습니다.');
					}
				})
				.catch(()=>{
					alert('공지사항 등록에 실패 하였습니다.');
				})
			}
		})
	})
})