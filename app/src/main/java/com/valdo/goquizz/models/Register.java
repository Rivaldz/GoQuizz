package com.valdo.goquizz.models;

import java.io.Serializable;

public class Register  implements Serializable {

    private String nama;
    private String username;
    private String email;
    private String password;
    private String  key;

    public Register() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Register{" +
                "nama='" + nama + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Register(String nama, String username, String email, String password) {
        this.nama = nama;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
