package com.baskota.notificationgateway.handlers;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SMSHandler {
    @Value("${twilio.username}")
    private String twilioUsername;

    @Value("${twilio.password}")
    private String twilioPassword;

    @Value("${twilio.phone-number}")
    private String twilioPhoneNumber;


    public boolean sendSMS(String toPhoneNumber, String smsContent) {
        try {
            Twilio.init(twilioUsername, twilioPassword);

            Message message = Message.creator(
                    new PhoneNumber(toPhoneNumber),
                    new PhoneNumber(twilioPhoneNumber),
                    smsContent
            ).create();

            System.out.println("sms sent sid: " + message.getSid());
        } catch (Exception ex) {
            System.out.println("error while sending sms: " + ex);

            return false;
        }

        return true;
    }
}
