package com.smartpay.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path.Node;
import jakarta.ws.rs.BadRequestException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionSystemException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ObjectMapper ObjectMapper;

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ExceptionResponse> handleAnyException(Exception exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), false, exception.getMessage(),
                null);
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {GlobalException.class})
    public ResponseEntity<ExceptionResponse> handleGlobalException(GlobalException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), false, exception.getMessage(),
                null);
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, exception.getHttpStatus());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), false, exception.getMessage(),
                null);
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    //@Override
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

    //@Override
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
    @ExceptionHandler(value = {TransactionSystemException.class})
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
    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ExceptionResponse> handleBadCredentialsException(BadCredentialsException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), false, exception.getMessage(),
                null);
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), false, exception.getMessage(),
                null);
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {FileStorageException.class})
    public ResponseEntity<ExceptionResponse> handleFileStorageException(FileStorageException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), false, exception.getMessage(),
                null);
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {FileNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleFileNotFoundException(FileNotFoundException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), false, exception.getMessage(),
                null);
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(value = {RestClientCallException.class})
//    public ResponseEntity<ExceptionResponse> handleHttpClientException(RestClientCallException exception) {
//        Response result = null;
//        if (exception.getHttpStatusCodeException() instanceof HttpClientErrorException || exception.getHttpStatusCodeException() instanceof HttpServerErrorException) {
//            if (exception.getHttpStatusCodeException().getStatusCode().is4xxClientError() || exception.getHttpStatusCodeException().getStatusCode().is5xxServerError()) {
//                logger.error("error while communicating with Bank Server.....!!!");
//                String data = exception.getHttpStatusCodeException().getResponseBodyAsString();
//                try {
//                    result = ObjectMapper.readValue(data, Response.class);
//                } catch (Exception ex) {
//                    log.error("Error while parsing response data{} ", ex);
//                    throw new GlobalException(ex);
//                }
//            }
//        }
//        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), false, (result.getMessage() == null ? " " : result.getMessage()),
//                result.getDatasource());
//        return new ResponseEntity<ExceptionResponse>(exceptionResponse, exception.getHttpStatusCodeException().getStatusCode());
//    }
}
