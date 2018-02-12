package com.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import Activity.R;


/**
 * Created by 340144 on 2017/12/28.
 */

public class MyFragment extends Fragment {
    private String content;
    public static final String ARGUMENT = "argument";
    public  MyFragment(){
    }

    public static MyFragment newInstance(String argument){
        Bundle bundle=new Bundle();
        MyFragment myFragment=new MyFragment();
        bundle.putString(ARGUMENT,argument);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fg_content,container,false);
        TextView txt_content = view.findViewById(R.id.txt_content);
        Bundle bundle = getArguments();
        String data = bundle.getString(ARGUMENT);
        if(data != null){
            txt_content.setText(data);
        }

        return view;
    }
}
