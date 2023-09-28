package eticaret.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eticaret.entities.Seller;

public interface SellerRepository extends JpaRepository<Seller, Integer>{
	
	boolean existsByNameIgnoreCase(String name);
	
	List<Seller> findAllByNameContainingIgnoreCase(String name);
}
