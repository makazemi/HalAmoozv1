package com.example.np.halamooz_v1.model;

import java.util.ArrayList;

public class DataSingError {

    private ArrayList<String> email = null;
    private ArrayList<String> password = null;
    private ArrayList<String> phone = null;
    private ArrayList<String> username = null;

    public ArrayList<String> getEmail() {
        return email;
    }

    public void setEmail(ArrayList<String> email) {
        this.email = email;
    }

    public ArrayList<String> getPassword() {
        return password;
    }

    public void setPassword(ArrayList<String> password) {
        this.password = password;
    }

    public ArrayList<String> getPhone() {
        return phone;
    }

    public void setPhone(ArrayList<String> phone) {
        this.phone = phone;
    }

    public ArrayList<String> getUsername() {
        return username;
    }

    public void setUsername(ArrayList<String> username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "DataSingError{" +
                "email=" + email +
                ", password=" + password +
                ", phone=" + phone +
                ", username=" + username +
                '}';
    }
}
