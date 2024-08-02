package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Products {
	private int pidx;
	private String cate, pcode, pname, padd_text, price, pdc_per, pdc_price, 
	pstock, puse, psold_out, ptext, pdate;
}
