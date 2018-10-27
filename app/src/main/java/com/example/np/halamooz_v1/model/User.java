package com.example.np.halamooz_v1.model;

public class User {
    private String token;
    private String refreshToken;
    public boolean isLogin=false;
    private String id;
    private String name;
    private String family;
    private String email;
    private String phone;
    private String userName;
    private String major;
    private String University;
    private String degree;


    public User(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public User(String id, String email, String phone, String userName) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getUniversity() {
        return University;
    }

    public void setUniversity(String university) {
        University = university;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }



    public  String getToken() {
        return token;
    }

    public  void setToken(String token) {
        this.token = token;
    }

    public  String getRefreshToken() {
        return refreshToken;
    }

    public  void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}

