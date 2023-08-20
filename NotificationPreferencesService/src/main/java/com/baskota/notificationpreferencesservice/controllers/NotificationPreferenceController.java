package com.baskota.notificationpreferencesservice.controllers;

import com.baskota.notificationpreferencesservice.model.NotificationPreferenceRQ;
import com.baskota.notificationpreferencesservice.model.NotificationPreferenceRS;
import com.baskota.notificationpreferencesservice.services.NotificationPreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationPreferenceController {
    private final NotificationPreferenceService notificationPreferenceService;

    @PostMapping("/preference")
    public NotificationPreferenceRS getCustomerNotificationPreference(
            @RequestBody NotificationPreferenceRQ notificationPreferenceRQ
    ) {
        return notificationPreferenceService.getCustomerNotificationPreference(notificationPreferenceRQ);
    }
}
