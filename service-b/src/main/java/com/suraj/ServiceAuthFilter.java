package com.suraj;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** created by @author suraj on 30/05/20 */

/*
  Filter to extract the incoming SERVICE-AUTH-TOKEN and
  validate it before passing the control to any api method
*/
@Component
@Slf4j
public class ServiceAuthFilter implements Filter {

  @Autowired private ServiceAuthConfig serviceAuthConfig;

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {

    HttpServletRequest httpServletRequest = ((HttpServletRequest) servletRequest);
    String serviceAuthToken = httpServletRequest.getHeader("SERVICE-AUTH-TOKEN");
    boolean isTokenValid = validateJwtToken(serviceAuthToken);
    if (!isTokenValid) {
      HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
      httpServletResponse.setStatus(401);
      httpServletResponse.getWriter().write("INVALID_TOKEN");
    } else {
      filterChain.doFilter(servletRequest, servletResponse);
    }
  }

  /*
   Validate the token by comparing the secret key in it against list of secret keys
   registered in service B
  */
  private boolean validateJwtToken(String jwtToken) {
    if (StringUtils.isEmpty(jwtToken)) {
      return false;
    }
    DecodedJWT decodedJWT =
        JWT.require(Algorithm.HMAC512(serviceAuthConfig.getJwtAlgorithmKey()))
            .build()
            .verify(jwtToken);
    log.info("Request Initiated from {}", decodedJWT.getIssuer());
    return serviceAuthConfig.getRegisteredSecretKeys().contains(decodedJWT.getSubject());
  }
}
