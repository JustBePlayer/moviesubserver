package com.ado.moviesub.server.app.facade;

import com.ado.moviesub.server.app.entity.user.User;

import java.util.List;

public interface MovieManagementFacade {
  User registerUser(User user);

  void removeUser(User user);

  List<User> getUsers();
}
