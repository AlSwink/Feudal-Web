package feudal_web.validator.impl;

import org.springframework.stereotype.Component;

import com.cooksys.ftd.assignments.collections.model.Peon;

import feudal_web.validator.Validator;

@Component
public class PeonValidator implements Validator {

	@Override
	public String getValidatedClassName() {
		return Peon.class.getCanonicalName();
	}

	@Override
	public void validate(Object feudal) {
		// TODO Auto-generated method stub
		
	}

}
