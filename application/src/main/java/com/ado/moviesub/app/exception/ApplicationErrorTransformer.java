package com.ado.moviesub.app.exception;


public interface ApplicationErrorTransformer {
  ApplicationError toApplicationError();

  ApplicationError.Builder getApplicationErrorBuilder();
}
