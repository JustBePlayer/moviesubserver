package com.ado.moviesub.server.app.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Person")
public class User {

  protected User(){

  }
  // @formatter:off
  private User(
      @JsonProperty("id") Long id,
      @JsonProperty(value = "userName", required = true) @NotBlank String userName,
      @JsonProperty("age") Integer age,
      @JsonProperty(value = "email", required = true) @NotBlank @Email String email
  ) {
    this.id = id;
    this.userName = userName;
    this.age = age;
    this.email = email;
  }
  // @formatter:on

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotBlank
  @Column(name = "userName")
  private String userName;

  @Column(name = "age")
  private Integer age;

  @NotBlank
  @Email
  @Column(name = "email")
  private String email;

  public Long getId() {
    return id;
  }

  public String getUserName() {
    return userName;
  }

  public Integer getAge() {
    return age;
  }

  public String getEmail() {
    return email;
  }
}
