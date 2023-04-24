package com.example.realnews;

public class User {
    public String username;
    public String email;
    public String password;
    public String phonenumer;

    public User(String username, String email, String phonenumber, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phonenumer = phonenumber;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumer() {
        return phonenumer;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhonenumer(String phonenumer) {
        this.phonenumer = phonenumer;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}