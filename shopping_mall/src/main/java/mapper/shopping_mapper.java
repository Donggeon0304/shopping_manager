package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dto.admin_dto;
import dto.pay_dto;
import dto.siteinfo_dto;

@Mapper
public interface shopping_mapper {
	
	admin_dto admin_selectOne(String aid);
	int admin_insert(admin_dto dto);
	int admin_idcheck(String aid);
	List<admin_dto> admin_select();
	int admin_update(admin_dto dto);
	
    int site_insert(siteinfo_dto dto);
    siteinfo_dto site_select();
    int site_selectOne();
    int site_update(siteinfo_dto dto);
    
    int pay_insert(pay_dto dto);
    pay_dto pay_select();
    int pay_update(pay_dto dto);
}