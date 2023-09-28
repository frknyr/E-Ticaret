package eticaret.business.request.product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
	
	@NotBlank
	@NotNull
	@Size(min = 2, max = 30)
	private String name;
	
	@NotBlank
	@NotNull
	@Size(min = 0)
	private int unitPrice;
	
	@NotBlank
	@NotNull
	@Size(min = 1)
	private int quantityStock;
	
	@NotBlank
	@NotNull
	private int brandId;
	
	@NotBlank
	@NotNull
	private int modelId;
	
	@NotBlank
	@NotNull
	private int categoryId;
	
	@NotBlank
	@NotNull
	private int sellerId;
}
