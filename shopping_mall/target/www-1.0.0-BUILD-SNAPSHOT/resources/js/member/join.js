document.addEventListener("DOMContentLoaded", function() {
    document.querySelector("#btnNextStep").addEventListener("click", () => {
        // 입력 필드의 값을 가져옵니다.
        const fields = {
            user_id: document.querySelector('input[name="user_id"]').value.trim(),
            user_password: document.querySelector('input[name="user_password"]').value.trim(),
            confirm_password: document.querySelector('input[name="user_password"]').value.trim(),
            user_name: document.querySelector('input[name="user_name"]').value.trim(),
            user_email: document.querySelector('input[name="user_email"]').value.trim(),
            verification_code: document.querySelector("#verification_code").value.trim(),
            user_tel: document.querySelector('input[name="user_tel"]').value.trim(),
            event_email: document.querySelector('input[name="event_email"]').checked,
            event_sms: document.querySelector('input[name="event_sms"]').checked
        };

        // 빈 필드를 확인합니다.
        let message = '';
        let isValid = true;

        if (!fields.user_id) {
            message += '아이디를 입력해 주세요.\n';
            isValid = false;
        }
        if (!fields.user_password) {
            message += '비밀번호를 입력해 주세요.\n';
            isValid = false;
        }
        if (!fields.confirm_password) {
            message += '비밀번호 확인을 입력해 주세요.\n';
            isValid = false;
        }
        if (!fields.user_name) {
            message += '이름을 입력해 주세요.\n';
            isValid = false;
        }
        if (!fields.user_email) {
            message += '이메일을 입력해 주세요.\n';
            isValid = false;
        }
        if (!fields.verification_code) {
            message += '인증번호를 입력해 주세요.\n';
            isValid = false;
        }
        if (!fields.user_tel) {
            message += '전화번호를 입력해 주세요.\n';
            isValid = false;
        }

        if (!isValid) {
            alert(message);
        } else {
            window.location.href = './login';
        }
    });
});