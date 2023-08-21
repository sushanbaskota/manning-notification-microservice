package com.baskota.notificationgateway.controllers;

import com.baskota.notificationgateway.model.SendNotificationRQ;
import com.baskota.notificationgateway.model.SendNotificationRS;
import com.baskota.notificationgateway.service.NotificationGatewayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationGatewayController {
    private final NotificationGatewayService notificationGatewayService;

    @PostMapping("/send")
    public SendNotificationRS sendNotificationMessage(@RequestBody SendNotificationRQ sendNotificationRQ) {
        return notificationGatewayService.sendNotification(sendNotificationRQ);
    }
}
