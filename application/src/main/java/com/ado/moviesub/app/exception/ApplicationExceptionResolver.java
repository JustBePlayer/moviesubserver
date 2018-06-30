package com.ado.moviesub.app.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApplicationExceptionResolver extends ResponseEntityExceptionHandler {

  @ExceptionHandler(DataIntegrityViolationException.class)
  protected ResponseEntity<ApplicationError> handleUniqueException(DataIntegrityViolationException ex){
    if(!(ex.getCause() instanceof ConstraintViolationException)){
      return handleUnknownException(ex);
    }

    // @formatter:off
    ApplicationError error = new ConstraintViolationApplicationError.Builder()
        .setConstraintType("uniqueness")
        .setMessage("One of the attributes already exist")
        .build();
    // @formatter:on

    return createResponseBody(error);
  }

  @ExceptionHandler(InternalServiceException.class)
  protected ResponseEntity<ApplicationError> handleInternalServerError(InternalServiceException ex){
   return createResponseBody(ex.toApplicationError());
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  protected ResponseEntity<ApplicationError> handleResourceNotFoundException(ResourceNotFoundException ex){
    return createResponseBody(ex.toApplicationError());
  }

  @ExceptionHandler(RuntimeException.class)
  protected ResponseEntity<ApplicationError> handleUnknownException(RuntimeException ex){
    return createResponseBody(new InternalServiceException(ex).toApplicationError());
  }

  private ResponseEntity<ApplicationError> createResponseBody(ApplicationError applicationError){
    return new ResponseEntity<>(applicationError, applicationError.getStatus());
  }
}
