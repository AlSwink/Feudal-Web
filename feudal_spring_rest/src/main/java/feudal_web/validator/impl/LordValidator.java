package feudal_web.validator.impl;

import org.springframework.stereotype.Component;

import com.cooksys.ftd.assignments.collections.model.Lord;

import feudal_web.validator.Validator;

@Component
public class LordValidator implements Validator {

	@Override
	public String getValidatedClassName() {
		return Lord.class.getCanonicalName();
	}

	@Override
	public void validate(Object feudal) {
		// TODO Auto-generated method stub
		
	}

}
