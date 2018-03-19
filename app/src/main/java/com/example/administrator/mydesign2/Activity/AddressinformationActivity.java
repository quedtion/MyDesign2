package com.example.administrator.mydesign2.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.mydesign2.AsyncTask.Differtask;
import com.example.administrator.mydesign2.AsyncTask.MessageSend;
import com.example.administrator.mydesign2.Common;
import com.example.administrator.mydesign2.CommonAdapter.CommonAdapter;
import com.example.administrator.mydesign2.CommonAdapter.ViewHolder;
import com.example.administrator.mydesign2.R;
import com.example.administrator.mydesign2.Utils;
import com.example.administrator.mydesign2.model.Address;
import com.example.administrator.mydesign2.model.Recommend;
import com.example.administrator.mydesign2.model.ResultCode;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class AddressinformationActivity extends AppCompatActivity  implements MessageSend {
    private Button button;
    private EditText tv1;
    private EditText tv2;
    private EditText tv3;
    private EditText tv4;
//    private EditText tv5;
    private RadioGroup radioGroup;
    private String address_name;
    private String address_phone;
    private String address_address;
    private String address_door;
    private String address_label;

    private Gson gson = new Gson();
    private ListView listView;
    private List<Address> addressList = new ArrayList<>();
    private String baseUrl = Common.baseUrl;
    private int gender = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressinformation);
        final Button button = (Button) findViewById(R.id.certain_button);
        tv1 = (EditText) findViewById(R.id.address_name);
        tv2 = (EditText) findViewById(R.id.address_phone);
        tv3 = (EditText) findViewById(R.id.address_address);
        tv4 = (EditText) findViewById(R.id.address_door);
//        tv5 = (EditText) findViewById(R.id.address_label);
     /*性别组按钮*/
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.male:
                        gender = 1;
                        break;
                    case R.id.female:
                        gender = 0;
                        break;
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Address address = new Address();
                address_name = tv1.getText().toString();
                address_phone = tv2.getText().toString();
                address_address = tv3.getText().toString();
                address_door = tv4.getText().toString();
//                address_label=tv5.getText().toString();

                address.setName(address_name);
                address.setTel(address_phone);
                address.setAddress(address_address);
                address.setNum(address_door);
                address.setGender(gender);
                address.setLabel(address_label);
                address.setUserid(1);
                String value = gson.toJson(address);
                doAsync(value);
            }

        });

    }
    /*异步任务*/
    private void doAsync(String value){
        String url = Common.baseUrl + "/address/create";
        Log.d("value", value);
        Differtask differtask = new Differtask(getApplication(), url, value, this);
        differtask.execute();
    }
        @Override
        public void receivedMsg (String result){
            ResultCode<List<Address>> resultCode = gson.fromJson(result,
                    new TypeToken<ResultCode<List<Address>>>(){}.getType());
            try{
                if(resultCode.getRs() > 0){
                    Toast.makeText(getApplication(),"新增成功", Toast.LENGTH_SHORT).show();
                    AddressinformationActivity.this.finish();
                }else{
                    Toast.makeText(getApplication(),resultCode.getMsg(), Toast.LENGTH_SHORT).show();
                }
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                Toast.makeText(getApplication(),"解析出错", Toast.LENGTH_SHORT).show();
            }
        }

}
