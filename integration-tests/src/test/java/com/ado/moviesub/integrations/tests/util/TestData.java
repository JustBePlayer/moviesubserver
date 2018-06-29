package com.ado.moviesub.integrations.tests.util;

import com.ado.moviesub.app.entity.user.User;

public class TestData {

  private static final String EMAIL_DOMAIN = "@test.com";
  private TestData(){

  }

  public static User createSampleUser(String userName){
    // @formatter:off
    return new User.Builder()
        .setUserName(userName)
        .setAge(12)
        .setEmail(userName.concat(EMAIL_DOMAIN))
        .build();
    // @formatter:on
  }
}
