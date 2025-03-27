package com.oliveira.carrentalapi.infra.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.oliveira.carrentalapi.domain.exceptions.ObjectNotFoundException;
import com.oliveira.carrentalapi.repositories.UserRepository;
import com.oliveira.carrentalapi.services.impl.TokenServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  private final TokenServiceImpl tokenService;
  private final UserRepository userRepository;

  public SecurityFilter(TokenServiceImpl tokenService, UserRepository userRepository) {
    this.tokenService = tokenService;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(@SuppressWarnings("null") HttpServletRequest request,
      @SuppressWarnings("null") HttpServletResponse response,
      @SuppressWarnings("null") FilterChain filterChain) throws ServletException, IOException {

    var token = this.recoverToken(request);

    if (token != null) {
      var login = tokenService.validateToken(token);
      UserDetails user = userRepository.findByLogin(login);

      if (user == null) {
        throw new ObjectNotFoundException("User not logged in!!!");
      }

      // User Verifications
      var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
      // Save in the context of this request for spring user after
      SecurityContextHolder.getContext().setAuthentication(authentication);

    }
    filterChain.doFilter(request, response);

  }

  private String recoverToken(HttpServletRequest request) {
    var authHeader = request.getHeader("Authorization");
    if (authHeader == null)
      return null;
    return authHeader.replace("Bearer ", "");

  }
}
