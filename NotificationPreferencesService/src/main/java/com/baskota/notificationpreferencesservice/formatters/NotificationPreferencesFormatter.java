package com.baskota.notificationpreferencesservice.formatters;

import com.baskota.notificationpreferencesservice.entities.NotificationPreferenceDTO;
import com.baskota.notificationpreferencesservice.model.NotificationPreferenceRS;
import com.baskota.notificationpreferencesservice.model.Status;
import org.springframework.stereotype.Component;

@Component
public class NotificationPreferencesFormatter {

    public NotificationPreferenceRS formatNotificationPreferenceResponse(
            NotificationPreferenceDTO notificationPreferenceDTO
    ) {
        if (notificationPreferenceDTO != null) {
            return createNotificationPreferenceRSSuccess(notificationPreferenceDTO);
        }

        return createNotificationPreferenceRSFailed();
    }

    private NotificationPreferenceRS createNotificationPreferenceRSSuccess(
            NotificationPreferenceDTO notificationPreferenceDTO
    ) {
        NotificationPreferenceRS notificationPreferenceRS = new NotificationPreferenceRS();

        notificationPreferenceRS.setStatus(Status.SUCCESS.name());
        notificationPreferenceRS.setEmailPreferenceFlag(notificationPreferenceDTO.isEmailPreferenceFlag());
        notificationPreferenceRS.setSmsPreferenceFlag(notificationPreferenceDTO.isSmsPreferenceFlag());
        notificationPreferenceRS.setPhoneNumber(notificationPreferenceDTO.getPhoneNumber());
        notificationPreferenceRS.setEmailAddress(notificationPreferenceDTO.getEmailAddress());
        notificationPreferenceRS.setStatusDescription("Notification Received Successfully!");

        return notificationPreferenceRS;
    }

    private NotificationPreferenceRS createNotificationPreferenceRSFailed() {
        NotificationPreferenceRS notificationPreferenceRS = new NotificationPreferenceRS();

        notificationPreferenceRS.setStatus(Status.FAILED.name());
        notificationPreferenceRS.setStatusDescription("Customer Not Found!");

        return notificationPreferenceRS;
    }
}
