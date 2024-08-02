package service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.PayDto;
import dto.SiteInfoDto;
import dto.SitePayDto;
import mapper.ShoppingMapper;

@Service
public class SiteService {
	
	@Autowired
	ShoppingMapper mp;
	
	public String add_site(SiteInfoDto dto) {
		return mp.site_insert(dto) > 0 ? "success" : "fail";
	}
	
	public String add_pay(PayDto dto) {
		return mp.pay_insert(dto) > 0 ? "success" : "fail";
	}
	
	public SiteInfoDto get_site() {
		return mp.site_select();
	}
	
	public PayDto get_pay() {
		return mp.pay_select();
	}
	
	public boolean update_site(SiteInfoDto dto) {
		return mp.site_update(dto) > 0;
	}
	
	public boolean update_pay(PayDto dto) {
		return mp.pay_update(dto) > 0;
	}
	
	public boolean get_siteOne() {
		return mp.site_selectOne() > 0;
	}
	
    public SitePayDto get_allinfo() {
        SitePayDto dto = new SitePayDto();

        SiteInfoDto siteinfo = mp.site_select();
        dto.setSiteinfo_dto(siteinfo);

        PayDto pay = mp.pay_select();
        dto.setPay_dto(pay);

        return dto;
    }
}
