package com.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
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
import com.Utils.LoadingDialog;
import com.Utils.SharedHelper;

import Activity.R;


/**
 * Created by 340144 on 2018/1/9.
 * 登录验证
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Context mContext;
    private RelativeLayout rl_user;
    private Button mLogin;
    private Button mRegister;
    private EditText account;
    private EditText password;
    private ImageView back;
    private LoadingDialog ld;





    //返回的结果
    private String result;

    private Handler handler = new Handler();

    public  String url = "http://172.16.0.1:8080/hello/LoginServlet";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        bind();
        mContext=this;
        init();
    }

    private void bind(){
        rl_user=(RelativeLayout) findViewById(R.id.rl_user);
        mLogin=(Button)findViewById(R.id.login);
        mRegister=(Button)findViewById(R.id.register);
        account=(EditText) findViewById(R.id.account);
        password=(EditText)findViewById(R.id.password);
        back=(ImageView)findViewById(R.id.imageView5);

        //final String username=account.getText().toString();
       // final String passw=password.getText().toString();
        back.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);

    }

    private void init(){
        Animation anim= AnimationUtils.loadAnimation(mContext, R.anim.login_anim);
        anim.setFillAfter(true);
        rl_user.startAnimation(anim);  //设置动画
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.imageView5:  //后退键
                LoginActivity.this.finish();
               break;
            case R.id.register:    //新用户
                Intent intents=new Intent(mContext, RegisterActivity.class);
                startActivity(intents);
                break;
            case R.id.login:      //点击登录
                if (!checkNetwork()) {
                     Toast.makeText(LoginActivity.this, "网络未连接", Toast.LENGTH_SHORT);
                    break;
                }
                if(TextUtils.isEmpty(account.getText().toString().trim()))   //判断为空和去空格
                {
                    Toast.makeText(LoginActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
                    break;
                }
                if(TextUtils.isEmpty(password.getText()))
                {
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    break;
                }
                ld = new LoadingDialog(this).setMessage("正在登陆");
                ld.show();

                new Thread(new MyThread()).start();   //验证
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
                    Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
                    Log.v("verbose", "1");
                    ld.dismiss();
                    if(result.equals("登陆成功！")){
                        SharedHelper.save(getApplicationContext(),"username",account.getText().toString());//账号保存
                        SharedHelper.save(getApplicationContext(),"password",password.getText().toString());//密码保存
                        ld.dismiss();

                        Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(mContext, MainActivity.class);
                        startActivity(intent);
                        ActivityManagerUtils.getInstance().finishActivityclass(ChooseActivity.class); //结束指定的activity
                        finish();
                    }
                }
            });
        }
    }

    /**
     * 检测网络
     */
    private boolean checkNetwork() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }




}
