package com.baskota.applicationnotification.integration;

import com.baskota.applicationnotification.integration.http.NotificationRestClient;
import com.baskota.applicationnotification.model.templateformatter.NotificationTemplateFormatterRQ;
import com.baskota.applicationnotification.model.templateformatter.NotificationTemplateFormatterRS;
import com.baskota.applicationnotification.util.JsonConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationTemplateFormatterServiceIntegrator {
    private final JsonConverter jsonMap;
    private final NotificationRestClient notificationRestClient;

    public NotificationTemplateFormatterRS getNotificationTemplate(NotificationTemplateFormatterRQ notificationTemplateFormatterRQ) {
        String requestPayload = jsonMap.toJson(notificationTemplateFormatterRQ);

        String notificationTemplateRS = notificationRestClient
                .execute(HttpMethod.POST, "http://localhost:8082/api/notifications/templates", requestPayload);

        return (NotificationTemplateFormatterRS) jsonMap.fromJson(notificationTemplateRS);
    }
}
