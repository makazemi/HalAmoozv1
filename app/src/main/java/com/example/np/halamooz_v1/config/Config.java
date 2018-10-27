package com.example.np.halamooz_v1.config;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.np.halamooz_v1.model.User;

public class Config extends Application {

    public static User CurrentUser;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static boolean isOnline(Context context)
    {
        boolean isOnline = false;
        try
        {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            isOnline = (netInfo != null && netInfo.isConnected());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return isOnline;
    }
}
