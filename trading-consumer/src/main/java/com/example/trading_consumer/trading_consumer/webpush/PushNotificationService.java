package com.example.trading_consumer.trading_consumer.webpush;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;
import org.apache.http.HttpResponse;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

public class PushNotificationService {
  private final PushService pushService;

  static {
    // BouncyCastle 암호화 방법 등록
    if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
      Security.addProvider(new BouncyCastleProvider());
    }
  }
  public PushNotificationService() throws Exception {
    pushService = new PushService();
    // VAPID 키 등록
    pushService.setPublicKey("BCz1BNLNccwKDd9XwGJUnNNcKoluFigzD_5xRlehWtGinDRoESwgR63bHrhHvEcZydUj4qPWDk7YcDhmvisNmrM");
    pushService.setPrivateKey("3p2xHXA51O4LJVW7Og3svbiJDGEg5orhfM9vX8LYUX0");
  }

  public void sendPushNotification(Subscription subscription, String title, String body) throws Exception {
    String payload = "{\"title\": \"" + title + "\", \"body\": \"" + body + "\"}";

    Notification notification = new Notification(subscription, payload);
    HttpResponse response = pushService.send(notification);

    System.out.println("Push notification sent with status: " + response.getStatusLine());
  }
}
