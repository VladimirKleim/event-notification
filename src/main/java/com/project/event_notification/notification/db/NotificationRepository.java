package com.project.event_notification.notification.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    Optional<NotificationEntity> findAllByIdIn(List<Long> ids);

}
