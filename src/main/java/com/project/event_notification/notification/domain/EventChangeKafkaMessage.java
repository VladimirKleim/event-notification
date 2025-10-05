package com.project.event_notification.notification.domain;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

public class EventChangeKafkaMessage{
    private Long eventId;
    private Long ownerId;
    private Long changedById;
    private List<String> subsList;
    private FieldChange<String> name;
    private FieldChange<Integer> maxPlaces;
    private FieldChange<OffsetDateTime> date;
    private FieldChange<Integer> cost;
    private FieldChange<Integer> duration;
    private FieldChange<Long> locationId;
    private FieldChange<EventStatus> status;


    public EventChangeKafkaMessage() {
    }

    public List<String> getSubsList() {
        return subsList;
    }

    public void setSubsList(List<String> subsList) {
        this.subsList = subsList;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getChangedById() {
        return changedById;
    }

    public void setChangedById(Long changedById) {
        this.changedById = changedById;
    }

    public FieldChange<String> getName() {
        return name;
    }

    public void setName(FieldChange<String> name) {
        this.name = name;
    }

    public FieldChange<Integer> getMaxPlaces() {
        return maxPlaces;
    }

    public void setMaxPlaces(FieldChange<Integer> maxPlaces) {
        this.maxPlaces = maxPlaces;
    }

    public FieldChange<OffsetDateTime> getDate() {
        return date;
    }

    public void setDate(FieldChange<OffsetDateTime> date) {
        this.date = date;
    }

    public FieldChange<Integer> getCost() {
        return cost;
    }

    public void setCost(FieldChange<Integer> cost) {
        this.cost = cost;
    }

    public FieldChange<Integer> getDuration() {
        return duration;
    }

    public void setDuration(FieldChange<Integer> duration) {
        this.duration = duration;
    }

    public FieldChange<Long> getLocationId() {
        return locationId;
    }

    public void setLocationId(FieldChange<Long> locationId) {
        this.locationId = locationId;
    }

    public FieldChange<EventStatus> getStatus() {
        return status;
    }

    public void setStatus(FieldChange<EventStatus> status) {
        this.status = status;
    }

    public EventChangeKafkaMessage(Long eventId, Long ownerId, Long changedById, List<String> subsList, FieldChange<String> name, FieldChange<Integer> maxPlaces, FieldChange<OffsetDateTime> date, FieldChange<Integer> cost, FieldChange<Integer> duration, FieldChange<Long> locationId, FieldChange<EventStatus> status) {
        this.eventId = eventId;
        this.ownerId = ownerId;
        this.changedById = changedById;
        this.subsList = subsList;
        this.name = name;
        this.maxPlaces = maxPlaces;
        this.date = date;
        this.cost = cost;
        this.duration = duration;
        this.locationId = locationId;
        this.status = status;
    }

    @Override
    public String toString() {
        return "EventChangeKafkaMessage{" +
                "eventId=" + eventId +
                ", ownerId=" + ownerId +
                ", changedById=" + changedById +
                ", name=" + name +
                ", maxPlaces=" + maxPlaces +
                ", date=" + date +
                ", cost=" + cost +
                ", duration=" + duration +
                ", locationId=" + locationId +
                ", status=" + status +
                '}';
    }
}
