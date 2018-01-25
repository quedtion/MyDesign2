package com.example.administrator.mydesign2.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.administrator.mydesign2.MyAdapter;
import com.example.administrator.mydesign2.R;

public class SearchActivity extends AppCompatActivity {
    private SearchView sv;
    private ListView lv;
   // private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        MyAdapter myApater = new MyAdapter(this);
       // listView=(ListView)findViewById(R.id.lv) ;
       // listView.setAdapter(myApater);
         /*设置搜索框*/
        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(myApater);
        //设置ListView启动·过滤
        lv.setTextFilterEnabled(true);
        sv = (SearchView) findViewById(R.id.sv);
        //设置该SearchView是否自动缩小图标
        sv.setIconifiedByDefault(false);
        //设置该SearchView显示搜索按钮
        sv.setSubmitButtonEnabled(true);
        //设置该SearchView内默认显示的提示文本
        sv.setQueryHint("查找");
        //为该SearchView组件设置时间监听器
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            //用户输入字符时激发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                //如果newText不是长度为0的字符串
                if (TextUtils.isEmpty(newText)) {
                    //清除ListView的过滤
                    lv.clearTextFilter();
                } else {
                    //使用用户输入的内容对ListView的列表项进行过滤
                    lv.setFilterText(newText);
                }
                return false;
            }

            //单击搜索按钮时激发方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                //实际应用中应该在该方法内执行实际查询
                //此处仅使用Toast显示用户输入的查询内容
                Toast.makeText(getApplicationContext(), "您的选择是："
                        + query, Toast.LENGTH_SHORT).show();

                return false;
            }


        });




    }

}





