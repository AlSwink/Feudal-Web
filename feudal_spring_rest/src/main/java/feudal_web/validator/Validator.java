package feudal_web.validator;

public interface Validator {
	
	/**
	 * 
	 * @return the canonical name of the class this validator is expected to operate on
	 */
	String getValidatedClassName();
	
	
	/**
	 * This will throw an appropriate exception if the parameter does not pass a series of validations
	 * @param feudal the feudal to be validated
	 */
	void validate(Object feudal);
	
}
