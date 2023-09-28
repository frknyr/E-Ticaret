package eticaret.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import eticaret.business.abstracts.ProductService;
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
import eticaret.business.rules.BrandBusinessRules;
import eticaret.business.rules.CategoryBusinessRules;
import eticaret.business.rules.ModelBusinessRules;
import eticaret.business.rules.ProductBusinessRules;
import eticaret.business.rules.SellerBusinessRules;
import eticaret.core.mappers.abstracts.ModelMapperService;
import eticaret.dataAccess.ProductRepository;
import eticaret.entities.Product;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService{
	
	private ProductRepository productRepository;
	private ModelMapperService modelMapperService;
	private ProductBusinessRules productBusinessRules;
	private BrandBusinessRules brandBusinessRules;
	private ModelBusinessRules modelBusinessRules;
	private CategoryBusinessRules categoryBusinessRules;
	private SellerBusinessRules sellerBusinessRules;
	
	
	//-------------------- ÜRÜN EKLEME ------------------------------
	@Override
	public void add(CreateProductRequest createProductRequest) {
		
		this.productBusinessRules.checkIfProductNameExists(createProductRequest.getName());
		this.brandBusinessRules.checkIfBrandIdExists(createProductRequest.getBrandId());
		this.modelBusinessRules.checkIfModelIdExists(createProductRequest.getModelId());
		this.categoryBusinessRules.checkIfCategoryIdExists(createProductRequest.getCategoryId());
		this.sellerBusinessRules.checkIfSellerIdExists(createProductRequest.getSellerId());
		
		Product product= this.modelMapperService.forRequest()
				.map(createProductRequest, Product.class);
		
		this.productRepository.save(product);
	}
	
	//-------------------- ÜRÜN SİLME ------------------------------
	@Override
	public void delete(int id) {
		this.productRepository.deleteById(id);
		
	}
	
	//-------------------- ÜRÜN GÜNCELLEME ------------------------------
	@Override
	public void update(UpdateProductRequest updateProductRequest) {
		
		this.productBusinessRules.checkIfProductIdExists(updateProductRequest.getId());
		this.productBusinessRules.checkIfProductNameExists(updateProductRequest.getName());
		this.brandBusinessRules.checkIfBrandIdExists(updateProductRequest.getBrandId());
		this.modelBusinessRules.checkIfModelIdExists(updateProductRequest.getModelId());
		this.categoryBusinessRules.checkIfCategoryIdExists(updateProductRequest.getCategoryId());
		this.sellerBusinessRules.checkIfSellerIdExists(updateProductRequest.getSellerId());
		
		Product product= this.modelMapperService.forRequest()
				.map(updateProductRequest, Product.class);
		
		this.productRepository.save(product);
		
	}

	//-------------------- VERİLEN ID'YE SAHİP OLAN ÜRÜNÜ GETİRİR ------------------------------
	@Override
	public GetByIdProductResponse getByIdProductResponse(int id) {
		
		this.productBusinessRules.checkIfProductIdExists(id);
		
		Product product= this.productRepository.findById(id).orElseThrow();
		
		GetByIdProductResponse getByIdProductResponse= this.modelMapperService.forResponse()
				.map(product, GetByIdProductResponse.class);
		
		return getByIdProductResponse;
	}

	//-------------------- BÜTÜN ÜRÜNLERİ GETİRİR ------------------------------
	@Override
	public List<GetAllProductsResponse> getAllProductsResponses() {
		List<Product> products= this.productRepository.findAll();
		
		List<GetAllProductsResponse> getAllProductsResponses= products.stream()
				.map(product-> this.modelMapperService.forResponse().map(product, GetAllProductsResponse.class))
				.toList();
		
		return getAllProductsResponses;
	}
	
	//-------------- VERİLEN ID'LERE AİT OLAN ÜRÜNLERİ GETİRİR -------------------------
	@Override
	public List<GetAllByIdProductsInResponse> getAllByIdProductsInResponses(List<Integer> ids) {
		
		this.productBusinessRules.checkIfProductIdsExists(ids);
		
		List<Product> products= this.productRepository.findAllById(ids);
		
		List<GetAllByIdProductsInResponse> getAllByIdProductsInResponses= products.stream()
				.map(product-> this.modelMapperService.forResponse().map(product, GetAllByIdProductsInResponse.class))
				.toList();
		
		return getAllByIdProductsInResponses;
	}

	//-------------------- VERİLEN METİNSEL İFADEYİ İÇEREN ÜRÜNLERİ GETİRİR ---------------------------
	@Override
	public List<GetAllByNameProductsContainingResponse> getAllByNameProductsContainingResponses(String name) {
		List<Product> products= this.productRepository.findAllByNameContainingIgnoreCase(name);
		
		List<GetAllByNameProductsContainingResponse> getAllByNameProductsContainingResponses= products.stream()
				.map(product-> this.modelMapperService.forResponse().map(product, GetAllByNameProductsContainingResponse.class))
				.toList();
		
		return getAllByNameProductsContainingResponses;
	}
	
	//------------------ İSTENİLEN MARKALARIN TÜM ÜRÜNLERİNİ GETİRİR --------------------------------------
	@Override
	public List<GetAllByBrandIdProductsInResponse> getAllByBrandIdProductsInResponses(List<Integer> brandİds) {
		
		this.brandBusinessRules.checkIfBrandIdsExists(brandİds);
		
		List<Product> products= this.productRepository.findAllByBrandIdIn(brandİds);
		
		List<GetAllByBrandIdProductsInResponse> getAllByBrandIdProductsInResponses= products.stream()
				.map(product-> this.modelMapperService.forResponse().map(product, GetAllByBrandIdProductsInResponse.class))
				.toList();
				
		
		return getAllByBrandIdProductsInResponses;
	}

	//---------------------- SEÇİLEN MARKALARIN SEÇİLEN KATEGORİLERDEKİ ÜRÜNLERİNİ GETİRİR ------------------------
	@Override
	public List<GetAllByBrandIdAndCategoryIdProductsInResponse> getAllByBrandIdAndCategoryIdProductsInResponses(
			List<Integer> brandIds, List<Integer> categoryIds) {
		
		this.brandBusinessRules.checkIfBrandIdsExists(brandIds);
		this.categoryBusinessRules.checkIfCategoryIdsExists(categoryIds);
		
		List<Product> products= this.productRepository.findAllByBrandIdInAndCategoryIdIn(brandIds, categoryIds);
		
		List<GetAllByBrandIdAndCategoryIdProductsInResponse> getAllByBrandIdAndCategoryIdProductsInResponses=
				products.stream().map(product-> this.modelMapperService.forResponse().map(product, GetAllByBrandIdAndCategoryIdProductsInResponse.class))
				.toList();
		
		return getAllByBrandIdAndCategoryIdProductsInResponses;
	}

	//---------------------- SEÇİLEN MARKANIN SEÇİLEN MODELLERİNE AİT OLAN ÜRÜNLERİNİ GETİRİR ------------------------
	@Override
	public List<GetAllByBrandIdAndModelIdProductsInResponse> getAllByBrandIdAndModelIdProductsInResponses(int brandId,
			List<Integer> modelIds) {
		
		this.brandBusinessRules.checkIfBrandIdExists(brandId);
		this.modelBusinessRules.checkIfModelIdsExists(modelIds);
		
		List<Product> products= this.productRepository.findAllByBrandIdAndModelIdIn(brandId, modelIds);
		
		List<GetAllByBrandIdAndModelIdProductsInResponse> getAllByBrandIdAndModelIdProductsInResponses= products.stream()
				.map(product-> this.modelMapperService.forResponse().map(product, GetAllByBrandIdAndModelIdProductsInResponse.class))
				.toList();
		
		return getAllByBrandIdAndModelIdProductsInResponses;
	}

	//----------------- SEÇİLEN MARKANIN SEÇİLEN MODELİNİN SEÇİLEN SATICILARA AİT ÜRÜNLERİ GETİRİR ------------------------
	@Override
	public List<GetAllByBrandIdAndModelIdAndSellerIdProductsInResponse> getAllByBrandIdAndModelIdAndSellerIdProductsInResponses(
			int brandId, int modelId, List<Integer> sellerIds) {
		
		this.brandBusinessRules.checkIfBrandIdExists(brandId);
		this.modelBusinessRules.checkIfModelIdExists(modelId);
		this.sellerBusinessRules.checkIfSellerIdsExists(sellerIds);
		
		List<Product> products= this.productRepository.findAllByBrandIdAndModelIdAndSellerIdIn(brandId, modelId, sellerIds);
		
		List<GetAllByBrandIdAndModelIdAndSellerIdProductsInResponse> getAllByBrandIdAndModelIdAndSellerIdProductsInResponses=
				products.stream().map(product-> this.modelMapperService.forResponse().map(product, GetAllByBrandIdAndModelIdAndSellerIdProductsInResponse.class))
				.toList();
		
		return getAllByBrandIdAndModelIdAndSellerIdProductsInResponses;
	}

	//-------------------------- SEÇİLEN MODELLERE AİT OLAN ÜRÜNLERİ GETİRİLİR -----------------------------
	@Override
	public List<GetAllByModeldProductsInResponse> getAllByModeldProductsInResponses(List<Integer> modelIds) {
		
		this.modelBusinessRules.checkIfModelIdsExists(modelIds);
		
		List<Product> products= this.productRepository.findAllByModelIdIn(modelIds);
		
		List<GetAllByModeldProductsInResponse> getAllByModeldProductsInResponses= products.stream()
				.map(product-> this.modelMapperService.forResponse().map(product, GetAllByModeldProductsInResponse.class))
				.toList();
		
		return getAllByModeldProductsInResponses;
	}
	
	//------------------------ SEÇİLEN MODELLERİN SEÇİLEN SATICILARA AİT OLAN ÜRÜNLERİ GETİRİR -----------------------
	@Override
	public List<GetAllByModelIdAndSellerIdProductsInResponse> getAllByModelIdAndSellerIdProductsInResponses(
			List<Integer> modelIds, List<Integer> sellerIds) {
		
		this.modelBusinessRules.checkIfModelIdsExists(modelIds);
		this.sellerBusinessRules.checkIfSellerIdsExists(sellerIds);
		
		
		List<Product> products= this.productRepository.findAllByModelIdInAndSellerIdIn(modelIds, sellerIds);
		
		List<GetAllByModelIdAndSellerIdProductsInResponse> getAllByModelIdAndSellerIdProductsInResponses= products.stream()
				.map(product-> this.modelMapperService.forResponse().map(product, GetAllByModelIdAndSellerIdProductsInResponse.class))
				.toList();
		
		return getAllByModelIdAndSellerIdProductsInResponses;
	}

	//--------------------------- SEÇİLEN KATEGORİLERE AİT BÜTÜN ÜRÜNLERİ GETİRİR ----------------------------------
	@Override
	public List<GetAllByCategoryIdProductsInResponse> getAllByCategoryIdProductsInResponses(List<Integer> categoryIds) {
		
		this.categoryBusinessRules.checkIfCategoryIdsExists(categoryIds);
		
		List<Product> products= this.productRepository.findAllByCategoryIdIn(categoryIds);
		
		List<GetAllByCategoryIdProductsInResponse> getAllByCategoryIdProductsInResponses= products.stream()
				.map(product-> this.modelMapperService.forResponse().map(product, GetAllByCategoryIdProductsInResponse.class))
				.toList();
		
		return getAllByCategoryIdProductsInResponses;
	}

	//----------------------- FİYATI İSTENİLEN DEĞER ARALIĞINDA OLAN ÜRÜNLERİ GETİRİR --------------------------------
	@Override
	public List<GetAllByUnitPriceProductsBetweenResponse> getAllByUnitPriceProductsBetweenResponses(int minPrice,
			int maxPrice) {
		List<Product> products= this.productRepository.findAllByUnitPriceBetween(minPrice, maxPrice);
		
		List<GetAllByUnitPriceProductsBetweenResponse> getAllByUnitPriceProductsBetweenResponses= products.stream()
				.map(product-> this.modelMapperService.forResponse().map(product, GetAllByUnitPriceProductsBetweenResponse.class))
				.toList();
		
		return getAllByUnitPriceProductsBetweenResponses;
	}
	
	//------------------------ SEÇİLEN SATICILARIN TÜM ÜRÜNLERİNİ GETİRİR -------------------------------
	@Override
	public List<GetAllBySellerIdProductsInResponse> getAllBySellerIdProductsInResponses(List<Integer> sellerIds) {
		
		this.sellerBusinessRules.checkIfSellerIdsExists(sellerIds);
		
		List<Product> products= this.productRepository.findAllBySellerIdIn(sellerIds);
		
		List<GetAllBySellerIdProductsInResponse> getAllBySellerIdProductsInResponses= products.stream()
				.map(product-> this.modelMapperService.forResponse().map(product, GetAllBySellerIdProductsInResponse.class))
				.toList();
		
		return getAllBySellerIdProductsInResponses;
	}
	
	//--------------- SEÇİLEN SATICILARIN SEÇİLEN KATEGORİLERDEKİ ÜRÜNLERİNİ GETİRİR ----------------------------
	@Override
	public List<GetAllBySellerIdAndCategoryIdProductsInResponse> getAllBySellerIdAndCategoryIdProductsInResponses(
			List<Integer> sellerIds, List<Integer> categoryIds) {
		
		this.sellerBusinessRules.checkIfSellerIdsExists(sellerIds);
		this.categoryBusinessRules.checkIfCategoryIdsExists(categoryIds);
		
		List<Product> products= this.productRepository.findAllBySellerIdInAndCategoryIdIn(sellerIds, categoryIds);
		
		List<GetAllBySellerIdAndCategoryIdProductsInResponse> getAllBySellerIdAndCategoryIdProductsInResponses= products.stream()
				.map(product-> this.modelMapperService.forResponse().map(product, GetAllBySellerIdAndCategoryIdProductsInResponse.class))
				.toList();
		
		return getAllBySellerIdAndCategoryIdProductsInResponses;
	}
	
	
	
}
