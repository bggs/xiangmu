package com.bawei.liujie.xiangmu.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.liujie.xiangmu.R;
import com.bawei.liujie.xiangmu.modle.bean.GoodsData;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 类的作用:
 * author: 刘婕
 * date:2017/7/21
 */

public class GoodsAdapter extends BaseAdapter {
    private Context context;
    private List<GoodsData.DatasBean.GoodsListBean> list;

    public GoodsAdapter(Context context, List<GoodsData.DatasBean.GoodsListBean> list) {
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.goods_listview_item, null);
            holder = new ViewHolder();
            holder.icon= (ImageView) convertView.findViewById(R.id.image);
            holder.title= (TextView) convertView.findViewById(R.id.title);
            holder.price= (TextView) convertView.findViewById(R.id.price);
            holder.count= (TextView) convertView.findViewById(R.id.count);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getGoods_image_url()).into(holder.icon);
        holder.title.setText(list.get(position).getGoods_name());
        holder.count.setText("已售:"+list.get(position).getEvaluation_count()+"件");
        holder.price.setText("§"+list.get(position).getGoods_price());
        return convertView;
    }

    class ViewHolder {
        ImageView icon;
        TextView title;
        TextView price;
        TextView count;
    }
}
