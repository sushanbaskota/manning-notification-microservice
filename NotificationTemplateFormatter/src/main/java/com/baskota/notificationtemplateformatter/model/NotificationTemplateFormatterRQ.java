package com.baskota.notificationtemplateformatter.model;

import lombok.Data;

import java.util.List;

@Data
public class NotificationTemplateFormatterRQ {
    private String notificationTemplateName;
    private String notificationMode;

    private List<NotificationParameter> notificationParameters;
}
