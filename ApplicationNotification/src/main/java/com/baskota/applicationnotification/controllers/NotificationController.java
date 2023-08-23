package com.baskota.applicationnotification.controllers;

import com.baskota.applicationnotification.model.NotificationRQ;
import com.baskota.applicationnotification.model.NotificationRS;
import com.baskota.applicationnotification.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/notifications")
    public NotificationRS getNotification(@RequestBody NotificationRQ notificationRQ) {
        return notificationService.getNotification(notificationRQ);
    }
}
