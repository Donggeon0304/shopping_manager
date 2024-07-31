package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import dto.admin_dto;
import service.md5_service;

@Repository("admindao")
public class admin_dao {
	/*
	@Resource(name="template")
	private SqlSessionTemplate tp;
	
	//회원가입
	public String admin_insert(admin_dto at) {
		String apass = new md5_service().md5_making(at.getApass());
		at.setApass(apass);
		String atel = at.getAtel();
		atel = atel.replaceAll(",", "");
		at.setAtel(atel);
		at.setAuse("N");
		try{
			tp.insert("shopping_mall.insert",at);
			return "success";
		}catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	//아이디 중복체크
	public int admin_idselect(String aid) {
		try {
			System.out.println(aid);
			admin_dto result = tp.selectOne("shopping_mall.idck",aid);
			System.out.println(result.getAid());
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	//관리자 로그인 정보 확인
	public admin_dto admin_selectOne(String aid) {
		try {
			admin_dto result = tp.selectOne("shopping_mall.idck",aid);
			return result;
		}catch (Exception e) {
			return null;
		}
	}
	
	//관리자 리스트
	public List<admin_dto> admin_select() {
		List<admin_dto> result = tp.selectList("shopping_mall.select");
		for(admin_dto at : result) {
			at.setAjoin_date(at.getAjoin_date().substring(0,10));
		}
		return result;
	}
	
	//관리자 사용승인
	public String admin_update(String ause, String aidx) {
		Map<String, Object> data = new HashMap<>();
		data.put("ause", ause);
		data.put("aidx", aidx);
		try {
			tp.update("shopping_mall.update",data);
			return "ok";
		}catch(Exception e) {
			return "no";
		}
	}
	*/
}
