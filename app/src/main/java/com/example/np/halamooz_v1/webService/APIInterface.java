package com.example.np.halamooz_v1.webService;

import com.example.np.halamooz_v1.model.signResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {
    @POST("login")
    @FormUrlEncoded
    Call<signResponse> login(@Field("email") String email,@Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    Call<signResponse> register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone);
}
