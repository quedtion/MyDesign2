package com.example.administrator.mydesign2.Adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mydesign2.ListView.DishListActivity;
import com.example.administrator.mydesign2.R;
import com.example.administrator.mydesign2.Utils;
import com.example.administrator.mydesign2.model.Dish;

import java.util.List;

/**
 * Created by MobileLy on 2018/3/18.
 */

public class TrolleyAdapter extends BaseAdapter {

    private SparseArray<Dish> dataList;

    private DishListActivity dishListActivity;

    private DishAdapter dishAdapter;

    int selection = 0;

    public TrolleyAdapter(DishListActivity activity, DishAdapter dishAdapter , SparseArray<Dish> dataList) {
        this.dishListActivity = activity;
        this.dataList = dataList;
        this.dishAdapter = dishAdapter;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.valueAt(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        final ViewHolder viewholder;
        if (view == null) {
            view = LayoutInflater.from(dishListActivity).inflate(R.layout.trolley_item, null);
            viewholder = new ViewHolder();
            viewholder.tv_name = (TextView) view.findViewById(R.id.trolley_name);
            viewholder.tv_price = (TextView) view.findViewById(R.id.trolley_price);
            viewholder.iv_add= (ImageView) view.findViewById(R.id.trolley_add);
            viewholder.iv_remove= (ImageView) view.findViewById(R.id.trolley_remove);
            viewholder.tv_count= (TextView) view.findViewById(R.id.trolley_count);

            view.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) view.getTag();
        }


        Utils.filtNull(viewholder.tv_name,dataList.valueAt(position).getName());//商品名称
        Utils.filtNull(viewholder.tv_price,"￥"+dataList.valueAt(position).getPrice());//商品价格
        viewholder.tv_count.setText(String.valueOf(dataList.valueAt(position).getNum()));//商品数量

        viewholder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dishListActivity.handlerCarNum(1,dataList.valueAt(position),true);
                dishAdapter.notifyDataSetChanged();
            }
        });
        viewholder.iv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dishListActivity.handlerCarNum(0,dataList.valueAt(position),true);
                dishAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    class ViewHolder {
        TextView tv_price;
        TextView tv_name;
        ImageView iv_add,iv_remove;
        TextView tv_count;
    }
}
