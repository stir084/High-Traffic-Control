self.addEventListener('push', function(event) { // 푸시 알림이 도착할 때 발생하는 push 이벤트를 리스닝하고, 해당 알림을 처리합니다.
    const data = event.data.json();

    const options = {
        body: data.body,
        icon: 'icon.png'
    };

    event.waitUntil( // 푸시 알림을 정상적으로 브라우저에 표시할 때까지 작업이 중단되지 않도록 보장하는 역할을 합니다.
        self.registration.showNotification(data.title, options) // Service Worker의 registration 객체에서 showNotification() 메서드를 호출하여 푸시 알림을 실제로 표시합니다.
    );
});