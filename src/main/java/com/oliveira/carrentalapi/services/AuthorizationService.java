package com.oliveira.carrentalapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oliveira.carrentalapi.repositories.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

  @Autowired
  UserRepository repository;

  /*
   * Spring Security search here the users
   * In this method we can get the user auth in a other api like google or
   * same in a Database table
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository.findByLogin(username);
  }

}
