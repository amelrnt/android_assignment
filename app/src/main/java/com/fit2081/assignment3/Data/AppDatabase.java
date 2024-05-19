package com.fit2081.assignment3.Data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.fit2081.assignment3.Dao.EventCategoryDao;
import com.fit2081.assignment3.Dao.EventDao;
import com.fit2081.assignment3.Data.Event;
import com.fit2081.assignment3.Data.EventCategory;

@Database(entities = {Event.class, EventCategory.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EventDao eventDao();
    public abstract EventCategoryDao eventCategoryDao();
}
