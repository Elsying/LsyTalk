package com.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.Utils.SharedHelper;

import Activity.R;

/**
 * Created by 340144 on 2018/1/5.
 * 启动页面，判断SharedPreferences内是否有账号密码
 * 有就直接进入主页面，否则登录
 */

public class SplashActivity extends AppCompatActivity {
    private SharedHelper sp;
    private Context mContext;

    public String username;
    public String password;
    public Object o;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashactivit);
        mContext=getApplicationContext();
        username= SharedHelper.get(mContext,"username");
        password= SharedHelper.get(mContext,"password");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(username==null||username.isEmpty()){
                    Intent intent=new Intent(SplashActivity.this, ChooseActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent=new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        },2000);

    }
}
