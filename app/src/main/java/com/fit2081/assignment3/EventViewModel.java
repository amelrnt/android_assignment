package com.fit2081.assignment3;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.fit2081.assignment3.Data.Event;
import com.fit2081.assignment3.Data.EventCategory;

import java.util.List;

public class EventViewModel extends AndroidViewModel {

    private EventRepository repository;
    private LiveData<List<Event>> allEvents;
    private LiveData<List<EventCategory>> allCategories;
    private MutableLiveData<Boolean> isCategoryValid = new MutableLiveData<>();

    public EventViewModel(@NonNull Application application) {
        super(application);
        repository = new EventRepository(application);
        allEvents = repository.getAllEvents();
        allCategories = repository.getAllCategories();
    }

    // Event methods
    public void insert(Event event) {
        repository.insertEvent(event);
    }

    public LiveData<List<Event>> getAllEvents() {
        return allEvents;
    }

    public LiveData<List<Event>> getEventsForCategory(int categoryId) {
        return repository.getEventsForCategory(categoryId);
    }

    public void update(Event event) {
        repository.updateEvent(event);
    }

    public void delete(Event event) {
        repository.deleteEvent(event);
    }

    // EventCategory methods
    public void insert(EventCategory category) {
        repository.insertCategory(category);
    }

    public LiveData<List<EventCategory>> getAllCategories() {
        return allCategories;
    }

    public LiveData<EventCategory> getCategoryById(String categoryId) {
        return repository.getCategoryById(categoryId);
    }

    public LiveData<Boolean> validateCategory(String categoryId) {
        // Reset validation result
        isCategoryValid.setValue(false);

        // Query the category by ID
        repository.getCategoryById(categoryId).observeForever(new Observer<EventCategory>() {
            @Override
            public void onChanged(EventCategory category) {
                if (category != null) {
                    // Category exists
                    isCategoryValid.setValue(true);
                }
            }
        });

        return isCategoryValid;
    }

    public void update(EventCategory category) {
        repository.updateCategory(category);
    }

    public void delete(EventCategory category) {
        repository.deleteCategory(category);
    }

    public void deleteAllCategories() {
        repository.deleteAllCategories();
    }

    public void deleteAllEvents() {
        repository.deleteAllEvents();
    }
}
