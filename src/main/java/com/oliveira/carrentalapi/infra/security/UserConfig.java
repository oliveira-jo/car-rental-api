package com.oliveira.carrentalapi.infra.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.oliveira.carrentalapi.domain.models.User;
import com.oliveira.carrentalapi.domain.models.UserRole;
import com.oliveira.carrentalapi.repositories.UserRepository;

/**
 * Create a Admin User in the database, case it's not exist.
 * Create a Support User in the database, case it's not exist.
 */
@Component
public class UserConfig {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public UserConfig(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    veryfyAdminBd();

  }

  public void veryfyAdminBd() {

    var userAdmin = this.userRepository.findByLogin("admin");
    if (userAdmin == null) {
      var user = new User();
      user.setLogin("admin");
      user.setPassword(passwordEncoder.encode("admin"));
      user.setRole(UserRole.ADMIN);
      userRepository.save(user);
      userAdmin = this.userRepository.findByLogin("admin");
      System.out.println("------------------------- ADMINISTRATOR CREATE AND SAVE IN BD ------------------------- "
          + userAdmin.getUsername());
    } else {
      System.out.println("------------------------- ADMINISTRATOR ALREADY EXIST IN BD -------------------------");

    }

    var userSuport = this.userRepository.findByLogin("suport");
    if (userSuport == null) {
      var user = new com.oliveira.carrentalapi.domain.models.User();
      user.setLogin("suport");
      user.setPassword(passwordEncoder.encode("suport"));
      user.setRole(UserRole.SUPPORT);
      userRepository.save(user);
      userAdmin = this.userRepository.findByLogin("suport");
      userSuport = this.userRepository.findByLogin("suport");
      System.out.println("------------------------- SUPORT USER CREATE AND SAVE IN BD ------------------------- "
          + userSuport.getUsername());
    } else {
      System.out.println("------------------------- SUPORT USER ALREADY EXIST IN BD -------------------------");

    }

  }

}
