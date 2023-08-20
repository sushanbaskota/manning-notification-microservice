package com.baskota.notificationtemplateformatter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class ThymeleafConfig {

    @Bean("customTemplateEngine")
    public TemplateEngine templateEngine() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        templateResolver.setPrefix("templates/email/"); // Thymeleaf templates directory
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");

        TemplateEngine templateEngine = new TemplateEngine();

        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine;
    }
}
