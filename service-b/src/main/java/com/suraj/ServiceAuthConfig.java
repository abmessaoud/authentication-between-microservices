package com.suraj;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/** created by @author suraj on 30/05/20 */
@Configuration
@ConfigurationProperties(prefix = "service")
@Slf4j
@Setter
@Getter
public class ServiceAuthConfig {

  private String jwtAlgorithmKey;

  private List<String> registeredSecretKeys;
}
