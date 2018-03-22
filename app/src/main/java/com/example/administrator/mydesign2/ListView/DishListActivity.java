package com.example.administrator.mydesign2.ListView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.mydesign2.Adapter.DishAdapter;
import com.example.administrator.mydesign2.Adapter.TrolleyAdapter;
import com.example.administrator.mydesign2.MyView.MyListView;
import com.example.administrator.mydesign2.R;
import com.example.administrator.mydesign2.model.Dish;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DishListActivity extends Activity {
    //购物车Adapter
    private TrolleyAdapter trolleyAdapter;
    //菜品Adapter
    private DishAdapter dishAdapter;

    private List<Dish> dishList;
    //购物车数据
    private SparseArray<Dish> selectedList;

    //订单中的菜品项，用于“去结算”按钮点击跳转后传递数据
    private List<Dish> orderList = new ArrayList<Dish>();

    private ViewGroup anim_mask_layout;//动画层

    //菜品列表
    private ListView stu_lv;
    //购物车图标
    private TextView trolley_tv;
    //购物车图标上的数字
    private TextView bv_unm;
    //显示的总价格
    private TextView tv_totle_money;
    //购物车部分View
    private View bottomSheet;

    //去结算
    private TextView trolleyPay;

    private BottomSheetLayout bottomSheetLayout;

    //购物车部分显示的总价格
    Double totleMoney = 0.00;

    private Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //主页面绑定的学生食堂布局
        setContentView(R.layout.student_canteen);

        initView();
        initData();


//        SimpleAdapter adapter = new SimpleAdapter
//                (this, getData(), R.layout.student_listview,
//                        new String[]{"ftitle", "stitle", "price", "img"},
//                        new int[]{R.id.ftitle, R.id.stitle, R.id.price, R.id.stu_img});
//        stu_lv.setAdapter(adapter);

        trolley_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet();
            }
        });
        //去结算的点击事件
        trolleyPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedList.size() > 0){
                    //若有有效数据，则触发去结算功能，跳转到订单确认页

                    //将SparseArray转换成List
                    for(int i = 0; i < selectedList.size(); i ++){
                        orderList.add(selectedList.valueAt(i));
                    }
                    Log.v("order", gson.toJson(orderList));

                    //页面跳转，并传递数据

                }
            }
        });
    }

    //初始化页面
    private void initView(){
        stu_lv = (ListView) findViewById(R.id.student_listview);
        trolley_tv = (TextView) findViewById(R.id.trolley_tv);
        bv_unm = (TextView) findViewById(R.id.bv_unm);
        tv_totle_money= (TextView) findViewById(R.id.tv_totle_money);
        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomSheetLayout);
        selectedList = new SparseArray<>();
        trolleyPay = (TextView) findViewById(R.id.trolley_pay);
    }

    private void initData(){
        dishList = new ArrayList<Dish>();
        Dish dish = new Dish();
        dish.setId(1);
        dish.setName("牛");
        dish.setNum(0);
        dish.setPrice(10.5);

        dishList.add(dish);

        dish = new Dish();
        dish.setId(2);
        dish.setName("羊");
        dish.setNum(0);
        dish.setPrice(8.5);

        dishList.add(dish);

        dishAdapter = new DishAdapter(this, dishList);
        stu_lv.setAdapter(dishAdapter);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ftitle", "牛排");
        map.put("price", "15元");
        map.put("stitle", "商家6点开始订购");
        map.put("img", R.drawable.meishi1);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("ftitle", "G2");
        map.put("price", "16元");
        map.put("stitle", "google 2");
        map.put("img", R.drawable.meishi2);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("ftitle", "G3");
        map.put("price", "32元");
        map.put("stitle", "google 3");
        map.put("img", R.drawable.meishi3);
        list.add(map);

        return list;
    }

    //根据商品id获取当前商品的采购数量
    public int getSelectedItemCountById(int id){
        Dish temp = selectedList.get(id);
        if(temp==null){
            return 0;
        }
        return temp.getNum();
    }

    public void setAnim(final View v, int[] startLocation) {
        anim_mask_layout = null;
        anim_mask_layout = createAnimLayout();
        anim_mask_layout.addView(v);//把动画小球添加到动画层
        final View view = addViewToAnimLayout(anim_mask_layout, v, startLocation);
        int[] endLocation = new int[2];// 存储动画结束位置的X、Y坐标
        trolley_tv.getLocationInWindow(endLocation);
        // 计算位移
        int endX = 0 - startLocation[0] + 40;// 动画位移的X坐标
        int endY = endLocation[1] - startLocation[1];// 动画位移的y坐标

        TranslateAnimation translateAnimationX = new TranslateAnimation(0, endX, 0, 0);
        translateAnimationX.setInterpolator(new LinearInterpolator());
        translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(true);

        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0, 0, endY);
        translateAnimationY.setInterpolator(new AccelerateInterpolator());
        translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationY.setFillAfter(true);

        AnimationSet set = new AnimationSet(false);
        set.setFillAfter(false);
        set.addAnimation(translateAnimationY);
        set.addAnimation(translateAnimationX);
        set.setDuration(800);// 动画的执行时间
        view.startAnimation(set);
        // 动画监听事件
        set.setAnimationListener(new Animation.AnimationListener() {
            // 动画的开始
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            // 动画的结束
            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.GONE);
            }
        });
    }

    /**
     * @Description: 创建动画层
     * @param
     * @return void
     * @throws
     */
    private ViewGroup createAnimLayout() {
        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE-1);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    private View addViewToAnimLayout(final ViewGroup parent, final View view,
                                     int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }

    public void handlerCarNum(int type, Dish dish, boolean refreshGoodList){
        if (type == 0) {
            Dish temp = selectedList.get(dish.getId());
            if(temp!=null){
                if(temp.getNum()<2){
                    dish.setNum(0);
                    selectedList.remove(dish.getId());

                }else{
                    int i =  dish.getNum();
                    dish.setNum(--i);
                }
            }

        } else if (type == 1) {
            Dish temp = selectedList.get(dish.getId());
            if(temp == null){
                dish.setNum(1);
                selectedList.append(dish.getId(), dish);
                Log.v("dishActivity", "已添加");
            }else{
                int i= dish.getNum();
                Log.v("dishActivity", "再次添加");
                dish.setNum(++i);
            }
        }

        update(refreshGoodList);
    }

    //刷新布局 总价、购买数量等
    private void update(boolean refreshGoodList){
        int size = selectedList.size();
        int count =0;
        for(int i=0;i<size;i++){
            Dish item = selectedList.valueAt(i);
            count += item.getNum();
            totleMoney += item.getNum()*item.getPrice();
        }
        tv_totle_money.setText("￥"+String.valueOf(totleMoney));
        totleMoney = 0.00;
        if(count<1){
            bv_unm.setVisibility(View.GONE);
        }else{
            bv_unm.setVisibility(View.VISIBLE);
        }

        bv_unm.setText(String.valueOf(count));

        if(trolleyAdapter!=null){
            trolleyAdapter.notifyDataSetChanged();
        }

        if(dishAdapter!=null){
            dishAdapter.notifyDataSetChanged();
        }

        if(bottomSheetLayout.isSheetShowing() && selectedList.size()<1){
            bottomSheetLayout.dismissSheet();
        }
    }

    //创建购物车view
    private void showBottomSheet(){
        bottomSheet = createBottomSheetView();
        if(bottomSheetLayout.isSheetShowing()){
            bottomSheetLayout.dismissSheet();
        }else {
            if(selectedList.size()!=0){
                bottomSheetLayout.showWithSheetView(bottomSheet);
            }
        }
    }

    //查看购物车布局
    private View createBottomSheetView(){
        View view = LayoutInflater.from(this).inflate(R.layout.layout_bottom_sheet,(ViewGroup) getWindow().getDecorView(),false);
        MyListView lv_product = (MyListView) view.findViewById(R.id.lv_product);
        TextView clear = (TextView) view.findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(DishListActivity.this, "click", Toast.LENGTH_SHORT).show();
                clearCart();
            }
        });
        trolleyAdapter = new TrolleyAdapter(DishListActivity.this,dishAdapter, selectedList);
        lv_product.setAdapter(trolleyAdapter);
        return view;
    }

    //清空购物车
    public void clearCart(){
        Log.v("***list***" , gson.toJson(selectedList));
        selectedList.clear();
//        list2.clear();
        Log.v("***list***" , gson.toJson(selectedList));
        if (dishList.size() > 0) {
            for (int j=0;j<dishList.size();j++){
                dishList.get(j).setNum(0);

            }
//            list2.addAll(list.get(0).getList());
//            catograyAdapter.setSelection(0);
//            //刷新不能删
//            catograyAdapter.notifyDataSetChanged();
            dishAdapter.notifyDataSetChanged();
        }
        update(true);
    }
}
