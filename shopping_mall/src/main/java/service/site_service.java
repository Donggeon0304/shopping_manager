package service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.pay_dto;
import dto.siteinfo_dto;
import dto.sitepay_dto;
import mapper.shopping_mapper;

@Service
public class site_service {
	
	@Autowired
	shopping_mapper mp;
	
	public String add_site(siteinfo_dto dto) {
		return mp.site_insert(dto) > 0 ? "success" : "fail";
	}
	
	public String add_pay(pay_dto dto) {
		return mp.pay_insert(dto) > 0 ? "success" : "fail";
	}
	
	public siteinfo_dto get_site() {
		return mp.site_select();
	}
	
	public pay_dto get_pay() {
		return mp.pay_select();
	}
	
	public boolean update_site(siteinfo_dto dto) {
		return mp.site_update(dto) > 0;
	}
	
	public boolean update_pay(pay_dto dto) {
		return mp.pay_update(dto) > 0;
	}
	
	public boolean get_siteOne() {
		return mp.site_selectOne() > 0;
	}
	
    public sitepay_dto get_allinfo() {
        sitepay_dto dto = new sitepay_dto();

        siteinfo_dto siteinfo = mp.site_select();
        dto.setSiteinfo_dto(siteinfo);

        pay_dto pay = mp.pay_select();
        dto.setPay_dto(pay);

        return dto;
    }
}
