package eticaret.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eticaret.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer>{
	
	boolean existsByNameIgnoreCase(String name);

	
	List<Brand> findAllByIdIn(List<Integer> brandsId);
	List<Brand> findAllByNameContainingIgnoreCase(String name);
}
