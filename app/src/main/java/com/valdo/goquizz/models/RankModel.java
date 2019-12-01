package com.valdo.goquizz.models;

public class RankModel {
    String emailUser,scoreuser;

    public RankModel() {
    }

    public RankModel(String scoreuser) {
//        this.emailUser = emailUser;
        this.scoreuser = scoreuser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getScoreuser() {
        return scoreuser;
    }

    public void setScoreuser(String scoreuser) {
        this.scoreuser = scoreuser;
    }

    @Override
    public String toString() {
        return "ResultTest{" +
                "emailUser='" + emailUser + '\'' +
                "scoreuser='" + scoreuser + '\'' +
                '}';
    }
}
