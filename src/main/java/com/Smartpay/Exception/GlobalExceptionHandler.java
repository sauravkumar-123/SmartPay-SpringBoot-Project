package com.Smartpay.Exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path.Node;
import javax.ws.rs.BadRequestException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<ExceptionResponse> handleAnyException(Exception exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), false, exception.getMessage(),
				null);
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = { GlobalException.class })
	public ResponseEntity<ExceptionResponse> handleGlobalException(GlobalException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), false, exception.getMessage(),
				null);
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { ResourceNotFoundException.class })
	public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), false, exception.getMessage(),
				null);
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<String, String>();
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		log.info("Valadation Error!!{} ", errors);
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), false, "Failed!!", errors);
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException exception, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<String, String>();
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		log.info("Valadation Error!!{} ", errors);
		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), false, "Failed!!", errors);
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {
		Map<String, Object> errors = new HashMap<>();
		if (!ex.getConstraintViolations().isEmpty()) {
			for (ConstraintViolation constraintViolation : ex.getConstraintViolations()) {
				String fieldName = null;
				for (Node node : constraintViolation.getPropertyPath()) {
					fieldName = node.getName();
				}
				errors.put(fieldName, constraintViolation.getMessage());
			}
		}
		log.info("Validation Error!!{} ", errors);
		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), false, "Failed!!", errors);
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { BadCredentialsException.class })
	public ResponseEntity<ExceptionResponse> handleBadCredentialsException(BadCredentialsException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), false, exception.getMessage(),
				null);
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { BadRequestException.class })
	public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), false, exception.getMessage(),
				null);
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = { FileStorageException.class })
	public ResponseEntity<ExceptionResponse> handleFileStorageException(FileStorageException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), false, exception.getMessage(),
				null);
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { FileNotFoundException.class })
	public ResponseEntity<ExceptionResponse> handleFileNotFoundException(FileNotFoundException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), false, exception.getMessage(),
				null);
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
}
