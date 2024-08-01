package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.shopping_mapper;

@Service
public class products_service {

	@Autowired
	private shopping_mapper mp;
	
}
