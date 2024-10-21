package com.example.trading_producer.trading_producer.resilience4j;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @GetMapping("/test-circuitbreaker")
  @CircuitBreaker(name = "default", fallbackMethod = "fallback")
  public String withCircuitBreaker() {
    // 5번 호출해서 실패율이 30% 이상이면 서킷 생성되고 20초 동안 대기 후 서킷이 끝나면 반만 서킷을 열어놓고 2번의 호출만 허용한다.
    double random = Math.random();
    System.out.println(random);
    if (random > 0.2) {
      throw new RuntimeException("Failed operation");
    }
    return "Hello, CircuitBreaker!";
  }

  // fallback 메서드는 서킷이 열렸을 때 호출됩니다.
  public String fallback(Throwable t) {
    return "Fallback: Circuit breaker opened!";
  }
}