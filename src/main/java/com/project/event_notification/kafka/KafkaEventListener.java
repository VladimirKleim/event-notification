package com.project.event_notification.kafka;

import com.project.event_notification.notification.domain.NotificationEvent;
import com.project.event_notification.notification.domain.NotificationService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka Consumer
 */
@Component
public class KafkaEventListener {

    private final Logger log = LoggerFactory.getLogger(KafkaEventListener.class);
    private final NotificationService notificationService;

    public KafkaEventListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "event-notification", containerFactory = "containerFactory")
    public void listenEvents(
            ConsumerRecord<Long, NotificationEvent> record
    ) {
      log.info("Got notification event: {}", record.value());
      NotificationEvent message = record.value();
      notificationService.saveNotification(message);

    }
}
