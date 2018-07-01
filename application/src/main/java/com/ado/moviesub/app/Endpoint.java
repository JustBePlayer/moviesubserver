package com.ado.moviesub.app;

public enum Endpoint {
  USERS("users"),
  MOVIES ("movies"),
  TRANSLATED_SUBTITLE("subtitles/translated");

  private static final String URL_DELIMITER = "/";
  private static final String REQUEST_PARAM_DELIMITER = "?";
  private String name;

  private Endpoint(String name){
    this.name = name;
  }

  public String toEndpointPath(){
    return String.format("%s/%s", URL_DELIMITER, name);
  }

  public String toEndpointPathWithFilter(String filter){
    return toEndpointPath().concat(REQUEST_PARAM_DELIMITER).concat(filter);
  }

  public String toEndpointPath(Long id){
    return toEndpointPath((Object) id);
  }

  public String toEndpointPath(String id){
    return toEndpointPath((Object) id);
  }

  private String toEndpointPath(Object id){
    return toEndpointPath().concat(URL_DELIMITER).concat(id.toString());
  }
}
