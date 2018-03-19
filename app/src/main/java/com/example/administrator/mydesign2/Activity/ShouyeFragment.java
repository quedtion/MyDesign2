package com.example.administrator.mydesign2.Activity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.mydesign2.AsyncTask.Differtask;
import com.example.administrator.mydesign2.AsyncTask.MessageSend;
import com.example.administrator.mydesign2.Common;
import com.example.administrator.mydesign2.CommonAdapter.CommonAdapter;
import com.example.administrator.mydesign2.CommonAdapter.ViewHolder;
import com.example.administrator.mydesign2.ListView.FlavorListview;
import com.example.administrator.mydesign2.ListView.MuslimListview;
import com.example.administrator.mydesign2.ListView.DishListActivity;
import com.example.administrator.mydesign2.ListView.TeacherListview;
import com.example.administrator.mydesign2.MyAdapter;
import com.example.administrator.mydesign2.MyPagerAdapter;
import com.example.administrator.mydesign2.R;
import com.example.administrator.mydesign2.Utils;
import com.example.administrator.mydesign2.model.Canteen;
import com.example.administrator.mydesign2.model.ResultCode;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/22.
 */

public class ShouyeFragment extends Fragment implements View.OnClickListener, MessageSend {
    private ViewPager vpager_one;
    private ArrayList<ImageView> aList;
    private MyPagerAdapter mAdapter;
    private ListView listView;
    private ImageView stu_imgSource, tea_imgSource, mus_imgSource, fla_imgSource;
    private ImageView search;
    private Gson gson = new Gson();
    private List<Canteen> canteenList = new ArrayList<>();
    private String baseUrl = Common.baseUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_shouye, container, false);
        /*ListView推荐商家*/
        listView = (ListView) view.findViewById(R.id.shouyeTuijian);
        MyAdapter myApater = new MyAdapter(getActivity());
        listView.setAdapter(myApater);
        Utils.setListViewHeightBasedOnChildren(listView);

    /*viewpager*/
        vpager_one = (ViewPager) view.findViewById(R.id.vp);
        aList = new ArrayList<>();
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(R.drawable.bear);
        /*加入首行搜索框背景图片组*/
        aList.add(imageView);
        imageView = new ImageView(getActivity());
        imageView.setImageResource(R.drawable.elephant);
        aList.add(imageView);
        imageView = new ImageView(getActivity());
        imageView.setImageResource(R.drawable.giraffe);
        aList.add(imageView);
        mAdapter = new MyPagerAdapter(aList);
        vpager_one.setAdapter(mAdapter);


        // 初始化组件
        initView(view);

        // 初始化按钮单击事件
        initClickEvent(view);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        /*网络请求*/
        doPost();

    }

    /*异步任务*/
    private void doPost() {
        String url = Common.baseUrl + "/canteen/list";
        String value = "";
        Differtask differtask = new Differtask(getActivity(), url, value, this);
        differtask.execute();

    }

    /**
     * 初始化组件 设置点击事件
     */
    @SuppressLint("WrongViewCast")

    private void initView(View view) {
        //相关食堂的点击事件
        stu_imgSource = (ImageView) view.findViewById(R.id.student_image);
        tea_imgSource = (ImageView) view.findViewById(R.id.teacher_image);
        mus_imgSource = (ImageView) view.findViewById(R.id.muslim_image);
        fla_imgSource = (ImageView) view.findViewById(R.id.flavor_image);
        search=(ImageView)view.findViewById(R.id.search_img);


    }

    /**
     * 初始化按钮单击事件
     */
    private void initClickEvent(View view) {
        stu_imgSource.setOnClickListener(this);
        tea_imgSource.setOnClickListener(this);
        mus_imgSource.setOnClickListener(this);
        fla_imgSource.setOnClickListener(this);
        search.setOnClickListener(this);

  /*实现首页推荐的跳转*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), " " + i + " " + l, Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("name", canteenList.get(i).getName());
                bundle.putString("photo", canteenList.get(i).getPhoto());
                bundle.putInt("id", canteenList.get(i).getId());
//                bundle.putString("message", message[arg2]);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(getActivity(), TextActivity.class);
                Intent shouye_intent = new Intent(getActivity(), TextActivity.class);
//                Log.i("message", message[arg2]);
                startActivity(intent);
            }
        });


    }


    //点击事件
    @Override
    public void onClick(View view) {
        // TODO Auto-generated methodstub

        switch (view.getId()) {
            case R.id.student_image:
                //点击学生食堂
                Intent stu_intent = new Intent(getActivity(), DishListActivity.class);
                startActivity(stu_intent);
                break;

            case R.id.teacher_image:
                //点击教工食堂
                Intent tea_intent = new Intent(getActivity(), TeacherListview.class);
                startActivity(tea_intent);
                break;

            case R.id.muslim_image:
                //点击清真食堂
                Intent mus_intent = new Intent(getActivity(), MuslimListview.class);
                startActivity(mus_intent);
                break;

            case R.id.flavor_image:
                //点击风味食堂
                Intent fla_intent = new Intent(getActivity(), FlavorListview.class);
                startActivity(fla_intent);
                break;

            case R.id.search_img:
                //点击风味食堂
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;

            default:
                break;

        }
    }

    /*网络数据获取*/
    @Override
    public void receivedMsg(String result) {
        try {
            ResultCode<List<Canteen>> resultCode = gson.fromJson(result,
                    new TypeToken<ResultCode<List<Canteen>>>() {
                    }.getType());
            if (resultCode.getRs() > 0) {
                canteenList = resultCode.getValue();
                listView.setAdapter(new CommonAdapter<Canteen>(getActivity(), R.layout.shoutetuijian, canteenList) {
                    @Override
                    public void convert(ViewHolder helper, Canteen canteen) {
                        helper.setText(R.id.Ftitle, canteen.getName());
                        helper.setPicassoImg(getActivity(), R.id.shouyetuijian_img1,
                                baseUrl + canteen.getPhoto(), R.drawable.adress);
                    }
                });
                Utils.setListViewHeightBasedOnChildren(listView);
            } else {
                Toast.makeText(getActivity(), resultCode.getMsg(), Toast.LENGTH_SHORT).show();
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "解析出错", Toast.LENGTH_SHORT).show();
        }

    }


}
