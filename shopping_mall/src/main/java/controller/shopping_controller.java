package controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.shopping_dao;
import dto.pay_dto;
import dto.siteinfo_dto;

@Controller
public class shopping_controller {
	
	@Resource(name="shoppingdao")
	private shopping_dao sd;
	
	@RequestMapping("/admin/siteinfo.do")
	public String admin_siteinfo(siteinfo_dto st, pay_dto pt, Model m) {
		if(st.getPage_name()!=null && pt.getDeli_name()!=null) {
			m.addAttribute("siteinfo",sd.site_insert(st)); 
			m.addAttribute("pay",sd.pay_insert(pt)); 			
		}else {
			m.addAttribute("siteinfo","");
			m.addAttribute("pay","");
		}
		return "/admin/admin_siteinfo";
	}
}
