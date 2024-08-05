package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@GetMapping("/notice_list")
	public String notice_list() {
		return "/notice/notice_list";
	}
	
	@GetMapping("/notice_write")
	public String notice_write() {
		return "/notice/notice_write";
	}
}
