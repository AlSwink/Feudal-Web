package feudal_web.validators;

import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import feudal_web.dto.validator.idExists.IdExists;
import feudal_web.dto.validator.idExists.IdExistsValidator;
import feudal_web.service.KingdomService;

@Component
public class IdExistsValidatorImpl implements IdExistsValidator {
	
	private final KingdomService kingdomService;

	public IdExistsValidatorImpl(KingdomService kingdomService) {
		this.kingdomService = kingdomService;
	}

	public void initialize(IdExists constraint) {
	}

	public boolean isValid(Integer id, ConstraintValidatorContext context) {
		return id == 0 || kingdomService.has(id);
	}

}
