package com.fit2081.assignment3.Data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "events")
public class Event {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String eventId;

    private String name;

    private String categoryId;

    private String description;
    private int ticketQty;
    private Boolean isActive;

    private long timestamp;

    // Constructors, getters, and setters
    public Event(int id, String eventId,String categoryId, String name, int ticketQty, boolean isActive) {
        this.id = id;
        this.eventId = eventId;
        this.name = name;
        this.categoryId = categoryId;
        this.ticketQty = ticketQty;
        this.isActive = isActive;
    }

    public Event() {
    }

    public Event(String eventId,String categoryId, String name, int ticketQty, boolean isActive) {
        this.eventId = eventId;
        this.name = name;
        this.categoryId = categoryId;
        this.ticketQty = ticketQty;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setTicketQty(int ticketQty) {
        this.ticketQty = ticketQty;
    }

    public int getTicketQty() {
        return ticketQty;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
