package com.project.event_notification.kafka;

import com.project.event_notification.notification.domain.EventChangeKafkaMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventListener {

    private final Logger log = LoggerFactory.getLogger(KafkaEventListener.class);

    @KafkaListener(topics = "event-notification", containerFactory = "containerFactory")
    public void listenEvents(
            ConsumerRecord<Long, EventChangeKafkaMessage> record
    ) {
      log.info("Got notification event: {}", record.value());
    }
}
