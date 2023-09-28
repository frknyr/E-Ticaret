package eticaret.business.rules;

import java.util.List;

import org.springframework.stereotype.Service;

import eticaret.core.exceptions.BusinessException;
import eticaret.dataAccess.ProductRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductBusinessRules {
	
	private ProductRepository productRepository;
	
	public void checkIfProductIdExists(int id) {
		if(!this.productRepository.existsById(id)) {
			throw new BusinessException(id+" id numaralı ürün bulunmuyor !!!");
		}
	}
	
	public void checkIfProductIdsExists(List<Integer> ids) {
		
		for(int id: ids) {
			if(!this.productRepository.existsById(id)) {
				throw new BusinessException(id+" id numaralı ürün bulunmuyor !!!");
			}
		}

	}
	
	public void checkIfProductNameExists(String name) {
		if(this.productRepository.existsByNameIgnoreCase(name)) {
			throw new BusinessException(name+" adında ürün bulunuyor !!!");
		}
	}
	
}
