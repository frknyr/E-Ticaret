package eticaret.webApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import eticaret.business.abstracts.CategoryService;
import eticaret.business.request.category.CreateCategoryRequest;
import eticaret.business.request.category.UpdateCategoryRequest;
import eticaret.business.responses.category.GetAllByIdCategoriesInResponse;
import eticaret.business.responses.category.GetAllCategoriesResponse;
import eticaret.business.responses.category.GetByIdCategoryResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoriesController {
	
	private CategoryService categoryService;
	
	//------------------- KATEGORİ EKLER --------------------------------
	@PostMapping("/createCategory")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void add(@RequestBody CreateCategoryRequest createCategoryRequest) {
		this.categoryService.add(createCategoryRequest);
	}
	
	//------------------- KATEGORİ SİLER --------------------------------
	@DeleteMapping("/deleteCategory/{id}")
	public void delete(@PathVariable int id) {
		this.categoryService.delete(id);
	}
	
	//------------------- KATEGORİ GÜNCELLER --------------------------------
	@PutMapping("/updateCategory")
	public void update(@RequestBody UpdateCategoryRequest updateCategoryRequest) {
		this.categoryService.update(updateCategoryRequest);
	}
	
	//------------------- VERİLEN ID'YE AİT KATEGORİYİ GETİRİR --------------------------------
	@GetMapping("/getByIdCategory/{id}")
	public GetByIdCategoryResponse getByIdCategoryResponse(int id) {
		return this.categoryService.getByIdCategoryResponse(id);
	}
	
	//------------------- BÜTÜN KATEGORİLERİ GETİRİR --------------------------------
	@GetMapping("/getAllCategories")
	public List<GetAllCategoriesResponse> getAllCategoriesResponses(){
		return this.categoryService.getAllCategoriesResponses();
	}
	
	//------------------- VERİLEN ID LİSTESİNE GÖRE KATEGORİLERİ GETİRİR --------------------------------
	@GetMapping("/getAllByIdCategoriesIn/{ids}")
	public List<GetAllByIdCategoriesInResponse> getAllByIdCategoriesInResponses(@PathVariable List<Integer> ids){
		return this.categoryService.getAllByIdCategoriesInResponses(ids);
	}
	
	
	
	
}
