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

  @GetMapping("/api/call-external")
  public ResponseEntity<String> callExternalApi() {
    asyncService.callExternalApi();
    return ResponseEntity.ok("Request sent to external API, not waiting for response.");
  }
}