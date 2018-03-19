package com.example.administrator.mydesign2.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.mydesign2.ListView.DishListActivity;
import com.example.administrator.mydesign2.R;
import com.example.administrator.mydesign2.model.Dish;

import java.util.List;

/**
 * Created by MobileLy on 2018/3/15.
 * 菜品列表Adapter
 */

public class DishAdapter extends BaseAdapter{
    //展示的数据源
    private List<Dish> dishList;

    private Context context;

    public DishAdapter(Context context, List<Dish> dishList) {
        this.dishList = dishList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dishList.size();
    }

    @Override
    public Object getItem(int position) {
        return dishList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            convertView= LayoutInflater.from(context).inflate(R.layout.dish_item,null);
            viewHolder = new ViewHolder();
            //菜品价格
            viewHolder.dish_price = (TextView) convertView.findViewById(R.id.dish_price);
            //菜品名称
            viewHolder.dish_title = (TextView) convertView.findViewById(R.id.dish_title);
            //某菜品选中的个数
            viewHolder.dish_account = (TextView) convertView.findViewById(R.id.dish_account);

            viewHolder.dish_pic = (ImageView) convertView.findViewById(R.id.dish_pic);
            viewHolder.dish_add = (ImageView) convertView.findViewById(R.id.dish_add);
            viewHolder.dish_remove = (ImageView) convertView.findViewById(R.id.dish_remove);


            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置菜品名称
        viewHolder.dish_title.setText(dishList.get(position).getName());
        //设置价格
        viewHolder.dish_price.setText(String.valueOf(dishList.get(position).getPrice()) + "元");
        //设置菜品图片


        //若存在菜品
        if(dishList.get(position) != null){

            if(dishList.get(position).getNum() < 1){
                //如果没有选中该菜品，设置已选数量和删除按钮为初始设置
                viewHolder.dish_account.setVisibility(View.INVISIBLE);
                viewHolder.dish_remove.setVisibility(View.INVISIBLE);
            }else{
                //如果选中该菜品，设置已选数量和删除按钮为可见
                viewHolder.dish_account.setVisibility(View.VISIBLE);
                viewHolder.dish_remove.setVisibility(View.VISIBLE);
                //设置已选中该菜品的数量
                viewHolder.dish_account.setText(String.valueOf(dishList.get(position).getNum()));
            }
        }else{
            //若不存在菜品
            viewHolder.dish_account.setVisibility(View.INVISIBLE);
            viewHolder.dish_remove.setVisibility(View.INVISIBLE);
        }

        viewHolder.dish_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = ((DishListActivity) context).getSelectedItemCountById(
                        dishList.get(position).getId());
                Log.i("dish_add", String.valueOf(count));
                if(count < 1){
                    viewHolder.dish_remove.setAnimation(getShowAnimation());
                    viewHolder.dish_remove.setVisibility(View.VISIBLE);
                    viewHolder.dish_account.setVisibility(View.VISIBLE);
                }

                ((DishListActivity)context).handlerCarNum(1, dishList.get(position), true);

                int loc[] = new int[2];
                viewHolder.dish_add.getLocationInWindow(loc);
                for(int i = 0; i < loc.length; i++){
                    Log.i("dish_add",String.valueOf(loc[i]));
                }
                int[] startLocation = new int[2];// 一个整型数组，用来存储按钮的在屏幕的X、Y坐标
                v.getLocationInWindow(startLocation);// 这是获取购买按钮的在屏幕的X、Y坐标（这也是动画开始的坐标）
                ImageView ball = new ImageView(context);
                ball.setImageResource(R.drawable.number);
                ((DishListActivity)context).setAnim(ball, startLocation);// 开始执行动画
            }
        });

        viewHolder.dish_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = ((DishListActivity)context).getSelectedItemCountById(dishList.get(position).getId());
                Log.i("fyg","iv_remove"+String.valueOf(count));
                if (count < 2) {
                    viewHolder.dish_remove.setAnimation(getHiddenAnimation());
                    viewHolder.dish_remove.setVisibility(View.GONE);
                    viewHolder.dish_account.setVisibility(View.GONE);
                }
                ((DishListActivity)context).handlerCarNum(0,dishList.get(position),true);
            }
        });

        return convertView;
    }

    class ViewHolder{
        TextView dish_title, dish_price, dish_account;
        ImageView dish_pic,dish_add, dish_remove;
        RelativeLayout dish_rl;
    }

    //显示减号的动画
    private Animation getShowAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,2f
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(0,1);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }
    //隐藏减号的动画
    private Animation getHiddenAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,2f
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(1,0);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }
}
