$(document).ready(()=>{
	$(".sub_btn2").click(() => {
		window.location.reload();
	})
})

document.addEventListener("DOMContentLoaded", function() {
    document.querySelector(".sub_btn1").addEventListener("click", function(event) {
        var formElements = document.querySelectorAll(".info_form input, .info_form2 input");
        
        var firstInvalidField = null;
        formElements.forEach(function(element) {
			var name = element.name;
			var value = element.value.trim();
            if (element.type === "text" && !element.value.trim()) {
				if(!element.value.trim()){
	                if (!firstInvalidField) {
	                    firstInvalidField = element;
						firstInvalidFieldName = name;
	                }
	                return;
				}					
            }
			
			if (name === "pay_dto.min_point") {
                var minPointValue = parseInt(value, 10);
                if (isNaN(minPointValue) || minPointValue < 1000) {
                    if (!firstInvalidField) {
                        firstInvalidField = element;
                        firstInvalidFieldName = name+1;
	                    return;
                    }
                }
            }

			if (name === "pay_dto.max_point") {
                var minPointValue = parseInt(value, 10);
                if (isNaN(minPointValue) || minPointValue < 1000) {
                    if (!firstInvalidField) {
                        firstInvalidField = element;
                        firstInvalidFieldName = name+1;
	                    return;
                    }
                }
            }

            if (element.type === "radio" && !document.querySelector(`input[name="${element.name}"]:checked`)) {
                if (!firstInvalidField) {
                    firstInvalidField = element;
					firstInvalidFieldName = name;
                }
                return;
            }
        });
        
        if (firstInvalidField) {
            alert(getAlertMessage(firstInvalidFieldName));
            event.preventDefault(); // 폼의 기본 제출 동작을 막음
        } else {
			var formData = {};
            $("#frm").find("[name]").each(function() {
				var name = this.name;
			    var value = $(this).val();

			    var names = name.split('.');
			    if (names.length === 2) {
					if (!formData[names[0]]) {
			            formData[names[0]] = {};
			        }
			        formData[names[0]][names[1]] = value;
			    } else {
			        formData[name] = value;
			    }
            });

            fetch('./siteinsert.do',{
				method:'POST',
				headers: {
			            'Content-Type': 'application/json'
			        },
			    body: JSON.stringify(formData)
			})
			.then(response => {
				return response.text();
			})
   			.then(data => {
				if(data == "ok"){
			        alert('저장 되었습니다.');
					repage();					
				}else{
					alert('저장에 실패하였습니다.');
				}
		    })
		    .catch(error => {
		        console.error('Error:', error);
		    });
        }
    });

	function repage(){
		window.location.reload();
	}

    function getAlertMessage(name) {
	    switch (name) {
	        case "siteinfo_dto.page_name":
	            return "홈페이지 제목을 입력해 주세요.";
	        case "siteinfo_dto.admin_email":
	            return "관리자 메일 주소를 입력해 주세요.";
	        case "siteinfo_dto.point_use":
	            return "포인트 사용 여부를 선택해 주세요.";
	        case "siteinfo_dto.join_point":
	            return "회원가입 시 적립금을 입력해 주세요.";
	        case "siteinfo_dto.join_level":
	            return "회원가입 시 권한 레벨을 입력해 주세요.";
	        case "siteinfo_dto.com_name":
	            return "회사명을 입력해 주세요.";
	        case "siteinfo_dto.business_num":
	            return "사업자 등록번호를 입력해 주세요.";
	        case "siteinfo_dto.ceo_name":
	            return "대표자명을 입력해 주세요.";
	        case "siteinfo_dto.ceo_tel":
	            return "대표 전화번호를 입력해 주세요.";
	        case "siteinfo_dto.com_post":
	            return "사업장 우편번호를 입력해 주세요.";
	        case "siteinfo_dto.com_address":
	            return "사업장 주소를 입력해 주세요.";
	        case "siteinfo_dto.info_name":
	            return "정보관리 책임자명을 입력해 주세요.";
	        case "siteinfo_dto.info_email":
	            return "정보 책임자 E-mail을 입력해 주세요.";
	
	        case "pay_dto.bank":
	            return "무통장 은행을 입력해 주세요.";
	        case "pay_dto.account_num":
	            return "은행 계좌번호를 입력해 주세요.";
	        case "pay_dto.card_use":
	            return "신용카드 결제 사용 여부를 선택해 주세요.";
	        case "pay_dto.phone_use":
	            return "휴대폰 결제 사용 여부를 선택해 주세요.";
	        case "pay_dto.giftcard_use":
	            return "도서상품권 결제 사용 여부를 선택해 주세요.";
	        case "pay_dto.min_point":
	            return "결제 최소 포인트를 입력해 주세요.";
			case "pay_dto.min_point1":
				return "결제 최소 포인트는 1000이상만 가능합니다.";
	        case "pay_dto.max_point":
	            return "결제 최대 포인트를 입력해 주세요.";
	        case "pay_dto.cash_receipt":
	            return "현금 영수증 발급 사용 여부를 선택해 주세요.";
	        case "pay_dto.deli_name":
	            return "배송업체명을 입력해 주세요.";
	        case "pay_dto.deli_price":
	            return "배송비 가격을 입력해 주세요.";
	        case "pay_dto.deli_date":
	            return "희망 배송일 사용 여부를 선택해 주세요.";
	
	        default:
	            return "입력값을 확인해 주세요.";
	    }
	}
});