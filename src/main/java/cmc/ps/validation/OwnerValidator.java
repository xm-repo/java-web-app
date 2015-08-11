package cmc.ps.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import cmc.ps.model.Owner;
import cmc.ps.service.LegalEntityService;

@Component
public class OwnerValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg) {
		return Owner.class.isAssignableFrom(arg);

	}
	
	private LegalEntityService legalEntityService;
	
	@Autowired
	public void setLegalEntityService(LegalEntityService legalEntityService) {
		this.legalEntityService = legalEntityService;
	}
	
	public OwnerValidator(LegalEntityService legalEntityService) {
		//this.legalEntityService = legalEntityService;
	}
	
	public OwnerValidator() {
		
	}
	


	@Override
	public void validate(Object object, Errors arg1) {
		
		Owner owner = (Owner) object;
		
		if(owner.getLegalEntity1() != null) {
			legalEntityService.refresh(owner.getLegalEntity1());
		}
		
	}

}
