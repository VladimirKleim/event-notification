package com.project.event_notification.notification.db;


import com.project.event_notification.notification.domain.FieldChange;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "notification")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<String> subsList;

    private Long eventId;

    private Long ownerId;

    private Long changerId;

    private String oldName;
    private String NewName;
    private Integer oldPlace;
    private Integer newPlace;
    private OffsetDateTime oldTime;
    private OffsetDateTime newTime;
    private Integer oldCost;
    private Integer newCost;
    private Integer oldDuration;
    private Integer newDuration;
    private Long oldLocationId;
    private Long newLocationId;
    private String oldEventStatus;
    private String newEventStatus;

    private Boolean isRead;

    public NotificationEntity() {
    }

    public NotificationEntity(Long id, Long eventId, Long ownerId, Long changerId, List<String> subsList, String oldName, String newName, Integer oldPlace, Integer newPlace, OffsetDateTime oldTime, OffsetDateTime newTime, Integer oldCost, Integer newCost, Integer oldDuration, Integer newDuration, Long oldLocationId, Long newLocationId, String oldEventStatus, String newEventStatus, Boolean isRead) {
        this.id = id;
        this.eventId = eventId;
        this.ownerId = ownerId;
        this.changerId = changerId;
        this.subsList = subsList;
        this.oldName = oldName;
        NewName = newName;
        this.oldPlace = oldPlace;
        this.newPlace = newPlace;
        this.oldTime = oldTime;
        this.newTime = newTime;
        this.oldCost = oldCost;
        this.newCost = newCost;
        this.oldDuration = oldDuration;
        this.newDuration = newDuration;
        this.oldLocationId = oldLocationId;
        this.newLocationId = newLocationId;
        this.oldEventStatus = oldEventStatus;
        this.newEventStatus = newEventStatus;
        this.isRead = isRead;
    }

    public Long getChangerId() {
        return changerId;
    }

    public void setChangerId(Long changerId) {
        this.changerId = changerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getSubsList() {
        return subsList;
    }

    public void setSubsList(List<String> subsList) {
        this.subsList = subsList;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return NewName;
    }

    public void setNewName(String newName) {
        NewName = newName;
    }

    public Integer getOldPlace() {
        return oldPlace;
    }

    public void setOldPlace(Integer oldPlace) {
        this.oldPlace = oldPlace;
    }

    public Integer getNewPlace() {
        return newPlace;
    }

    public void setNewPlace(Integer newPlace) {
        this.newPlace = newPlace;
    }

    public OffsetDateTime getOldTime() {
        return oldTime;
    }

    public void setOldTime(OffsetDateTime oldTime) {
        this.oldTime = oldTime;
    }

    public OffsetDateTime getNewTime() {
        return newTime;
    }

    public void setNewTime(OffsetDateTime newTime) {
        this.newTime = newTime;
    }

    public Integer getOldCost() {
        return oldCost;
    }

    public void setOldCost(Integer oldCost) {
        this.oldCost = oldCost;
    }

    public Integer getNewCost() {
        return newCost;
    }

    public void setNewCost(Integer newCost) {
        this.newCost = newCost;
    }

    public Integer getOldDuration() {
        return oldDuration;
    }

    public void setOldDuration(Integer oldDuration) {
        this.oldDuration = oldDuration;
    }

    public Integer getNewDuration() {
        return newDuration;
    }

    public void setNewDuration(Integer newDuration) {
        this.newDuration = newDuration;
    }

    public Long getOldLocationId() {
        return oldLocationId;
    }

    public void setOldLocationId(Long oldLocationId) {
        this.oldLocationId = oldLocationId;
    }

    public Long getNewLocationId() {
        return newLocationId;
    }

    public void setNewLocationId(Long newLocationId) {
        this.newLocationId = newLocationId;
    }

    public String getOldEventStatus() {
        return oldEventStatus;
    }

    public void setOldEventStatus(String oldEventStatus) {
        this.oldEventStatus = oldEventStatus;
    }

    public String getNewEventStatus() {
        return newEventStatus;
    }

    public void setNewEventStatus(String newEventStatus) {
        this.newEventStatus = newEventStatus;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }
}
