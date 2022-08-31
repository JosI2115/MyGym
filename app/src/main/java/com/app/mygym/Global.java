package com.app.mygym;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;

import com.app.mygym.entities.User;
import com.app.mygym.util.MockEntities;

public class Global extends Application {

    private static Global instance;
    private AppCompatActivity currentActivity;
    private User loggedUser;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        loggedUser = MockEntities.getSuperUser();
    }

    public static Global getInstance() {
        return instance;
    }

    public void setCurrentActivity(AppCompatActivity currentActivity) {
        this.currentActivity = currentActivity;
    }

    public AppCompatActivity getCurrentActivity() {
        return currentActivity;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

}