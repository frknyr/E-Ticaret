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
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductsController {
	
	private ProductService productService;
	
	//-------------------- ÜRÜN EKLEME ------------------------------
	@PostMapping("/createProduct")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void add(@RequestBody CreateProductRequest createProductRequest) {
		this.productService.add(createProductRequest);
	}
	
	//-------------------- ÜRÜN SİLME ------------------------------
	@DeleteMapping("/deleteProduct/{id}")
	public void delete(@PathVariable int id) {
		this.productService.delete(id);
	}
	
	
	//-------------------- ÜRÜN GÜNCELLEME -----------------------------
	@PutMapping("/updateProduct")
	public void update(@RequestBody UpdateProductRequest updateProductRequest) {
		this.productService.update(updateProductRequest);
	}
	
	//-------------------- VERİLEN ID'YE SAHİP OLAN ÜRÜNÜ GETİRİR ------------------------------
	@GetMapping("/getByIdProduct/{id}")
	public GetByIdProductResponse getByIdProductResponse(@PathVariable int id) {
		return this.productService.getByIdProductResponse(id);
	}
	
	//-------------------- BÜTÜN ÜRÜNLERİ GETİRİR ------------------------------
	@GetMapping("/getAllProducts")
	public List<GetAllProductsResponse> getAllProductsResponse() {
		return this.productService.getAllProductsResponses();
	}
	
	//-------------- VERİLEN ID'LERE AİT OLAN ÜRÜNLERİ GETİRİR -------------------------
	@GetMapping("/getAllByIdProductsIn/{ids}")
	public List<GetAllByIdProductsInResponse> getAllByIdProductsInResponses(@PathVariable List<Integer> ids){
		return this.productService.getAllByIdProductsInResponses(ids);
	}
	
	//-------------------- VERİLEN METİNSEL İFADEYİ İÇEREN ÜRÜNLERİ GETİRİR ---------------------------
	@GetMapping("/getAllByNameProductsContaining/{name}")
	public List<GetAllByNameProductsContainingResponse> getAllByNameProductsContainingResponses(@PathVariable String name){
		return this.productService.getAllByNameProductsContainingResponses(name);
	}
	
	//------------------ İSTENİLEN MARKALARIN TÜM ÜRÜNLERİNİ GETİRİR --------------------------------------
	@GetMapping("/getAllByBrandInProductsIn/{brandIds}")
	public List<GetAllByBrandIdProductsInResponse> getAllByBrandIdProductsInResponses(@PathVariable List<Integer> brandIds){
		return this.productService.getAllByBrandIdProductsInResponses(brandIds);
	}
	
	//---------------------- SEÇİLEN MARKALARIN SEÇİLEN KATEGORİLERDEKİ ÜRÜNLERİNİ GETİRİR -----------------------
	@GetMapping("/getAllByBrandIdAndCategoryIdProductsIn/{brandIds}/{categoryIds}")
	public List<GetAllByBrandIdAndCategoryIdProductsInResponse> getAllByBrandIdAndCategoryIdProductsInResponses(
			@PathVariable List<Integer> brandIds,@PathVariable List<Integer> categoryIds){
		return this.productService.getAllByBrandIdAndCategoryIdProductsInResponses(brandIds, categoryIds);
	}
	
	//---------------------- SEÇİLEN MARKANIN SEÇİLEN MODELLERİNE AİT OLAN ÜRÜNLERİNİ GETİRİR ------------------------
	@GetMapping("/getAllByBrandIdAndModelIdProductsIn/{brandId}/{modelIds}")
	public List<GetAllByBrandIdAndModelIdProductsInResponse> getAllByBrandIdAndModelIdProductsInResponses(@PathVariable int brandId,
			@PathVariable List<Integer> modelIds){
		return this.productService.getAllByBrandIdAndModelIdProductsInResponses(brandId, modelIds);
	}
	
	//----------------- SEÇİLEN MARKANIN SEÇİLEN MODELİNİN SEÇİLEN SATICILARA AİT ÜRÜNLERİ GETİRİR ------------------------
	@GetMapping("/getAllByBrandIdAndModelIdAndSellersIdProductsIn/{brandId}/{modelId}/{sellerIds}")
	public List<GetAllByBrandIdAndModelIdAndSellerIdProductsInResponse> getAllByBrandIdAndModelIdAndSellerIdProductsInResponses(
				@PathVariable int brandId, @PathVariable int modelId, @PathVariable List<Integer> sellerIds) {
		return this.productService.getAllByBrandIdAndModelIdAndSellerIdProductsInResponses(brandId, modelId, sellerIds);
	}
	
	//-------------------------- SEÇİLEN MODELLERE AİT OLAN ÜRÜNLERİ GETİRİLİR -----------------------------
	@GetMapping("/getAllByModelIdProductsIn/{modelIds}")
	public List<GetAllByModeldProductsInResponse> getAllByModeldProductsInResponses(@PathVariable List<Integer> modelIds){
		return this.productService.getAllByModeldProductsInResponses(modelIds);
	}
	
	//------------------------ SEÇİLEN MODELLERİN SEÇİLEN SATICILARA AİT OLAN ÜRÜNLERİ GETİRİR -----------------------
	@GetMapping("/getAllByModelIdAndSellerIdProductsIn/{modelIds}/{sellerIds}")
	public List<GetAllByModelIdAndSellerIdProductsInResponse> getAllByModelIdAndSellerIdProductsInResponses
						(@PathVariable List<Integer> modelIds,@PathVariable List<Integer> sellerIds){
		return this.productService.getAllByModelIdAndSellerIdProductsInResponses(modelIds, sellerIds);
	}
	
	//--------------------------- SEÇİLEN KATEGORİLERE AİT BÜTÜN ÜRÜNLERİ GETİRİR ----------------------------------
	@GetMapping("/getAllByCategoryIdProductsIn/{categoryIds}")
	public List<GetAllByCategoryIdProductsInResponse> getAllByCategoryIdProductsInResponses(@PathVariable List<Integer> categoryIds) {
		return this.productService.getAllByCategoryIdProductsInResponses(categoryIds);
	}
	
	//----------------------- FİYATI İSTENİLEN DEĞER ARALIĞINDA OLAN ÜRÜNLERİ GETİRİR --------------------------------
	@GetMapping("/getAllByUnitPriceProductsBetween/{minPrice}/{maxPrice}")
	public List<GetAllByUnitPriceProductsBetweenResponse> getAllByUnitPriceProductsBetweenResponses(int minPrice,int maxPrice) {
		return this.productService.getAllByUnitPriceProductsBetweenResponses(minPrice, maxPrice);
	}
	
	//------------------------ SEÇİLEN SATICILARIN TÜM ÜRÜNLERİNİ GETİRİR -------------------------------
	@GetMapping("/getAllBySellerIdProductsIn/{sellerIds}")
	public List<GetAllBySellerIdProductsInResponse> getAllBySellerIdProductsInResponses(@PathVariable List<Integer> sellerIds) {
		return this.productService.getAllBySellerIdProductsInResponses(sellerIds);
	}
	
	//--------------- SEÇİLEN SATICILARIN SEÇİLEN KATEGORİLERDEKİ ÜRÜNLERİNİ GETİRİR ----------------------------
	@GetMapping("/getAllBySellerIdAndCategoryIdProductsIn/{sellerIds}/{categoryIds}")
	public List<GetAllBySellerIdAndCategoryIdProductsInResponse> getAllBySellerIdAndCategoryIdProductsInResponses(
					@PathVariable List<Integer> sellerIds,@PathVariable List<Integer> categoryIds) {
		return this.productService.getAllBySellerIdAndCategoryIdProductsInResponses(sellerIds, categoryIds);
	}
	
}
