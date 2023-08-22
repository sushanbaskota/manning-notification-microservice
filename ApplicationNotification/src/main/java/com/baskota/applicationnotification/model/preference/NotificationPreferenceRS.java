package com.baskota.applicationnotification.model.preference;

import lombok.Data;

@Data
public class NotificationPreferenceRS {
    private String status;
    private String statusDescription;
    private boolean smsPreferenceFlag;
    private boolean emailPreferenceFlag;
    private String emailAddress;
    private String phoneNumber;
}
