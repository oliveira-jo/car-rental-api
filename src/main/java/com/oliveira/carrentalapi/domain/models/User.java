package com.oliveira.carrentalapi.domain.models;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.oliveira.carrentalapi.domain.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "USERS")
@Entity(name = "USERS")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String login;
  private String password;

  @Enumerated(EnumType.STRING)
  private UserRole role;

  private String email;
  private String username;
  private String phone;
  private String cnh;
  private LocalDate birthDate;

  public User() {
  }

  public User(String login, String password, UserRole role, String email, String username, String phone, String cnh,
      LocalDate birthDate) {
    this.login = login;
    this.password = password;
    this.role = role;
    this.email = email;
    this.username = username;
    this.phone = phone;
    this.cnh = cnh;
    this.birthDate = birthDate;
  }

  /*
   * what roles each user have,
   * return what is the role of each user to know with
   * securituy block or not the expecificly endpoint
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    if (this.role == UserRole.ADMIN)
      return List.of(
          new SimpleGrantedAuthority("ROLE_ADMIN"),
          new SimpleGrantedAuthority("ROLE_SUPPORT"),
          new SimpleGrantedAuthority("ROLE_CLIENT"));

    else if (this.role == UserRole.SUPPORT)
      return List.of(
          new SimpleGrantedAuthority("ROLE_SUPPORT"),
          new SimpleGrantedAuthority("ROLE_CLIENT"));

    else if (this.role == UserRole.CLIENT)
      return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));

    else
      return List.of();

  }

  @Override
  public String getUsername() {
    return login;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
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

  public UserRole getRole() {
    return role;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCnh() {
    return cnh;
  }

  public void setCnh(String cnh) {
    this.cnh = cnh;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", login=" + login + ", role=" + role + ", email=" + email + ", username=" + username
        + ", phone=" + phone + ", cnh=" + cnh + ", birthDate=" + birthDate + "]";
  }

}
