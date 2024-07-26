package com.coupangmall.www;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class web_controller {
	
	@GetMapping("/ajaxok.do")
	public String ajaxok() {
		
		return null;
	}
	
}
