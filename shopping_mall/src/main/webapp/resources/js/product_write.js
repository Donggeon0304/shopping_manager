document.addEventListener("DOMContentLoaded",function(){
	
	document.querySelector(".p_button_color1").addEventListener("click",()=>{
		location.href = './product_list.do';
	})
	document.querySelector(".product_btn").addEventListener("click",()=>{
		window.open("./cate_list.do", "_blank");
	})
	
	document.querySelector("#pdc_per").addEventListener("keyup",function(){
		var dcPer = parseFloat(this.value) || 0;
		var price = parseFloat(document.getElementById("price").value) || 0; 
		
        if (dcPer === 0 || isNaN(dcPer)) {
            document.getElementById("pdc_price").value = 0;
            return;
        }

        // 할인 금액을 계산합니다.
        var discountAmount = price * (dcPer / 100);

        // 계산된 값을 입력 필드에 설정합니다.
        document.getElementById("pdc_price").value = price - discountAmount;
	})
	
	function sizeCheck(event){
		const pfile = event.target.files;
		console.log(pfile[0].type);
		if(!pfile[0].type.includes('image')){
			alert('이미지 파일만 업로드 가능합니다.');
			event.target.value = '';
		}else if(pfile[0].size > 2097152){
			alert('첨부파일은 2MB 이하만 가능합니다.');
			event.target.value = '';
		}
	}
	document.getElementById("pfile").addEventListener("change",function(event){
		sizeCheck(event);
	})
	document.getElementById("padd_file1").addEventListener("change",function(event){
		sizeCheck(event);
	})
	document.getElementById("padd_file2").addEventListener("change",function(event){
		sizeCheck(event);
	})
	
	document.querySelector(".p_button_color2").addEventListener("click",function(){
		var pfile = document.getElementById("pfile");
		var padd_file1 = document.getElementById("padd_file1");
		var padd_file2 = document.getElementById("padd_file2");
		if(pfile.value == ""){
			alert('대표 이미지는 필수입니다.');
		}else{
			frm.method="post";
			frm.action="./product_add.do";
			frm.submit();			
		}
	})
})

function ran(){
	var ran = "";
	for(var f=0; f<7; f++){
		ran+=Math.floor(Math.random()*10);
	}
	console.log(ran);
	document.getElementById("rancode").value = ran;
}
ran();