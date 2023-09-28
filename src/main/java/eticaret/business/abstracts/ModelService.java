package eticaret.business.abstracts;

import java.util.List;

import eticaret.business.request.model.CreateModelRequest;
import eticaret.business.request.model.UpdateModelRequest;
import eticaret.business.responses.model.GetAllByBrandIdAndIdInResponse;
import eticaret.business.responses.model.GetAllByIdModelsInResponse;
import eticaret.business.responses.model.GetAllByNameModelsContainingResponse;
import eticaret.business.responses.model.GetAllModelsResponse;
import eticaret.business.responses.model.GetByIdModelResponse;

public interface ModelService {
	void add(CreateModelRequest createModelRequest);
	void delete(int id);
	void update(UpdateModelRequest updateModelRequest);
	
	GetByIdModelResponse getByIdModelResponse(int id);
	
	List<GetAllModelsResponse> getAllModelsResponses();
	List<GetAllByIdModelsInResponse> getAllByIdModelsInResponses(List<Integer> ids);
	List<GetAllByNameModelsContainingResponse> getAllByNameModelsContainings(String name);
	List<GetAllByBrandIdAndIdInResponse> getAllByBrandIdAndIdInResponses(int brandId, List<Integer> ids);
}
