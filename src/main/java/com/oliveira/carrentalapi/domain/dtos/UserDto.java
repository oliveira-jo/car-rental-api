package com.oliveira.carrentalapi.domain.dtos;

import com.oliveira.carrentalapi.domain.models.UserRole;

public record UserDto(
    String login,
    String password,
    UserRole role) {

}
