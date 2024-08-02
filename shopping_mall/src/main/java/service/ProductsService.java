package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.ProductsDto;
import mapper.ShoppingMapper;
import model.Products;

@Service
public class ProductsService {

	@Autowired
	private ShoppingMapper mp;
	
	public boolean add_products(ProductsDto dto) {
		return mp.products_insert(dto) > 0;
	}
	
	public int get_pidx() {
		return mp.products_pidx();
	}
	
	public List<ProductsDto> get_products(){
		return mp.products_select();
	}
}