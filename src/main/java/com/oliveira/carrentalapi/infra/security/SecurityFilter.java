package com.oliveira.carrentalapi.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.oliveira.carrentalapi.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  TokenService tokenService;

  @Autowired
  UserRepository userRepository;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    var token = this.recoverToken(request);
    if (token != null) {
      var login = tokenService.validateToken(token);
      UserDetails user = userRepository.findByLogin(login);

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
