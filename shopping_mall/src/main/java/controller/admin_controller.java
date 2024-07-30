package controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.admin_dao;
import dto.admin_dto;
import service.shop_md5;

@Controller
public class admin_controller {
	
	@Resource(name="admindao")
	private admin_dao ad;
	
	//관리자 로그인
	@PostMapping(value="/admin/admin_main.do")
	public ResponseEntity<String> admin_main(@RequestBody admin_dto at, HttpSession hs) throws Exception{
		String pw = new shop_md5().md5_making(at.getApass());
		if(at.getAid().equals("master")) {
			if(!at.getApass().equals("shop_master123")) {
				return ResponseEntity.ok("pwno");
			}
			hs.setAttribute("admin", "최고 관리자");
			hs.setMaxInactiveInterval(30 * 60);
			return ResponseEntity.ok("ok");
		}else {
			at = ad.admin_selectOne(at.getAid());
			if(at == null || !pw.equals(at.getApass())) {
				return ResponseEntity.ok("pwno");
			}else {
				if(at.getAuse().equals("N")) {
					return ResponseEntity.ok("no");
				}else {
					hs.setAttribute("admin", at.getAname());
					hs.setMaxInactiveInterval(30 * 60);
					return ResponseEntity.ok("ok");
				}
			}
		}
	}
	
	//관리자 로그아웃
	@GetMapping("/admin/admin_logout.do")
	@ResponseBody
	public String admin_logout(HttpServletRequest req, HttpServletResponse res,String ck) throws Exception {
		HttpSession hs = req.getSession(false);

	    if (hs == null || hs.getAttribute("admin") == null|| ck==null) {
	        res.sendRedirect("/admin/index.jsp");
	        return null;
	    }else {
	    	hs.removeAttribute("admin");
	    	return "ok";
	    }
	}
	
	//관리자 회원가입
	@PostMapping(value="/admin/admin_join.do")
	@ResponseBody
	public ResponseEntity<String> admin_join(@RequestBody admin_dto at) {
		String result = ad.admin_insert(at);
		if(result.equals("success")) {
			System.out.println("win");
		}else {
			System.out.println("lose");
		}
		return ResponseEntity.ok("ok");
	}
	
	//관리자 아이디 중복체크
	@PostMapping("/admin/admin_idck.do")
	@ResponseBody
	public String admin_idck(String aid) {
		int result = ad.admin_idselect(aid);
		if(result>0) {
			return "no";			
		}else {
			return "ok";
		}
	}
	
	//관리자 승인요청
	@GetMapping("/admin/admin_use.do")
	@ResponseBody
	public String admin_use(@RequestParam String aidx, String ause) {
		String result = ad.admin_update(ause, aidx);
		return result;
	}
	
	//관리자 리스트
	@GetMapping("/admin/admin_list.do")
	public String admin_list(Model m) {
		m.addAttribute("result",ad.admin_select());
		return "/admin/admin_list";
	}
	
	//메인 페이지
	@GetMapping("/admin/main.do")
	public String main() {
		return "/admin/main";
	}
	
	//관리자 로그인 페이지
	@GetMapping("/admin/index")
	public String index() {
		return "/admin/index";
	}
	
	//관리자 회원가입 페이지
	@GetMapping("/admin/join")
	public String add_master() {
		return "/admin/add_master";
	}
}
