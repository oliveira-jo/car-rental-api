package com.oliveira.carrentalapi.services.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.oliveira.carrentalapi.domain.models.User;

@Service
public class TokenServiceImpl {

  // Only the consul application have the secret
  // a plus param to pass for the algorithn
  @Value("${api.security.token.secret}")
  private String secret;

  /*
   * See if the user have the role necessary to do same request
   * the information was save in the token and the token
   * transists between the server and client
   */
  public String generateToken(User user) {
    try {
      // Algorithm to token generation
      // Pass for paramether a password secret, an unique information
      Algorithm algorithm = Algorithm.HMAC256(secret);

      String token = JWT.create()
          // how was the sender
          .withIssuer("car-rental-api")
          // how was the user that receive the token
          .withSubject(user.getLogin())
          // when the token was created
          .withIssuedAt(new Date())
          // time to expire
          .withExpiresAt(getExpirationDate())
          // do the final assign
          .sign(algorithm);

      return token;
    } catch (JWTCreationException exception) {
      throw new RuntimeException("Error while genereting token", exception);
    }
  }

  /*
   * Token is valid?
   */
  public String validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);

      return JWT.require(algorithm)
          .withIssuer("car-rental-api")
          .build()
          .verify(token)
          .getSubject();

    } catch (JWTVerificationException exception) {
      // String empy equals no user
      return "";

    }

  }

  /*
   * Two hours add with the instant in time zone specific
   */
  private Instant getExpirationDate() {
    return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
  }

}
