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

import eticaret.business.abstracts.ModelService;
import eticaret.business.request.model.CreateModelRequest;
import eticaret.business.request.model.UpdateModelRequest;
import eticaret.business.responses.model.GetAllByBrandIdAndIdInResponse;
import eticaret.business.responses.model.GetAllByIdModelsInResponse;
import eticaret.business.responses.model.GetAllByNameModelsContainingResponse;
import eticaret.business.responses.model.GetAllModelsResponse;
import eticaret.business.responses.model.GetByIdModelResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/models")
@AllArgsConstructor
public class ModelsController {
	
	private ModelService modelService;
	
	//---------------------- MODEL EKLEME ---------------------------
	@PostMapping("/createModel")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void add(@RequestBody CreateModelRequest createModelRequest) {
		this.modelService.add(createModelRequest);
	}
	
	//--------------------- MODEL SİLME ---------------------------------
	@DeleteMapping("/deleteModel/{id}")
	public void delete(@PathVariable int id) {
		this.modelService.delete(id);
	}
	
	//--------------------- MODEL GÜNCELLEME --------------------------
	@PutMapping("/updateModel")
	public void update(@RequestBody UpdateModelRequest updateModelRequest) {
		this.modelService.update(updateModelRequest);
	}
	
	//-------------------- BÜTÜN MODELLERİ GETİRİR ------------------------
	@GetMapping("/getAllModels")
	public List<GetAllModelsResponse> getAllModelsResponses(){
		return this.modelService.getAllModelsResponses();
	}
	
	//-------------------- ID NUMARASI VERİLEN IDLERİN İÇİNDE OLAN MODELLERİ GETİRİR ----------
	@GetMapping("/getAllByIdModelsIn/{ids}")
	public List<GetAllByIdModelsInResponse> getAllByIdModelsInResponses(@PathVariable List<Integer> ids){
		return this.modelService.getAllByIdModelsInResponses(ids);
	}
	
	
	//--------------------- ADINDA VERİLEN METİNİ İÇEREN MODELLERİ GETİRİR ------------------
	@GetMapping("/getAllByNameBrandsContaining/{name}")
	public List<GetAllByNameModelsContainingResponse> getAllByNameModelsContainings(@PathVariable String name){
		return this.modelService.getAllByNameModelsContainings(name);
	}
	
	//------------------ SEÇİLEN MARKANIN SEÇİLEN MODELLERİNİ GETİRİR -----------------------
	@GetMapping("/getAllByBrandIdAndIdIn/{brandId}/{ids}")
	public List<GetAllByBrandIdAndIdInResponse> getAllByBrandIdAndIdInResponses(@PathVariable int brandId,@PathVariable List<Integer> ids){
		return this.modelService.getAllByBrandIdAndIdInResponses(brandId, ids);
	}
	
	//------------------ VERİLEN ID'YE SAHİP OLAN MARKAYI GETİRİR ------------------------------
	@GetMapping("/getByIdModel/{id}")
	public GetByIdModelResponse getByIdModelResponse(@PathVariable int id) {
		return this.modelService.getByIdModelResponse(id);
	}
	
	
	
	
	
	
	
	
	
	
}
