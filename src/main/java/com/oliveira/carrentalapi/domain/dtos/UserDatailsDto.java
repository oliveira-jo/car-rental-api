package com.oliveira.carrentalapi.domain.dtos;

import java.util.UUID;

public class UserDatailsDto {

    private UUID id;
    private String login;
    private String password;

    public UserDatailsDto() {
    }

    public UserDatailsDto(UUID id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
