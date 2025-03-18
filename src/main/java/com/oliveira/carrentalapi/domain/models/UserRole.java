package com.oliveira.carrentalapi.domain.models;

public enum UserRole {

  ADMIN("admin"),
  SUPPORT_ATTENDANT("support_attendant"),
  USER("user");

  private String role;

  UserRole(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }

}
