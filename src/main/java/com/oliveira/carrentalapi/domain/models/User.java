package com.oliveira.carrentalapi.domain.models;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "USERS")
@Entity(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String login;
  private String password;
  private UserRole role;

  public User(String login, String password, UserRole role) {
    this.login = login;
    this.role = role;
    this.password = password;
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
          new SimpleGrantedAuthority("ROLE_USER"));

    else
      return List.of(new SimpleGrantedAuthority("ROLE_USER"));

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

}
