package shopping_admin;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import shopping_module.shop_md5;

@Repository("admindao")
public class admin_dao {
	
	@Resource(name="template2")
	private SqlSessionTemplate tp;
	
	//회원가입
	public String admin_insert(admin_dto at) {
		String apass = new shop_md5().md5_making(at.apass);
		String atel = at.atel;
		atel = atel.replaceAll(",", "");
		at.setAtel(atel);
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
			int result = tp.selectOne("shopping_mall.idck",aid);
			return result;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
