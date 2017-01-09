package feudal_web.exception;

import org.springframework.http.HttpStatus;

public class CustomValidationFailedException extends RuntimeException {

	private final HttpStatus statusCode;
	
	public CustomValidationFailedException(String message, HttpStatus statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

}
