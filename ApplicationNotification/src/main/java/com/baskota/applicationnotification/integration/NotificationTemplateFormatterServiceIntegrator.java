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
    private final JsonConverter jsonConverter;
    private final NotificationRestClient notificationRestClient;

    public NotificationTemplateFormatterRS getNotificationTemplate(NotificationTemplateFormatterRQ notificationTemplateFormatterRQ) {
        String requestPayload = jsonConverter.toJson(notificationTemplateFormatterRQ);

        String notificationTemplateRS = notificationRestClient
                .execute(HttpMethod.POST, "http://notification-template-formatter/api/notifications/templates", requestPayload);

        return jsonConverter.toObject(notificationTemplateRS, NotificationTemplateFormatterRS.class);
    }
}
