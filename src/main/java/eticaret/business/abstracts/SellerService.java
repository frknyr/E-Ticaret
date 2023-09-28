package eticaret.business.abstracts;

import java.util.List;

import eticaret.business.request.seller.CreateSellerRequest;
import eticaret.business.request.seller.UpdateSellerRequest;
import eticaret.business.responses.seller.GetAllByIdSellersInResponse;
import eticaret.business.responses.seller.GetAllByNameSellersContainingResponse;
import eticaret.business.responses.seller.GetAllSellersResponse;
import eticaret.business.responses.seller.GetByIdSellerResponse;

public interface SellerService {
	void add(CreateSellerRequest createSellerRequest);
	void delete(int id);
	void update(UpdateSellerRequest updateSellerRequest);
	
	GetByIdSellerResponse getByIdSellerResponse(int id);
	
	List<GetAllSellersResponse> getAllSellersResponses();
	List<GetAllByIdSellersInResponse> getAllByIdSellersInResponses(List<Integer> ids);
	List<GetAllByNameSellersContainingResponse> getAllByNameSellersContainingResponses(String name);
}
