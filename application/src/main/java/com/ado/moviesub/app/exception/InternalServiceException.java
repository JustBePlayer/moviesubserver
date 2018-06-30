package com.ado.moviesub.app.exception;

public class InternalServiceException extends RuntimeException implements ApplicationErrorTransformer{

  public InternalServiceException() {
    super();
  }
  public InternalServiceException(String message, Throwable cause) {
    super(message, cause);
  }
  public InternalServiceException(String message) {
    super(message);
  }
  public InternalServiceException(Throwable cause) {
    super(cause);
  }

  public InternalApplicationError toApplicationError(){
    return getApplicationErrorBuilder().build();
  }

  public InternalApplicationError.Builder getApplicationErrorBuilder(){
    return new InternalApplicationError.Builder().setMessage(getMessage());
  }
}
