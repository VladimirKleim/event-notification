package com.project.event_notification;

import com.project.event_notification.notification.db.NotificationRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class NotificationDeleteScheduler {

    private final NotificationRepository notificationRepository;
    private final Logger log = LoggerFactory.getLogger(NotificationDeleteScheduler.class);

    public NotificationDeleteScheduler(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    @Scheduled(cron = "${read.status.cron}")
    public void statusUpdater() {
        log.info("Scheduler has started");
        notificationRepository.deleteAllByIsReadIsTrue();
        log.info("Scheduler delete notification");
    }
}
