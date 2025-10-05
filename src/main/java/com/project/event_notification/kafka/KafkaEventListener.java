package com.project.event_notification.kafka;

import com.project.event_notification.notification.domain.EventChangeKafkaMessage;
import com.project.event_notification.notification.domain.NotificationService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            ConsumerRecord<Long, EventChangeKafkaMessage> record
    ) {
      log.info("Got notification event: {}", record.value());
      EventChangeKafkaMessage message = record.value();
      notificationService.saveNotification(message);

    }
}
