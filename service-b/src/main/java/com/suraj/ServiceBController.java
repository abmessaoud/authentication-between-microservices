package com.suraj;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** created by @author suraj on 30/05/20 */
@RestController
@RequestMapping(value = "/service-b")
public class ServiceBController {

  @GetMapping(value = "/get-greetings")
  public ResponseEntity sayGreeting() {
    return ResponseEntity.ok("Hello There from Service B!!!");
  }
}
