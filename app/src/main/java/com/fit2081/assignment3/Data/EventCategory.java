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
    private String locationName;


    // Constructors, getters, and setters
    public EventCategory() {
    }

    public EventCategory(int id, String eventCategoryId, String name, int categoryCount, Boolean isActive, String locationName) {
        this.id = id;
        this.eventCategoryId = eventCategoryId;
        this.name = name;
        this.categoryCount = categoryCount;
        this.isActive = isActive;
        this.locationName = locationName;
    }

    public EventCategory(String eventCategoryId, String name, int categoryCount, Boolean isActive, String locationName) {
        this.eventCategoryId = eventCategoryId;
        this.name = name;
        this.categoryCount = categoryCount;
        this.isActive = isActive;
        this.locationName = locationName;
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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
