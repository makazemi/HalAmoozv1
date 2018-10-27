package com.example.np.halamooz_v1.model;

import com.google.gson.annotations.SerializedName;

public class signResponse {
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;
    @SerializedName("token")
    private String token;
    @SerializedName("id")
    private String id;
    @SerializedName("data")
    private DataSingError data;

    public signResponse(boolean success, String message, String token) {
        this.success = success;
        this.message = message;
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DataSingError getData() {
        return data;
    }

    public void setData(DataSingError data) {
        this.data = data;
    }
}
