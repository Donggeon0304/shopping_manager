package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dto.AdminDto;
import dto.CateDto;
import dto.PayDto;
import dto.ProductsDto;
import dto.SiteInfoDto;
import model.ProductFile;
import model.Products;

@Mapper
public interface ShoppingMapper {
	
	AdminDto admin_selectOne(String aid);
	int admin_insert(AdminDto dto);
	int admin_idcheck(String aid);
	List<AdminDto> admin_select();
	int admin_update(AdminDto dto);
	
    int site_insert(SiteInfoDto dto);
    SiteInfoDto site_select();
    int site_selectOne();
    int site_update(SiteInfoDto dto);
    
    int pay_insert(PayDto dto);
    PayDto pay_select();
    int pay_update(PayDto dto);
    
    int cate_insert(CateDto dto);
    List<CateDto> cate_select();
    int cate_delete(List<String> cidx);
    
    int products_insert(ProductsDto dto);
    int products_pidx();
    int productFile_insert(ProductsDto dto);
    List<ProductsDto> products_select();
    List<ProductsDto> product_file_select();
}