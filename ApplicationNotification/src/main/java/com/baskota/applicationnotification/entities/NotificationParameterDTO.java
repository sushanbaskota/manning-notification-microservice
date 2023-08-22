package com.baskota.applicationnotification.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "notification_parameters")
public class NotificationParameterDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String notificationParameterName;
    private String notificationParameterValue;
}
