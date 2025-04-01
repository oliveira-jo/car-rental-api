package com.oliveira.carrentalapi.domain.dtos.response;

import java.time.LocalDate;

import com.oliveira.carrentalapi.domain.enums.UserRole;

public record UserResponseDto(
                String id,
                String login,
                String email,
                String username,
                UserRole role,
                String phone,
                String cnh,
                LocalDate birthDate) {

}
