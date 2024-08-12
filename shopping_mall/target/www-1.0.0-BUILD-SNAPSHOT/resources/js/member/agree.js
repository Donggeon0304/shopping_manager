document.addEventListener("DOMContentLoaded", function() {
    let all = document.getElementById("allAgree").checked;
    let agr1 = document.getElementById("termsAgree1").checked;
    let agr2 = document.getElementById("termsAgree2").checked;

    document.querySelector("#allAgree").addEventListener("click", function() {
        all = this.checked;
        agr1 = all;
        agr2 = all;
        updateCheckboxes();
    });

    document.querySelector("#termsAgree1").addEventListener("click", function() {
        agr1 = this.checked;
        if (agr1 === agr2) {
            all = agr1;
            updateCheckboxes();
        }else{
			all = false;
			updateCheckboxes();
		}
    });

    document.querySelector("#termsAgree2").addEventListener("click", function() {
        agr2 = this.checked;
        if (agr1 === agr2) {
            all = agr2;
            updateCheckboxes();
        }else{
			all = false;
			updateCheckboxes();
		}
    });

    function updateCheckboxes() {
        document.getElementById("allAgree").checked = all;
        document.getElementById("termsAgree1").checked = agr1;
        document.getElementById("termsAgree2").checked = agr2;
    }
	
	document.querySelector("#btnNextStep").addEventListener("click",function(){
		if(agr1 === false || agr2 === false){
			alert("약관에 동의하셔야 합니다.");
		}else{
			location.href='./join';
		}
	})
});