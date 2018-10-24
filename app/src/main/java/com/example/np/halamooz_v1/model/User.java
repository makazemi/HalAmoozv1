package com.example.np.halamooz_v1.model;

public class User {
    private static String token;
    private static String refreshToken;
    public static boolean isLogin=false;
    private String name;
    private String family;
    private String email;

    public User(String email) {
        this.email = email;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        User.token = token;
    }

    public static String getRefreshToken() {
        return refreshToken;
    }

    public static void setRefreshToken(String refreshToken) {
        User.refreshToken = refreshToken;
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
}
