package com.project.event_notification.notification.domain;

import com.project.event_notification.notification.db.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationConverter {

    public NotificationEntity toEntity(NotificationEvent notificationEvent) {
        var notification = new NotificationEntity();
        notification.setEventId(notificationEvent.getEventId());
        notification.setSubsList(notificationEvent.getUsers());
        notification.setOwnerId(notificationEvent.getOwnerId());
        notification.setChangerId(notification.getChangerId());

        if (notificationEvent.getName() != null) {
            notification.setOldName(notificationEvent.getName().getOldField());
            notification.setNewName(notificationEvent.getName().getNewField());
        }

        if (notificationEvent.getMaxPlaces() != null) {
            notification.setOldPlace(notificationEvent.getMaxPlaces().getOldField());
            notification.setNewPlace(notificationEvent.getMaxPlaces().getNewField());
        }

        if (notificationEvent.getDate() != null) {
            notification.setOldTime(notificationEvent.getDate().getOldField());
            notification.setNewTime(notificationEvent.getDate().getNewField());
        }

        if (notificationEvent.getCost() != null) {
            notification.setOldCost(notificationEvent.getCost().getOldField());
            notification.setNewCost(notificationEvent.getCost().getNewField());
        }

        if (notificationEvent.getDuration() != null) {
            notification.setOldDuration(notificationEvent.getDuration().getOldField());
            notification.setNewDuration(notificationEvent.getDuration().getNewField());
        }

        if (notificationEvent.getLocationId() != null) {
            notification.setOldLocationId(notificationEvent.getLocationId().getOldField());
            notification.setNewLocationId(notificationEvent.getLocationId().getNewField());
        }

        if (notificationEvent.getStatus() != null) {
            notification.setOldEventStatus(notificationEvent.getStatus().getOldField().name());
            notification.setNewEventStatus(notificationEvent.getStatus().getNewField().name());
        }
        notification.setRead(false);
        return notification;
    }

    public NotificationEvent toDomain(NotificationEntity notificationEntity) {
        var event = new NotificationEvent();
        event.setEventId(notificationEntity.getEventId());
        event.setOwnerId(notificationEntity.getOwnerId());
        event.setChangedById(notificationEntity.getChangerId());
        event.setUsers(notificationEntity.getSubsList());

        if (notificationEntity.getNewPlace() != null) {
            event.setMaxPlaces(new FieldChange<>(notificationEntity.getOldPlace(), notificationEntity.getNewPlace()));
        }
        if (notificationEntity.getNewName() != null) {
            event.setName(new FieldChange<>(notificationEntity.getOldName(), notificationEntity.getNewName()));
        }
        if (notificationEntity.getNewTime() != null) {
            event.setDate(new FieldChange<>(notificationEntity.getOldTime(), notificationEntity.getNewTime()));
        }
        if (notificationEntity.getNewCost() != null) {
            event.setCost(new FieldChange<>(notificationEntity.getOldCost(), notificationEntity.getNewCost()));
        }
        if (notificationEntity.getNewDuration() != null) {
            event.setDuration(new FieldChange<>(notificationEntity.getOldDuration(), notificationEntity.getNewDuration()));
        }
        if (notificationEntity.getNewLocationId() != null) {
            event.setLocationId(new FieldChange<>(notificationEntity.getOldLocationId(), notificationEntity.getNewLocationId()));
        }
        if (notificationEntity.getNewEventStatus() != null) {
            event.setStatus(new FieldChange<>(EventStatus.valueOf(notificationEntity.getOldEventStatus()), EventStatus.valueOf(notificationEntity.getNewEventStatus())));
        }
     return event;
    }
}
