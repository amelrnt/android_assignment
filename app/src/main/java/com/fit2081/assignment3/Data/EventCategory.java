package com.fit2081.assignment3.Data;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "event_categories")
public class EventCategory {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String eventCategoryId;

    private String name;

    private int categoryCount;
    private Boolean isActive;
    private String location_name;


    // Constructors, getters, and setters

    public EventCategory() {}


    public EventCategory(String eventCategoryId, String name, int categoryCount, Boolean isActive, String locationName) {
        this.eventCategoryId = eventCategoryId;
        this.name = name;
        this.categoryCount = categoryCount;
        this.isActive = isActive;
        this.location_name = locationName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventCategoryId() {
        return eventCategoryId;
    }

    public void setEventCategoryId(String eventCategoryId) {
        this.eventCategoryId = eventCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryCount(int categoryCount) {
        this.categoryCount = categoryCount;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }
}
