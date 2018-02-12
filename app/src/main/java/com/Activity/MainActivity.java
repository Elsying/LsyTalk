package com.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.Fragment.MyFragment;
import com.Utils.CircleImageView;
import com.Utils.SharedHelper;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import Activity.R;

import static Activity.R.id.textView2;
import static Activity.R.id.txt_chat;
import static Activity.R.id.txt_contract;
import static Activity.R.id.txt_me;
import static Activity.R.mipmap.icon_settings;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private TextView topbar;
    private TextView chat;
    private TextView contract;
    private TextView diacover;
    private TextView me;
    private ImageView imageView2;
    private FrameLayout content;
    private CircleImageView circleImageView;
    private MyFragment fg1,fg2,fg3,fg4;
    private FragmentManager fManeger;
    private Toolbar toolbar;

    private ResideMenu resideMenu;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemProfile;
    private ResideMenuItem itemCalendar;
    private ResideMenuItem itemSettings;

    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fManeger=getFragmentManager();
        context = this;

        bindViews();
        toolbar.setTitle("");//设置标题为空  因为不设置会默认label
        setSupportActionBar(toolbar);//加载toolbar
        setRsMenu();


        //Drawable drawable = ContextCompat.getDrawable(getBaseContext(),R.drawable.tab_menu_bettersss);
       // drawable.setBounds(0,0,1,1);

        chat.performClick();//模拟第一次点击事件
    }


    private void bindViews(){
       // topbar=(TextView)findViewById(R.id.txt_topbar);
        chat=(TextView)findViewById(txt_chat);
        contract=(TextView)findViewById(txt_contract);
        diacover=(TextView)findViewById(R.id.txt_diacover);
        me=(TextView)findViewById(txt_me);
        content=(FrameLayout)findViewById(R.id.ly_content);

        circleImageView=(CircleImageView)findViewById(R.id.circleImageView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        imageView2 = (ImageView) findViewById(R.id.imageVie2);


        chat.setOnClickListener(this);
        contract.setOnClickListener(this);
        diacover.setOnClickListener(this);
        me.setOnClickListener(this);
        imageView2.setOnClickListener(this);

    }

    //左悬浮框设置
    private void setRsMenu(){
        resideMenu = new ResideMenu(this);
        resideMenu.setUse3D(true);
        //设置背景图片
        resideMenu.setBackground(R.mipmap.menu_background);
        //绑定当前Activity
        resideMenu.attachToActivity(this);
        //设置监听
        resideMenu.setMenuListener(menuListener);
        //设置内容缩放比例（0.1~1f）
        resideMenu.setScaleValue(0.6f);
        //创建子菜单
        itemHome     = new ResideMenuItem(this, R.mipmap.icon_home,     "Home");
        itemProfile  = new ResideMenuItem(this, R.mipmap.icon_profile,  "Profile");
        itemCalendar = new ResideMenuItem(this, R.mipmap.icon_calendar, "Calendar");
        itemSettings = new ResideMenuItem(this, icon_settings, "注销");
        //设置点击事件
        itemHome.setOnClickListener(this);
        itemProfile.setOnClickListener(this);
        itemCalendar.setOnClickListener(this);
        itemSettings.setOnClickListener(this);
        //将刚创建的子菜单添加到侧换菜单中
        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCalendar, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_LEFT);

        //设置打开悬浮框按钮事件
        findViewById(R.id.circleImageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });

    }





    //重写dispatchTouchEvent，实现滑动打开悬浮框
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }


//打开menu和关闭menu
    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            //Toast.makeText(context, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            //Toast.makeText(context, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    //重置文本状态 不被选中
    private void setSelected(){
        chat.setSelected(false);
        contract.setSelected(false);
        diacover.setSelected(false);
        me.setSelected(false);
    }

    //隐藏Fragment
    private void hiddAllFragment(FragmentTransaction fTransaction){
        if (fg1!=null)fTransaction.hide(fg1);
        if(fg2 != null)fTransaction.hide(fg2);
        if(fg3 != null)fTransaction.hide(fg3);
        if(fg4 != null)fTransaction.hide(fg4);
    }




    @Override
    public void onClick(View view) {
        FragmentTransaction fTransaction = fManeger.beginTransaction();
        //hiddAllFragment(fTransaction);


        //左悬浮窗按钮点击事件
        if (view==itemHome){
            resideMenu.closeMenu();
        }else if (view == itemSettings){
            //退出登录 弹窗判断是否注销
            alert = null;
            builder = new AlertDialog.Builder(context);
            alert=builder.setTitle("退出登录").setMessage("你确定？")
                    .setNegativeButton("确认退出", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SharedHelper.clear(getApplicationContext());
                            Intent intents=new Intent(context, LoginActivity.class);
                            startActivity(intents);
                            finish();  //注销
                        }
                    })
                    .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //Toast.makeText(context, "你点击了取消按钮~", Toast.LENGTH_SHORT).show();
                        }
                    }).create();
            alert.show();
        }

        //加载Fragment事件
        switch (view.getId()){
            case R.id.txt_chat:
                setSelected();
                chat.setSelected(true);
                if(fg1==null){
                    fg1= MyFragment.newInstance("第一个fragment");
                    fTransaction.add(R.id.ly_content,fg1);
                }
                else {
                    fTransaction.show(fg1);
                }
                break;
            case R.id.txt_contract:
                setSelected();
                contract.setSelected(true);
                if(fg2==null){
                    fg2= MyFragment.newInstance("第二个fragment");
                    fTransaction.add(R.id.ly_content,fg2);

                }
                else{
                    fTransaction.show(fg2);
                }
                break;
            case R.id.txt_diacover:
                setSelected();
                diacover.setSelected(true);
                if(fg3==null){
                    fg3= MyFragment.newInstance("第三个fragment");
                    fTransaction.add(R.id.ly_content,fg3);

                }
                else{
                    fTransaction.show(fg3);
                }
                break;
            case txt_me:
                setSelected();
                me.setSelected(true);
                if(fg4==null){
                    fg4= MyFragment.newInstance("第四个fragment");
                    fTransaction.add(R.id.ly_content,fg4);
                }
                else{
                    fTransaction.show(fg4);
                }
                break;

            case R.id.imageVie2:
                initPopWindow(view);
                break;


        }
        fTransaction.commit();
    }


    /**
     *
     * 顶部悬浮窗
     */
    private void initPopWindow(View v){
        View contentView  = LayoutInflater.from(context).inflate(
                R.layout.popuwindow_activity, null,false);

        TextView addfriend=(TextView)contentView.findViewById(textView2);




        final PopupWindow popWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //popWindow.setAnimationStyle(R.anim.anim_pop);  //设置加载动画

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效

        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAsDropDown(v, 100, 20);


        //设置popupWindow里的按钮的事件


        addfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "添加好友~", Toast.LENGTH_SHORT).show();
                Intent intents=new Intent(context, AddfriendActivity.class);
                startActivity(intents);
                popWindow.dismiss();

            }
        });
    }








/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setIconsVisible(menu, true);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void setIconsVisible(Menu menu, boolean flag) {
        //判断menu是否为空
        if (menu != null) {
            try {
                //如果不为空,就反射拿到menu的setOptionalIconsVisible方法
                Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                //暴力访问该方法
                method.setAccessible(true);
                //调用该方法显示icon
                method.invoke(menu, flag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    */


}




