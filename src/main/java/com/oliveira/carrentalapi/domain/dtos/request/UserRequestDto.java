package com.oliveira.carrentalapi.domain.dtos.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
                @NotNull @Email(message = "Invalid email format") String email,
                @NotNull @Size(min = 4, message = "Password must have at least 4 characters") @Size(max = 20, message = "Password must have at most 20 characters") String password,
                @NotNull @Size(min = 4, message = "Password Confirmation must have at least 4 characters") @Size(max = 20, message = "Password Confirmation must have at most 20 characters") String passwordConfirmation,
                @NotNull @Size(min = 4, message = "Username must have at least 3 characters") @Size(max = 50, message = "Username must have at most 50 characters") String username,
                @NotNull @Pattern(regexp = "\\(\\d{2}\\)\\d{5}-\\d{4}", message = "Phone must follow the format (XX)XXXXX-XXXX") String phone,
                @Pattern(regexp = "\\d{11}", message = "CNH must be exactly 11 numeric digits") @NotNull String cnh,
                @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC") LocalDate birthDate) {

}
