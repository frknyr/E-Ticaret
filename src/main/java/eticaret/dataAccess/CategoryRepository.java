package eticaret.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import eticaret.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	boolean existsByNameIgnoreCase(String name);
}
