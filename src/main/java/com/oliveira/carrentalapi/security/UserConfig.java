package com.oliveira.carrentalapi.security;

import java.time.LocalDate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.oliveira.carrentalapi.domain.enums.UserRole;
import com.oliveira.carrentalapi.domain.models.User;
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

      user.setEmail("admin@admin.com");
      user.setUsername("administrator");
      user.setPhone("(54)98181-5555");
      user.setCnh("00000000000");
      user.setBirthDate(LocalDate.now());

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

    var userSuport = this.userRepository.findByLogin("support");
    if (userSuport == null) {
      var user = new User();

      user.setEmail("support@support.com");
      user.setUsername("support attendant");
      user.setPhone("(54)98181-4444");
      user.setCnh("00000000000");
      user.setBirthDate(LocalDate.now());

      user.setLogin("support");
      user.setPassword(passwordEncoder.encode("support"));
      user.setRole(UserRole.SUPPORT);
      userRepository.save(user);
      userSuport = this.userRepository.findByLogin("support");
      System.out.println("------------------------- SUPORT USER CREATE AND SAVE IN BD ------------------------- "
          + userSuport.getUsername());
    } else {
      System.out.println("------------------------- SUPORT USER ALREADY EXIST IN BD -------------------------");

    }

  }

}
