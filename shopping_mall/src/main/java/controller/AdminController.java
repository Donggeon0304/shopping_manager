package controller;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.AdminDto;
import service.AdminService;
import utils.Md5Utils;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService as;
	
	//이용약관 수정
	@PostMapping("/use_terms")
	public ResponseEntity<String> modifyUseTerms(@RequestParam("use_terms") String use_terms) {
		if(as.useTerms(use_terms)) {
			return ResponseEntity.ok("ok");			
		}else {
			return ResponseEntity.ok("no");
		}
	}
	
	//개인정보 약관 수정
	@PostMapping("/personal_terms")
	public ResponseEntity<String> modifyPersonalTerms(@RequestParam("personal_terms") String personal_terms) {
		if(as.personalTerms(personal_terms)) {
			return ResponseEntity.ok("ok");			
		}else {
			return ResponseEntity.ok("no");
		}
	}
	
	//쇼핑몰 회원관리 페이지
	@GetMapping("/shop_member_list.do")
	public String shop_memeber_list(Model m) {
		m.addAttribute("terms",as.getTerms());
		return "/admin/shop_member_list";
	}
	
	//관리자 로그인
	@PostMapping("/admin_main.do")
	public ResponseEntity<String> admin_main(@RequestBody AdminDto at, HttpSession hs) throws Exception{
		String pw = new Md5Utils().md5_making(at.getApass());
		if(at.getAid().equals("master")) {
			if(!at.getApass().equals("shop_master123")) {
				return ResponseEntity.ok("pwno");
			}
			hs.setAttribute("admin", "최고 관리자");
			hs.setMaxInactiveInterval(30 * 60);
			return ResponseEntity.ok("ok");
		}else {
			AdminDto admin = as.get_admin(at.getAid());
			if(admin == null || !pw.equals(admin.getApass())) {
				return ResponseEntity.ok("pwno");
			}else {
				if(admin.getAuse().equals("N")) {
					return ResponseEntity.ok("no");
				}else {
					hs.setAttribute("admin", admin.getAname());
					hs.setMaxInactiveInterval(30 * 60);
					return ResponseEntity.ok("ok");
				}
			}
		}
	}
	
	//관리자 로그아웃
	@GetMapping("/admin_logout.do")
	@ResponseBody
	public String admin_logout(HttpSession hs, HttpServletResponse res,String ck) throws Exception {
	    if (hs == null || hs.getAttribute("admin") == null|| ck==null) {
	        res.sendRedirect("/admin/index");
	        return null;
	    }else {
	    	hs.removeAttribute("admin");
	    	return "ok";
	    }
	}
	
	//관리자 회원가입
	@PostMapping("/admin_join.do")
	@ResponseBody
	public ResponseEntity<String> admin_join(@RequestBody AdminDto at) {
		String result = as.add_admin(at);
		if(result.equals("success")) {
			System.out.println("win");
		}else {
			System.out.println("lose");
		}
		return ResponseEntity.ok("ok");
	}
	
	//관리자 아이디 중복체크
	@PostMapping("/admin_idck.do")
	@ResponseBody
	public String admin_idck(String aid) {
		boolean result = as.idcheck_admin(aid);
		if(result) {
			return "no";			
		}else {
			return "ok";
		}
	}
	
	//관리자 승인요청
	@GetMapping("/admin_use.do")
	@ResponseBody
	public String admin_use(@RequestParam int aidx, String ause) {
		String result = as.update_admin(ause, aidx);
		return result;
	}
	
	//관리자 리스트
	@GetMapping("/admin_list.do")
	public String admin_list(Model m) {
		m.addAttribute("result",as.get_alladmin());
		return "/admin/admin_list";
	}
	
	//메인 페이지
	@GetMapping("/main.do")
	public String main() {
		return "/admin/main";
	}
	
	//관리자 로그인 페이지
	@GetMapping("/index")
	public String index() {
		return "/admin/index";
	}
	
	//관리자 회원가입 페이지
	@GetMapping("/join")
	public String add_master() {
		return "/admin/add_master";
	}
}
