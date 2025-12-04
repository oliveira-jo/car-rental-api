package com.oliveira.carrentalapi.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.oliveira.carrentalapi.domain.enums.UserRole;
import com.oliveira.carrentalapi.domain.models.User;
import com.oliveira.carrentalapi.repositories.UserRepository;

@Component
public class SeedUser implements ApplicationRunner {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Override
  public void run(ApplicationArguments args) {

    if (userRepository.count() > 0) {
      return; // evita duplicar dados
    }

    System.out.println("----------------------------- RUN SEED USER -----------------------------");

    // -----------------------------
    // USER - ADMIN
    // -----------------------------
    if (userRepository.findByLogin("admin") == null) {
      User user = new User(
          "admin",
          passwordEncoder.encode("admin"),
          UserRole.ADMIN,
          "admin@admin.com",
          "administrator",
          "(54)98181-5555",
          "00000000000",
          LocalDate.now());
      userRepository.save(user);
    }

    // -----------------------------
    // USER - SUPPORT
    // -----------------------------
    if (userRepository.findByLogin("support") == null) {
      User user = new User(
          "support",
          passwordEncoder.encode("support"),
          UserRole.SUPPORT,
          "support@support.com",
          "support attendant",
          "(54)98181-4444",
          "00000000000",
          LocalDate.now());
      userRepository.save(user);
      userRepository.save(user);
    }

  }

}
