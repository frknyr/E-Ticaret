package eticaret.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import eticaret.business.abstracts.ModelService;
import eticaret.business.request.model.CreateModelRequest;
import eticaret.business.request.model.UpdateModelRequest;
import eticaret.business.responses.model.GetAllByBrandIdAndIdInResponse;
import eticaret.business.responses.model.GetAllByIdModelsInResponse;
import eticaret.business.responses.model.GetAllByNameModelsContainingResponse;
import eticaret.business.responses.model.GetAllModelsResponse;
import eticaret.business.responses.model.GetByIdModelResponse;
import eticaret.business.rules.BrandBusinessRules;
import eticaret.business.rules.ModelBusinessRules;
import eticaret.core.mappers.abstracts.ModelMapperService;
import eticaret.dataAccess.ModelRepository;
import eticaret.entities.Model;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService{
	
	private ModelRepository modelRepository;
	private ModelMapperService modelMapperService;
	private ModelBusinessRules modelBusinessRules;
	private BrandBusinessRules brandBusinessRules;
	
	//------------------------ MODEL EKLEME --------------------------
	@Override
	public void add(CreateModelRequest createModelRequest) {
		
		this.modelBusinessRules.checkIfModelNameExists(createModelRequest.getName());
		this.brandBusinessRules.checkIfBrandIdExists(createModelRequest.getBrandId());
		
		Model model= this.modelMapperService.forRequest()
				.map(createModelRequest, Model.class);
		
		this.modelRepository.save(model);
		
	}
	
	//---------------------- MODEL SİLME -------------------------------
	@Override
	public void delete(int id) {
		
		this.modelBusinessRules.checkIfModelIdExists(id);
		
		this.modelRepository.deleteById(id);
		
	}

	//---------------------- MODEL GÜNCELLEME -------------------------
	@Override
	public void update(UpdateModelRequest updateModelRequest) {
		
		this.modelBusinessRules.checkIfModelIdExists(updateModelRequest.getId());
		this.modelBusinessRules.checkIfModelNameExists(updateModelRequest.getName());
		this.brandBusinessRules.checkIfBrandIdExists(updateModelRequest.getBrandId());
		
		Model model= this.modelMapperService.forRequest()
				.map(updateModelRequest, Model.class);
		
		this.modelRepository.save(model);
		
	}
	
	//-------------------- BÜTÜN MODELLERİ GETİRİR ------------------------
	@Override
	public List<GetAllModelsResponse> getAllModelsResponses() {
		List<Model> models= this.modelRepository.findAll();
		
		List<GetAllModelsResponse> getAllModelsResponses= models.stream()
				.map(model-> this.modelMapperService.forResponse().map(model, GetAllModelsResponse.class))
				.toList();
		
		return getAllModelsResponses;
	}
	
	//-------------------- ID NUMARASI VERİLEN IDLERİN İÇİNDE OLAN MODELLERİ GETİRİR ----------
	@Override
	public List<GetAllByIdModelsInResponse> getAllByIdModelsInResponses(List<Integer> ids) {
		
		this.modelBusinessRules.checkIfModelIdsExists(ids);
		
		List<Model> models= this.modelRepository.findAllByIdIn(ids);
		
		List<GetAllByIdModelsInResponse> getAllByIdModelsInResponses= models.stream()
				.map(model-> this.modelMapperService.forResponse().map(model, GetAllByIdModelsInResponse.class))
				.toList();
		
		return getAllByIdModelsInResponses;
	}
	
	
	//--------------------- ADINDA VERİLEN METİNİ İÇEREN MODELLERİ GETİRİR ------------------
	@Override
	public List<GetAllByNameModelsContainingResponse> getAllByNameModelsContainings(String name) {
		List<Model> models= this.modelRepository.findAllByNameContainingIgnoreCase(name);
		
		List<GetAllByNameModelsContainingResponse> getAllByNameModelsContainings= models.stream()
				.map(model-> this.modelMapperService.forResponse().map(model, GetAllByNameModelsContainingResponse.class))
				.toList();
		
		return getAllByNameModelsContainings;
	}
	
	//------------------ SEÇİLEN MARKANIN SEÇİLEN MODELLERİNİ GETİRİR -----------------------
	@Override
	public List<GetAllByBrandIdAndIdInResponse> getAllByBrandIdAndIdInResponses(int brandId, List<Integer> ids) {
		
		this.brandBusinessRules.checkIfBrandIdExists(brandId);
		this.modelBusinessRules.checkIfModelIdsExists(ids);
		
		List<Model> models= this.modelRepository.findAllByBrandIdAndIdIn(brandId, ids);
		
		List<GetAllByBrandIdAndIdInResponse> getAllByBrandIdAndIdInResponses= models.stream()
				.map(model-> this.modelMapperService.forResponse().map(model, GetAllByBrandIdAndIdInResponse.class))
				.toList();
		
		return getAllByBrandIdAndIdInResponses;
	}

	
	//------------------ VERİLEN ID'YE SAHİP OLAN MARKAYI GETİRİR ------------------------------
	@Override
	public GetByIdModelResponse getByIdModelResponse(int id) {
		
		this.modelBusinessRules.checkIfModelIdExists(id);
		
		Model model= this.modelRepository.findById(id).orElseThrow();
		
		GetByIdModelResponse getByIdModelResponse= this.modelMapperService.forRequest().map(model, GetByIdModelResponse.class);
		
		return getByIdModelResponse;
	}

}
