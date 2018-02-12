package com.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.Adapter.MyAdapter;
import com.Bean.Addfunction;

import java.util.ArrayList;

import Activity.R;

/**
 * Created by 340144 on 2018/2/6.
 */

public class AddfriendActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private ArrayList<Addfunction> mData = null;
    private MyAdapter mAdapter = null;
    private ListView addfunction;
    private Toolbar toolbar;
    private ImageView toolbarimage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfreintdactivity);
        mContext = this;

        bindViews();
        init();
        addfunction.setAdapter(mAdapter);
        toolbar.setTitle("");//设置标题为空  因为不设置会默认label
        setSupportActionBar(toolbar);//加载toolbar
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.allsearch:
                //点击整个RelativeLayout,设置android:onClick="onClick"  Vandroid:clickable="true"
                Toast.makeText(this, "添加好友~", Toast.LENGTH_SHORT).show();
                Intent intents=new Intent(mContext,SearchActivity .class);
                startActivity(intents);
                break;
            case R.id.toolbarimage:
                finish();//后退
                break;
        }
    }

    public void init(){

        mData = new ArrayList<Addfunction>();
        mData.add(new Addfunction("1","11",R.mipmap.icon_002_cover));
        mData.add(new Addfunction("2","22",R.mipmap.icon_007_cover));
        mData.add(new Addfunction("3","33",R.mipmap.icon_010_cover));
        mData.add(new Addfunction("4","44",R.mipmap.icon_012_cover));
        mData.add(new Addfunction("5","55",R.mipmap.icon_013_cover));
        mAdapter=new MyAdapter<Addfunction>(mData,R.layout.item_list_animal){
            @Override
            public void bindView(ViewHolder holder, Addfunction obj){
                holder.setText(R.id.name,obj.getaName());
                holder.setText(R.id.says,obj.getaSpeak());
                holder.setImageResource(R.id.imgtou,obj.getaIcon());
            }
            };
    }


    public void bindViews(){
        addfunction=(ListView) findViewById(R.id.addfunction);
        toolbar=(Toolbar) findViewById(R.id.toolbaradd);
        toolbarimage=(ImageView)findViewById(R.id.toolbarimage);

        toolbarimage.setOnClickListener(this);




    }
}
