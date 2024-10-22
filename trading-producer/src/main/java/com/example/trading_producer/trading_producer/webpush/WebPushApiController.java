package com.example.trading_producer.trading_producer.webpush;

import com.example.trading_producer.trading_producer.PushNotificationService;
import lombok.RequiredArgsConstructor;
import nl.martijndwars.webpush.Subscription;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
@RequiredArgsConstructor
public class WebPushApiController {

  private final WebPushService webPushService;
  @GetMapping("/index")
  public String home(Model model) {
    model.addAttribute("message", "Hello, Thymeleaf!");
    return "index";  // templates 폴더 안에 있는 index.html을 찾음
  }

  // 이곳에 구독 정보를 저장할 저장소나 DB가 필요합니다.
/*  private List<Subscription> subscriptions = new ArrayList<>();

  @PostMapping("/subscribe")
  public ResponseEntity<String> subscribe(@RequestBody Subscription subscription) {
    // 받은 구독 정보를 리스트에 추가 (실제 환경에서는 DB에 저장하는 것이 좋습니다)
    subscriptions.add(subscription);
    System.out.println("구독 정보가 저장되었습니다: " + subscription);

    return ResponseEntity.ok("Subscription saved.");
  }*/

  @PostMapping("/subscribe")
  public ResponseEntity<String> subscribe(@RequestBody Subscription subscription) {
    // 받은 구독 정보를 리스트에 추가 (실제 환경에서는 DB에 저장하는 것이 좋습니다)
    webPushService.callExternalApi(subscription);
    subscriptions.add(subscription);
    System.out.println("구독 정보가 저장되었습니다: " + subscription);

    return ResponseEntity.ok("Subscription saved.");
  }



  @PostMapping("/sendNotification")
  public ResponseEntity<String> sendNotification(/*@RequestBody String message*/) throws Exception {
    String message = "hello";
    PushNotificationService pushService = new PushNotificationService();

    for (Subscription subscription : subscriptions) {
      pushService.sendPushNotification(subscription, "알림 제목", message);
    }

    return ResponseEntity.ok("Push notifications sent.");
  }



}
