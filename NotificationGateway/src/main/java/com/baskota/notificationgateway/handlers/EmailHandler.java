package com.baskota.notificationgateway.handlers;

import org.springframework.stereotype.Component;

@Component
public class EmailHandler {

    public boolean sendEmail(String emailAddress, String emailSubject, String emailContent) {
        return true;
    }
}
