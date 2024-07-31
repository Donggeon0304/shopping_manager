package dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import dto.pay_dto;
import dto.siteinfo_dto;
import dto.sitepay_dto;

@Repository("shoppingdao")
public class shopping_dao {
/*
	@Resource(name="template")
	private SqlSessionTemplate tp;
	
	//siteinfo insert
	public String site_insert(siteinfo_dto sd) {
		tp.insert("shopping_mall.site_insert",sd);
		return "ok";
	}
	
	//pay insert
	public String pay_insert(pay_dto sd) {
		tp.insert("shopping_mall.pay_insert",sd);
		return "ok";
	}
	
	public sitepay_dto getSitePayData() {
	    sitepay_dto dto = new sitepay_dto();

	    // siteinfo 데이터 조회
	    siteinfo_dto siteInfo = tp.selectOne("shopping_mall.site_select");
	    dto.setSiteinfo_dto(siteInfo);

	    // pay 데이터 조회
	    pay_dto payInfo = tp.selectOne("shopping_mall.pay_select");
	    dto.setPay_dto(payInfo);

	    return dto;
	}*/
}
