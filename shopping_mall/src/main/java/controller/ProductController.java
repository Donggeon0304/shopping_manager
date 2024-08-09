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
	public String cate_list() {
		return "/product/cate_list";
	}
	
	//카테고리 리스트 출력 + 검색 (ajax)
	@GetMapping("cate_list_ajax")
	public void cate_list_ajax(HttpServletResponse res, int page, int size, String part, String word) throws Exception {
		res.setContentType("aplication/json;charset=utf-8");
		page = size * (page-1);
		
		JSONArray ja = new JSONArray();
		if(part!=null && word!=null && !(word.equals(""))) {
			ja.put(cs.search_cate(page, size, part, word));
			ja.put(cs.search_ck_cate(part, word));
		}else {
			ja.put(cs.get_cate_page(page,size));
			ja.put(cs.ck_cate());
		}
		res.getWriter().print(ja.toString());
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
	
	//상품 리스트 페이지
	@GetMapping("product_list.do")
	public String product_list() {
		return "/product/product_list";
	}
	
	//상품 리스트 출력 + 검색 (ajax)
	@GetMapping("product_list_ajax")
	public void product_list_ajax(HttpServletResponse res, int page, int size, String part, String word) throws Exception {
		res.setContentType("aplication/json;charset=utf-8");
		page = size * (page-1);
		
		List<Integer> data = new ArrayList<Integer>();
		JSONArray ja = new JSONArray();
		if(part!=null && word!=null && !(word.equals(""))) {
			for(ProductsDto li : ps.search_products(page, size, part, word)) {
				data.add(li.getPidx());
			}
			ja.put(ps.search_products(page, size, part, word));
			ja.put(ps.search_ck_products(part, word));
			ja.put(fs.search_productFile(page, size, data));
		}else {
			ja.put(ps.get_products_page(page,size));
			ja.put(ps.ck_products());
			ja.put(fs.get_productFile_page(page, size));
		}
		res.getWriter().print(ja.toString());
	}
	
	@GetMapping("product_write.do")
	public String product_write(Model m) {
		m.addAttribute("category",cs.get_cate());
		return "/product/product_write";
	}
	
	//상품 등록
	@PostMapping("product_add.do")
	@ResponseBody
	public String product_add(ProductsDto pt, HttpServletRequest req) throws IOException {
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
