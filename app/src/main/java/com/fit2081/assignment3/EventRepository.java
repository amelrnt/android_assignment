package com.fit2081.assignment3;

import android.content.Context;
import androidx.lifecycle.LiveData;

import com.fit2081.assignment3.Dao.EventCategoryDao;
import com.fit2081.assignment3.Dao.EventDao;
import com.fit2081.assignment3.Data.AppDatabase;
import com.fit2081.assignment3.Data.DatabaseClient;
import com.fit2081.assignment3.Data.Event;
import com.fit2081.assignment3.Data.EventCategory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventRepository {

    private EventDao eventDao;
    private EventCategoryDao eventCategoryDao;
    private ExecutorService executorService;
    private LiveData<List<Event>> allEvents;
    private LiveData<List<EventCategory>> allCategories;

    public EventRepository(Context context) {
        AppDatabase db = DatabaseClient.getInstance(context).getAppDatabase();
        eventDao = db.eventDao();
        eventCategoryDao = db.eventCategoryDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    // Event operations
    public void insertEvent(Event event) {
        executorService.execute(() -> eventDao.insert(event));
    }

    public LiveData<List<Event>> getAllEvents() {
        return (LiveData<List<Event>>) eventDao.getAllEvents();
    }

    public LiveData<List<Event>> getEventsForCategory(int categoryId) {
        return (LiveData<List<Event>>) eventDao.getEventsForCategory(categoryId);
    }

    public void updateEvent(Event event) {
        executorService.execute(() -> eventDao.update(event));
    }

    public void deleteEvent(Event event) {
        executorService.execute(() -> eventDao.delete(event));
    }

    // EventCategory operations
    public void insertCategory(EventCategory category) {
        executorService.execute(() -> eventCategoryDao.insert(category));
    }

    public LiveData<List<EventCategory>> getAllCategories() {
        return (LiveData<List<EventCategory>>) eventCategoryDao.getAllEventCategories();
    }

    public LiveData<EventCategory> getCategoryById(String categoryId) {
        return eventCategoryDao.getCategoryById(categoryId);
    }

    public void updateCategory(EventCategory category) {
        executorService.execute(() -> eventCategoryDao.update(category));
    }

    public void deleteCategory(EventCategory category) {
        executorService.execute(() -> eventCategoryDao.delete(category));
    }

    public void deleteAllCategories() {
        executorService.execute(() -> {
            eventCategoryDao.deleteAllEventCategories();
        });
    }

    public void deleteAllEvents() {
        executorService.execute(() -> {
            eventDao.deleteAllEvents();
        });
    }
}

