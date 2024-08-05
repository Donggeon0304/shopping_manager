package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dto.ProductsDto;
import mapper.ShoppingMapper;
import model.ProductFile;
import utils.FileUtils;

@Service
public class FileService {
	
	@Autowired
	private ShoppingMapper mp;
	
	public boolean add_file(ProductsDto dto, HttpServletRequest req) throws IOException {
		dto = new FileUtils().file_url(dto, req);
		return mp.productFile_insert(dto) > 0;
	}
	
	public List<ProductsDto> get_productFile(){
		return mp.product_file_select();
	}
}