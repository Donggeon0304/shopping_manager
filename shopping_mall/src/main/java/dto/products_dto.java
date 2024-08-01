package dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class products_dto {
	private int pidx;
	private String cate, pcode, pname, padd_text, price, pdc_per, pdc_price, 
	puse, psold_out, pimg_name, pimg_url, paddimg_name, paddimg_url, ptext, pdata;
	private MultipartFile pfile, padd_file1, padd_file2;
}
