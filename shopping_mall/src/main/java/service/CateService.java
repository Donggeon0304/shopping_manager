package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.CateDto;
import dto.ProductsDto;
import mapper.ShoppingMapper;

@Service
public class CateService {

	@Autowired
	ShoppingMapper mp;
	
	public boolean add_cate(CateDto dto) {
		return mp.cate_insert(dto) > 0;
	}
	
	public List<CateDto> get_cate(){
		return mp.cate_select();
	}
	
	public List<CateDto> get_cate_page(int page, int size){
		return mp.cate_select_page(page, size);
	}
	
	public List<CateDto> search_cate(int page, int size, String search_part, String search_word){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("size", size);
		map.put("search_part", search_part);
		map.put("search_word", search_word);
		return mp.cate_search(map);
	}
	
	public int search_ck_cate(String search_part, String search_word) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("search_part", search_part);
		map.put("search_word", search_word);
		return mp.cate_ck_search(map);
	}
	
	public int ck_cate() {
		return mp.cate_ck();
	}
	
	public boolean del_cate(List<String> cidx) {
		return mp.cate_delete(cidx) > 0;
	}
	
	public int get_cidx(String cate) {
		return mp.cate_selectCidx(cate);
	}
}
