package com.ado.moviesub.app.exception;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public abstract class ApplicationError {
  private HttpStatus status;

  private Instant timestamp;
  private String message;

  protected ApplicationError(Builder builder){
    this.status = builder.status;
    this.timestamp = builder.timestamp;
    this.message = builder.message;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }

  protected static abstract class Builder{
    private HttpStatus status;

    private Instant timestamp;
    private String message;

    protected Builder(){
      this.timestamp = Instant.now();
    }

    protected Builder setStatus(HttpStatus status) {
      this.status = status;
      return this;
    }

    protected Builder setMessage(String message) {
      this.message = message;
      return this;
    }

    protected abstract ApplicationError build();
  }
}
