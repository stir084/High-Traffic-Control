package com.example.trading_producer.trading_producer.webpush;

import nl.martijndwars.webpush.Subscription;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class WebPushService {
  private final WebClient webClient;

  public WebPushService(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl("http://localhost:9091").build(); // 외부 API URL
  }

  public String sendToConsumerServer(Subscription subscription) {
    return webClient.post()
      .uri("/subscribe")
      .contentType(MediaType.APPLICATION_JSON)
      .body(Mono.just(subscription), Subscription.class)
      .retrieve()
      .bodyToMono(String.class)
      .block();
  }
}
