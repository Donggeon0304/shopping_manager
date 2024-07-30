package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class siteinfo_dto {
	private int sidx;
	private String page_name, admin_email, point_use, join_point, join_level, com_name, business_num,
			ceo_name, ceo_tel, mob_num, vat_num, com_post, com_address, info_name, info_email;
}
