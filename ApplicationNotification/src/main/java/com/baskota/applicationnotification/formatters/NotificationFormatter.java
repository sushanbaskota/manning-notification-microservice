package com.baskota.applicationnotification.formatters;

import com.baskota.applicationnotification.entities.NotificationDTO;
import com.baskota.applicationnotification.entities.NotificationParameterDTO;
import com.baskota.applicationnotification.model.NotificationMode;
import com.baskota.applicationnotification.model.NotificationParameter;
import com.baskota.applicationnotification.model.NotificationRQ;
import com.baskota.applicationnotification.model.NotificationTemplateName;
import com.baskota.applicationnotification.model.gateway.SendNotificationRQ;
import com.baskota.applicationnotification.model.preference.NotificationPreferenceRQ;
import com.baskota.applicationnotification.model.preference.NotificationPreferenceRS;
import com.baskota.applicationnotification.model.templateformatter.NotificationTemplateFormatterRQ;
import com.baskota.applicationnotification.model.templateformatter.NotificationTemplateFormatterRS;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NotificationFormatter {

    public NotificationPreferenceRQ createNotificationPreferenceRQ(String customerId) {
        NotificationPreferenceRQ notificationPreferenceRQ = new NotificationPreferenceRQ();

        notificationPreferenceRQ.setCustomerId(customerId);

        return notificationPreferenceRQ;
    }

    public NotificationTemplateFormatterRQ createNotificationFormatterRQ(
            NotificationRQ notificationRQ,
            NotificationPreferenceRS notificationPreferenceRS
    ) {
        String notificationMode = getNotificationMode(notificationPreferenceRS);

        NotificationTemplateFormatterRQ notificationTemplateFormatterRQ = new NotificationTemplateFormatterRQ();

        notificationTemplateFormatterRQ.setNotificationMode(notificationMode);
        notificationTemplateFormatterRQ.setNotificationTemplateName(
                NotificationTemplateName.valueOf(notificationRQ.getNotificationTemplateName()).name()
        );
        notificationTemplateFormatterRQ.setNotificationParameters(notificationRQ.getNotificationParameters());

        return notificationTemplateFormatterRQ;
    }

    public SendNotificationRQ createSendNotificationRQ(
            String customerId,
            NotificationTemplateFormatterRS notificationTemplateRS,
            NotificationPreferenceRS notificationPreferenceRS
    ) {
        String notificationMode = getNotificationMode(notificationPreferenceRS);
        String notificationContent = getNotificationContent(notificationTemplateRS, notificationMode);

        SendNotificationRQ sendNotificationRQ = new SendNotificationRQ();

        sendNotificationRQ.setCustomerId(customerId);
        sendNotificationRQ.setNotificationMode(notificationMode);
        sendNotificationRQ.setNotificationContent(notificationContent);
        sendNotificationRQ.setEmailAddress(notificationPreferenceRS.getEmailAddress());
        sendNotificationRQ.setPhoneNumber(notificationPreferenceRS.getPhoneNumber());
        sendNotificationRQ.setEmailSubject(notificationTemplateRS.getEmailSubject());

        return sendNotificationRQ;
    }

    private String getNotificationMode(NotificationPreferenceRS notificationPreferenceRS) {
        return notificationPreferenceRS.isEmailPreferenceFlag()
                ? NotificationMode.EMAIL.name() : NotificationMode.SMS.name();
    }

    private static String getNotificationContent(
            NotificationTemplateFormatterRS notificationTemplateRS, String notificationMode
    ) {
        return NotificationMode.SMS.name().equalsIgnoreCase(notificationMode) ?
                notificationTemplateRS.getSmsContent() : notificationTemplateRS.getEmailContent();
    }

    public NotificationDTO createNotificationDTO(
            String customerId,
            NotificationRQ notificationRQ,
            NotificationPreferenceRS notificationPreferenceRS
    ) {
        NotificationDTO notificationDTO = new NotificationDTO();

        notificationDTO.setCustomerId(customerId);
        notificationDTO.setNotificationMode(getNotificationMode(notificationPreferenceRS));
        notificationDTO.setNotificationTemplateName(NotificationTemplateName.valueOf(notificationRQ.getNotificationTemplateName()).name());
        notificationDTO.setNotificationParameters(createNotificationParameterDTO(notificationRQ.getNotificationParameters()));

        return notificationDTO;
    }

    private List<NotificationParameterDTO> createNotificationParameterDTO(List<NotificationParameter> notificationParameters) {
        return notificationParameters.stream()
                .map(notificationParameter -> {
                    NotificationParameterDTO notificationParameterDTO = new NotificationParameterDTO();

                    notificationParameterDTO.setNotificationParameterName(notificationParameter.getNotificationParameterName());
                    notificationParameterDTO.setNotificationParameterValue(notificationParameter.getNotificationParameterValue());

                    return notificationParameterDTO;
                }).collect(Collectors.toList());
    }
}
