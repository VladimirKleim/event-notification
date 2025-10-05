package com.project.event_notification.notification.domain;

import com.project.event_notification.EventNotificationApplication;
import com.project.event_notification.notification.db.NotificationEntity;
import com.project.event_notification.notification.db.NotificationRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final Logger log = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationRepository notificationRepository;

    private final NotificationConverter notificationConverter;

    public NotificationService(NotificationRepository notificationRepository, NotificationConverter notificationConverter) {
        this.notificationRepository = notificationRepository;
        this.notificationConverter = notificationConverter;
    }

    public void saveNotification(EventChangeKafkaMessage kafkaMessage) {
      log.info("Inserted message to database: {}", kafkaMessage);
        notificationRepository.save(notificationConverter.toEntity(kafkaMessage));

    }

    public List<EventChangeKafkaMessage> getAllUnreadMessage(String login) {
        List<EventChangeKafkaMessage> notifications =
         notificationRepository.findAll()
                .stream()
                .filter(n -> n.getSubsList().contains(login))
                .map(notificationConverter::toDomain)
                .toList();
        return notifications;
    }

    @Transactional
    public void readAllNotifications(List<Long> notificationIds) {
        notificationRepository.findAllByIdIn(notificationIds)
                .stream()
                .forEach(n -> { n.setRead(true);
                    notificationRepository.save(n);
                });

    }

}
