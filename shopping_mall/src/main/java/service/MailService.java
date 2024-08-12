package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendVerificationCode(String to, String code) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("rodzld18@naver.com");
		message.setTo(to);
		message.setSubject("회원 가입 인증 코드");
		message.setText("인증 코드: "+code);
		mailSender.send(message);
	}
}
