package com.baskota.notificationgateway.service;

import com.baskota.notificationgateway.handlers.EmailHandler;
import com.baskota.notificationgateway.handlers.SMSHandler;
import com.baskota.notificationgateway.model.NotificationMode;
import com.baskota.notificationgateway.model.SendNotificationRQ;
import com.baskota.notificationgateway.model.SendNotificationRS;
import com.baskota.notificationgateway.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationGatewayService {
    private final EmailHandler emailHandler;
    private final SMSHandler smsHandler;

    public SendNotificationRS sendNotification(SendNotificationRQ sendNotificationRQ) {
        String notificationMode = sendNotificationRQ.getNotificationMode();
        String notificationContent = sendNotificationRQ.getNotificationContent();

        if (NotificationMode.SMS.name().equalsIgnoreCase(notificationMode)) {
            boolean smsSentSuccess = smsHandler.sendSMS(sendNotificationRQ.getPhoneNumber(), notificationContent);

            if (smsSentSuccess) {
                return sendNotification(Status.SUCCESS);
            }
        } else if (NotificationMode.EMAIL.name().equalsIgnoreCase(notificationMode)) {
            boolean sendEmailSuccess = emailHandler.sendEmail(
                    sendNotificationRQ.getEmailAddress(), sendNotificationRQ.getEmailSubject(), notificationContent
            );

            if (sendEmailSuccess) {
                return sendNotification(Status.SUCCESS);
            }
        }

        return sendNotification(Status.FAILED);
    }

    private SendNotificationRS sendNotification(Status status) {
        SendNotificationRS sendNotificationRS = new SendNotificationRS();

        if (Status.SUCCESS == status) {
            sendNotificationRS.setStatus(Status.SUCCESS.name());
            sendNotificationRS.setStatusDescription("Notification Send Successfully!");

            return sendNotificationRS;
        }

        sendNotificationRS.setStatus(Status.FAILED.name());
        sendNotificationRS.setStatusDescription("Failed to send notification!");

        return sendNotificationRS;
    }
}
