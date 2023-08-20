package com.baskota.notificationtemplateformatter.controllers;

import com.baskota.notificationtemplateformatter.model.NotificationTemplateFormatterRQ;
import com.baskota.notificationtemplateformatter.model.NotificationTemplateFormatterRS;
import com.baskota.notificationtemplateformatter.services.NotificationTemplateFormatterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationTemplateFormatterController {
    private final NotificationTemplateFormatterService notificationTemplateFormatterService;

    @PostMapping("/templates")
    public NotificationTemplateFormatterRS getNotificationTemplates(
            @RequestBody NotificationTemplateFormatterRQ notificationTemplateFormatterRQ
    ) {
        return notificationTemplateFormatterService.getNotificationTemplates(notificationTemplateFormatterRQ);
    }
}
