package com.example.administrator.mydesign2.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.administrator.mydesign2.AsyncTask.Differtask;
import com.example.administrator.mydesign2.AsyncTask.MessageSend;
import com.example.administrator.mydesign2.Common;
import com.example.administrator.mydesign2.CommonAdapter.CommonAdapter;
import com.example.administrator.mydesign2.CommonAdapter.ViewHolder;
import com.example.administrator.mydesign2.MyAdapter;
import com.example.administrator.mydesign2.R;
import com.example.administrator.mydesign2.Utils;
import com.example.administrator.mydesign2.model.Address;
import com.example.administrator.mydesign2.model.Recommend;
import com.example.administrator.mydesign2.model.ResultCode;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressActivity extends AppCompatActivity implements MessageSend {
    private Gson gson = new Gson();
    private ListView listView;
    private String baseUrl = Common.baseUrl;
    private LinearLayout linearLayout;
    private String add_address;
    private List<Address> addressList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        linearLayout = (LinearLayout) findViewById(R.id.add_address);

        /*点击新增地址消息 跳转到填写地址信息页面*/
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddressActivity.this, AddressinformationActivity.class);
                startActivity(intent);
            }
        });

        /*地址信息栏*/
        SimpleAdapter simpleAdapter=new SimpleAdapter
                (this, getData(),R.layout.addressing_listview,
                        new String[]{"lable","adress","name","gender","phone","img"},
                        new int[]{R.id.title,R.id.adress,R.id.name, R.id.gender,R.id.phone,R.drawable.creat});
        listView=(ListView)findViewById(R.id.addressing) ;
        listView.setAdapter(simpleAdapter);


    }
    List<? extends Map<String, ?>> getData(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("lable","标签");
        map.put("adress","地址");
        map.put("name","姓名");
        map.put("gender","性别");
        map.put("phone","1356489562");
       // map.put("img",R.drawable.creat);
        list.add(map);
        return list;
    }

    @Override
    protected void onResume() {

        super.onResume();
        /*网络请求*/
        doPost();
    }

    private void doPost() {
        String url = baseUrl + "/address/list";
        Address address = new Address();
        address.setUserid(1);
        String value = gson.toJson(address);
        Differtask differtask = new Differtask(getApplication(), url, value, this);
        differtask.execute();

    }


/*取数据*/
    @Override
    public void receivedMsg(String result) {
        try {
            ResultCode<List<Address>> resultCode = gson.fromJson(result,
                    new TypeToken<ResultCode<List<Address>>>() {}.getType());

                if (resultCode.getRs() > 0) {
                    addressList = resultCode.getValue();
                    listView.setAdapter(new CommonAdapter<Address>
                            (getApplication(),R.layout.addressing_listview,addressList) {

                        @Override
                        public void convert(ViewHolder helper, Address item) {
                            helper.setText(R.id.lable,item.getLabel());
                            helper.setText(R.id.adress,item.getAddress());
                            helper.setText(R.id.name,item.getName());
                            int gender = item.getGender();
                            String sex = "";
                            if(gender == 0){
                                sex = "女";
                            }else{
                                sex = "男";
                            }
                            helper.setText(R.id.gender,sex);
                            helper.setText(R.id.phone,item.getTel());

                        }
                    });
//
                } else {
                    Toast.makeText(getApplication(), resultCode.getMsg(), Toast.LENGTH_SHORT).show();
                }


            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                Toast.makeText(getApplication(), "解析出错", Toast.LENGTH_SHORT).show();
            }
        }


}
