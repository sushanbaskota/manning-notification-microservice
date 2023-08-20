package com.baskota.notificationpreferencesservice.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "notification_preferences")
public class NotificationPreferenceDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "customerId")
    private String customerId;

    @Column(name = "smsPreferenceFlag")
    private boolean smsPreferenceFlag;

    @Column(name = "emailPreferenceFlag")
    private boolean emailPreferenceFlag;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "emailAddress")
    private String emailAddress;
}
