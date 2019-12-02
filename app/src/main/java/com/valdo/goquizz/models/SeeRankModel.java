package com.valdo.goquizz.models;

    public class SeeRankModel {
    String pinUsers,pinUserActivity;

        public SeeRankModel() {
        }

        public SeeRankModel(String pinUsers) {
        this.pinUsers = pinUsers;
    }

        public String getPinUserActivity() {
            return pinUserActivity;
        }

        public void setPinUserActivity(String pinUserActivity) {
            this.pinUserActivity = pinUserActivity;
        }

        public String getPinUsers() {
        return pinUsers;
    }

    public void setPinUsers(String pinUsers) {
        this.pinUsers = pinUsers;
    }

        @Override
        public String toString() {
            return "SeeRankModel{" +
                    "pinUsers='" + pinUsers + '\'' +
                    '}';
        }
    }
