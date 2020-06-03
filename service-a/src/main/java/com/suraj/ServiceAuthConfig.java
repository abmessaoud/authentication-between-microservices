package com.suraj;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/** created by @author suraj on 30/05/20 */
@Configuration
@ConfigurationProperties(prefix = "service")
public class ServiceAuthConfig {

  @Setter @Getter private String jwtAlgorithmKey;

  @Setter @Getter private String serviceBSecretKey;

  @Value("${spring.application.name}")
  private String applicationName;

  @Getter private String serviceBAuthToken;

  /*
    Generate auth token which will be passed in header when calling api of service B
   */
  @PostConstruct
  public void load() {
    serviceBAuthToken = createJwtToken(serviceBSecretKey);
  }

  /*
   Create JWT Token with the algorithm key (common for both the called and caller service)
   Service Secret key is to be generate by caller service and then shared
   and stored in called service yml
  */
  private String createJwtToken(String serviceSecretKey) {
    return JWT.create()
        .withIssuer(applicationName)
        .withSubject(serviceSecretKey)
        .sign(Algorithm.HMAC512(jwtAlgorithmKey));
  }
}
