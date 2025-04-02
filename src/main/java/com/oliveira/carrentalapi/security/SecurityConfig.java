package com.oliveira.carrentalapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final SecurityFilter securityFilter;

  private static final String[] AUTH_WHITELIST = {
      "/api/v1/auth/**", "/v3/api-docs/**", "/v3/api-docs/yaml",
      "/swagger-ui/**", "/swagger-ui.html", "/actuator/**" };

  public SecurityConfig(SecurityFilter securityFilter) {
    this.securityFilter = securityFilter;
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    return httpSecurity
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(AUTH_WHITELIST).permitAll()
            .requestMatchers(HttpMethod.GET, "/index.html").permitAll()
            // -> USER
            .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
            .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
            .requestMatchers(HttpMethod.GET, "/user/all").hasAnyRole("ADMIN", "SUPPORT")
            .requestMatchers(HttpMethod.PUT, "/user/{id}").hasAnyRole("ADMIN", "SUPPORT", "CLIENT")
            .requestMatchers(HttpMethod.DELETE, "/user/{id}").hasAnyRole("ADMIN", "SUPPORT", "CLIENT")
            // -> CATEGORY
            .requestMatchers(HttpMethod.GET, "/category").permitAll()
            .requestMatchers(HttpMethod.POST, "/category").hasAnyRole("ADMIN", "SUPPORT")
            .requestMatchers(HttpMethod.PUT, "/category/{id}").hasAnyRole("ADMIN", "SUPPORT")
            .requestMatchers(HttpMethod.DELETE, "/category/{id}").hasAnyRole("ADMIN", "SUPPORT")
            // -> VEHICLE
            .requestMatchers(HttpMethod.GET, "/vehicle").permitAll()
            .requestMatchers(HttpMethod.POST, "/vehicle").hasAnyRole("ADMIN", "SUPPORT")
            .requestMatchers(HttpMethod.PUT, "/vehicle/{id}").hasAnyRole("ADMIN", "SUPPORT")
            .requestMatchers(HttpMethod.DELETE, "/vehicle/{id}").hasAnyRole("ADMIN", "SUPPORT")
            // -> RESERVATION
            // .requestMatchers(HttpMethod.GET, "/reservation/all").hasAnyRole("ADMIN",
            // "SUPPORT")
            .requestMatchers(HttpMethod.GET, "/reservation/{id}").hasAnyRole("ADMIN", "SUPPORT", "CLIENT")
            .requestMatchers(HttpMethod.POST, "/reservation").hasAnyRole("ADMIN", "SUPPORT", "CLIENT")
            .requestMatchers(HttpMethod.DELETE, "/reservation/{id}").hasAnyRole("ADMIN", "SUPPORT", "CLIENT")
            // -> ANY
            .anyRequest().authenticated())

        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  /*
   * Indicate to spring how is that authentication manager and where spring get it
   */
  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
