package com.baskota.applicationnotification.model.templateformatter;

import com.baskota.applicationnotification.model.NotificationParameter;
import lombok.Data;

import java.util.List;

@Data
public class NotificationTemplateFormatterRQ {
    private String notificationTemplateName;
    private String notificationMode;

    private List<NotificationParameter> notificationParameters;
}
