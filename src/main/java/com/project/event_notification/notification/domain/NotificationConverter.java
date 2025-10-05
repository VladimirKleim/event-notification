package com.project.event_notification.notification.domain;

import com.project.event_notification.notification.db.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationConverter {

    public NotificationEntity toEntity(NotificationEvent notification) {
        var notificationEntity = new NotificationEntity();
        notificationEntity.setEventId(notification.getEventId());
        notificationEntity.setOwnerId(notification.getOwnerId());
        notificationEntity.setSubsList(notification.getSubsList());
        if(notification.getName() != null) {
            notificationEntity.setOldName(notification.getName().getOldField());
            notificationEntity.setNewName(notification.getName().getNewField());
        }
        if(notification.getMaxPlaces() != null) {
            notificationEntity.setOldPlace(notification.getMaxPlaces().getOldField());
            notificationEntity.setNewPlace(notification.getMaxPlaces().getNewField());
        }
        if(notification.getDate() != null) {
            notificationEntity.setOldTime(notification.getDate().getOldField());
            notificationEntity.setNewTime(notification.getDate().getNewField());
        }
        if(notification.getCost() != null) {
            notificationEntity.setOldCost(notification.getCost().getOldField());
            notificationEntity.setNewCost(notification.getCost().getNewField());
        }
        if(notification.getDuration() != null) {
            notificationEntity.setOldDuration(notification.getDuration().getOldField());
            notificationEntity.setNewDuration(notification.getDuration().getNewField());
        }
        if(notification.getLocationId() != null) {
            notificationEntity.setOldLocationId(notification.getLocationId().getOldField());
            notificationEntity.setNewLocationId(notification.getLocationId().getNewField());
        }
        if(notification.getStatus() != null) {
            notificationEntity.setOldEventStatus(notification.getStatus().getOldField().name());
            notificationEntity.setNewEventStatus(notification.getStatus().getNewField().name());
        }
        notificationEntity.setRead(false);
        return notificationEntity;
    }


    public NotificationEvent toDomain(NotificationEntity notificationEntity) {
        var event = new NotificationEvent();
        event.setEventId(notificationEntity.getEventId());
        event.setOwnerId(notificationEntity.getOwnerId());
        event.setChangedById(notificationEntity.getChangerId());
        event.setSubsList(notificationEntity.getSubsList());

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
