package com.fit2081.assignment3;

import android.content.Context;
import androidx.lifecycle.LiveData;

import com.fit2081.assignment3.Dao.EventCategoryDao;
import com.fit2081.assignment3.Dao.EventDao;
import com.fit2081.assignment3.Data.AppDatabase;
import com.fit2081.assignment3.Data.Event;
import com.fit2081.assignment3.Data.EventCategory;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class EventRepository {

    private EventDao eventDao;
    private EventCategoryDao eventCategoryDao;
    private LiveData<List<Event>> allEvents;
    private LiveData<List<EventCategory>> allCategories;

    public EventRepository(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        eventDao = db.eventDao();
        eventCategoryDao = db.eventCategoryDao();
    }

    // Event operations
    public void insertEvent(Event event) {
        AppDatabase.databaseWriteExecutor.execute(() -> eventDao.insert(event));
    }

    public LiveData<List<Event>> getAllEvents() {
        return (LiveData<List<Event>>) eventDao.getAllEvents();
    }

    public LiveData<List<Event>> getEventsForCategory(int categoryId) {
        return (LiveData<List<Event>>) eventDao.getEventsForCategory(categoryId);
    }

    public void updateEvent(Event event) {
        AppDatabase.databaseWriteExecutor.execute(() -> eventDao.update(event));
    }

    public void deleteEvent(Event event) {
        AppDatabase.databaseWriteExecutor.execute(() -> eventDao.delete(event));
    }

    // EventCategory operations
    public void insertCategory(EventCategory category) {
        AppDatabase.databaseWriteExecutor.execute(() -> eventCategoryDao.insert(category));
    }

    public LiveData<List<EventCategory>> getAllCategories() {
        return (LiveData<List<EventCategory>>) eventCategoryDao.getAllEventCategories();
    }

    public LiveData<EventCategory> getCategoryById(String categoryId) {
        return eventCategoryDao.getCategoryById(categoryId);
    }

    public void updateCategory(EventCategory category) {
        AppDatabase.databaseWriteExecutor.execute(() -> eventCategoryDao.update(category));
    }

    public void deleteCategory(EventCategory category) {
        AppDatabase.databaseWriteExecutor.execute(() -> eventCategoryDao.delete(category));
    }

    public void deleteAllCategories() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            eventCategoryDao.deleteAllEventCategories();
        });
    }

    public void deleteAllEvents() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            eventDao.deleteAllEvents();
        });
    }
}

