package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.cate_dto;
import service.cate_service;

@Controller
@RequestMapping("/product")
public class product_controller {
	
	@Autowired
	private cate_service cs;
	
	//카테고리 리스트 사이트
	@GetMapping("cate_list.do")
	public String cate_list(Model m) {
		m.addAttribute("data",cs.get_cate());
		return "/product/cate_list";
	}
	//카테고리 작성 사이트
	@GetMapping("cate_write.do")
	public String cate_write() {
		return "/product/cate_write";
	}
	//카테고리 추가
	@PostMapping("cate_add.do")
	public String cate_add(cate_dto dto) {
		boolean result = cs.add_cate(dto);
		if(result) {
			return "redirect:/product/cate_list.do";
		}else {
			return null;
		}
	}
	//카테고리 삭제
	@PostMapping("cate_del.do")
	public ResponseEntity<String> cate_del(@RequestBody List<String> cidx) {
		boolean result = cs.del_cate(cidx);
		if(result) {
			return ResponseEntity.ok("ok");			
		}else {
			return ResponseEntity.ok("no");
		}
	}
	
	@GetMapping("product_list.do")
	public String product_list() {
		return "/product/product_list";
	}
	
	@GetMapping("product_write.do")
	public String product_write(Model m) {
		m.addAttribute("category",cs.get_cate());
		return "/product/product_write";
	}
	
	
}
