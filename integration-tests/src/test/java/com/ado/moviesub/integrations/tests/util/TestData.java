package com.ado.moviesub.integrations.tests.util;

import com.ado.moviesub.app.entity.movie.Movie;
import com.ado.moviesub.app.entity.user.User;

import java.time.Duration;

public class TestData {

  private static final String EMAIL_DOMAIN = "@test.com";
  private TestData(){

  }

  public static User createSampleUser(String userName){
    return createSampleUserBuilder(userName).build();
  }

  public static Movie createSampleMovie(String name){
    return createSampleMovieBuilder(name).build();
  }

  public static User.Builder createSampleUserBuilder(String userName){
    // @formatter:off
    return new User.Builder()
        .setUserName(userName)
        .setAge(12)
        .setEmail(userName.concat(EMAIL_DOMAIN));
    // @formatter:on
  }

  public static Movie.Builder createSampleMovieBuilder(String name){
    // @formatter:off
    return new Movie.Builder()
        .setName(name)
        .setDescription("description")
        .setDuration(Duration.ZERO);
    // @formatter:on
  }
}
