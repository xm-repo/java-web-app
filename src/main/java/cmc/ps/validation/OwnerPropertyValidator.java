package cmc.ps.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cmc.ps.model.Owner;
import cmc.ps.service.LegalEntityService;


public class OwnerPropertyValidator implements ConstraintValidator<OwnerConstraint, Owner> {

	public LegalEntityService legalEntityService;
	
	@Autowired
	public void setLegalEntityService(LegalEntityService legalEntityService) {
		this.legalEntityService = legalEntityService;
	}

	@Override
	public boolean isValid(Owner owner, ConstraintValidatorContext context) {
		
		if(owner.getLegalEntity1() != null) {
			legalEntityService.refresh(owner.getLegalEntity1());
		}
		
		List<Owner> owners = owner.getLegalEntity1().getOwners1(); 
		int property = 0;
		
		for(Owner next : owners) {
			property += next.getProperty();
		}
		
		if(property > 100) {
			return false;
		}
		
		return true;
	}

	@Override
	public void initialize(OwnerConstraint constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

}
