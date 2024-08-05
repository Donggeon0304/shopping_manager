document.addEventListener("DOMContentLoaded",function(){
	//상품리스트 페이지로 이동
	document.querySelector(".p_button_color1").addEventListener("click",()=>{
		location.href = './product_list.do';
	})
	//카테고리 리스트 페이지로 이동
	document.querySelector(".product_btn").addEventListener("click",()=>{
		window.open("./cate_list.do", "_blank");
	})
	
	//상품코드 중복체크
	var codeck = false;
	document.querySelector("#redundancy_ck").addEventListener("click",function(){
		var pcode = document.getElementById("rancode").value;
		fetch('./product_codeck?pcode='+pcode,{
			method:'GET',
		})
		.then(response => response.text())
		.then(data => {
			if(data === "ok"){
				alert("사용할 수 있는 상품코드입니다.");
				codeck = true;
			}else{
				alert("중복된 상품코드입니다.")
			}
		})
		.catch(()=>{
			alert("에러가 발생하였습니다.")
		})
	})
	
	//할인율 입력시 할인가격 자동계산
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
	
	//이미지 파일 크기 계산
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
	
	//상품등록
	document.querySelector(".p_button_color2").addEventListener("click",function(){
		var frm = document.forms['frm'];
		var pfile = document.getElementById("pfile");
		
		if(frm.cate.value == ""){
			alert('대메뉴 카테고리를 선택하세요');
		}else if(!codeck){
			alert('상품코드 중복체크를 하셔야 합니다.');
		}else if(frm.pname.value == ""){
			alert('상품명을 입력하세요.');
		}else if(frm.padd_text.value == ""){
			alert('상품 부가설명을 입력하세요.');
		}else if(frm.price.value == ""){
			alert("판매가격을 입력하세요.");
		}else if(frm.pdc_per.value == ""){
			alert('할인율을 입력하세요.');
		}else if(frm.pstock.value == ""){
			alert('상품재고를 입력하세요.');
		}else if(pfile.value.value == ""){
			alert('대표 이미지는 필수입니다.');
		}else if(frm.ptext.value == ""){
			alert('상품 상세설명을 입력하세요.');
		}else{
			var formData = new FormData(frm);
			
			fetch('./product_add.do',{
				method:'POST',
				body:formData
			})
			.then(response => response.text())
			.then(data => {
				if(data === 'ok'){
					alert('정상적으로 상품 등록이 완료 되었습니다.');
					location.href = './product_list.do';
				}else{
					alert('상품 등록에 실패 하였습니다.');
				}
			})
			.catch(()=>{
				alert('상품 등록에 실패 하였습니다.');
			})
		}
	})
})

//상품코드 난수설정
function ran(){
	var ran = "";
	for(var f=0; f<7; f++){
		ran+=Math.floor(Math.random()*10);
	}
	document.getElementById("rancode").value = ran;
}
ran();