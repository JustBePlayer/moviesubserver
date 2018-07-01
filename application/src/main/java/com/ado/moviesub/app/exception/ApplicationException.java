package com.ado.moviesub.app.exception;

public abstract class ApplicationException extends RuntimeException {

  public ApplicationException(){

  }
  public ApplicationException(String message){
    super(message);
  }

  public ApplicationException(String message, Throwable cause) {
    super(message, cause);
  }

  public ApplicationException(Throwable cause) {
    super(cause);
  }

  public abstract ApplicationError toApplicationError();

  public abstract ApplicationError.Builder getApplicationErrorBuilder();
}
