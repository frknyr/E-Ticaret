package eticaret.business.rules;

import java.util.List;

import org.springframework.stereotype.Service;

import eticaret.core.exceptions.BusinessException;
import eticaret.dataAccess.CategoryRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryBusinessRules {
	
	private CategoryRepository categoryRepository;
	
	public void checkIfCategoryIdExists(int id) {
		if(!this.categoryRepository.existsById(id)) {
			throw new BusinessException(id+" id numaralı kategori bulunmuyor !!!");
		}
	}
	
	public void checkIfCategoryIdsExists(List<Integer> ids) {
		
		for(int id: ids) {
			if(!this.categoryRepository.existsById(id)) {
				throw new BusinessException(id+" id numarasına sahip kategori bulunmuyor !!!");
			}
		}
		
	}
	
	public void checkIfCategoryNameExists(String name) {
		
		if(this.categoryRepository.existsByNameIgnoreCase(name)) {
			throw new BusinessException(name+" adında kategori bulunuyor !!!");
		}
		
	}
	
	
	
}
