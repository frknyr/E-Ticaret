package eticaret.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import eticaret.business.abstracts.SellerService;
import eticaret.business.request.seller.CreateSellerRequest;
import eticaret.business.request.seller.UpdateSellerRequest;
import eticaret.business.responses.seller.GetAllByIdSellersInResponse;
import eticaret.business.responses.seller.GetAllByNameSellersContainingResponse;
import eticaret.business.responses.seller.GetAllSellersResponse;
import eticaret.business.responses.seller.GetByIdSellerResponse;
import eticaret.business.rules.SellerBusinessRules;
import eticaret.core.mappers.abstracts.ModelMapperService;
import eticaret.dataAccess.SellerRepository;
import eticaret.entities.Seller;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SellerManager implements SellerService{
	
	private SellerRepository sellerRepository;
	private ModelMapperService modelMapperService;
	private SellerBusinessRules sellerBusinessRules;
	
	//------------------------- SATICI EKLEME ------------------------
	@Override
	public void add(CreateSellerRequest createSellerRequest) {
		
		this.sellerBusinessRules.checkIfSellerNameExists(createSellerRequest.getName());
		
		Seller seller= this.modelMapperService.forRequest().map(createSellerRequest, Seller.class);
		
		this.sellerRepository.save(seller);
		
	}
	
	//------------------------ SATICI SİLME -----------------------------
	@Override
	public void delete(int id) {
		
		this.sellerBusinessRules.checkIfSellerIdExists(id);
		
		this.sellerRepository.deleteById(id);
	}
	
	//------------------------ SATICI GÜNCELLEME -------------------------
	@Override
	public void update(UpdateSellerRequest updateSellerRequest) {
		
		this.sellerBusinessRules.checkIfSellerIdExists(updateSellerRequest.getId());
		this.sellerBusinessRules.checkIfSellerNameExists(updateSellerRequest.getName());
		
		Seller seller= this.modelMapperService.forRequest().map(updateSellerRequest, Seller.class);
		
		this.sellerRepository.save(seller);
	}
	
	//------------------ VERİLEN ID'YE SAHİP SATICIYI GETİRİR ---------------------
	@Override
	public GetByIdSellerResponse getByIdSellerResponse(int id) {
		
		this.sellerBusinessRules.checkIfSellerIdExists(id);
		
		Seller seller= this.sellerRepository.findById(id).orElseThrow();
		
		GetByIdSellerResponse getByIdSellerResponse= this.modelMapperService.forResponse()
				.map(seller, GetByIdSellerResponse.class);
		
		return getByIdSellerResponse;
	}
	
	//------------------ TÜM SATICILARI GETİRİR --------------------------
	@Override
	public List<GetAllSellersResponse> getAllSellersResponses() {
		List<Seller> sellers= this.sellerRepository.findAll();
		
		List<GetAllSellersResponse> getAllSellersResponses= sellers.stream()
				.map(seller-> this.modelMapperService.forResponse().map(seller, GetAllSellersResponse.class))
				.toList();
		
		return getAllSellersResponses;
	}

	
	//-------------------- ID NUMARASI VERİLEN IDLERİN İÇİNDE OLAN SATICILARI GETİRİR ----------
	@Override
	public List<GetAllByIdSellersInResponse> getAllByIdSellersInResponses(List<Integer> ids) {
		
		this.sellerBusinessRules.checkIfSellerIdsExists(ids);
		
		List<Seller> sellers= this.sellerRepository.findAllById(ids);
		
		List<GetAllByIdSellersInResponse> getAllByIdSellersInResponses= sellers.stream()
				.map(seller-> this.modelMapperService.forResponse().map(seller, GetAllByIdSellersInResponse.class))
				.toList();
		
		return getAllByIdSellersInResponses;
	}
	
	//---------------- ADINDA VERİLEN METİN GEÇEN SATICILARI GETİRİR ----------------
	@Override
	public List<GetAllByNameSellersContainingResponse> getAllByNameSellersContainingResponses(String name) {
		List<Seller> sellers= this.sellerRepository.findAllByNameContainingIgnoreCase(name);
		
		List<GetAllByNameSellersContainingResponse> getAllByNameSellersContainingResponses= sellers.stream()
				.map(seller-> this.modelMapperService.forResponse().map(seller, GetAllByNameSellersContainingResponse.class))
				.toList();
		
		return getAllByNameSellersContainingResponses;
	}

}
