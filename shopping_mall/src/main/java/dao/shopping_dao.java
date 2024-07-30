package dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import dto.pay_dto;
import dto.siteinfo_dto;

@Repository("shoppingdao")
public class shopping_dao {

	@Resource(name="template2")
	private SqlSessionTemplate tp;
	
	//stieinfo insert
	public String site_insert(siteinfo_dto sd) {
		tp.insert("shopping_mall.site_insert",sd);
		return "ok";
	}
	
	//pay insert
	public String pay_insert(pay_dto sd) {
		tp.insert("shopping_mall.pay_insert",sd);
		return "ok";
	}
	
	//siteinfo select
	public List<siteinfo_dto> site_select(){
		return tp.selectList("shopping_mall.site_select");
	}
	
	//pay select
	public List<pay_dto> pay_select(){
		return tp.selectList("shopping_mall.pay_select");
	}
	
}
