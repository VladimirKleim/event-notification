package com.project.event_notification.notification.db;


import jakarta.persistence.*;

@Entity
@Table(name = "notify")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
