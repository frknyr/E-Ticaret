package eticaret.business.responses.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllByModelIdAndSellerIdProductsInResponse {
	private String name;
	private int unitPrice;
	private int quantityStock;
	private String brandName;
	private String modelName;
	private String sellerName;
}
