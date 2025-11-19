package com.project.event_notification.notification.domain;

import org.springframework.stereotype.Service;

@Service
public class NotificationDtoConverter {
    public NotificationDto toDto(NotificationEvent gotMessage) {
        var notification = new NotificationDto();
        notification.setEventId(gotMessage.getEventId());
        if (gotMessage.getName() != null) {
            notification.setName(new FieldChange<>(gotMessage.getName().getOldField(), gotMessage.getName().getNewField()));
        }
        if (gotMessage.getMaxPlaces() != null) {
            notification.setMaxPlaces(new FieldChange<>(gotMessage.getMaxPlaces().getOldField(), gotMessage.getMaxPlaces().getNewField()));
        }
        if (gotMessage.getDate() != null) {
            notification.setDate(new FieldChange<>(gotMessage.getDate().getOldField(), gotMessage.getDate().getNewField()));
        }
        if (gotMessage.getCost() != null) {
            notification.setCost(new FieldChange<>(gotMessage.getCost().getOldField(), gotMessage.getCost().getNewField()));
        }
        if (gotMessage.getDuration() != null) {
            notification.setDuration(new FieldChange<>(gotMessage.getDuration().getOldField(), gotMessage.getDuration().getNewField()));
        }
        if (gotMessage.getLocationId() != null) {
            notification.setLocationId(new FieldChange<>(gotMessage.getLocationId().getOldField(), gotMessage.getLocationId().getNewField()));
        }
        if (gotMessage.getStatus() != null) {
            notification.setStatus(new FieldChange<>(gotMessage.getStatus().getOldField(), gotMessage.getStatus().getNewField()));
        }
        return notification;
    }
}
