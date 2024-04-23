package com.oliveira.carrentalapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oliveira.carrentalapi.repositories.UserRepository;

@Service
public class AuthorizationServiceImpl implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  public AuthorizationServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /*
   * Spring Security search here the users
   * In this method we can get the user auth in a other api like google or
   * same in a Database table
   */
  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    return userRepository.findByLogin(login);
  }

}
