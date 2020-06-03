package com.suraj;

import com.auth0.jwt.JWT;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableFeignClients
@Slf4j
public class ServiceAApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServiceAApplication.class, args);

    val decodedJWT =
        JWT.decode(
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJjcmVhdGVkQXRcIjpcIjIwMjAtMDUtMzBUMTc6MDY6MDIuMDgwKzAwMDBcIixcInVzZXJBY2NvdW50SWRcIjpcIkFDQzgwNzI5MTU1ODYzOFwiLFwiaXNTdWNjZXNzXCI6dHJ1ZSxcInNlc3Npb25JZFwiOlwiOWQwMGYwZGQtN2VmMi00ZTE2LTk3OWEtYTVlMDU1MDQwNWE3XCIsXCJpcEFkZHJlc3NcIjpcIjEyMi4xNzEuNjUuMjA5LFwiLFwiZGV2aWNlSWRcIjpudWxsLFwic3VwZXJBY2NvdW50SWRcIjpcIkFDQzgwNzI5MTU1ODYzOFwifSIsIm5iZiI6MTU5MDg1ODMxMiwiaXNzIjoiZ3Jvd3diaWxsaW9ubWlsbGVubmlhbCIsImV4cCI6MTU5MzQ1MDM2MiwiaWF0IjoxNTkwODU4MzYyfQ.HfcUG-FbwPQ_eWCa5Lr6gFIfzYC8eShokZgJFVEIMq_DujLkjXma0ss_qLk4uvcOCWm6cen_yEZBi1ZTYOg4");
    log.info("Issuer {}", decodedJWT.getIssuer());
    log.info("Subject {}", decodedJWT.getSubject());
    log.info("Issued at {}", decodedJWT.getSignature());
    log.info("Payload {}", decodedJWT.getPayload());
    log.info("Issued At {}", decodedJWT.getIssuedAt());
    log.info("Not before {}", decodedJWT.getNotBefore());

  }
}
