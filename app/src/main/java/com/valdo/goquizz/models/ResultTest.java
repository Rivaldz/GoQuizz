package com.valdo.goquizz.models;

import java.util.List;

public class ResultTest {

    String emailUser,scoreuser;

    public ResultTest() {
    }

    public ResultTest(List<ResultTest> rankModelList) {
    }

    public ResultTest( String emailUser,String scoreuser) {
        this.emailUser = emailUser;
        this.scoreuser = scoreuser;
    }

    public ResultTest(String scoreuser) {
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
                "emailUser" + emailUser + '\'' +
                "scoreuser='" + scoreuser + '\'' +
                '}';
    }
}
