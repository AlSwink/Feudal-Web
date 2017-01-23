package feudal_web.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import feudal_web.exception.CustomValidationFailedException;

@ControllerAdvice
public class FeudalExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger log = LoggerFactory.getLogger(getClass());

	public FeudalExceptionHandler() {
		super();
	}

	@Override
	protected final ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		ResponseEntity<Object> errorResponse = handleExceptionInternal(ex, ex.getMessage(), headers, HttpStatus.BAD_REQUEST, request);
		log.debug(errorResponse.toString());
		return errorResponse;
	}

	@Override
	protected final ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		ResponseEntity<Object> errorResponse = handleExceptionInternal(ex, ex.getMessage(), headers, HttpStatus.BAD_REQUEST, request);
		log.debug(errorResponse.toString());
		return errorResponse;
	}

	@ExceptionHandler
	public final ResponseEntity<Object> handleBadRequest(final CustomValidationFailedException ex,
			final WebRequest request) {
		ResponseEntity<Object> errorResponse = handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), ex.getStatusCode(), request);
		log.debug(errorResponse.toString());
		return errorResponse;
	}
}