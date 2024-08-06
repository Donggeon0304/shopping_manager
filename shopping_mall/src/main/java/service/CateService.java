package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.CateDto;
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
