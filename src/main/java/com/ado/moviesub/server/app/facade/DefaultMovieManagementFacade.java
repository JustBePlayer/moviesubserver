package com.ado.moviesub.server.app.facade;

import com.ado.moviesub.server.app.api.UserAPI;
import com.ado.moviesub.server.app.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultMovieManagementFacade implements MovieManagementFacade {

  @Autowired
  private UserAPI userAPI;

  @Override
  public User registerUser(User user) {
    return userAPI.persistUser(user);
  }

  @Override
  public void removeUser(User user) {

  }

  @Override
  public List<User> getUsers() {
    return userAPI.getUsers();
  }
}
