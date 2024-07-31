package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.sitepay_dto;
import service.site_service;

@Controller
@RequestMapping("/admin")
public class site_controller {
	
	@Autowired
	private site_service sv;
	
	//저장된 정보 출력
	@GetMapping("/siteinfo.do")
	public String admin_siteinfo(Model m) {
		m.addAttribute("site",sv.get_site());
		m.addAttribute("pay",sv.get_pay());
		return "/admin/admin_siteinfo";
	}
	
	//정보 저장
	@PostMapping("/siteinsert.do")
	public ResponseEntity<String> admin_siteinsert(@RequestBody sitepay_dto spt) {
		if(sv.get_siteOne()) {
			boolean result1 = sv.update_site(spt.getSiteinfo_dto());
			boolean result2 = sv.update_pay(spt.getPay_dto());
			if(result1&&result2) {
				return ResponseEntity.ok("ok");				
			}else {
				return ResponseEntity.ok("no");
			}
		}else {
			String result1 = sv.add_site(spt.getSiteinfo_dto());
			String result2 = sv.add_pay(spt.getPay_dto());
			if(result1.equals("success") && result2.equals("success")) {
				return ResponseEntity.ok("ok");
			}else {
				return ResponseEntity.ok("no");				
			}
		}
	}
}
