package com.ado.moviesub.app.api;

import com.ado.moviesub.app.entity.user.User;
import com.ado.moviesub.app.exception.ResourceNotFoundException;
import com.ado.moviesub.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserAPI {

  protected UserAPI(){

  }

  @Autowired
  private UserRepository repository;

  @Transactional
  public User persistUser(User user){
    return repository.save(user);
  }

  public List<User> getUsers(){
    return repository.findAll();
  }

  @Transactional
  public User getUser(Long id){
    return repository.findById(id).orElseThrow(() -> ResourceNotFoundException.forUser(id));
  }

  @Transactional
  public void removeUser(Long id){
    repository.deleteById(id);
  }

  @Transactional
  public void updateUser(User user) {
    if(!isUserExist(user.getId())){
      throw ResourceNotFoundException.forUser(user.getId());
    }

    repository.save(user);
  }

  private boolean isUserExist(Long userId){
    return repository.findById(userId).isPresent();
  }
}
