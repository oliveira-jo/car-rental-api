package com.oliveira.carrentalapi.services;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.oliveira.carrentalapi.domain.dtos.request.UserRequestDto;
import com.oliveira.carrentalapi.domain.dtos.response.UserResponseDto;

public interface UserService {

  public UserResponseDto save(UserRequestDto request);

  public UserResponseDto update(UUID id, UserRequestDto request);

  public void delete(UUID id);

  public List<UserResponseDto> findUsers(Authentication auth);

  public UserDetails findByLogin(String login);

  public UserResponseDto getUserByLogin(String login);

}
