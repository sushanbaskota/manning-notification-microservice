package com.baskota.notificationtemplateformatter.services;

import com.baskota.notificationtemplateformatter.model.NotificationParameter;
import com.baskota.notificationtemplateformatter.model.NotificationTemplateFormatterRQ;
import com.baskota.notificationtemplateformatter.model.NotificationTemplateFormatterRS;
import com.baskota.notificationtemplateformatter.model.NotificationTemplateName;
import com.baskota.notificationtemplateformatter.util.SMSTemplateUtil;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.baskota.notificationtemplateformatter.model.NotificationMode.EMAIL;
import static com.baskota.notificationtemplateformatter.model.NotificationMode.SMS;
import static com.baskota.notificationtemplateformatter.model.Status.FAILED;
import static com.baskota.notificationtemplateformatter.model.Status.SUCCESS;

@Service
public class NotificationTemplateFormatterService {

    private final TemplateEngine templateEngine;

    @Autowired
    public NotificationTemplateFormatterService(@Qualifier("customTemplateEngine") TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public NotificationTemplateFormatterRS getNotificationTemplates(NotificationTemplateFormatterRQ notificationTemplateFormatterRQ) {
        Map<String, Object> notificationParameterMap = getNotificationParameterMap(notificationTemplateFormatterRQ);

        String notificationTemplateName = notificationTemplateFormatterRQ.getNotificationTemplateName();
        String notificationMode = notificationTemplateFormatterRQ.getNotificationMode();

        NotificationTemplateFormatterRS notificationTemplateFormatterRS = new NotificationTemplateFormatterRS();

        notificationTemplateFormatterRS.setStatus(SUCCESS.name());
        notificationTemplateFormatterRS.setStatusDescription("Successfully merged the template with the template parameters");

        if (EMAIL.name().equalsIgnoreCase(notificationMode)) {
            String emailContent = getEmailContent(notificationParameterMap, notificationTemplateName);

            notificationTemplateFormatterRS.setEmailContent(emailContent);
            notificationTemplateFormatterRS.setEmailSubject(notificationTemplateName);
        } else if (SMS.name().equalsIgnoreCase(notificationMode)) {
            String smsContent = getSmsContent(notificationTemplateName, notificationParameterMap);

            notificationTemplateFormatterRS.setSmsContent(smsContent);
        } else {
            notificationTemplateFormatterRS.setStatus(FAILED.name());
            notificationTemplateFormatterRS.setStatusDescription("Failed to merge the template!");
        }

        return notificationTemplateFormatterRS;
    }

    private Map<String, Object> getNotificationParameterMap(NotificationTemplateFormatterRQ notificationTemplateFormatterRQ) {
        List<NotificationParameter> notificationParameterList =
                notificationTemplateFormatterRQ.getNotificationParameters().isEmpty() ?
                        new ArrayList<>() :
                        notificationTemplateFormatterRQ.getNotificationParameters();

        return notificationParameterList
                .stream()
                .collect(Collectors.toMap(
                        NotificationParameter::getNotificationParameterName,
                        NotificationParameter::getNotificationParameterValue
                ));
    }

    private String getEmailContent(Map<String, Object> notificationParameterMap, String notificationTemplateName) {
        var thymeLeafContext = new Context();

        thymeLeafContext.setVariables(notificationParameterMap);

        return templateEngine.process(notificationTemplateName + ".html", thymeLeafContext);
    }

    private String getSmsContent(String notificationTemplateName, Map<String, Object> notificationParameterMap) {
        String smsTemplate = null;

        if (NotificationTemplateName.ViewBalance.name().equalsIgnoreCase(notificationTemplateName)) {
            smsTemplate = SMSTemplateUtil.getBalanceSMSTemplate();
        } else if (NotificationTemplateName.PhoneNumberChanged.name().equalsIgnoreCase(notificationTemplateName)) {
            smsTemplate = SMSTemplateUtil.getPhoneNumberChanged();
        }

        var stringSubstitutor = new StringSubstitutor(notificationParameterMap);

        return stringSubstitutor.replace(smsTemplate);
    }
}
