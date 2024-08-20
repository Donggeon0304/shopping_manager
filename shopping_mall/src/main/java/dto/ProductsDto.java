package dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductsDto {
	private int pidx, cidx;
	private String cate, sm_cate, pcode, pname, padd_text, price, pdc_per, pdc_price, 
	pstock, puse, psold_out, ptext, pdate;;
	private MultipartFile pfile, padd_file1, padd_file2;
	
	private int idx;
	private String mfile_name, mfile_url, file1_name, file1_url, file2_name, file2_url;
}