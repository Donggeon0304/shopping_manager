package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.UserDto;
import mapper.ShoppingMapper;

@Service
public class UserService {

	@Autowired
	private ShoppingMapper mp;
	
	public boolean getUserId(String user_id) {
		return mp.user_select_id(user_id) > 0;
	}
	
	public boolean addUser(UserDto dto) {
		return mp.user_insert(dto)>0;
	}
}
