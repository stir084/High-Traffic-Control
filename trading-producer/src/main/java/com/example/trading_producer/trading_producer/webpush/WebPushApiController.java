package com.example.trading_producer.trading_producer.webpush;

import lombok.RequiredArgsConstructor;
import nl.martijndwars.webpush.Subscription;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class WebPushApiController {

  private final WebPushService webPushService;
  @GetMapping("/index")
  public String home(Model model) {
    return "index";
  }

  @PostMapping("/subscribe")
  public ResponseEntity<String> subscribe(@RequestBody Subscription subscription) {

    webPushService.sendToConsumerServer(subscription);

    return ResponseEntity.ok("Subscription saved.");
  }
}
