package controller;

import java.security.SecureRandom;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.MailService;

@RestController
public class MailController {

	@Autowired
	private MailService ms;
	
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 8;
    private SecureRandom random = new SecureRandom();
	
    //인증번호 발송
	@GetMapping("/member/send-code")
	public String sendCode(@RequestParam String email, HttpSession hs) {
		String code = generateCode();
		try {
			hs.setAttribute("code", code);
			ms.sendVerificationCode(email, code);
			return "ok";
		}catch(Exception e) {
			e.printStackTrace();
			return "no";
		}		
	}
	
	public String generateCode() {
		StringBuilder code = new StringBuilder(CODE_LENGTH);
		for(int f=0; f<CODE_LENGTH; f++) {
			code.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
		}
		return code.toString();
	}
}
