package eticaret.business.request.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateModelRequest {
	
	@NotBlank
	@NotNull
	private int id;
	
	@NotBlank
	@NotNull
	@Size(min = 2, max = 30)
	private String name;
	
	@NotBlank
	@NotNull
	private int brandId;
}
