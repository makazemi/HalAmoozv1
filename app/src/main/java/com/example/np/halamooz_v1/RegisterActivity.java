package com.example.np.halamooz_v1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUsername,edtEmail,edtPassword,edtPhone;
    private TextView txtLogin;
    private Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsername=findViewById(R.id.edtUsername);
        edtEmail=findViewById(R.id.edtEmail);
        edtPassword=findViewById(R.id.edtPassword);
        edtPhone=findViewById(R.id.edtPhone);
        btnSignUp=findViewById(R.id.btnSignUp);
        txtLogin=findViewById(R.id.txtLogin);
    }

    private void doRegister(){
        APIInterface apiInterface= APIClient.getClient().create(APIInterface.class);
        Call<signResponse> call=apiInterface.register(
                edtUsername.getText().toString(),
                edtEmail.getText().toString(),
                edtPassword.getText().toString(),
                edtPhone.getText().toString());
        call.enqueue(new Callback<signResponse>() {
            @Override
            public void onResponse(Call<signResponse> call, Response<signResponse> response) {
                if(response.isSuccessful()){
                    Config.CurrentUser=new User(
                            response.body().getId(),
                            edtEmail.getText().toString(),
                            edtPhone.getText().toString(),
                            edtUsername.getText().toString());
                    Config.CurrentUser.setToken(response.body().getToken());
                    Config.CurrentUser.setLogin(true);
                    Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                if(response.code()==404){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getApplicationContext());
                    dialog.setCancelable(true);
                    if(response.body().getData()!=null){
                        dialog.setMessage(response.body().getData().toString());
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
        String phone=edtPhone.getText().toString();
        String username=edtUsername.getText().toString();
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
            Toast.makeText(RegisterActivity.this,"ایمیل را صحیح وارد کنید!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(RegisterActivity.this,"لطفا ایمیل خود را وارد کنید!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(RegisterActivity.this,"لطفا پسورد خود را وارد کنید!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.length()<6){
            Toast.makeText(RegisterActivity.this,"لطفا حداقل 6 کاراکتر برای پسورد وارد کنید!",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(username)){
            Toast.makeText(RegisterActivity.this,"لطفا نام کاربری خود را وارد کنید!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(phone)){
            Toast.makeText(RegisterActivity.this,"لطفا شماره همراه خود را وارد کنید!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!phone.matches("(\\+98|0)?9\\d{9}")){
            Toast.makeText(RegisterActivity.this,"شماره موبایل نا معتبر است!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id) {
            case R.id.btnSignUp: {
                if(Config.isOnline(this)) {
                    if(validation())
                        doRegister();
                }
                else
                    Toast.makeText(this,"اتصال به اینترنت برقرار نمی باشد!",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.txtLogin: {
                break;
            }
        }
    }
}
