package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class product_controller {
	
	@GetMapping("cate_list.do")
	public String cate_list() {
		return "/product/cate_list";
	}
	@GetMapping("cate_write.do")
	public String cate_write() {
		return "/product/cate_write";
	}
	@GetMapping("product_list.do")
	public String product_list() {
		return "/product/product_list";
	}
	@GetMapping("product_write.do")
	public String product_write() {
		return "/product/product_write";
	}
	
}
