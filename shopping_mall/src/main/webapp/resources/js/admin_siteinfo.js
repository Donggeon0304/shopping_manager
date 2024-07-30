$(document).ready(()=>{
	$(".sub_btn2").click(() => {
		window.location.reload();
	})
})

document.addEventListener("DOMContentLoaded", function() {
    document.querySelector(".sub_btn1").addEventListener("click", function(event) {
        // 폼 요소 가져오기
        var formElements = document.querySelectorAll(".info_form input, .info_form2 input");
        
        // 폼 데이터 검사
        var firstInvalidField = null;
        formElements.forEach(function(element) {
            if (element.type === "text" && !element.value.trim()) {
                if (!firstInvalidField) {
                    firstInvalidField = element;
                }
                return; // 첫 번째 비어 있는 입력값을 찾으면 검사 종료
            }
            if (element.type === "radio" && !document.querySelector(`input[name="${element.name}"]:checked`)) {
                if (!firstInvalidField) {
                    firstInvalidField = element;
                }
                return; // 첫 번째 비어 있는 입력값을 찾으면 검사 종료
            }
        });
        
        // 첫 번째 비어 있는 필드가 있을 경우 경고 메시지 출력
        if (firstInvalidField) {
            alert(getAlertMessage(firstInvalidField.name));
            event.preventDefault(); // 폼의 기본 제출 동작을 막음
        } else {
            // 유효성 검사 통과 시 폼 제출 로직 추가
            console.log("폼 데이터가 유효합니다. 제출합니다.");
            frm.method="post";
			frm.action="./siteinfo.do";
			frm.submit();
        }
    });

    function getAlertMessage(name) {
        switch (name) {
            case "page_name":
                return "홈페이지 제목을 입력해 주세요.";
            case "admin_email":
                return "관리자 메일 주소를 입력해 주세요.";
            case "point_use":
                return "포인트 사용 여부를 선택해 주세요.";
            case "join_point":
                return "회원가입 시 적립금을 입력해 주세요.";
            case "join_level":
                return "회원가입 시 권한 레벨을 입력해 주세요.";
            case "com_name":
                return "회사명을 입력해 주세요.";
            case "business_num":
                return "사업자 등록번호를 입력해 주세요.";
            case "ceo_name":
                return "대표자명을 입력해 주세요.";
            case "ceo_tel":
                return "대표 전화번호를 입력해 주세요.";
            case "mob_num":
                return "통신판매업 신고번호를 입력해 주세요.";
            case "vat_num":
                return "부가통신 사업자번호를 입력해 주세요.";
            case "com_post":
                return "사업장 우편번호를 입력해 주세요.";
            case "com_address":
                return "사업장 주소를 입력해 주세요.";
            case "info_name":
                return "정보관리 책임자명을 입력해 주세요.";
            case "info_email":
                return "정보 책임자 E-mail을 입력해 주세요.";
            case "bank_name":
                return "무통장 은행을 입력해 주세요.";
            case "account_number":
                return "은행 계좌번호를 입력해 주세요.";
            case "credit_card_use":
                return "신용카드 결제 사용 여부를 선택해 주세요.";
            case "mobile_payment_use":
                return "휴대폰 결제 사용 여부를 선택해 주세요.";
            case "book_coupon_use":
                return "도서상품권 결제 사용 여부를 선택해 주세요.";
            case "min_point":
                return "결제 최소 포인트를 입력해 주세요.";
            case "max_point":
                return "결제 최대 포인트를 입력해 주세요.";
            case "cash_receipt_use":
                return "현금 영수증 발급 사용 여부를 선택해 주세요.";
            case "delivery_company":
                return "배송업체명을 입력해 주세요.";
            case "delivery_fee":
                return "배송비 가격을 입력해 주세요.";
            case "preferred_delivery_date":
                return "희망 배송일 사용 여부를 선택해 주세요.";
            default:
                return "입력값을 확인해 주세요.";
        }
    }
});