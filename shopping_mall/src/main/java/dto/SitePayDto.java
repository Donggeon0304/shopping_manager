package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SitePayDto {
	private SiteInfoDto siteinfo_dto;
	private PayDto pay_dto;
	
	public SitePayDto() {
		this.siteinfo_dto = new SiteInfoDto();
		this.pay_dto = new PayDto();
	}
}
