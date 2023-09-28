package eticaret.business.abstracts;

import java.util.List;

import eticaret.business.request.category.CreateCategoryRequest;
import eticaret.business.request.category.UpdateCategoryRequest;
import eticaret.business.responses.category.GetAllByIdCategoriesInResponse;
import eticaret.business.responses.category.GetAllCategoriesResponse;
import eticaret.business.responses.category.GetByIdCategoryResponse;

public interface CategoryService {
	void add(CreateCategoryRequest createCategoryRequest);
	void delete(int id);
	void update(UpdateCategoryRequest updateCategoryRequest);
	
	GetByIdCategoryResponse getByIdCategoryResponse(int id);
	
	List<GetAllCategoriesResponse> getAllCategoriesResponses();
	List<GetAllByIdCategoriesInResponse> getAllByIdCategoriesInResponses(List<Integer> ids);
}
