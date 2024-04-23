package com.oliveira.carrentalapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.oliveira.carrentalapi.domain.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {

  UserDetails findByLogin(String login);

}
