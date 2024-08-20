package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import utils.Md5Utils;
@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private AdminService as;
	
	@Autowired
	private UserService us;
	
	//로그인 페이지
	@GetMapping("/login")
	public String login(HttpSession hs, Model m) {
		if(hs.getAttribute("saveId") != null) {
			m.addAttribute("saveId",hs.getAttribute("saveId"));
		}
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
		String sendedCode = (String)hs.getAttribute("code");
		if(dto.getCode().equals(sendedCode)) {
			if(us.addUser(dto)) {
				return "ok";
			}else {
				return "fail";
			}
		}else {
			return "no";
		}
	}
	
	//로그인
	@GetMapping("/user_login")
	public ResponseEntity<String> userLogin(
			@RequestParam String id, 
			@RequestParam String password, 
			@RequestParam String idSave, 
			HttpSession hs) {
		UserDto dto = us.loginUser(id);
		Md5Utils md = new Md5Utils();
		if(dto != null && dto.getUser_password().equals(md.md5_making(password))) {
			if(dto.getUser_use().equals("정상")) {
				if(idSave.equals("Y")) {
					hs.setAttribute("saveId", id);
					hs.setMaxInactiveInterval(5 * 60);
				}else {
					hs.removeAttribute("saveId");
				}
				return ResponseEntity.ok("ok");
			}else {
				return ResponseEntity.ok("fail");
			}
		}else {
			return ResponseEntity.ok("no");
		}
	}
}