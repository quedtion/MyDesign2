package com.example.administrator.mydesign2;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/22.
 */

public class MyPagerAdapter extends PagerAdapter {
    private ArrayList<ImageView> viewLists;

    public MyPagerAdapter() {
    }

    public MyPagerAdapter(ArrayList<ImageView> viewLists) {
        super();
        this.viewLists = viewLists;
    }
    /*放入图片的数量*/
    @Override
    public int getCount() {
        return viewLists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewLists.get(position));
        return viewLists.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewLists.get(position));
    }
}

