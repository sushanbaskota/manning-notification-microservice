package com.baskota.applicationnotification.model.templateformatter;

import lombok.Data;

@Data
public class NotificationTemplateFormatterRS {
    private String status;
    private String statusDescription;
    private String emailContent;
    private String smsContent;
    private String emailSubject;
}
