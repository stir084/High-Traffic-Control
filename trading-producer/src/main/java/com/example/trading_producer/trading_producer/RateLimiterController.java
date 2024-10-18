package com.example.trading_producer.trading_producer;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class RateLimiterController {


  /**
   * runtimeOnly group: 'org.aspectj', name: 'aspectjweaver', version: '1.9.22.1' 이게 필요하다
   *
   * 근데 이건 https://github.com/resilience4j/resilience4j/issues/1825 이 이슈에 가깝다. 왜 aspectJ를 강조하는가?
   *
   * Spring Boot 3이라면 implementation 'io.github.resilience4j:resilience4j-spring-boot3:2.2.0' 이게 필요하다
   */
  @GetMapping("/test")
  @RateLimiter(name = "default")
  public String test() {
    return "Hello, World!";
  }
}