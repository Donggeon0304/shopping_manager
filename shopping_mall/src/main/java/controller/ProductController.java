package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
		boolean result = cs.del_cate(cidx);
		if(result) {
			return ResponseEntity.ok("ok");			
		}else {
			return ResponseEntity.ok("no");
		}
	}
	
	@GetMapping("product_list.do")
	public String product_list(Model m, HttpServletRequest req) {
		System.out.println(req.getServletContext().getRealPath("/upload/"));
		List<List<ProductsDto>> result = new ArrayList<List<ProductsDto>>();
		result.add(ps.get_products());
		result.add(fs.get_productFile());
		m.addAttribute("products",ps.get_products());
		m.addAttribute("files",fs.get_productFile());
		return "/product/product_list";
	}
	
	@GetMapping("product_write.do")
	public String product_write(Model m) {
		m.addAttribute("category",cs.get_cate());
		return "/product/product_write";
	}
	
	//상품 등록
	@PostMapping("product_add.do")
	public String product_add(@ModelAttribute ProductsDto pt, HttpServletRequest req) throws IOException {
		boolean addResult = ps.add_products(pt);
		if(addResult) {
			pt.setPidx(ps.get_pidx());
			System.out.println(ps.get_pidx());
			fs.add_file(pt, req);
		}
		return "redirect:/product/product_list.do";
	}
	
}
