package eticaret.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eticaret.entities.Model;

public interface ModelRepository extends JpaRepository<Model, Integer>{
	
	boolean existsByNameIgnoreCase(String name);
	
	List<Model> findAllByIdIn(List<Integer> ids);
	List<Model> findAllByNameContainingIgnoreCase(String name);
	List<Model> findAllByBrandIdAndIdIn(int brandId, List<Integer> ids);
}
