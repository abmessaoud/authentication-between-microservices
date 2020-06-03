package com.suraj;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** created by @author suraj on 30/05/20 */
@RestController
@RequestMapping(value = "/service-a")
@Slf4j
public class ServiceAController {

  @Autowired private ServiceAuthConfig serviceAuthConfig;

  @Autowired private ServiceBFeignClient serviceBFeignClient;

  @GetMapping(value = "/get-greeting-from-service-b")
  public ResponseEntity getGreetingFromServiceB() {
    try {
      String serviceBGreetingResponse =
          serviceBFeignClient.getGreetings(serviceAuthConfig.getServiceBAuthToken()); // passing jwt auth token for service B
      return ResponseEntity.ok(serviceBGreetingResponse);
    } catch (FeignException exception) {
      /*
        Extract the error thrown from service B
        and if the error is `INVALID_TOKEN`, then return 401
       */
      String error = new String(exception.responseBody().get().array());
      log.error("Error {}", error);
      if ("INVALID_TOKEN".equals(error)) {
        return ResponseEntity.status(401).body(error);
      }
      throw exception;
    }
  }
}
