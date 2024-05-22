package com.fit2081.assignment3.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.fit2081.assignment3.Dao.EventCategoryDao;
import com.fit2081.assignment3.Dao.EventDao;
import com.fit2081.assignment3.Data.Event;
import com.fit2081.assignment3.Data.EventCategory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Event.class, EventCategory.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EventDao eventDao();
    public abstract EventCategoryDao eventCategoryDao();
    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "MyDatabase")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
