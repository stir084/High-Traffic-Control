package com.example.trading_producer.trading_producer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AsyncController {

  private final AsyncService asyncService;

  public AsyncController(AsyncService asyncService) {
    this.asyncService = asyncService;
  }

  /**
   * Spring MVC로 Webflux와 같이 비동기처럼 구현하기
   */
  @GetMapping("/api/call-external")
  public ResponseEntity<String> callExternalApi() {
    asyncService.callExternalApi();
    return ResponseEntity.ok("Request sent to external API, not waiting for response.");
  }
}