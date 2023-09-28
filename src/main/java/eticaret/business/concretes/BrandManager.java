package eticaret.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import eticaret.business.abstracts.BrandService;
import eticaret.business.request.brand.CreateBrandRequest;
import eticaret.business.request.brand.UpdateBrandRequest;
import eticaret.business.responses.brand.GetAllBrandsResponse;
import eticaret.business.responses.brand.GetAllByIdBrandsInResponse;
import eticaret.business.responses.brand.GetAllByNameBrandsContainingResponse;
import eticaret.business.responses.brand.GetByIdBrandResponse;
import eticaret.business.rules.BrandBusinessRules;
import eticaret.core.mappers.abstracts.ModelMapperService;
import eticaret.dataAccess.BrandRepository;
import eticaret.entities.Brand;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService{
	
	private ModelMapperService modelMapperService;
	private BrandRepository brandRepository;
	private BrandBusinessRules brandBusinessRules;
	
	//------------------------ MARKA EKLEME ----------------------
	@Override
	public void add(CreateBrandRequest createBrandRequest) {
		
		this.brandBusinessRules.checkIfBrandNameExists(createBrandRequest.getName());
		
		Brand brand= this.modelMapperService.forRequest()
				.map(createBrandRequest, Brand.class);
		
		this.brandRepository.save(brand);
	}

	//----------------------- MARKA SİLME ------------------------
	@Override
	public void delete(int id) {
		
		this.brandBusinessRules.checkIfBrandIdExists(id);
		
		this.brandRepository.deleteById(id);
		
	}

	//----------------------- MARKA GÜNCELLEME ---------------------
	@Override
	public void update(UpdateBrandRequest updateBrandRequest) {
		
		this.brandBusinessRules.checkIfBrandIdExists(updateBrandRequest.getId());
		this.brandBusinessRules.checkIfBrandNameExists(updateBrandRequest.getName());
		
		Brand brand= this.modelMapperService.forRequest()
				.map(updateBrandRequest, Brand.class);
		
		this.brandRepository.save(brand);
	}

	//----------------------- BÜTÜN MARKALARI GETİRME --------------------
	@Override
	public List<GetAllBrandsResponse> getAllBrandsResponses() {
		List<Brand> brands= this.brandRepository.findAll();
		
		List<GetAllBrandsResponse> getAllBrandsResponses= brands.stream()
				.map(brand-> this.modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class))
				.collect(Collectors.toList());
		
		return getAllBrandsResponses;
	}
	
	//------------------ İSTENİLEN ID NUMARASINA SAHİP MARKAYI GETİRME --------------
	@Override
	public GetByIdBrandResponse getByBrandIdResponse(int id) {
		
		this.brandBusinessRules.checkIfBrandIdExists(id);
		
		Brand brand= this.brandRepository.findById(id).orElseThrow();
		
		GetByIdBrandResponse getByBrandIdResponse= this.modelMapperService.forResponse()
				.map(brand, GetByIdBrandResponse.class);
		
		return getByBrandIdResponse;
	}
	
	//--------------- VERİLEN ID NUMARALARINA AİT MARKALARI GETİRİR -------------------
	@Override
	public List<GetAllByIdBrandsInResponse> getAllByBrandsIdInResponses(List<Integer> brandsId) {
		
		this.brandBusinessRules.checkIfBrandIdsExists(brandsId);
		
		List<Brand> brands=this.brandRepository.findAllByIdIn(brandsId);
	
		List<GetAllByIdBrandsInResponse> getAllByBrandIdInResponses= brands.stream()
				.map(brand-> this.modelMapperService.forResponse().map(brand, GetAllByIdBrandsInResponse.class))
				.toList();
			
		return getAllByBrandIdInResponses;
	}
	
	//----------------- ADINDA VERİLEN METNİ İÇEREN MARKALARI GETİRİR ---------------------
	@Override
	public List<GetAllByNameBrandsContainingResponse> getAllByNameBrandsContainings(String name) {
		List<Brand> brands=this.brandRepository.findAllByNameContainingIgnoreCase(name);
		
		List<GetAllByNameBrandsContainingResponse> getAllByNameBrandsContainings= brands.stream()
				.map(brand-> this.modelMapperService.forResponse().map(brand, GetAllByNameBrandsContainingResponse.class))
				.toList();
		
		return getAllByNameBrandsContainings;
	}

}
