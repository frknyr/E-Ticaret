package eticaret.business.abstracts;

import java.util.List;

import eticaret.business.request.brand.CreateBrandRequest;
import eticaret.business.request.brand.UpdateBrandRequest;
import eticaret.business.responses.brand.GetAllBrandsResponse;
import eticaret.business.responses.brand.GetAllByIdBrandsInResponse;
import eticaret.business.responses.brand.GetAllByNameBrandsContainingResponse;
import eticaret.business.responses.brand.GetByIdBrandResponse;

public interface BrandService {
	void add(CreateBrandRequest createBrandRequest);
	void delete(int id);
	void update(UpdateBrandRequest updateBrandRequest);
	
	GetByIdBrandResponse getByBrandIdResponse(int id);
	
	List<GetAllBrandsResponse> getAllBrandsResponses();
	List<GetAllByIdBrandsInResponse> getAllByBrandsIdInResponses(List<Integer> brandsId);
	List<GetAllByNameBrandsContainingResponse> getAllByNameBrandsContainings(String name);
}
