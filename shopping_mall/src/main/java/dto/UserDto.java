package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	private int uidx;
	private String user_id, user_password, user_name, user_email, 
	user_tel, event_email, event_sms, user_use, join_date;
	
	private String code;
}
