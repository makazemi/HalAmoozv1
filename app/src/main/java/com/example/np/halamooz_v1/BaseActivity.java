package com.example.np.halamooz_v1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.np.halamooz_v1.model.User;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_base);

        boolean isConnected=isOnline(getApplicationContext());

        showSnack(isConnected);
    }

    public boolean isOnline(Context context)
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

    private void showSnack(boolean isConnected) {
        if (isConnected) {
            setContentView(getLayout());
            load();
        }

        else{
            setContentView(R.layout.internet_screen);
            Button btnReload=findViewById(R.id.btnReload);
            btnReload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // showSnack(isOnline(getApplicationContext()));
                    finish();
                    startActivity(getIntent());
                }
            });

        }
    }

    public abstract int getLayout();

    public abstract void load();

    public abstract void loadCustomView();

    @Override
    protected void onStart() {
        super.onStart();
        if( isOnline(getApplicationContext()) ){
            loadCustomView();
        }
    }
}
