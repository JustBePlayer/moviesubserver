package com.ado.moviesub.app.exception;

import org.springframework.http.HttpStatus;

public class ConstraintViolationApplicationError extends ApplicationError{

  private String constraintType;

  private ConstraintViolationApplicationError(Builder builder) {
    super(builder);
    constraintType = builder.constraintType;
  }

  public String getConstraintType() {
    return constraintType;
  }

  public static class Builder extends ApplicationError.Builder{
    private String constraintType;

    public Builder(){
      setStatus(HttpStatus.CONFLICT);
    }

    @Override
    protected ConstraintViolationApplicationError build() {
      return new ConstraintViolationApplicationError(this);
    }

    public Builder setConstraintType(String constraintType) {
      this.constraintType = constraintType;
      return this;
    }

    public Builder setMessage(String message){
      super.setMessage(message);
      return this;
    }
  }
}
