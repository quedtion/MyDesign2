package com.example.administrator.mydesign2.Activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.mydesign2.R;

/**
 * Created by Administrator on 2017/11/22.
 */

public class MeFragment extends Fragment {
    private LinearLayout adress;
    private LinearLayout collect;
    private LinearLayout inform;
    private LinearLayout usual;
    private LinearLayout exit;
    private LinearLayout bussness;
    public MeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.activity_me, container, false);

//实现跳转
        //与 id进行关联

        //给tn1绑定事件点击
        // 初始化组件
        initView(view);

        // 初始化按钮单击事件
        initClickEvent(view);
        return view;


    }

    private void initView(View view){
        //相关我的点击事件
        adress=(LinearLayout) view.findViewById(R.id.adress);
        collect=(LinearLayout) view.findViewById(R.id.collect);
        inform=(LinearLayout) view.findViewById(R.id.inform);
        usual=(LinearLayout) view.findViewById(R.id.usual);
        exit=(LinearLayout) view.findViewById(R.id.exit);
        bussness=(LinearLayout) view.findViewById(R.id.bussness);

    }
    /**
     * 初始化按钮单击事件
     */


    private void initClickEvent(View view){
        bussness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),BussnessActivity.class);
                //启动
                startActivity(intent);
            }
        }) ;

        adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AddressActivity.class);
                //启动
                startActivity(intent);
            }
        });

    }


}

