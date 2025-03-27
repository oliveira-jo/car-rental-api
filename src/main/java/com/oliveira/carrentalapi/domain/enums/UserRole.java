package com.oliveira.carrentalapi.domain.enums;

public enum UserRole {

  ADMIN("admin"),
  SUPPORT("support"),
  USER("user");

  private String role;

  UserRole(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }

}
