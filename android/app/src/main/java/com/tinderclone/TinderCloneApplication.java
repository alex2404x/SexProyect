package com.tinderclone;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class TinderCloneApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Firebase
        FirebaseApp.initializeApp(this);
    }
}
