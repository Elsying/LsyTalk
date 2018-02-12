package com.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import Activity.R;


/**
 * Created by 340144 on 2018/1/9.
 */

public class ChooseActivity extends AppCompatActivity implements View.OnClickListener{

    private Button login;
    private Button register;
    private Context mContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_activity);
        login=(Button) findViewById(R.id.button2);
        register=(Button) findViewById(R.id.button6);
        mContext=this;
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button2:
                Intent intent=new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.button6:
                Intent intents=new Intent(mContext,RegisterActivity.class);
                startActivity(intents);
                break;
        }

    }

}
