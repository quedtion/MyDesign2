package com.example.administrator.mydesign2.ListView;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.mydesign2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MuslimListview extends Activity {
    private ListView mus_lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.muslim_canteen);
        SimpleAdapter adapter=new SimpleAdapter
                (this,getData(),R.layout.muslim_listview,
                        new String[]{"ftitle","stitle","price","img"},
        new int[]{R.id.ftitle,R.id.stitle,R.id.price,R.id.mus_img});
        mus_lv=(ListView) findViewById(R.id.muslim_listview);
        mus_lv.setAdapter(adapter);

    }

    public List<? extends Map<String,?>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ftitle", "牛排");
        map.put("price","15元");
        map.put("stitle", "商家6点开始订购");
        map.put("img", R.drawable.meishi1);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("ftitle", "G2");
        map.put("price","16元");
        map.put("stitle", "google 2");
        map.put("img", R.drawable.meishi2);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("ftitle", "G3");
        map.put("price","32元");
        map.put("stitle", "google 3");
        map.put("img", R.drawable.meishi3);
        list.add(map);

        return list;
    }
}
