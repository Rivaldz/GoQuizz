package com.valdo.goquizz.models;

public class LoginModels {

    int key ;
    String username;
    String passsword;

    public LoginModels() {
    }

    public LoginModels(String username, String passsword) {
        this.username = username;
        this.passsword = passsword;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }
}
