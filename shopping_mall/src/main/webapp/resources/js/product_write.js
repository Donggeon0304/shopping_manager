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