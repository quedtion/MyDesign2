package com.example.administrator.mydesign2.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mydesign2.AsyncTask.Common;
import com.example.administrator.mydesign2.HttpUrlConnection;

/**
 * Created by Administrator on 2017/12/21.
 */

public class Differtask extends AsyncTask<Void,Integer,String>  {

    private Context context;
    private String url;
    private String postValue;
    private MessageSend messageSend;
    public Differtask(Context context, String url, String postValue, MessageSend messageSend) {
        this.context = context;
        this.url = url;
        this.postValue = postValue;
        this.messageSend = messageSend;
    }
    /**
     * 运行在UI线程中，在调用doInBackground()之前执行
     * 该方法运行在UI线程当中,并且运行在UI线程当中 可以对UI空间进行设置
     */
    @Override
    protected void onPreExecute() {
        Log.d("asyunc","start");
        Toast.makeText(context,"开始执行", Toast.LENGTH_SHORT).show();
    }
    /**
     * 后台运行的方法，可以运行非UI线程，可以执行耗时的方法
     * 这里的Void参数对应AsyncTask中的第一个参数
     * 这里的String返回值对应AsyncTask的第三个参数
     * 该方法并不运行在UI线程当中，主要用于异步操作，所有在该方法中不能对UI当中的空间进行设置和修改
     * 但是可以调用publishProgress方法触发onProgressUpdate对UI进行操作
     */

    @Override
    protected String doInBackground(Void... voids) {
        int i = 0;
//        while (i < 10) {
//            i++;
//            //publishProgress 更新进度，给onProgressUpdate()传递进度参数
//            publishProgress(i);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//            }
//        }
        String result = //Common.postGetJson(url, postValue);
                HttpUrlConnection.httpUrlConnPost(url,postValue);
        //第三个参数为String 所以此处return一个String类型的数据
        return result;
    }
        /**
         * 这里的String参数对应AsyncTask中的第三个参数（也就是接收doInBackground的返回值）
         * 运行在ui线程中，在doInBackground()执行完毕后执行,传入的参数为doInBackground()返回的结果
         */
        @Override
        protected void onPostExecute(String i){
            Toast.makeText(context, i, Toast.LENGTH_SHORT).show();
            messageSend.receivedMsg(i);
        }

        /**
         * 在publishProgress()被调用以后执行，publishProgress()用于更新进度
         * 这里的Intege参数对应AsyncTask中的第二个参数
         * 在doInBackground方法当中，，每次调用publishProgress方法都会触发onProgressUpdate执行
         * onProgressUpdate是在UI线程中执行，所有可以对UI空间进行操作
         */
        @Override
        protected void onProgressUpdate(Integer...values)

        {
            //第二个参数为Int
        }

}
