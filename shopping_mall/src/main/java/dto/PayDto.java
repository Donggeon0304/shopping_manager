package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayDto {
	private int pidx;
	private String bank, account_num, card_use, phone_use, giftcard_use,
	min_point, max_point, cash_receipt, deli_name, deli_price, deli_date;
}
