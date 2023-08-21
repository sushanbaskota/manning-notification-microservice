package com.baskota.notificationgateway.handlers;

import org.springframework.stereotype.Component;

@Component
public class SMSHandler {

    public boolean sendSMS(String phoneNumber, String smsContent) {
        return true;
    }
}
