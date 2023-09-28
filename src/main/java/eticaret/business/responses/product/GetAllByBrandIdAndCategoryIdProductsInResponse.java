package eticaret.business.responses.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllByBrandIdAndCategoryIdProductsInResponse {
	private String name;
	private int unitPrice;
	private String brandName;
	private String categoryName;
	private String sellerName;
}
