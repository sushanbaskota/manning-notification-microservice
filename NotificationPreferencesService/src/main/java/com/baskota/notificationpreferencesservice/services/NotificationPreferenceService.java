package com.baskota.notificationpreferencesservice.services;

import com.baskota.notificationpreferencesservice.entities.NotificationPreferenceDTO;
import com.baskota.notificationpreferencesservice.formatters.NotificationPreferencesFormatter;
import com.baskota.notificationpreferencesservice.model.NotificationPreferenceRQ;
import com.baskota.notificationpreferencesservice.model.NotificationPreferenceRS;
import com.baskota.notificationpreferencesservice.repositories.NotificationPreferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationPreferenceService {
    private final NotificationPreferencesFormatter notificationPreferencesFormatter;
    private final NotificationPreferenceRepository notificationPreferenceRepository;

    public NotificationPreferenceRS getCustomerNotificationPreference(NotificationPreferenceRQ notificationPreferenceRQ) {
        Optional<NotificationPreferenceDTO> optNotificationPreferenceDTO =
                notificationPreferenceRepository.findByCustomerId(notificationPreferenceRQ.getCustomerId());

        return notificationPreferencesFormatter.formatNotificationPreferenceResponse(optNotificationPreferenceDTO.orElse(null));
    }
}
