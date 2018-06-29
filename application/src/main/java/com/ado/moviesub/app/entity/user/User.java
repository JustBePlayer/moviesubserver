package com.ado.moviesub.app.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

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

  private User(Builder builder){
    // @formatter:off
    this(
        builder.id,
        builder.userName,
        builder.age,
        builder.email
    );
    // @formatter:on
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    User user = (User) o;
    return Objects.equals(id, user.id) && Objects.equals(userName, user.userName) && Objects.equals(age, user.age) && Objects
        .equals(email, user.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userName, age, email);
  }

  public static class Builder{
    private Long id;
    private String userName;
    private Integer age;
    private String email;

    public Builder(){

    }

    public Builder setId(Long id) {
      this.id = id;
      return this;
    }

    public Builder setUserName(String userName) {
      this.userName = userName;
      return this;
    }

    public Builder setAge(Integer age) {
      this.age = age;
      return this;
    }

    public Builder setEmail(String email) {
      this.email = email;
      return this;
    }

    public User build(){
      return new User(this);
    }
  }
}
