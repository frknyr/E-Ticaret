package eticaret.webApi.controllers;

import java.util.List;

import javax.validation.Valid;

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

import eticaret.business.abstracts.BrandService;
import eticaret.business.request.brand.CreateBrandRequest;
import eticaret.business.request.brand.UpdateBrandRequest;
import eticaret.business.responses.brand.GetAllBrandsResponse;
import eticaret.business.responses.brand.GetAllByIdBrandsInResponse;
import eticaret.business.responses.brand.GetAllByNameBrandsContainingResponse;
import eticaret.business.responses.brand.GetByIdBrandResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor
public class BrandsController {
	
	private BrandService brandService;
	
	//-------------------------------- MARKA EKLEME ------------------------------
	@PostMapping("/createBrand")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void add(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
		this.brandService.add(createBrandRequest);
	}
	
	
	//----------------------------- MARKA SİLME ---------------------------------
	@DeleteMapping("/deleteBrand/{id}")
	public void delete(int id) {
		this.brandService.delete(id);
	}
	
	
	//-------------------------- MARKA GÜNCELLEME ---------------------------------
	@PutMapping("/updateBrand")
	public void update(@RequestBody UpdateBrandRequest updateBrandRequest) {
		this.brandService.update(updateBrandRequest);
	}
	
	//-------------------------- BÜTÜN MARKALARI GETİRME --------------------------
	@GetMapping("/getAllBrands")
	public List<GetAllBrandsResponse> getAllBrandsResponses(){
		return this.brandService.getAllBrandsResponses();
	}
	
	//------------------ İSTENİLEN ID NUMARASINA SAHİP MARKAYI GETİRME --------------
	@GetMapping("/getByBrandId/{id}")
	public GetByIdBrandResponse getByBrandIdResponse(@PathVariable int id) {
		return this.brandService.getByBrandIdResponse(id);
	}
	
	//--------------- VERİLEN ID NUMARALARINA AİT MARKALARI GETİRİR -------------------
	@GetMapping("/getAllByBrandsIdIn/{brandsId}")
	public List<GetAllByIdBrandsInResponse> getAllByBrandsIdInResponse(@PathVariable List<Integer> brandsId) {
		return this.brandService.getAllByBrandsIdInResponses(brandsId);
	}
	
	//----------------- ADINDA VERİLEN METNİ İÇEREN MARKALARI GETİRİR ---------------------
	@GetMapping("/getAllByNameBrandsContaining/{name}")
	public List<GetAllByNameBrandsContainingResponse> getAllByNameBrandsContainings(@PathVariable String name){
		return this.brandService.getAllByNameBrandsContainings(name);
	}
	
	
	
	
	
	
	
	
	
}
