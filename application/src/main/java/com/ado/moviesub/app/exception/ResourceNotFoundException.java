package com.ado.moviesub.app.exception;

import com.ado.moviesub.app.ResourceType;

public class ResourceNotFoundException extends ApplicationException{

  /**
	 * 
	 */
	private static final long serialVersionUID = 7206003987078972539L;
private static final String MESSAGE = "Resource was not found";
  private Long resourceId;
  private ResourceType resourceType;

  private ResourceNotFoundException(ResourceType resourceType, Long resourceId) {
    super(MESSAGE);
    this.resourceType = resourceType;
    this.resourceId = resourceId;
  }

  public static ResourceNotFoundException forUser(Long resourceId){
    return new ResourceNotFoundException(ResourceType.USER, resourceId);
  }

  public static ResourceNotFoundException forMovie(Long resourceId){
    return new ResourceNotFoundException(ResourceType.MOVIE, resourceId);
  }

  @Override
  public ResourceNotFoundApplicationError toApplicationError(){
    return getApplicationErrorBuilder().build();
  }

  @Override
  public ResourceNotFoundApplicationError.Builder getApplicationErrorBuilder(){
    // @formatter:off
    return new ResourceNotFoundApplicationError.Builder()
        .setMessage(getMessage())
        .setResourceId(resourceId)
        .setResourceType(resourceType);
    // @formatter:on
  }
}
