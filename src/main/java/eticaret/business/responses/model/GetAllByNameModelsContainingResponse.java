package eticaret.business.responses.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllByNameModelsContainingResponse {
	
	private int id;
	private String brandName;
	private String modelName;
}
