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
    private final JsonConverter jsonConverter;
    private final NotificationRestClient notificationRestClient;

    public NotificationPreferenceRS getNotificationPreference(NotificationPreferenceRQ notificationPreferenceRQ) {
        String requestPayload = jsonConverter.toJson(notificationPreferenceRQ);

        String notificationPreferenceRS = notificationRestClient
                .execute(HttpMethod.POST, "http://notification-preference/notification/preference", requestPayload);

        return jsonConverter.toObject(notificationPreferenceRS, NotificationPreferenceRS.class);
    }
}
