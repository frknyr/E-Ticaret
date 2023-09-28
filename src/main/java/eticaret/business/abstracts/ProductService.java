package eticaret.business.abstracts;

import java.util.List;

import eticaret.business.request.product.CreateProductRequest;
import eticaret.business.request.product.UpdateProductRequest;
import eticaret.business.responses.product.GetAllByBrandIdAndCategoryIdProductsInResponse;
import eticaret.business.responses.product.GetAllByBrandIdAndModelIdAndSellerIdProductsInResponse;
import eticaret.business.responses.product.GetAllByBrandIdAndModelIdProductsInResponse;
import eticaret.business.responses.product.GetAllByBrandIdProductsInResponse;
import eticaret.business.responses.product.GetAllByCategoryIdProductsInResponse;
import eticaret.business.responses.product.GetAllByIdProductsInResponse;
import eticaret.business.responses.product.GetAllByModelIdAndSellerIdProductsInResponse;
import eticaret.business.responses.product.GetAllByModeldProductsInResponse;
import eticaret.business.responses.product.GetAllByNameProductsContainingResponse;
import eticaret.business.responses.product.GetAllBySellerIdAndCategoryIdProductsInResponse;
import eticaret.business.responses.product.GetAllBySellerIdProductsInResponse;
import eticaret.business.responses.product.GetAllByUnitPriceProductsBetweenResponse;
import eticaret.business.responses.product.GetAllProductsResponse;
import eticaret.business.responses.product.GetByIdProductResponse;

public interface ProductService {
	void add(CreateProductRequest createProductRequest);
	void delete(int id);
	void update(UpdateProductRequest updateProductRequest);
	
	GetByIdProductResponse getByIdProductResponse(int id);
	
	List<GetAllProductsResponse> getAllProductsResponses();
	List<GetAllByIdProductsInResponse> getAllByIdProductsInResponses(List<Integer> ids);
	List<GetAllByNameProductsContainingResponse> getAllByNameProductsContainingResponses(String name);
	List<GetAllByBrandIdProductsInResponse> getAllByBrandIdProductsInResponses(List<Integer> brandİds);
	List<GetAllByBrandIdAndCategoryIdProductsInResponse> getAllByBrandIdAndCategoryIdProductsInResponses(List<Integer> brandIds, List<Integer> categoryIds);
	List<GetAllByBrandIdAndModelIdProductsInResponse> getAllByBrandIdAndModelIdProductsInResponses(int brandId, List<Integer> modelIds);
	List<GetAllByBrandIdAndModelIdAndSellerIdProductsInResponse> getAllByBrandIdAndModelIdAndSellerIdProductsInResponses(int brandId, int modelId, List<Integer> sellerIds);
	List<GetAllByModeldProductsInResponse> getAllByModeldProductsInResponses(List<Integer> modelIds);
	List<GetAllByModelIdAndSellerIdProductsInResponse> getAllByModelIdAndSellerIdProductsInResponses(List<Integer> modelIds, List<Integer> sellerIds);
	List<GetAllByCategoryIdProductsInResponse> getAllByCategoryIdProductsInResponses(List<Integer> categoryIds);
	List<GetAllByUnitPriceProductsBetweenResponse> getAllByUnitPriceProductsBetweenResponses(int minPrice, int maxPrice);
	List<GetAllBySellerIdProductsInResponse> getAllBySellerIdProductsInResponses(List<Integer> sellerIds);
	List<GetAllBySellerIdAndCategoryIdProductsInResponse> getAllBySellerIdAndCategoryIdProductsInResponses(List<Integer> sellerIds, List<Integer> categoryIds);
	
	
}
