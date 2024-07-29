package com.coupangmall.www;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

//user table (select, insert, update, delete)
@Repository("userdao")
public class user_dao {
	@Resource(name="template")
	private SqlSessionTemplate tp;
	
	public user_dto search_id(String uname, String uemail) {
		Map<String, String> data = new HashMap<>();
		data.put("part", "1");
		data.put("uname", uname);
		data.put("uemail", uemail);
		try {
			user_dto result = tp.selectOne("shopping_mall.search",data);
			return result;
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public user_dto search_pw(String uid, String uname) {
		Map<String, String> data = new HashMap<>();
		data.put("part", "2");
		data.put("uid", uid);
		data.put("uname", uname);
		try {
			user_dto result = tp.selectOne("shopping_mall.search",data);
			return result;
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
