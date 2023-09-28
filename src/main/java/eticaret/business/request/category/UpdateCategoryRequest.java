package eticaret.business.request.category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryRequest {
	
	@NotBlank
	@NotNull
	private int id;
	
	@NotBlank
	@NotNull
	@Size(min = 2,max = 30)
	private String name;
}
