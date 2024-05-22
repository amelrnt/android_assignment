package com.fit2081.assignment3.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.fit2081.assignment3.Data.Event;

import java.util.List;

@Dao
public interface EventDao {

    @Insert
    void insert(Event event);

    @Update
    void update(Event event);

    @Delete
    void delete(Event event);

    @Query("SELECT * FROM events")
    LiveData<List<Event>> getAllEvents();

    @Query("SELECT * FROM events WHERE categoryId = :categoryId")
    LiveData<List<Event>> getEventsForCategory(int categoryId);

    @Query("DELETE FROM events")
    void deleteAllEvents();
}
