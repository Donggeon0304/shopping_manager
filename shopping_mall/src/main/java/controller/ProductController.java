package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.CateDto;
import dto.ProductsDto;
import model.ProductFile;
import model.Products;
import service.CateService;
import service.FileService;
import service.ProductsService;
import utils.FileUtils;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private CateService cs;
	
	@Autowired
	private ProductsService ps;
	
	@Autowired
	private FileService fs;
	
	//카테고리 리스트 사이트
	@GetMapping("cate_list.do")
	public String cate_list(Model m, Integer page) {
		if(page == null) {
			page = 1;
		}
		int size = 5;
		page = (page-1) * size;
		m.addAttribute("size",size);
		m.addAttribute("data",cs.get_cate_page(page,size));
		m.addAttribute("count", cs.ck_cate());
		return "/product/cate_list";
	}
	
	//카테고리 작성 사이트
	@GetMapping("cate_write.do")
	public String cate_write() {
		return "/product/cate_write";
	}
	//카테고리 추가
	@PostMapping("cate_add.do")
	public String cate_add(CateDto dto) {
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
		for(String a : cidx ) {
			if(ps.cateck_products(Integer.parseInt(a))) {
				return ResponseEntity.ok("has");
			}
		}
		boolean result = cs.del_cate(cidx);
		if(result) {
			return ResponseEntity.ok("ok");			
		}else {
			return ResponseEntity.ok("no");
		}	
	}
	
	@GetMapping("product_list.do")
	public String product_list(Model m, HttpServletRequest req, String search_part, String search_word) {
		List<List<ProductsDto>> result = new ArrayList<List<ProductsDto>>();
		List<Integer> data = new ArrayList<Integer>();
		if(search_part!=null && search_word!=null) {
			result.add(ps.search_products(search_part, search_word));
			for(ProductsDto li : ps.search_products(search_part, search_word)) {
				data.add(li.getPidx());
			}
			m.addAttribute("products",ps.search_products(search_part, search_word));
			m.addAttribute("files",fs.search_productFile(data));
		}else {
			result.add(ps.get_products());
			result.add(fs.get_productFile());
			m.addAttribute("products",ps.get_products());
			m.addAttribute("files",fs.get_productFile());			
		}
		return "/product/product_list";
	}
	
	@GetMapping("product_write.do")
	public String product_write(Model m) {
		m.addAttribute("category",cs.get_cate());
		return "/product/product_write";
	}
	
	//상품 등록
	@PostMapping("product_add.do")
	@ResponseBody
	public String product_add(@ModelAttribute ProductsDto pt, HttpServletRequest req) throws IOException {
		pt.setCidx(cs.get_cidx(pt.getCate()));
		boolean addResult = ps.add_products(pt);
		if(addResult) {
			pt.setPidx(ps.get_pidx());
			fs.add_file(pt, req);
			return "ok";
		}else {
			return "no";
		}
	}
	
	//상품 삭제
	@PostMapping("product_del.do")
	public ResponseEntity<String> product_del(@RequestBody List<String> pidx){
		if(fs.del_productFile(pidx) && ps.del_products(pidx)) {
			return ResponseEntity.ok("ok");			
		}else{
			return ResponseEntity.ok("no");
		}
	}
	
	//상품코드 중복확인
	@GetMapping("product_codeck")
	@ResponseBody
	public String product_codeck(@RequestParam String pcode) {
		if(ps.codeck_products(pcode)) {
			return "no";			
		}else {
			return "ok";
		}
	}
	
}
