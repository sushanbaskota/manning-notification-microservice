package com.baskota.applicationnotification.integration;

import com.baskota.applicationnotification.integration.http.NotificationRestClient;
import com.baskota.applicationnotification.model.preference.NotificationPreferenceRQ;
import com.baskota.applicationnotification.model.preference.NotificationPreferenceRS;
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
        String requestPayload = jsonMap.toJson(notificationPreferenceRQ);

        String notificationPreferenceRS = notificationRestClient
                .execute(HttpMethod.POST, "http://localhost:8081/notification/preference", requestPayload);

        return (NotificationPreferenceRS) jsonMap.fromJson(notificationPreferenceRS);
    }
}
