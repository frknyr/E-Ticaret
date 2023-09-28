package eticaret.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import eticaret.business.abstracts.CategoryService;
import eticaret.business.request.category.CreateCategoryRequest;
import eticaret.business.request.category.UpdateCategoryRequest;
import eticaret.business.responses.category.GetAllByIdCategoriesInResponse;
import eticaret.business.responses.category.GetAllCategoriesResponse;
import eticaret.business.responses.category.GetByIdCategoryResponse;
import eticaret.business.rules.CategoryBusinessRules;
import eticaret.core.mappers.abstracts.ModelMapperService;
import eticaret.dataAccess.CategoryRepository;
import eticaret.entities.Category;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService{
	
	private CategoryRepository categoryRepository;
	private ModelMapperService modelMapperService;
	private CategoryBusinessRules categoryBusinessRules;
	
	//------------------- KATEGORİ EKLER --------------------------------
	@Override
	public void add(CreateCategoryRequest createCategoryRequest) {
		
		this.categoryBusinessRules.checkIfCategoryNameExists(createCategoryRequest.getName());
		
		Category category= this.modelMapperService.forRequest()
				.map(createCategoryRequest, Category.class);
		
		this.categoryRepository.save(category);
	}

	//------------------- KATEGORİ SİLER --------------------------------
	@Override
	public void delete(int id) {
		
		this.categoryBusinessRules.checkIfCategoryIdExists(id);
		
		this.categoryRepository.deleteById(id);
	}
	
	//------------------- KATEGORİ GÜNCELLER --------------------------------
	@Override
	public void update(UpdateCategoryRequest updateCategoryRequest) {
		
		this.categoryBusinessRules.checkIfCategoryIdExists(updateCategoryRequest.getId());
		this.categoryBusinessRules.checkIfCategoryNameExists(updateCategoryRequest.getName());
		
		Category category= this.modelMapperService.forRequest()
				.map(updateCategoryRequest, Category.class);
		
		this.categoryRepository.save(category);
	}

	//------------------- VERİLEN ID'YE AİT KATEGORİYİ GETİRİR --------------------------------
	@Override
	public GetByIdCategoryResponse getByIdCategoryResponse(int id) {
		
		this.categoryBusinessRules.checkIfCategoryIdExists(id);
		
		Category category= this.categoryRepository.findById(id).orElseThrow();
		
		GetByIdCategoryResponse getByIdCategoryResponse= this.modelMapperService.forResponse()
				.map(category, GetByIdCategoryResponse.class);
		
		return getByIdCategoryResponse;
	}

	//------------------- BÜTÜN KATEGORİLERİ GETİRİR --------------------------------
	@Override
	public List<GetAllCategoriesResponse> getAllCategoriesResponses() {
		List<Category> categories= this.categoryRepository.findAll();
		
		List<GetAllCategoriesResponse> getAllCategoriesResponses= categories.stream()
				.map(category-> this.modelMapperService.forResponse().map(category, GetAllCategoriesResponse.class))
				.toList();
		
		return getAllCategoriesResponses;
	}

	//------------------- VERİLEN ID LİSTESİNE GÖRE KATEGORİLERİ GETİRİR --------------------------------
	@Override
	public List<GetAllByIdCategoriesInResponse> getAllByIdCategoriesInResponses(List<Integer> ids) {
		
		this.categoryBusinessRules.checkIfCategoryIdsExists(ids);
		
		List<Category> categories= this.categoryRepository.findAllById(ids);
		
		List<GetAllByIdCategoriesInResponse> getAllByIdCategoriesInResponses= categories.stream()
				.map(category-> this.modelMapperService.forResponse().map(category, GetAllByIdCategoriesInResponse.class))
				.toList();
		
		return getAllByIdCategoriesInResponses;
	}

}
