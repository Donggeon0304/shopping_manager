package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import service.AdminService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private AdminService as;
	
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
}
