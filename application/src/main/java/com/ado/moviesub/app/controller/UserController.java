package com.ado.moviesub.app.controller;

import com.ado.moviesub.app.entity.user.User;
import com.ado.moviesub.app.facade.MovieManagementFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.websocket.server.PathParam;
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

  @GetMapping("/{id}")
  public ResponseEntity<User> getUser(@PathVariable("id") Long id){
    User user = movieManagementFacade.getUser(id);
    return ResponseEntity.ok(user);
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updatedUser(@PathVariable("id") Long id, @RequestBody User user){
    User updatedUser = movieManagementFacade.updateUser(user);
    return ResponseEntity.ok(updatedUser);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
    movieManagementFacade.removeUser(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user){
    User createdUser = movieManagementFacade.registerUser(user);
    return ResponseEntity.ok(createdUser);
  }


}