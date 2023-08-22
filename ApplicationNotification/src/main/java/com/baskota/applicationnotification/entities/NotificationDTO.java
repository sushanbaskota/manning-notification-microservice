package com.baskota.applicationnotification.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "notification")
public class NotificationDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String customerId;
    private String notificationMode;
    private String notificationTemplateName;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<NotificationParameterDTO> notificationParameters;
}
