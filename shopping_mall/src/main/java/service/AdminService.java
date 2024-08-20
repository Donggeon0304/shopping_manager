package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.AdminDto;
import dto.TermsDto;
import mapper.ShoppingMapper;
import utils.Md5Utils;

@Service
public class AdminService {

	@Autowired
	private ShoppingMapper mp;
	
	public AdminDto get_admin(String aid) {
		return mp.admin_selectOne(aid);
	}
	
	//관리자 회원가입
	public String add_admin(AdminDto at) {
		String apass = new Md5Utils().md5_making(at.getApass());
		at.setApass(apass);
		String atel = at.getAtel();
		atel = atel.replaceAll(",", "");
		at.setAtel(atel);
		System.out.println(at.getAtel());
		at.setAuse("N");
		int result = mp.admin_insert(at);
		return result > 0 ? "success" : "fail";
	}
	
	//관리자 아이디 중복체크
	public boolean idcheck_admin(String aid) {
		return mp.admin_idcheck(aid) > 0 ;
	}
	
	//관리자 리스트
	public List<AdminDto> get_alladmin() {
		List<AdminDto> result = mp.admin_select();
		for(AdminDto at : result) {
			at.setAjoin_date(at.getAjoin_date().substring(0,10));
		}
		return result;
	}
	
	//관리자 승인요청
	public String update_admin(String ause, int aidx) {
		AdminDto at = new AdminDto();
		at.setAuse(ause);
		at.setAidx(aidx);
		int result = mp.admin_update(at);
		return result > 0 ? "ok" : "no";
	}
	
	//이용약관 수정
	public boolean useTerms(String use_terms) {
		return mp.use_terms(use_terms)>0;
	}
	
	//개인정보 약관 수정
	public boolean personalTerms(String personal_terms) {
		return mp.personal_terms(personal_terms)>0;
	}
	
	//약관정보 데이터
	public TermsDto getTerms() {
		return mp.terms_select();
	}
	
}
