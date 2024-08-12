package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.UserDto;
import service.AdminService;
import service.MailService;
import service.UserService;
@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private AdminService as;
	
	@Autowired
	private UserService us;
	
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	@GetMapping("/join")
	public String join() {
		return "member/join";
	}
	@GetMapping("/agree")
	public String agree(Model m) {
		m.addAttribute("terms",as.getTerms());
		return "member/agree";
	}
	
	//아이디 중복체크
	@ResponseBody
	@GetMapping("check-id")
	public String idCheck(@RequestParam String id) {
		if(us.getUserId(id)) {
			return "no";
		}else {
			return "ok";
		}
	}
	
	//회원가입
	@ResponseBody
	@PostMapping("/join")
	public String join(@ModelAttribute UserDto dto, HttpSession hs) {
		System.out.println(dto.getEvent_email());
		System.out.println(dto.getEvent_sms());
		String sendedCode = (String)hs.getAttribute("code");
		if(dto.getCode().equals(sendedCode)) {
			dto.setUser_use("Y");
			if(dto.getEvent_email() == null) {
				dto.setEvent_email("");
			}
			if(dto.getEvent_sms() == null) {
				dto.setEvent_sms("");
			}
			if(us.addUser(dto)) {
				return "ok";			
			}else {
				return "fail";
			}
		}else {
			return "no";
		}
	}
}
