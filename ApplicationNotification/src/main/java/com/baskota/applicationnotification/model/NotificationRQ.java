package com.baskota.applicationnotification.model;

import lombok.Data;

import java.util.List;

@Data
public class NotificationRQ {
    private String customerId;
    private String notificationTemplateName;
    private List<NotificationParameter> notificationParameters;
}
