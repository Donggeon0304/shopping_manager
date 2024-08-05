package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

	@GetMapping("/shop_member_list.do")
	public String shop_memeber_list() {
		return "/member/shop_member_list";
	}
}
