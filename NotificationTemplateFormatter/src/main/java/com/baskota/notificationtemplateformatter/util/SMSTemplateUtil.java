package com.baskota.notificationtemplateformatter.util;

public final class SMSTemplateUtil {
    private SMSTemplateUtil() {
    }

    public static String getBalanceSMSTemplate() {
        return "Hello ${name}\n"
                .concat("Welcome to the Citizen Bank\n")
                .concat("Your Balance is ${balance}\n")
                .concat("Thanks!");
    }

    public static String getPhoneNumberChanged() {
        return "Hello ${name}\n"
                .concat("Welcome to the Citizen Bank\n")
                .concat("Your Phone number is changed from ${oldPhoneNumber} to ${newPhoneNumber}\n")
                .concat("Thanks!");
    }
}
