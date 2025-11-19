package com.project.event_notification.notification.domain;

import java.util.List;

public record NotificationListenerList(
        List<Long> notificationIds
) {
}
