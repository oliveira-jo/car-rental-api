package com.oliveira.carrentalapi.infra.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.oliveira.carrentalapi.domain.models.UserRole;
import com.oliveira.carrentalapi.repositories.UserRepository;

import lombok.var;

/* 
 * Create a User Amin in the database, case it's not exist.
 */
@Configuration
public class AdminUserConfig implements CommandLineRunner {

  private UserRepository userRepository;
  private BCryptPasswordEncoder passwordEncoder;

  public AdminUserConfig(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;

  }

  @Override
  public void run(String... args) throws Exception {

    var userAdmin = userRepository.findByLogin("admin");

    if (userAdmin == null) {
      var user = new com.oliveira.carrentalapi.domain.models.User();
      user.setLogin("admin");
      user.setPassword(passwordEncoder.encode("admin"));
      user.setRole(UserRole.ADMIN);
      userRepository.save(user);
    } else {
      System.out.println("-> AMIN already exist!");
    }

  }

}
