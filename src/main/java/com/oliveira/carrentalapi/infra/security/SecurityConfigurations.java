package com.oliveira.carrentalapi.infra.security;

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
public class SecurityConfigurations {

  private final SecurityFilter securityFilter;

  private static final String[] AUTH_WHITELIST = {
      "/api/v1/auth/**", "/v3/api-docs/**", "/v3/api-docs/yaml",
      "/swagger-ui/**", "/swagger-ui.html"
  };

  public SecurityConfigurations(SecurityFilter securityFilter) {
    this.securityFilter = securityFilter;
  }

  /*
   * Security Filter Chain
   * Methods to validations filters and security to the applications
   * Validations users and with it's able to access
   * 
   * auth STATFULL -> sava informations of user active session
   * auth STATELESS -> don't save sessions, but pass a token to user,
   * more use with standard rest
   * 
   */
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    return httpSecurity
        .csrf(csrf -> csrf.disable())
        // Server don't save status. Need to pass all in the requisition
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(AUTH_WHITELIST).permitAll()
            .requestMatchers(HttpMethod.GET, "/index.html").permitAll()
            // -> USER
            // just to test, normaly we need to create a admin user in the database
            .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
            .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
            .requestMatchers(HttpMethod.GET, "/user").hasRole("ADMIN")
            // -> CATEGORY
            .requestMatchers(HttpMethod.GET, "/category").permitAll()
            .requestMatchers(HttpMethod.POST, "/category").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/category").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/category").hasRole("ADMIN")
            // -> VEHICLE
            .requestMatchers(HttpMethod.GET, "/vehicle").permitAll()
            .requestMatchers(HttpMethod.POST, "/vehicle").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/vehicle").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/vehicle").hasRole("ADMIN")
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

  /*
   * Will save the password in database encrypted
   * point to spring that it's need to do this transformations
   */
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
