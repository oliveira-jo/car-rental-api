package com.oliveira.carrentalapi.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  SecurityFilter securityFilter;

  /*
   * Security Silter Chain
   * Methods to validations filters and security to the applications
   * Validations users and with it's able to access
   * 
   * auth STATFULL -> sava informations of user active session
   * auth STATELESS -> don't save sessions, but pass a token to user,
   * more use with standard rest
   * 
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    return httpSecurity
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
            // just to test, normaly we need to create a admin user in the database
            .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

            .requestMatchers(HttpMethod.POST, "/user").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/ping").hasRole("ADMIN")
            // all others need auth
            .anyRequest().authenticated())

        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();

  }

  /*
   * Indicate to spring how is that authentication manager and where spring get it
   */
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  /*
   * Will save the password in database encrypted
   * point to spring that it's need to do this transformations
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
