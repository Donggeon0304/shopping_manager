package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.cate_dto;
import mapper.shopping_mapper;

@Service
public class cate_service {

	@Autowired
	shopping_mapper mp;
	
	public boolean add_cate(cate_dto dto) {
		return mp.cate_insert(dto) > 0;
	}
	
	public List<cate_dto> get_cate(){
		return mp.cate_select();
	}
	
	public boolean del_cate(List<String> cidx) {
		return mp.cate_delete(cidx) > 0;
	}
}
