package com.baskota.applicationnotification.model;

import lombok.Data;

@Data
public class NotificationRS {
    private String status;
    private String statusDescription;
    private Long notificationReferenceId;
}
