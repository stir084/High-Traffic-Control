package com.example.trading_consumer.trading_consumer.webpush;

import nl.martijndwars.webpush.Subscription;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebPushApiController {
  private List<Subscription> subscriptions = new ArrayList<>();
  @PostMapping("/subscribe")
  public ResponseEntity<String> subscribe(@RequestBody Subscription subscription) {
    // 받은 구독 정보를 리스트에 추가 (실제 환경에서는 DB에 저장하는 것이 좋습니다)
    subscriptions.add(subscription);
    System.out.println("구독 정보가 저장되었습니다: " + subscription);

    return ResponseEntity.ok("Subscription saved.");
  }

  @PostMapping("/sendNotification")
  public ResponseEntity<String> sendNotification() throws Exception {
    String message = "hello";
    PushNotificationService pushService = new PushNotificationService();

    for (Subscription subscription : subscriptions) {
      pushService.sendPushNotification(subscription, "알림 제목", message);
    }

    return ResponseEntity.ok("Push notifications sent.");
  }
}
