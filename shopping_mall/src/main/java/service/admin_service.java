package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.admin_dto;
import mapper.shopping_mapper;

@Service
public class admin_service {

	@Autowired
	private shopping_mapper mp;
	
	public admin_dto get_admin(String aid) {
		return mp.admin_selectOne(aid);
	}
	
	//관리자 회원가입
	public String add_admin(admin_dto at) {
		String apass = new md5_service().md5_making(at.getApass());
		at.setApass(apass);
		String atel = at.getAtel();
		atel = atel.replaceAll(",", "");
		at.setAtel(atel);
		at.setAuse("N");
		int result = mp.admin_insert(at);
		return result > 0 ? "success" : "fail";
	}
	
	//관리자 아이디 중복체크
	public boolean idcheck_admin(String aid) {
		return mp.admin_idcheck(aid) > 0 ;
	}
	
	//관리자 리스트
	public List<admin_dto> get_alladmin() {
		List<admin_dto> result = mp.admin_select();
		for(admin_dto at : result) {
			at.setAjoin_date(at.getAjoin_date().substring(0,10));
		}
		return result;
	}
	
	//관리자 승인요청
	public String update_admin(String ause, int aidx) {
		admin_dto at = new admin_dto();
		at.setAuse(ause);
		at.setAidx(aidx);
		int result = mp.admin_update(at);
		return result > 0 ? "ok" : "no";
	}
	
}
