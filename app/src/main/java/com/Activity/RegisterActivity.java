package com.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.Utils.ActivityManagerUtils;
import com.Utils.GetUtils;

import Activity.R;

/**
 * Created by 340144 on 2018/1/11.
 * 注册验证
 */


//以后改成手机号注册，短信验证
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Context mContext;
    private RelativeLayout rl_user;
    private Button mLogin;
    private Button mRegister;
    private EditText account;
    private EditText password;
    private ImageView back;

    //返回的结果
    private String result;

    private Handler handler = new Handler();

    public  String url = "http://172.16.0.1:8080/hello/ResignServlet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        bind();
        mContext = this;
        init();
    }

    private void bind() {
        rl_user = (RelativeLayout) findViewById(R.id.rl_users);
        mLogin = (Button) findViewById(R.id.registers);
        account = (EditText) findViewById(R.id.accounts);
        password = (EditText) findViewById(R.id.passwords);
        back = (ImageView) findViewById(R.id.imageView6);

        //final String username=account.getText().toString();
        // final String passw=password.getText().toString();
        back.setOnClickListener(this);
        mLogin.setOnClickListener(this);

    }

    private void init() {
        Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.login_anim);
        anim.setFillAfter(true);
        rl_user.startAnimation(anim);  //设置动画
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.imageView6:
                    RegisterActivity.this.finish();
                break;
            case R.id.registers:
                if (!checkNetwork()) {
                     Toast.makeText(RegisterActivity.this, "网络未连接", Toast.LENGTH_SHORT).show();
                    break;
                }

                new Thread(new MyThread()).start();
                break;


        }
    }
    public class MyThread implements Runnable{
        public void run(){
            result= GetUtils.logGet(url,account.getText().toString(), password.getText().toString());
            //Toast.makeText(LoginActivity.this, "aa！", Toast.LENGTH_SHORT).show();
            //在post方法里更新UI操作
            handler.post(new Runnable(){
                public void run(){
                    Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_SHORT).show();
                    if(result.equals("注册成功！")){
                        Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(mContext, LoginActivity.class);
                        startActivity(intent);
                        ActivityManagerUtils.getInstance().finishActivityclass(ChooseActivity.class); //结束指定的activity
                        finish();
                    }
                }
            });
        }
    }

    // 检测网络
    private boolean checkNetwork() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }

}
