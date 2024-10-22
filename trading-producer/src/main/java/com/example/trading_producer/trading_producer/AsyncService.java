package com.example.trading_producer.trading_producer;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

  private final WebClient webClient;

  public AsyncService(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl("http://localhost:9091").build(); // 외부 API URL
  }

  public CompletableFuture<String> callExternalApi() {
    return webClient.get()
      .uri("/api/sleep")
      .retrieve()
      .bodyToMono(String.class)
      .toFuture();
  }


}