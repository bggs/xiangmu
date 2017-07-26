package com.bawei.liujie.xiangmu.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bawei.liujie.xiangmu.R;
import com.bawei.liujie.xiangmu.modle.bean.ThridData;

import java.util.List;

/**
 * 类的作用:
 * author: 刘婕
 * date:2017/7/20
 */

public class GridAdapter extends BaseAdapter {
    private Context context;
    private List<ThridData.DatasBean.ClassListBean> data;

    public GridAdapter(Context context, List<ThridData.DatasBean.ClassListBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.thread_item,null);
            holder=new ViewHolder();
            holder.textview= (TextView) convertView.findViewById(R.id.textview);

            convertView.setTag(holder);

        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.textview.setText(data.get(position).getGc_name());
        return convertView;
    }

    class ViewHolder {
        TextView textview;
    }
}
