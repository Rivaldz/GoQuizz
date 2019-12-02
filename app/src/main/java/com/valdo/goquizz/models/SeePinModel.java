package com.valdo.goquizz.models;

public class SeePinModel {

    String pinUserActivity;
    public SeePinModel(String pinUserActivity) {
        this.pinUserActivity = pinUserActivity;
    }

    public SeePinModel() {
    }

    public String getPinUserActivity() {
        return pinUserActivity;
    }

    public void setPinUserActivity(String pinUserActivity) {
        this.pinUserActivity = pinUserActivity;
    }
}
