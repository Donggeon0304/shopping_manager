package shopping_admin;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class admin_controller {
	
	@Resource(name="admindao")
	private admin_dao ad;
	
	//관리자 로그인
	@RequestMapping("/admin/admin_main.do")
	public String admin_main(HttpSession hs, String aid) {
		if(aid.equals("master")) {
			hs.setAttribute("admin", "최고 관리자");			
		}else {
			hs.setAttribute("admin", aid);
		}
		return "/admin/admin_list";
	}
	
	//관리자 로그아웃
	@GetMapping("/admin/admin_logout.do")
	@ResponseBody
	public String admin_logout(HttpServletRequest req) {
		HttpSession hs = req.getSession();
		hs.removeAttribute("admin");
		return "ok";
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
		System.out.println(aid);
		return "ok";
	}
}
