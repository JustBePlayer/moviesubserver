package com.ado.moviesub.app.exception;

import com.ado.moviesub.app.ResourceType;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundApplicationError extends ApplicationError{

  private ResourceType resourceType;
  private Long resourceId;

  private ResourceNotFoundApplicationError(Builder builder) {
    super(builder);
    this.resourceType = builder.resourceType;
    this.resourceId = builder.resourceId;
  }

  public ResourceType getResourceType() {
    return resourceType;
  }

  public Long getResourceId() {
    return resourceId;
  }

  public static class Builder extends ApplicationError.Builder{
    private ResourceType resourceType;
    private Long resourceId;

    public Builder(){
      setStatus(HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResourceNotFoundApplicationError build() {
      return new ResourceNotFoundApplicationError(this);
    }

    public Builder setResourceType(ResourceType resourceType) {
      this.resourceType = resourceType;
      return this;
    }

    public Builder setResourceId(Long resourceId) {
      this.resourceId = resourceId;
      return this;
    }

    public ResourceNotFoundApplicationError.Builder setMessage(String message){
      super.setMessage(message);
      return this;
    }
  }
}
