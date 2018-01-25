package com.example.administrator.mydesign2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.mydesign2.R;

/**
 * Created by Administrator on 2017/11/29.
 */
/*为ListView推荐商家自定义的Adapter*/
public class MyAdapter extends BaseAdapter {

    private Context context;
    public MyAdapter(Context context){
        this.context=context;
    }

    //用于显示菜品的图片
    int[] images = {R.drawable.meishi1, R.drawable.meishi2, R.drawable.meishi3,
            R.drawable.meishi4, R.drawable.meishi5, R.drawable.meishi6,
            R.drawable.meishi7, R.drawable.meishi8, R.drawable.meishi9};
    //用于显示菜名
    String[] name = new String[]{
            "宫保鸡丁", "糖醋排骨", "麻婆豆腐",
            "红烧鱼", "回锅肉", "辣椒炒肉",
            "醋溜土豆丝", "大蒜腊牛肉", "红烧排骨"
    };
    //用于显示价格
    String[] price = new String[]{
            "32￥", "22￥", "15￥", "94￥", "45￥", "15￥", "20￥", "45￥", "85￥"
    };

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view == null){
            //根据布局文件获取View返回值
            view =  LayoutInflater.from(context).inflate(R.layout.shoutetuijian,null);
        }
        ImageView imageview = (ImageView) view.findViewById(R.id.shouyetuijian_img1);
        TextView greens_name = (TextView) view.findViewById(R.id.Ftitle);
        TextView greens_price = (TextView) view.findViewById(R.id.Stitle);


//        imageview.setImageResource(images[position]);
        greens_name.setText(name[position]);
        greens_price.setText(price[position]);
        return view;
    }
}