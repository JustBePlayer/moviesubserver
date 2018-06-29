package com.ado.moviesub.app;

public enum Endpoint {
  USERS("users"),
  MOVIES ("movies");

  private static final String URL_DELIMITER = "/";
  private String name;

  private Endpoint(String name){
    this.name = name;
  }

  public String toEndpointPath(){
    return String.format("%s/%s", URL_DELIMITER, name);
  }
}
