package com.baskota.notificationgateway.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailHandler {
    private final JavaMailSender javaMailSender;

    public boolean sendEmail(String emailAddress, String emailSubject, String emailContent) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(emailAddress);
            message.setSubject(emailSubject);
            message.setText(emailContent);

            javaMailSender.send(message);
        } catch (Exception ex) {
            System.out.println("error on sending message: " + ex);

            return false;
        }

        return true;
    }
}
