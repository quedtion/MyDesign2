package com.example.administrator.mydesign2.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mydesign2.AsyncTask.Differtask;
import com.example.administrator.mydesign2.AsyncTask.MessageSend;
import com.example.administrator.mydesign2.Common;
import com.example.administrator.mydesign2.CommonAdapter.CommonAdapter;
import com.example.administrator.mydesign2.CommonAdapter.ViewHolder;
import com.example.administrator.mydesign2.R;
import com.example.administrator.mydesign2.Utils;
import com.example.administrator.mydesign2.model.Dish;
import com.example.administrator.mydesign2.model.ResultCode;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextActivity extends AppCompatActivity implements MessageSend{
    private ListView shop_lv;
    private Gson gson = new Gson();
    private List<Dish> dishList = new ArrayList<>();
    private int canteenId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        Bundle bundle = getIntent().getExtras();

        SimpleAdapter adapter = new SimpleAdapter
                (this, getData(), R.layout.text_item,
                        new String[]{"ftitle", "price", "img"},
                        new int[]{R.id.ftitle, R.id.price, R.id.leng1_img});
        shop_lv = (ListView) findViewById(R.id.shop_listview);
        shop_lv.setAdapter(adapter);
      /*网络解析*/
        canteenId = bundle.getInt("id");
        String photo = bundle.getString("photo");
        String name = bundle.getString("name");
//        ImageView Iv=(ImageView) findViewById(R.id.canting);
//        Iv.setImageResource(id);
        TextView tv = (TextView) findViewById(R.id.canteen_name);
        tv.setText(name);
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
        /*网络请求*/
        doPost();
    }

    private void doPost(){
        String url = Common.baseUrl + "/dish/list";
        Dish dish = new Dish();
        dish.setCanteenid(canteenId);
        String value = gson.toJson(dish);
//        Log.d("value", value);
        Differtask differtask = new Differtask(this,url, value, this);
        differtask.execute();

    }



    public List<? extends Map<String, ?>> getData() {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ftitle", "金桔柠檬");
        map.put("price", "15元");
        map.put("img", R.drawable.leng1);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("ftitle", "青柠百香果");
        map.put("price", "16元");
        map.put("img", R.drawable.leng2);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("ftitle", "蓝色星辰");
        map.put("price", "15元");
        map.put("img", R.drawable.leng3);
        list.add(map);

        return list;


    }


    public void receivedMsg(String result) {
        Log.d("result", result);
        try {
            ResultCode<List<Dish>> resultCode = gson.fromJson(result,
                    new TypeToken<ResultCode<List<Dish>>>(){}.getType());
            if(resultCode.getRs() > 0){
                dishList = resultCode.getValue();
                shop_lv.setAdapter(new CommonAdapter<Dish>(this,R.layout.text_item, dishList){
                    @Override
                    public void convert(ViewHolder helper, Dish dish) {
                        helper.setText(R.id.ftitle, dish.getName());
                        //   helper. setImageResource(R.id.shouyetuijian_img1, dish.getName());

                    }
                });
                Utils.setListViewHeightBasedOnChildren(shop_lv);
            }else{
                Toast.makeText(this,resultCode.getMsg(), Toast.LENGTH_SHORT).show();
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            Log.d("error", "出错");
            Toast.makeText(new TextActivity(),"解析出错", Toast.LENGTH_SHORT).show();
        }

    }


}
