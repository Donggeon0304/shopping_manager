package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dto.AdminDto;
import dto.CateDto;
import dto.PayInfoDto;
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
    
    int pay_insert(PayInfoDto dto);
    PayInfoDto pay_select();
    int pay_update(PayInfoDto dto);
    
    int cate_insert(CateDto dto);
    List<CateDto> cate_select();
    List<CateDto> cate_select_page(@Param("page") int page, @Param("size") int size);
    List<CateDto> cate_search(Map<String, Object> map);
    int cate_ck_search(Map<String, String> map);
    int cate_delete(List<String> list);
    int cate_selectCidx(String cate);
    int cate_ck();
    
    int products_insert(ProductsDto dto);
    int products_pidx();
    List<ProductsDto> products_select();
    List<ProductsDto> products_select_page(@Param("page") int page, @Param("size") int size);
    List<ProductsDto> products_search(Map<String, Object> map);
    int products_ck_search(Map<String, String> map);
    int products_delete(List<String> list);
    int products_codeck(String pcode);
    int products_cateck(int cidx);
    int products_ck();
    
    int product_file_insert(ProductsDto dto);
    List<ProductsDto> product_file_select();
    List<ProductsDto> product_file_select_page(@Param("page") int page, @Param("size") int size);
    List<ProductsDto> product_file_search(Map<String, Object> map);
    int product_file_delete(List<String> list);
}