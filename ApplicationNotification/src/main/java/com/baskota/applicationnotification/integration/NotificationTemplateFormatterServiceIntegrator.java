package com.baskota.applicationnotification.integration;

import com.baskota.applicationnotification.integration.http.NotificationRestClient;
import com.baskota.applicationnotification.model.templateformatter.NotificationTemplateFormatterRQ;
import com.baskota.applicationnotification.model.templateformatter.NotificationTemplateFormatterRS;
import com.baskota.applicationnotification.util.GenericConverter;
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
        GenericConverter<NotificationTemplateFormatterRQ> requestConverter = new GenericConverter<>(NotificationTemplateFormatterRQ.class);

        String requestPayload = jsonMap.toJson(requestConverter.toMap(notificationTemplateFormatterRQ));

        String notificationTemplateRS = notificationRestClient
                .execute(HttpMethod.POST, "http://localhost:8082/api/notifications/templates", requestPayload);

        GenericConverter<NotificationTemplateFormatterRS> responseConverter = new GenericConverter<>(NotificationTemplateFormatterRS.class);

        return responseConverter.fromMap(jsonMap.fromJson(notificationTemplateRS));
    }
}
