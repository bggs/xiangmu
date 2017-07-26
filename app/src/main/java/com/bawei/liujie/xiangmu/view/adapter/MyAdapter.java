package com.bawei.liujie.xiangmu.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.liujie.xiangmu.R;
import com.bawei.liujie.xiangmu.modle.bean.FirstData;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 类的作用:
 * author: 刘婕
 * date:2017/7/20
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<FirstData.DatasBean.ClassListBean> list;

    public MyAdapter(Context context, List<FirstData.DatasBean.ClassListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.listview_item,null);
            holder=new ViewHolder();
            holder.icon= (ImageView) convertView.findViewById(R.id.icon);
            holder.textview= (TextView) convertView.findViewById(R.id.textview);
            convertView.setTag(holder);
        }else{
           holder= (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getImage()).into(holder.icon);
        holder.textview.setText(list.get(position).getGc_name());
        return convertView;
    }

    class ViewHolder {
        ImageView icon;
        TextView textview;

    }
}
