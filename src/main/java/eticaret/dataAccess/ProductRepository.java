package eticaret.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eticaret.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	boolean existsByNameIgnoreCase(String name);
	
	List<Product> findAllByNameContainingIgnoreCase(String name);
	List<Product> findAllByBrandIdIn(List<Integer> ids);
	List<Product> findAllByBrandIdInAndCategoryIdIn(List<Integer> brandIds, List<Integer> categoryIds);
	List<Product> findAllByBrandIdAndModelIdIn(int brandId, List<Integer> modelIds);
	List<Product> findAllByBrandIdAndModelIdAndSellerIdIn(int brandId, int modelId, List<Integer> sellerIds);
	List<Product> findAllByModelIdIn(List<Integer> modelIds);
	List<Product> findAllByModelIdInAndSellerIdIn(List<Integer> modelIds, List<Integer> sellerIds);
	List<Product> findAllByCategoryIdIn(List<Integer> categoryIds);
	List<Product> findAllByUnitPriceBetween(int minPrice, int maxPrice);
	List<Product> findAllBySellerIdIn(List<Integer> sellerIds);
	List<Product> findAllBySellerIdInAndCategoryIdIn(List<Integer> sellerIds, List<Integer> categoryIds);
	
}
