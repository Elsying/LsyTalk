package com.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Utils.Fragmentsendmessage;

import Activity.R;

/**
 * Created by 340144 on 2018/2/8.
 */

public class SearchResult extends Fragment {
    private String content;
    public static final String ARGUMENT = "argument";

     Fragmentsendmessage fragmentsendmessage;

    public  SearchResult(){
    }

    @Override
    public void onAttach(Context context) {
        // TODO Auto-generated method stub
        if (context instanceof Fragmentsendmessage) {
            fragmentsendmessage = (Fragmentsendmessage) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement Fragmentsendmessage");
        }
        super.onAttach(context);
    }



    public static SearchResult newInstance(String argument){
        Bundle bundle=new Bundle();
        SearchResult searchResult=new SearchResult();
        bundle.putString(ARGUMENT,argument);
        searchResult.setArguments(bundle);
        return searchResult;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fg_result,container,false);
        RelativeLayout result=view.findViewById(R.id.result);
        TextView friendname=view. findViewById(R.id.friendname);
        Bundle bundle = getArguments();
        String data = bundle.getString(ARGUMENT);
        if(data != null){
            friendname.setText(data);
        }
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentsendmessage.sendMessage();//点击传递事件到activity   必须在onAttach中定义接口
            }
        });

        return view;
    }
}
