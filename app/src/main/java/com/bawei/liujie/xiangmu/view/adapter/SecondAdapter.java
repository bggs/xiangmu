package com.bawei.liujie.xiangmu.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bawei.liujie.xiangmu.R;
import com.bawei.liujie.xiangmu.control.activity.GoodsActivity;
import com.bawei.liujie.xiangmu.modle.bean.SecondData;
import com.bawei.liujie.xiangmu.modle.bean.ThridData;
import com.bawei.liujie.xiangmu.modle.util.HttpUtils;
import com.bawei.liujie.xiangmu.view.application.NetDataCallBack;
import com.bawei.liujie.xiangmu.view.myview.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的作用:
 * author: 刘婕
 * date:2017/7/20
 */

public class SecondAdapter extends BaseAdapter {
    private Context context;
    private List<SecondData.DatasBean.ClassListBean> data;
    private String Threadurl = "http://169.254.35.154/mobile/index.php?act=goods_class";
    private List<ThridData.DatasBean.ClassListBean> thread_list = new ArrayList<>();
    private int po;

    public SecondAdapter(Context context, List<SecondData.DatasBean.ClassListBean> data, int po) {
        this.context = context;
        this.data = data;
        this.po = po;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.second_item, null);
            holder = new ViewHolder();
            holder.textview = (TextView) convertView.findViewById(R.id.textview);
            holder.mGridView = (MyGridView) convertView.findViewById(R.id.gridView3);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textview.setText(data.get(position).getGc_name());
        holder.textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.textview.setTextColor(Color.BLUE);
                HttpUtils utils = new HttpUtils();
                utils.LoadDataFromServer(Threadurl + "&gc_id="+ po + "&gc_id="+ data.get(position).getGc_id(), new NetDataCallBack() {
                    @Override
                    public void callback(Object o) {
                        ThridData thidrData = (ThridData) o;
                        thread_list = thidrData.getDatas().getClass_list();
                        GridAdapter adapter = new GridAdapter(context, thread_list);
                        holder.mGridView.setAdapter(adapter);
                        holder.mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                Intent intent = new Intent(context, GoodsActivity.class);
                                intent.putExtra("goods_id",thread_list.get(position).getGc_id());
                                context.startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void error(String message, int code) {

                    }
                }, ThridData.class);
            }
        });



        return convertView;
    }

    class ViewHolder {
        TextView textview;
        MyGridView mGridView;
    }
}
