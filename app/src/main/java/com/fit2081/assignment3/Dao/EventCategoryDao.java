package com.fit2081.assignment3.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.fit2081.assignment3.Data.EventCategory;

import java.util.List;

@Dao
public interface EventCategoryDao {
    @Insert
    void insert(EventCategory eventCategory);

    @Update
    void update(EventCategory eventCategory);

    @Delete
    void delete(EventCategory eventCategory);

    @Query("SELECT * FROM event_categories")
    List<EventCategory> getAllEventCategories();

    @Query("DELETE FROM event_categories")
    void deleteAllEventCategories();

    @Query("SELECT * FROM event_categories WHERE eventCategoryId = :eventCategoryId LIMIT 1")
    LiveData<EventCategory> getCategoryById(String eventCategoryId); // New method
}

