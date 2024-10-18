package com.example.trading_consumer.trading_consumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExternalController {

  @GetMapping("/api/sleep")
  public String sleep() throws InterruptedException {
    Thread.sleep(10000); // 10초 대기
    System.out.println("after 10 seconds");
    return "Processed after 10 seconds";
  }
}