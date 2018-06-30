package com.ado.moviesub.app.exception;

import org.springframework.http.HttpStatus;

public class InternalApplicationError extends ApplicationError{

  private InternalApplicationError(Builder builder) {
    super(builder);
  }

  public static class Builder extends ApplicationError.Builder{
    public Builder(){
      setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected InternalApplicationError build() {
      return new InternalApplicationError(this);
    }

    public Builder setMessage(String message){
      super.setMessage(message);
      return this;
    }
  }
}
