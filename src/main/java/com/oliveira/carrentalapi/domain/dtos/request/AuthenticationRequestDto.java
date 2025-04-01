package com.oliveira.carrentalapi.domain.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthenticationRequestDto(
        @NotNull String login,
        @NotNull @Size(min = 4, message = "Password must have at least 4 characters") @Size(max = 20, message = "Password must have at most 20 characters") String password) {

}
