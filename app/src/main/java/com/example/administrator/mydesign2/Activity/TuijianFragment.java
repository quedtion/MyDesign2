package com.example.administrator.mydesign2.Activity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.mydesign2.AsyncTask.Differtask;
import com.example.administrator.mydesign2.AsyncTask.MessageSend;
import com.example.administrator.mydesign2.Common;
import com.example.administrator.mydesign2.CommonAdapter.CommonAdapter;
import com.example.administrator.mydesign2.CommonAdapter.ViewHolder;
import com.example.administrator.mydesign2.MyAdapter;
import com.example.administrator.mydesign2.R;
import com.example.administrator.mydesign2.Utils;
import com.example.administrator.mydesign2.model.Canteen;
import com.example.administrator.mydesign2.model.Recommend;
import com.example.administrator.mydesign2.model.ResultCode;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.mydesign2.Common.baseUrl;

/**
 * Created by Administrator on 2017/11/22.
 */

public  class TuijianFragment extends Fragment implements MessageSend {
    private Gson gson = new Gson();
    private ListView listView;
    private List<Recommend> recommendList = new ArrayList<>();
    private String baseUrl = Common .baseUrl;
    public TuijianFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.activity_tuijian, container, false);
        listView=(ListView)view.findViewById(R.id.tuijian_listview);
        MyAdapter myApater = new MyAdapter(getActivity());
        listView.setAdapter(myApater);
        Utils.setListViewHeightBasedOnChildren(listView);
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        /*网络请求*/
        doPost();

    }
    /*异步任务*/
    private void doPost(){
        String url = baseUrl + "/recommend/list";
        String value = "";
        Differtask differtask = new Differtask(getActivity(),url, value, this);
        differtask.execute();

    }
    @Override
    public void receivedMsg(String result) {
        try {

            ResultCode<List<Recommend>> resultCode = gson.fromJson(result,
                    new TypeToken<ResultCode<List<Recommend>>>(){}.getType());
            if(resultCode.getRs() > 0){
                recommendList = resultCode.getValue();
                listView.setAdapter(new CommonAdapter<Recommend>(getActivity(),R.layout.tuijian_listview, recommendList){
                    @Override
                    public void convert(ViewHolder helper, Recommend item) {
                        helper.setText(R.id.tuijian_title,item.getTitle());
                        helper.setPicassoImg(getActivity(),R.id.tuijian1,
                                baseUrl+item.getPhoto1(),R.drawable.tuiian1);
                        helper.setPicassoImg(getActivity(),R.id.tuijian2,
                                baseUrl+item.getPhoto2(),R.drawable.tuijian2);
                        helper.setPicassoImg(getActivity(),R.id.tuijian3,
                                baseUrl+item.getPhoto3(),R.drawable.tuijian3);
                    }
                });
                Utils.setListViewHeightBasedOnChildren(listView);
            }else{
                Toast.makeText(getActivity(),resultCode.getMsg(), Toast.LENGTH_SHORT).show();
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(),"解析出错", Toast.LENGTH_SHORT).show();
        }


    }
}
