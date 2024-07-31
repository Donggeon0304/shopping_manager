package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class sitepay_dto {
	private siteinfo_dto siteinfo_dto;
	private pay_dto pay_dto;
	
	public sitepay_dto() {
		this.siteinfo_dto = new siteinfo_dto();
		this.pay_dto = new pay_dto();
	}
}
