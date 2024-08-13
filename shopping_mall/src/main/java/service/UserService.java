package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.AdminDto;
import dto.UserDto;
import mapper.ShoppingMapper;
import utils.Md5Utils;

@Service
public class UserService {

	@Autowired
	private ShoppingMapper mp;
	
	public boolean getUserId(String user_id) {
		return mp.user_select_id(user_id) > 0;
	}
	
	public boolean addUser(UserDto dto) {
		String upass = new Md5Utils().md5_making(dto.getUser_password());
		dto.setUser_password(upass);
		dto.setUser_use("Y");
		if(dto.getEvent_email() == null) {
			dto.setEvent_email("N");
		}
		if(dto.getEvent_sms() == null) {
			dto.setEvent_sms("N");
		}
		return mp.user_insert(dto)>0;
	}
	
	public List<UserDto> getUser(){
		List<UserDto> result = mp.user_select();
		for(UserDto dto : result) {
			dto.setJoin_date(dto.getJoin_date().substring(0,10));
		}
		return result;
	}
	
	public boolean modifyUser(String user_use, int uidx) {
		return mp.user_update(user_use, uidx) > 0;
	}
	
	public UserDto loginUser(String user_use) {
		return mp.user_select_one(user_use);
	}
}
