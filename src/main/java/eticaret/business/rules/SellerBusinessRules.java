package eticaret.business.rules;

import java.util.List;

import org.springframework.stereotype.Service;

import eticaret.core.exceptions.BusinessException;
import eticaret.dataAccess.SellerRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SellerBusinessRules {
	
	private SellerRepository sellerRepository;
	
	public void checkIfSellerIdExists(int id) {
		
		if(!this.sellerRepository.existsById(id)) {
			throw new BusinessException(id+" id numaralı satıcı bulunmuyor !!!");
		}
		
	}
	
	public void checkIfSellerIdsExists(List<Integer> ids) {
		
		for(int id: ids){
			if(!this.sellerRepository.existsById(id)) {
				throw new BusinessException(id+" id numaralı satıcı bulunmuyor !!!");
			}
		}
		
	}
	
	
	public void checkIfSellerNameExists(String name) {
		if(this.sellerRepository.existsByNameIgnoreCase(name)) {
			throw new BusinessException(name+" adında satıcı bulunuyor !!!");
		}
	}
	
	
	
	
}
