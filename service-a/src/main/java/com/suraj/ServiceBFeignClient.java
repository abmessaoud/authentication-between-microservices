package com.suraj;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/** created by @author suraj on 30/05/20 */
@FeignClient(name = "serviceBClient", url = "http://127.0.0.1:8081/service-b")
public interface ServiceBFeignClient {

  @GetMapping(value = "/get-greetings")
  @ApiOperation("Get Greetings from service B")
  String getGreetings(@RequestHeader("SERVICE-AUTH-TOKEN") String serviceAuthToken);
}
