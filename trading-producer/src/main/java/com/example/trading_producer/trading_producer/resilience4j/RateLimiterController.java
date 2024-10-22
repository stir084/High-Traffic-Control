package com.example.trading_producer.trading_producer.resilience4j;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class RateLimiterController {

  private final RateLimiterService rateLimiterService;
  /**
   * Resilience4J으로 TPS 관리
   * 1. Rate Limiter로 요청 횟수 제한하기
   * 어노테이션을 사용하려면 아래 둘중 하나를 사용한다.
   * runtimeOnly group: 'org.aspectj', name: 'aspectjweaver', version: '1.9.22.1'
   * implementation 'org.springframework.boot:spring-boot-starter-aop:'
   * Spring Boot 3이라면 implementation 'io.github.resilience4j:resilience4j-spring-boot3:2.2.0'
   */
  @GetMapping("/test")
  @RateLimiter(name = "default")
  public String withAnnotation() {
    return "Hello, World!";
  }

  @GetMapping("/test2")
  public String withoutAnnotation() {
    System.out.println(rateLimiterService.callWithRateLimiter());
    return "Hello, World!";
  }
}