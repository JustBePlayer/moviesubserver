package com.ado.moviesub.app.exception;

import org.springframework.http.HttpStatus;

public class SubtitleNotFoundApplicationError extends ApplicationError{
  private String providerId;

  private SubtitleNotFoundApplicationError(Builder builder) {
    super(builder);
    this.providerId = builder.providerId;
  }

  public String getProviderId() {
    return providerId;
  }

  public static class Builder extends ApplicationError.Builder{
    private String providerId;

    public Builder(){
      setStatus(HttpStatus.NOT_FOUND);
    }

    @Override
    protected SubtitleNotFoundApplicationError build() {
      return new SubtitleNotFoundApplicationError(this);
    }

    public Builder setResourceId(String providerId) {
      this.providerId = providerId;
      return this;
    }

    public Builder setMessage(String message){
      super.setMessage(message);
      return this;
    }
  }
}
