package com.example.np.halamooz_v1;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.np.halamooz_v1.config.Config;
import com.example.np.halamooz_v1.model.User;
import com.example.np.halamooz_v1.model.signResponse;
import com.example.np.halamooz_v1.webService.APIClient;
import com.example.np.halamooz_v1.webService.APIInterface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity  {

    private EditText edtEmail,edtPassword;
    private Button btnLogin;
    private ImageView imgVisible;
    private TextView txtForget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail=findViewById(R.id.edtEmail);
        edtPassword=findViewById(R.id.edtPassword);
        btnLogin=findViewById(R.id.btnSignIn);
        txtForget=findViewById(R.id.txtForgetPassword);
        imgVisible=findViewById(R.id.imgIconVisibilty);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Config.isOnline(LoginActivity.this)) {
                    if (validation()) {
                        doLogin();
                        Log.e("LoginActivity","btnsingin");
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this,"اتصال به اینترنت برقرار نمی باشد!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void doLogin(){
        APIInterface apiInterface= APIClient.getClient().create(APIInterface.class);
        Call<signResponse> call=apiInterface.login(edtEmail.getText().toString(),edtPassword.getText().toString());
        call.enqueue(new Callback<signResponse>() {
            @Override
            public void onResponse(Call<signResponse> call, Response<signResponse> response) {
                if(response.isSuccessful()){
                    Config.CurrentUser=new User(response.body().getId(),edtEmail.getText().toString());
                    Config.CurrentUser.setLogin(true);
                    Config.CurrentUser.setToken(response.body().getToken());
                    Log.e("LoginActivity","200");
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                if(response.code()==422){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getApplicationContext());
                    dialog.setCancelable(true);
                    dialog.setMessage(response.body().getMessage());
                    Log.e("LoginActivity","422");
                }
                if(response.code()==404){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getApplicationContext());
                    dialog.setCancelable(true);
                    dialog.setMessage(response.body().getMessage());
                    if(response.body().getData()!=null){
                        dialog.setMessage(response.body().getData().toString());
                        Log.e("LoginActivity","404");
                    }
                    else
                        dialog.setMessage(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<signResponse> call, Throwable t) {
                Log.e("LoginActivity","onFailure",t);
                Log.e("LoginActivity",call.toString());
            }
        });
    }

    private boolean validation(){
        String email=edtEmail.getText().toString();
        String password=edtPassword.getText().toString();
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if(!matcher.matches()){
            Toast.makeText(LoginActivity.this,"ایمیل را صحیح وارد کنید!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(LoginActivity.this,"لطفا ایمیل خود را وارد کنید!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this,"لطفا پسورد خود را وارد کنید!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.length()<6){
            Toast.makeText(LoginActivity.this,"لطفا حداقل 6 کاراکتر برای پسورد وارد کنید!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

//    @Override
//    public void onClick(View v) {
//        int id = v.getId();
//
//        switch (id) {
//            case R.id.btnSignIn: {
//                if(Config.isOnline(this)) {
//                    if (validation()) {
//                        doLogin();
//                        Log.e("LoginActivity","btnsingin");
//                    }
//                }
//                else {
//                    Toast.makeText(this,"اتصال به اینترنت برقرار نمی باشد!",Toast.LENGTH_SHORT).show();
//                }
//                break;
//            }
//            case R.id.txtRegister: {
//                Intent intent=new Intent(this,RegisterActivity.class);
//                startActivity(intent);
//                break;
//            }
//            case R.id.txtForgetPassword:
//                break;
//        }
//    }
}
