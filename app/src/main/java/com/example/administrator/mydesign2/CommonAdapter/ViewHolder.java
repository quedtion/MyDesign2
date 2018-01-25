package com.example.administrator.mydesign2.CommonAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by MobileLy on 2017/11/29.
 */

public class ViewHolder {
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;

    private ViewHolder(Context context, ViewGroup parent, int layoutId,
                       int position)
    {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        /**
         * 1. 如果root为null，attachToRoot将失去作用，设置任何值都没有意义。
         2. 如果root不为null，attachToRoot设为true，则会给加载的布局文件的指定一个父布局，即root。
         3. 如果root不为null，attachToRoot设为false，则会将布局文件最外层的所有layout属性进行设置，
         当该view被添加到父view当中时，这些layout属性会自动生效。
         4. 在不设置attachToRoot参数的情况下，如果root不为null，attachToRoot参数默认为true。
         */
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        // setTag
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    /*优化过程*/
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position)
    {
        if (convertView == null)
        {
            return new ViewHolder(context, parent, layoutId, position);
        }
        return (ViewHolder) convertView.getTag();
    }

    public View getConvertView()
    {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text)
    {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int drawableId)
    {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap drawableId)
    {
        ImageView view = getView(viewId);
        view.setImageBitmap(drawableId);
        return this;
    }


    public int getPosition()
    {
        return mPosition;
    }

    /**
     * 为按钮设置文字
     * @param viewId id
     * @param text 文字内容
     * @return
     */
    public ViewHolder setBtnText(int viewId, String text){
        Button view = getView(viewId);
        view.setText(text);
        return this;
    }

    public ViewHolder setBtnListener(final Context context, int viewId, final String text){
        Button view = getView(viewId);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, ""+text, Toast.LENGTH_SHORT).show();
            }
        });
        return this;
    }

    public ViewHolder setPicassoImg(final Context context, int viewId, String url, int drawableId){
        ImageView view = getView(viewId);
        Picasso.with(context)
                .load(url)
                .placeholder(drawableId)
                .into(view);
        return  this;
    }

}
