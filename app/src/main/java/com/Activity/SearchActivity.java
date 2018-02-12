package com.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.Fragment.SearchResult;
import com.Utils.Fragmentsendmessage;

import Activity.R;

/**
 * Created by 340144 on 2018/2/7.
 */

public class SearchActivity extends AppCompatActivity implements View.OnClickListener,Fragmentsendmessage {

    private Context mContext;
    private SearchView searchView;
    private Toolbar toolbar;
    private ImageView back;
    private FrameLayout searchresult;
    private FragmentManager fManeger;
    private SearchResult f1,f2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchactivity);
        mContext = this;
        fManeger=getFragmentManager();
        bindViews();
        initsearch();

        toolbar.setTitle("");//设置标题为空  因为不设置会默认label
        setSupportActionBar(toolbar);//加载toolbar

        //关闭需要点击开启搜索
        searchView.setIconifiedByDefault(false);


        //设置文本监听器

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                FragmentTransaction fTransaction = fManeger.beginTransaction();
                if (!TextUtils.isEmpty(newText)) {
                    //if(f1==null){
                        f1= SearchResult.newInstance(newText);
                        fTransaction.replace (R.id.searchresult,f1);
                   // }
//                    else {
//                        fTransaction.remove(f1);
//                    }
                }
                else {
                    if(f1!=null) {
                        fTransaction.hide(f1);
                    }
                }
                fTransaction.commit();
                return false;
            }
        });
    }

    public void bindViews(){
        searchView=(SearchView) findViewById(R.id.searchfriend);
        toolbar=(Toolbar) findViewById(R.id.toolbarsearch);
        back=(ImageView)findViewById(R.id.toolbarsearchimage);
        searchresult=(FrameLayout) findViewById(R.id.searchresult);


        back.setOnClickListener(this);
    }

    public void initsearch(){
        //设置searchview样式
        int id=searchView.getContext().getResources().getIdentifier("android:id/search_src_text",null,null);
        TextView textView = (TextView) searchView.findViewById(id);
        //设置字体大小为14sp
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);//14sp
        //设置字体颜色
        textView.setTextColor(ContextCompat.getColor(this,R.color.bgwhite));
        //设置提示字体颜色
        textView.setHintTextColor(ContextCompat.getColor(this,R.color.searchtext));
    }


    public void onClick(View view){
        switch (view.getId()){
            case R.id.toolbarsearchimage:
                this.finish();
                break;
        }

    }

    @Override
    public void sendMessage() {
        //回调接口 接手fragment传来的消息
        Toast.makeText(this,"开始搜索",Toast.LENGTH_LONG).show();
    }
}
