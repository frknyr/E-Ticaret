package eticaret.business.responses.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllByBrandIdAndModelIdProductsInResponse {
	private String name;
	private int unitPrice;
	private String modelName;
	private String categoryName;
	private String sellerName;
}
