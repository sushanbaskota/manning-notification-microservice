package com.baskota.notificationgateway.model;

import lombok.Data;

@Data
public class SendNotificationRQ {
    private String customerId;
    private String notificationMode;
    private String notificationContent;
    private String emailSubject;
    private String emailAddress;
    private String phoneNumber;
}
