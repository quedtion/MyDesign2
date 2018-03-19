package com.example.administrator.mydesign2.Activity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.administrator.mydesign2.MeFragment;
import com.example.administrator.mydesign2.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentManager fm;
    private FragmentTransaction ft;


    /*tab栏*/
    // 定位四个Fragment
    private Fragment shouyeFragment = new ShouyeFragment();
    private Fragment tuijianFragment = new TuijianFragment();
    private Fragment dingdanFragment = new DingdanFragment();
    private Fragment meFragment = new MeFragment();

    // tab中的四个帧布局
    private FrameLayout shouyeFrameLayout, tuijianFrameLayout,
            dingdanFrameLayout, meFrameLayout;

    // tab中的四个帧布局中的四个图片组件
    private ImageView shouyeImageView, tuijianImageView,
            dingdanImageView, meImageView;

    // tab中的四个帧布局中的四个图片对应文字
    private TextView shouyeTextView, meTextView, tuijianTextView,
            dingdanTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 初始化组件
        initView();

        // 初始化按钮单击事件
        initClickEvent();

        // 初始化所有fragment
        initFragment();
        /*默认选中首页*/
        clickTab(shouyeFragment);

    }

    /**
     * 用fragment建立底部tab,初始化所有fragment
     */
    private void initFragment() {
        FragmentTransaction fragmentTransaction = getFragmentManager()
                .beginTransaction();
        if (!shouyeFragment.isAdded()) {
            fragmentTransaction.add(R.id.content, shouyeFragment);
            fragmentTransaction.hide(shouyeFragment);
        }
        if (!tuijianFragment.isAdded()) {
            fragmentTransaction.add(R.id.content, tuijianFragment);
            fragmentTransaction.hide(tuijianFragment);
        }
        if (!dingdanFragment.isAdded()) {
            fragmentTransaction.add(R.id.content, dingdanFragment);
            fragmentTransaction.hide(dingdanFragment);
        }
        if (!meFragment.isAdded()) {
            fragmentTransaction.add(R.id.content, meFragment);
            fragmentTransaction.hide(meFragment);
        }
        hideAllFragment(fragmentTransaction);
        // 默认显示第一个fragment
        fragmentTransaction.show(shouyeFragment);
        fragmentTransaction.commit();
    }

    /**
     * 隐藏所有fragment
     *
     * @param fragmentTransaction
     */
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        fragmentTransaction.hide(shouyeFragment);
        fragmentTransaction.hide(tuijianFragment);
        fragmentTransaction.hide(dingdanFragment);
        fragmentTransaction.hide(meFragment);
        //为viewpager添加页面变化的监听以及事件处理
    }

    /**
     * 初始化按钮单击事件
     */

    private void initClickEvent() {
        shouyeFrameLayout.setOnClickListener(this);
        meFrameLayout.setOnClickListener(this);
        tuijianFrameLayout.setOnClickListener(this);
        dingdanFrameLayout.setOnClickListener(this);


    }

    /**
     * 初始化组件 设置点击事件
     */
    @SuppressLint("WrongViewCast")
    private void initView() {
        shouyeFrameLayout = (FrameLayout) findViewById(R.id.shouyeLayout);
        tuijianFrameLayout = (FrameLayout) findViewById(R.id.tuijianLayout);
        dingdanFrameLayout = (FrameLayout) findViewById(R.id.dingdanLayout);
        meFrameLayout = (FrameLayout) findViewById(R.id.meLayout);

        shouyeImageView = (ImageView) findViewById(R.id.shouyeImageView);
        tuijianImageView = (ImageView) findViewById(R.id.tuijianImageView);
        dingdanImageView = (ImageView) findViewById(R.id.dingdanImageView);
        meImageView = (ImageView) findViewById(R.id.meImageView);


        shouyeTextView = (TextView) findViewById(R.id.shouyeTextView);
        tuijianTextView = (TextView) findViewById(R.id.tuijianTextView);
        dingdanTextView = (TextView) findViewById(R.id.dingdanTextView);
        meTextView = (TextView) findViewById(R.id.meTextView);

       //tuijian1_l=(LinearLayout) findViewById(R.id.tuijian1_l);
    }

    /*设置点击事件*/
    @Override
    public void onClick(View v) {
        // TODO Auto-generated methodstub
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.shouyeLayout:
                // 点击首页tab
                clickTab(shouyeFragment);


                break;
            case R.id.tuijianLayout:
                // 点击联系人tab
                clickTab(tuijianFragment);


                break;

            case R.id.dingdanLayout:
                // 点击订单tab
                clickTab(dingdanFragment);


                break;

            case R.id.meLayout:
                // 点击我tab
                clickTab(meFragment);

                break;



            default:
                break;

        }

    }

    /**
     * 点击下面的Tab按钮
     *
     * @param tabFragment
     */
    private void clickTab(Fragment tabFragment) {

        // 清除上次选中状态
        clearSeleted();

        FragmentTransaction fragmentTransaction = getFragmentManager()
                .beginTransaction();

        // 隐藏所有fragment
        hideAllFragment(fragmentTransaction);

        // 显示该Fragment
        fragmentTransaction.show(tabFragment);

        // 提交事务
        fragmentTransaction.commit();

        // 改变tab的样式,设置为选中状态
        changeTabStyle(tabFragment);

    }

    /**
     * 清除上次选中状态
     */
    private void clearSeleted() {
        if (!shouyeFragment.isHidden()) {
            shouyeImageView.setImageResource(R.drawable.shouye0);
            shouyeTextView.setTextColor(Color.parseColor("#999999"));
        }

        if (!tuijianFragment.isHidden()) {
            tuijianImageView.setImageResource(R.drawable.tuijian0);
            tuijianTextView.setTextColor(Color.parseColor("#999999"));
        }

        if (!dingdanFragment.isHidden()) {
            dingdanImageView.setImageResource(R.drawable.dingdan0);
            dingdanTextView.setTextColor(Color.parseColor("#999999"));
        }

        if (!meFragment.isHidden()) {
            meImageView.setImageResource(R.drawable.wode0);
            meTextView.setTextColor(Color.parseColor("#999999"));
        }
    }

    /**
     * 根据Fragment的状态改变样式
     */
    private void changeTabStyle(Fragment tabFragment) {
        if (tabFragment instanceof ShouyeFragment) {
            shouyeImageView.setImageResource(R.drawable.shouye1);
            shouyeTextView.setTextColor(Color.parseColor("#FF8C00"));
        }

        if (tabFragment instanceof TuijianFragment) {
            tuijianImageView.setImageResource(R.drawable.tuijian1);
            tuijianTextView.setTextColor(Color.parseColor("#FF8C00"));
        }

        if (tabFragment instanceof DingdanFragment) {
            dingdanImageView.setImageResource(R.drawable.dingdan1);
            dingdanTextView.setTextColor(Color.parseColor("#FF8C00"));
        }

        if (tabFragment instanceof MeFragment) {
            meImageView.setImageResource(R.drawable.wode1);
            meTextView.setTextColor(Color.parseColor("#FF8C00"));
        }

    }


}

