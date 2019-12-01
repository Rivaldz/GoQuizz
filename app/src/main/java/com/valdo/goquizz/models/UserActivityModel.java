package com.valdo.goquizz.models;

public class UserActivityModel {
    String pinUserActivity;

    public UserActivityModel(String pinUserActivity) {
        this.pinUserActivity = pinUserActivity;
    }

    public String getPinUserActivity() {
        return pinUserActivity;
    }

    public void setPinUserActivity(String pinUserActivity) {
        this.pinUserActivity = pinUserActivity;
    }
}
