package com.baskota.applicationnotification.services;

import com.baskota.applicationnotification.entities.NotificationDTO;
import com.baskota.applicationnotification.formatters.NotificationFormatter;
import com.baskota.applicationnotification.integration.NotificationGatewayServiceIntegrator;
import com.baskota.applicationnotification.integration.NotificationPreferenceServiceIntegrator;
import com.baskota.applicationnotification.integration.NotificationTemplateFormatterServiceIntegrator;
import com.baskota.applicationnotification.model.NotificationRQ;
import com.baskota.applicationnotification.model.NotificationRS;
import com.baskota.applicationnotification.model.Status;
import com.baskota.applicationnotification.model.gateway.SendNotificationRQ;
import com.baskota.applicationnotification.model.gateway.SendNotificationRS;
import com.baskota.applicationnotification.model.preference.NotificationPreferenceRQ;
import com.baskota.applicationnotification.model.preference.NotificationPreferenceRS;
import com.baskota.applicationnotification.model.templateformatter.NotificationTemplateFormatterRQ;
import com.baskota.applicationnotification.model.templateformatter.NotificationTemplateFormatterRS;
import com.baskota.applicationnotification.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationFormatter notificationFormatter;
    private final NotificationGatewayServiceIntegrator notificationGatewayServiceIntegrator;
    private final NotificationPreferenceServiceIntegrator notificationPreferenceServiceIntegrator;
    private final NotificationTemplateFormatterServiceIntegrator notificationTemplateFormatterServiceIntegrator;

    public NotificationRS getNotification(NotificationRQ notificationRQ) {
        String customerId = notificationRQ.getCustomerId();

        if (StringUtils.hasText(customerId)) {
            // 1. get notification preference by customer id
            NotificationPreferenceRQ notificationPreferenceRQ =
                    notificationFormatter.createNotificationPreferenceRQ(customerId);

            NotificationPreferenceRS notificationPreferenceRS =
                    notificationPreferenceServiceIntegrator.getNotificationPreference(notificationPreferenceRQ);

            // 2. format notification
            if (notificationPreferenceRS != null &&
                    Status.SUCCESS.name().equalsIgnoreCase(notificationPreferenceRS.getStatus())
            ) {
                NotificationTemplateFormatterRQ notificationTemplateFormatterRQ =
                        notificationFormatter.createNotificationFormatterRQ(notificationRQ, notificationPreferenceRS);

                NotificationTemplateFormatterRS notificationTemplateRS =
                        notificationTemplateFormatterServiceIntegrator.getNotificationTemplate(notificationTemplateFormatterRQ);

                // 3. send notification
                if (notificationTemplateRS != null &&
                        Status.SUCCESS.name().equalsIgnoreCase(notificationTemplateRS.getStatus())
                ) {
                    SendNotificationRQ sendNotificationRQ =
                            notificationFormatter.createSendNotificationRQ(customerId, notificationTemplateRS, notificationPreferenceRS);

                    SendNotificationRS sendNotificationRS = notificationGatewayServiceIntegrator.sendNotification(sendNotificationRQ);

                    // 4. persist on DB
                    if (sendNotificationRS != null && Status.SUCCESS.name().equalsIgnoreCase(sendNotificationRS.getStatus())) {
                        NotificationDTO notificationDTO =
                                notificationFormatter.createNotificationDTO(customerId, notificationRQ, notificationPreferenceRS);

                        NotificationDTO savedDTO = notificationRepository.save(notificationDTO);


                        return createNotificationSuccessRS(savedDTO.getId());
                    }
                }
            }
        }

        return createNotificationFailureRS();
    }

    private NotificationRS createNotificationSuccessRS(Long id) {
        NotificationRS notificationRS = new NotificationRS();

        notificationRS.setNotificationReferenceId(id);
        notificationRS.setStatus(Status.SUCCESS.name());
        notificationRS.setStatusDescription("Notification Sent Successfully!");

        return notificationRS;
    }

    private NotificationRS createNotificationFailureRS() {
        NotificationRS notificationRS = new NotificationRS();

        notificationRS.setNotificationReferenceId(null);
        notificationRS.setStatus(Status.FAILED.name());
        notificationRS.setStatusDescription("Notification Failed to Sent!");

        return notificationRS;
    }
}
