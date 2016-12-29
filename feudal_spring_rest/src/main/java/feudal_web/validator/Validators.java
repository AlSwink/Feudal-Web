package feudal_web.validator;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import feudal_web.validator.exception.ValidatorNotFoundException;

@Component
public class Validators {
	
	private HashMap<String, Validator> validatorMap = new HashMap<>();
	
	public Validators(List<Validator> validators) {
		for(Validator validator : validators)
			validatorMap.put(validator.getValidatedClassName(), validator);
	}
	
	/**
	 * Finds the appropriate validator for an object, and then uses the validator to validate the object. 
	 * If the object fails validation, an appropriate exception is thrown by the validator
	 * @param o the object to be validated
	 * @throws ValidatorNotFoundException when no validator is found for o
	 */
	public void validate(Object o) {
		Validator validator = validatorMap.get(o.getClass().getCanonicalName());
		if(validator == null)
			throw new ValidatorNotFoundException(o);
		validator.validate(o);
	}
}
