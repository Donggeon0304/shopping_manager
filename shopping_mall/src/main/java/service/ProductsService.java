package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.CateDto;
import dto.ProductsDto;
import mapper.ShoppingMapper;
import model.Products;

@Service
public class ProductsService {

	@Autowired
	private ShoppingMapper mp;
	
	public boolean add_products(ProductsDto dto) {
		if(dto.getSm_cate().equals("")) {
			dto.setSm_cate("-");
		}
		return mp.products_insert(dto) > 0;
	}
	
	public boolean codeck_products(String pcode) {
		return mp.products_codeck(pcode) > 0;
	}
	
	public boolean cateck_products(int cidx) {
		return mp.products_cateck(cidx) > 0;
	}
	
	public int get_pidx() {
		return mp.products_pidx();
	}
	
	public List<ProductsDto> get_products(){
		return mp.products_select();
	}
	
	public List<ProductsDto> get_products_page(int page, int size){
		return mp.products_select_page(page, size);
	}
	
	public int ck_products() {
		return mp.products_ck();
	}
	
	public List<ProductsDto> search_products(int page, int size, String search_part, String search_word){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("size", size);
		map.put("search_part", search_part);
		map.put("search_word", search_word);
		return mp.products_search(map);
	}
	
	public int search_ck_products(String search_part, String search_word) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("search_part", search_part);
		map.put("search_word", search_word);
		return mp.products_ck_search(map);
	}
	
	public boolean del_products(List<String> pidx) {
		return mp.products_delete(pidx) > 0;
	}
	
}