package feudal_web.validator.exception;

public class ValidatorNotFoundException extends RuntimeException {

	/**
	 * generated serializable id
	 */
	private static final long serialVersionUID = -3789613700401748221L;

	public ValidatorNotFoundException(Object o) {
		super("Validator not found for class [ " + o.getClass().getCanonicalName() + " ]");
	}
	
}
