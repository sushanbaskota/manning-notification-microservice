package com.baskota.notificationpreferencesservice.services;

import com.baskota.notificationpreferencesservice.entities.NotificationPreferenceDTO;
import com.baskota.notificationpreferencesservice.model.NotificationPreferenceRQ;
import com.baskota.notificationpreferencesservice.model.NotificationPreferenceRS;
import com.baskota.notificationpreferencesservice.model.Status;
import com.baskota.notificationpreferencesservice.repositories.NotificationPreferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationPreferenceService {
    private final NotificationPreferenceRepository notificationPreferenceRepository;

    public NotificationPreferenceRS getCustomerNotificationPreference(NotificationPreferenceRQ notificationPreferenceRQ) {
        Optional<NotificationPreferenceDTO> optNotificationPreferenceDTO =
                notificationPreferenceRepository.findByCustomerId(notificationPreferenceRQ.getCustomerId());

        if (optNotificationPreferenceDTO.isPresent()) {
            NotificationPreferenceDTO notificationPreferenceDTO = optNotificationPreferenceDTO.get();

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
