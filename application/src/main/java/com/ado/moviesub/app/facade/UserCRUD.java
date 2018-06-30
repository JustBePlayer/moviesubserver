package com.ado.moviesub.app.facade;

import com.ado.moviesub.app.entity.user.User;

import java.util.List;

public interface UserCRUD {
  User registerUser(User user);

  void removeUser(Long id);

  List<User> getUsers();

  User getUser(Long id);

  User updateUser(User user);
}
