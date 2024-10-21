package com.example.trading_producer.trading_producer.resilience4j;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;


@Service
@RequiredArgsConstructor
public class RateLimiterService {

  private final RateLimiter rateLimiter;

  public String doSomething() {
    return "Hello, Resilience4j!";
  }

  public String callWithRateLimiter() {
    Supplier<String> restrictedSupplier = RateLimiter
      .decorateSupplier(rateLimiter, this::doSomething);

    try {
      // RateLimiter 적용된 메서드 호출
      return restrictedSupplier.get();
    } catch (RequestNotPermitted e) {
      // RateLimiter가 호출을 차단했을 때 예외 처리
      return "Request was not permitted";
    } catch (Exception e) {
      return "An error occurred";
    }
  }
}