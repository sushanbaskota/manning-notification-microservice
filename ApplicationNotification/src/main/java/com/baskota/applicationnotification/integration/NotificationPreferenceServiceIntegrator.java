package com.baskota.applicationnotification.integration;

import com.baskota.applicationnotification.integration.http.NotificationRestClient;
import com.baskota.applicationnotification.model.preference.NotificationPreferenceRQ;
import com.baskota.applicationnotification.model.preference.NotificationPreferenceRS;
import com.baskota.applicationnotification.util.GenericConverter;
import com.baskota.applicationnotification.util.JsonConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationPreferenceServiceIntegrator {
    private final JsonConverter jsonMap;
    private final NotificationRestClient notificationRestClient;

    public NotificationPreferenceRS getNotificationPreference(NotificationPreferenceRQ notificationPreferenceRQ) {
        GenericConverter<NotificationPreferenceRQ> requestConverter = new GenericConverter<>(NotificationPreferenceRQ.class);

        String requestPayload = jsonMap.toJson(requestConverter.toMap(notificationPreferenceRQ));

        String notificationPreferenceRS = notificationRestClient
                .execute(HttpMethod.POST, "http://localhost:8084/notification/preference", requestPayload);

        GenericConverter<NotificationPreferenceRS> responseConverter = new GenericConverter<>(NotificationPreferenceRS.class);

        return responseConverter.fromMap(jsonMap.fromJson(notificationPreferenceRS));
    }
}
