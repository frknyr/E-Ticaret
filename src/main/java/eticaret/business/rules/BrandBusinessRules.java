package eticaret.business.rules;

import java.util.List;

import org.springframework.stereotype.Service;

import eticaret.core.exceptions.BusinessException;
import eticaret.dataAccess.BrandRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BrandBusinessRules {
	
	private BrandRepository brandRepository;
	
	public void checkIfBrandIdExists(int id) {
		
		if(!this.brandRepository.existsById(id)) {
			throw new BusinessException(id+" id numarasına sahip marka bulunmuyor !!!");
		}
		
	}
	
	public void checkIfBrandIdsExists(List<Integer> ids) {
		
		for(int id: ids) {
			if(!this.brandRepository.existsById(id)) {
				throw new BusinessException(id+" id numarasına sahip marka bulunmuyor !!!");
			}
		}
		
	}
	
	public void checkIfBrandNameExists(String name) {
		
		if(this.brandRepository.existsByNameIgnoreCase(name)) {
			throw new BusinessException(name +" adında başka bir marka var !!!");
		}
		
	}
	
	
	
}
