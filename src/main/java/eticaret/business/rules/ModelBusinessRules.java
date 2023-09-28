package eticaret.business.rules;

import java.util.List;

import org.springframework.stereotype.Service;

import eticaret.core.exceptions.BusinessException;
import eticaret.dataAccess.ModelRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
	
	private ModelRepository modelRepository;
	
	
	public void checkIfModelIdExists(int id) {
		
		if(!this.modelRepository.existsById(id)) {
			throw new BusinessException(id+" id numarasına sahip model bulunmuyor !!!");
		}
		
	}
	
	public void checkIfModelIdsExists(List<Integer> ids) {
		
		for(int id: ids) {
			if(!this.modelRepository.existsById(id)) {
				throw new BusinessException(id+" id numarasına sahip model bulunmuyor !!!");
			}
		}
		
	}
	
	public void checkIfModelNameExists(String name) {
		
		if(this.modelRepository.existsByNameIgnoreCase(name)) {
			throw new BusinessException("Bu model adında başka bir model bulunuyor !!!");
		}
		
	}
	
}
