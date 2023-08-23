package com.baskota.applicationnotification.integration;

import com.baskota.applicationnotification.integration.http.NotificationRestClient;
import com.baskota.applicationnotification.model.gateway.SendNotificationRQ;
import com.baskota.applicationnotification.model.gateway.SendNotificationRS;
import com.baskota.applicationnotification.util.JsonConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationGatewayServiceIntegrator {
    private final JsonConverter jsonMap;
    private final NotificationRestClient notificationRestClient;

    public SendNotificationRS sendNotification(SendNotificationRQ sendNotificationRQ) {
        String requestPayload = jsonMap.toJson(sendNotificationRQ);

        String sendNotificationRS = notificationRestClient
                .execute(HttpMethod.POST, "http://localhost:8083/api/notifications/send", requestPayload);

        return (SendNotificationRS) jsonMap.fromJson(sendNotificationRS);
    }
}
