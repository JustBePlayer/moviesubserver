package com.ado.moviesub.app.exception;

public class InternalServiceException extends ApplicationException {

  /**
	 * 
	 */
	private static final long serialVersionUID = -4226536744594404023L;

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

  @Override
  public InternalApplicationError toApplicationError(){
    return getApplicationErrorBuilder().build();
  }

  @Override
  public InternalApplicationError.Builder getApplicationErrorBuilder(){
    return new InternalApplicationError.Builder().setMessage(getMessage());
  }
}
