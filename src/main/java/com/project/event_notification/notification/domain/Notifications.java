package com.project.event_notification.notification.domain;

import java.time.LocalDateTime;
import java.util.List;

public record Notifications(
         Long eventId,
         Long ownerId,
         Long changedById,
         List<String> subsList,
         FieldChange<String> name,
         FieldChange<Integer> maxPlaces,
         FieldChange<LocalDateTime> date,
         FieldChange<Integer> cost,
         FieldChange<Integer> duration,
         FieldChange<Long> locationId,
         FieldChange<EventStatus> status
) {
}
