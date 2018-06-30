package com.ado.moviesub.integrations.tests.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public class ServerResponse<T> {
  private static final ObjectMapper OBJECT_MAPPER;

  static {
    OBJECT_MAPPER = new ObjectMapper();
    OBJECT_MAPPER.registerModule(new JavaTimeModule());
  }

  private ResponseEntity<String> responseEntity;
  private JavaType type;

  private ServerResponse(ResponseEntity<String> responseEntity, JavaType type) {
    this.responseEntity = responseEntity;
    this.type = type;
  }

  public HttpStatus getStatusCode(){
    return responseEntity.getStatusCode();
  }

  public static <T> ServerResponse<List<T>> ofCollection(ResponseEntity<String> responseEntity, Class<T> clazz){
    return new ServerResponse<>(responseEntity, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
  }

  public static <T> ServerResponse<T> ofObject(ResponseEntity<String> responseEntity, Class<T> clazz){
    return new ServerResponse<>(responseEntity, OBJECT_MAPPER.constructType(clazz));
  }

  public T getResponseObject() throws IOException {
    return OBJECT_MAPPER.readValue(responseEntity.getBody(), type);
  }

}
