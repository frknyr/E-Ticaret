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

import eticaret.business.abstracts.SellerService;
import eticaret.business.request.seller.CreateSellerRequest;
import eticaret.business.request.seller.UpdateSellerRequest;
import eticaret.business.responses.seller.GetAllByIdSellersInResponse;
import eticaret.business.responses.seller.GetAllByNameSellersContainingResponse;
import eticaret.business.responses.seller.GetAllSellersResponse;
import eticaret.business.responses.seller.GetByIdSellerResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/sellers")
@AllArgsConstructor
public class SellersController {
	
	private SellerService sellerService;
	
	//------------------------- SATICI EKLEME ------------------------
	@PostMapping("/createSeller")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void add(@RequestBody CreateSellerRequest createSellerRequest) {
		this.sellerService.add(createSellerRequest);
	}
	
	
	//------------------------ SATICI SİLME -----------------------------
	@DeleteMapping("/deleteSeller/{id}")
	public void delete(@PathVariable int id) {
		this.sellerService.delete(id);
	}
	
	//------------------------ SATICI GÜNCELLEME -------------------------
	@PutMapping("/updateSeller")
	public void update(UpdateSellerRequest updateSellerRequest) {
		this.sellerService.update(updateSellerRequest);
	}
	
	//------------------ VERİLEN ID'YE SAHİP SATICIYI GETİRME ---------------------
	@GetMapping("/getByIdSeller/{id}")
	public GetByIdSellerResponse getByIdSellerResponse(@PathVariable int id) {
		return this.sellerService.getByIdSellerResponse(id);
	}
	
	
	//------------------ TÜM SATICILARI GETİRİR --------------------------
	@GetMapping("/getAllSellers")
	public List<GetAllSellersResponse> getAllSellersResponses(){
		return this.sellerService.getAllSellersResponses();
	}
	
	//-------------------- ID NUMARASI VERİLEN IDLERİN İÇİNDE OLAN SATICILARI GETİRİR ----------
	@GetMapping("/getAllByIdSellersIn/{ids}")
	public List<GetAllByIdSellersInResponse> getAllByIdSellersInResponses(@PathVariable List<Integer> ids){
		return this.sellerService.getAllByIdSellersInResponses(ids);
	}
	
	//---------------- ADINDA VERİLEN METİN GEÇEN SATICILARI GETİRİR ----------------
	@GetMapping("getAllByNameSellersContaining/{name}")
	public List<GetAllByNameSellersContainingResponse> getAllByNameSellersContainingResponses(@PathVariable String name){
		return this.sellerService.getAllByNameSellersContainingResponses(name);
	}
	
}
