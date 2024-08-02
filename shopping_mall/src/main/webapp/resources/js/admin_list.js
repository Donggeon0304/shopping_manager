$(document).ready(()=>{
	$(".new_addbtn1").click(function(){
		if(confirm('관리자 승인을 진행 하시겠습니까?')){
			fetch(`./admin_use.do?aidx=`+$(this).data('aidx')+'&ause=Y', {
				method: 'GET',
			})
			.then(response => {
				if(!response.ok){
					throw new Error('Network response was not ok');
				}
				return response.text();
			})
			.then(data => {
				console.log('Success:', data);
				alert('관리자 승인 되었습니다.');
				repage();
			})
			.catch(error => {
				console.error('Error:', error);
			})
		}
	})
	$('.new_addbtn2').click(function(){
		if(confirm('관리자 승인해제 하시겠습니까?')){
			fetch(`./admin_use.do?aidx=`+$(this).data('aidx')+'&ause=N', {
				method: 'GET',
			})
			.then(response => {
				if(!response.ok){
					throw new Error('Network response was not ok');
				}
				return response.text();
			})
			.then(data => {
				console.log('Success:', data);
				alert('관리자 승인해제 되었습니다.');
				repage();
			})
			.catch(error => {
				console.error('Error:', error);
			})
		}
	})
})
function repage(){
	window.location.reload();
}