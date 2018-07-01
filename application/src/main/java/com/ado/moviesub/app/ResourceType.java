package com.ado.moviesub.app;

public enum ResourceType {
  USER("User"),
  MOVIE ("Movie"),
  SUBTITLE("Subtitle");

  private String name;

  private ResourceType(String name){
    this.name = name;
  }

  @Override
  public String toString(){
    return name;
  }
}
