package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dto.CateDto;
import dto.ProductsDto;
import mapper.ShoppingMapper;
import model.ProductFile;
import utils.FileUtils;

@Service
public class FileService {
	
	@Autowired
	private ShoppingMapper mp;
	private FileUtils fu = new FileUtils();
	public boolean add_file(ProductsDto dto, HttpServletRequest req) throws IOException {
		dto = fu.file_url(dto, req);
		return mp.product_file_insert(dto) > 0;
	}
	
	public List<ProductsDto> get_productFile(){
		return mp.product_file_select();
	}
	
	public List<ProductsDto> get_productFile_page(int page, int size){
		return mp.product_file_select_page(page, size);
	}
	
	public List<ProductsDto> search_productFile(int page, int size, List<Integer> list){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("size", size);
		map.put("list", list);
		if(list.size() < 1) {
			list.add(0);
		}
		return mp.product_file_search(map);
	}
	
	public boolean del_productFile(List<String> pidx) {
		List<ProductsDto> file = mp.product_file_select();
		//fu.deleteProductFiles(file);
		return mp.product_file_delete(pidx) > 0;
	}
}
