package com.ado.moviesub.server.app.controller;

import com.ado.moviesub.server.app.entity.user.User;
import com.ado.moviesub.server.app.facade.MovieManagementFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private MovieManagementFacade movieManagementFacade;

  @GetMapping
  public ResponseEntity<List<User>> getUsers() {
    List<User> users = movieManagementFacade.getUsers();
    return ResponseEntity.ok(users);
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user){
    User createdUser = movieManagementFacade.registerUser(user);
    return ResponseEntity.ok(createdUser);
  }


}