package com.example.trading_producer.trading_producer.resilience4j;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RateLimitConfiguration {

  @Bean
  public RateLimiter createRateLimiter() {
    // RateLimiter 설정
    RateLimiterConfig config = RateLimiterConfig.custom()
      .timeoutDuration(Duration.ofMillis(0))
      .limitRefreshPeriod(Duration.ofSeconds(60))
      .limitForPeriod(5)
      .build();

    // RateLimiter 생성
    return RateLimiter.of("backendName", config);
  }

}
