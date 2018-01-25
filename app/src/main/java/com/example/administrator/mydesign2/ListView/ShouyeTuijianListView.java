package com.example.administrator.mydesign2.ListView;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.administrator.mydesign2.Activity.TextActivity;
import com.example.administrator.mydesign2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/11/29.
 */

public class ShouyeTuijianListView extends ListActivity {
    private ListView Lv = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SimpleAdapter adapter = new SimpleAdapter(this,getData(), R.layout.shoutetuijian,
                new String[]{"Ftitle","Stitle","img"},
                new int[]{R.id.Ftitle,R.id.Stitle,R.id.shouyetuijian_img1});
        setListAdapter(adapter);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Ftitle", "牛排");
        map.put("Stitle", "商家6点开始订购");
        map.put("img", R.drawable.meishi1);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("Ftitle", "G2");
        map.put("Stitle", "google 2");
        map.put("img", R.drawable.meishi2);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("Ftitle", "G3");
        map.put("Stitle", "google 3");
        map.put("img", R.drawable.meishi3);
        list.add(map);

        return list;

    }
    }


