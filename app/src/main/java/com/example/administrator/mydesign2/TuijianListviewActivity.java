package com.example.administrator.mydesign2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TuijianListviewActivity extends AppCompatActivity {
private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuijian);
        SimpleAdapter adapter = new SimpleAdapter
                (this,getData(),R.layout.student_listview,
                        new String[]{"title","img1","img2","img3"},
                        new int[]{R.id.tuijian_title,R.id.tuijian1,R.id.tuijian2,R.id.tuijian3});
        listView=(ListView) listView.findViewById(R.id.tuijian_listview);
        listView.setAdapter(adapter);

    }

    public List<? extends Map<String,?>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "文章标题");
        map.put("img1", R.drawable.tuiian1);
        map.put("img2", R.drawable.tuijian2);
        map.put("img3", R.drawable.tuijian3);
        list.add(map);
        return list;
    }
}
