package com.fit2081.assignment3;

import android.view.GestureDetector;
import android.view.MotionEvent;

import com.fit2081.assignment3.Activity.DashboardActivity;

public class CustomGestureListener extends GestureDetector.SimpleOnGestureListener {

    private final DashboardActivity activity;

    public CustomGestureListener(DashboardActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        super.onLongPress(e);
        activity.clearAllFields();
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        activity.saveEvent();
        return super.onDoubleTap(e);
    }
}
